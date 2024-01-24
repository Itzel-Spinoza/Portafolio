<?php


    session_start();

    // Verificar si el maestro ha iniciado sesión y tiene el correo almacenado en la variable de sesión
    if (!isset($_SESSION['correo'])) {
        echo "No se ha iniciado sesión como maestro.";
        exit();
    }

    $correo = $_SESSION['correo'];

    // Datos de conexión a la base de datos
    $host = "localhost";
    $user = "usuario";
    $pass = "contraseña";
    $db = "mysql";

    // Establecer conexión con la base de datos
    $conexion = mysqli_connect($host, $user, $pass, $db);
    if (!$conexion) {
        die("Error de conexión: " . mysqli_connect_error());
    }

    // Consulta para obtener los datos de los cursos
    $sql = "SELECT Nombre, Carrera, Descripcion FROM curso";
    $resultado = mysqli_query($conexion, $sql);

    // Verificar si se encontraron resultados
    if (mysqli_num_rows($resultado) > 0) {
        // Iterar sobre los resultados y generar el HTML del card para cada curso
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $tituloTarea = $_POST["titulotarea"];
            $descripcion = $_POST["descripcion"];
            $fechaHora = $_POST["datetime"];

            // Insertar nueva tarea en la base de datos
            $sql = "INSERT INTO Tarea (TituloTarea, Descripcion, Fecha, HoraEntrega) VALUES ('$tituloTarea', '$descripcion', '$fechaHora')";
            
            if ($conn->query($sql) === TRUE) {
                echo "Tarea creada correctamente";
            } else {
                echo "Error al crear la tarea: " . $conn->error;
            }
        }

    } else {
        echo "No se encontraron cursos.";
    }

    // Cerrar la conexión a la base de datos
    mysqli_close($conexion);
?>