<?php
    session_start();
    $correo = $_SESSION['correo'];

    $host = "localhost"; // Nombre del servidor de la base de datos (puede ser "localhost" en la mayoría de los casos)
    $user = "root"; // Usuario de la base de datos
    $password = ""; // Contraseña del usuario
    $database = "mysql"; // Nombre de la base de datos

    // Establecer la conexión con la base de datos
    $conexion = mysqli_connect($host, $user, $password, $database);

    // Verificar la conexión
    if (!$conexion) {
        die("Error de conexión: " . mysqli_connect_error());
    }

    // Verificar si se ha enviado el formulario
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // Obtener los valores del formulario
        $titulo = $_POST['titulotarea'];
        $descripcion = $_POST['descripcion'];
        $recursosextra = isset($_FILES['recursosextra']) && $_FILES['recursosextra']['error'] === UPLOAD_ERR_OK ? $_FILES['recursosextra']['name'] : '';
        $datetime = $_POST['datetime'];
        $time = $_POST['time'];
        $curso = $_POST['curso'];

        // Generar el UUID
        $uuid = uniqid();

        // Mover el archivo cargado a una ubicación deseada (opcional)
        if (!empty($recursosextra)) {
            $rutaDestino = "C:\\xampp\\htdocs\\proyectoo\\recursosextratarea\\" . $recursosextra;
            move_uploaded_file($_FILES['recursosextra']['tmp_name'], $rutaDestino);
        }

        // Obtener la clave del maestro
        $sqlMaestro = "SELECT ClaseMaestro FROM maestros WHERE CorreoElectronico = '$correo'";
        $resultadoMaestro = mysqli_query($conexion, $sqlMaestro);

        if ($resultadoMaestro) {
            $filaMaestro = mysqli_fetch_assoc($resultadoMaestro);
            $claveMaestro = $filaMaestro['ClaseMaestro'];
        } else {
            echo "Error en la consulta para obtener la clave del maestro: " . mysqli_error($conexion);
            exit();
        }

        // Obtener la clave del curso
        $sqlClaveCurso = "SELECT ClaveCurso FROM curso WHERE ClaveMaestro = '$claveMaestro' AND NombreCurso = '$curso'";
        $resultadoClaveCurso = mysqli_query($conexion, $sqlClaveCurso);

        if ($resultadoClaveCurso) {
            $filaClaveCurso = mysqli_fetch_assoc($resultadoClaveCurso);
            $claveCurso = $filaClaveCurso['ClaveCurso'];
        } else {
            echo "Error en la consulta para obtener la clave del curso: " . mysqli_error($conexion);
            exit();
        }

        // Insertar los datos en la tabla tarea
        $sql = "INSERT INTO tarea (ClaveTarea, TituloTarea, Descripcion, Fecha, HoraEntrega, ClaveCurso) VALUES ('$uuid', '$titulo', '$descripcion', '$datetime', '$time', '$claveCurso')";
        if (mysqli_query($conexion, $sql)) {
            echo "Tarea asignada correctamente.";
        } else {
            echo "Error al asignar la tarea: " . mysqli_error($conexion);
        }

        // Insertar el UUID y la ruta del archivo en otra tabla
        if (!empty($recursosextra)) {
            $sql2 = "INSERT INTO tarearecurso (ClaveTarea, Recursos) VALUES ('$uuid', '$rutaDestino')";
            if (mysqli_query($conexion, $sql2)) {
                echo "Datos insertados correctamente en otra tabla.";
            } else {
                echo "Error al insertar los datos en otra tabla: " . mysqli_error($conexion);
            }
        }
    }

    // Obtener los cursos del maestro
    $sqlCursos = "SELECT ClaveCurso, NombreCurso FROM cursos WHERE ClaveMaestro = (SELECT ClaveMaestro FROM maestros WHERE CorreoElectronico = '$correo')";
    $resultadoCursos = mysqli_query($conexion, $sqlCursos);

    // Cerrar la conexión
    mysqli_close($conexion);
?>