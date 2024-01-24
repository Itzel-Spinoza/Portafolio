<?php
session_start();
$correo = $_SESSION['correo'];

// Establecer la conexión con la base de datos
$host = "localhost";
$user = "root";
$pass = "";
$db = "mysql";

// Establecer conexión con la base de datos
$conexion = mysqli_connect($host, $user, $pass, $db);
if (!$conexion) {
    die("Error de conexión: " . mysqli_connect_error());
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Obtener los datos del formulario
    $clave = $_POST['clave'];
    $nombre = $_POST['nombre'];
    $descripcion = $_POST['descripcion'];
    $fechaHora = $_POST['fecha-hora'];
    $recursoExtra = $_POST['recursosextra'];

    // Generar el UUID
    $uuid = uniqid();

    // Obtener la clave del maestro basándote en su correo electrónico
    $sqlMaestro = "SELECT ClaseMaestro FROM maestros WHERE CorreoElectronico = '$correo'";
    $resultadoMaestro = mysqli_query($conexion, $sqlMaestro);

    if (!$resultadoMaestro || mysqli_num_rows($resultadoMaestro) == 0) {
        // No se encontró el maestro con el correo electrónico especificado
        echo "Error: Maestro no encontrado.";
        exit();
    }

    $rowMaestro = mysqli_fetch_assoc($resultadoMaestro);
    $claveMaestro = $rowMaestro['ClaseMaestro'];

    // Generar un ID automático para clave_curso utilizando UUID()
    $sql = "INSERT INTO lecciones (ClaveLecciones, NombreLecciones, Descripcion, Fecha, clavecurso) VALUES ('$uuid', '$nombre', '$descripcion', '$fechaHora', '$curso')";

    if (!empty($recursoExtra)) {
        $sql2 = "INSERT INTO leccirecur (claveleccion, RecursosExtra) VALUES ('$uuid', '$recursoExtra')";
        if (mysqli_query($conexion, $sql2)) {
            echo "Datos insertados correctamente en otra tabla.";
        } else {
            echo "Error al insertar los datos en otra tabla: " . mysqli_error($conexion);
        }
    }

    $resultado = mysqli_query($conexion, $sql);

    if ($resultado) {
        // Mostrar mensaje de éxito
        echo "Clase creada exitosamente.";
    } else {
        // Error en el registro de la clase
        echo "Error en el registro: " . mysqli_error($conexion);
        exit();
    }
}

// Cerrar la conexión
mysqli_close($conexion);
?>