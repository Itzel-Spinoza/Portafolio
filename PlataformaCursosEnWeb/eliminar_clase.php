<?php
    session_start();

    // Verificar si el maestro ha iniciado sesión y tiene el correo almacenado en la variable de sesión
    if (!isset($_SESSION['correo'])) {
        echo "No se ha iniciado sesión como maestro.";
        exit();
    }

    $correo = $_SESSION['correo'];

    // Establecer la conexión con la base de datos
    $host = "localhost";
    $user = "root";
    $pass = "";
    $db = "proyecto";

    $conexion = mysqli_connect($host, $user, $pass, $db);
    if (!$conexion) {
        die("Error de conexión: " . mysqli_connect_error());
    }

    // Obtener la lista de clases del maestro
    $sql = "SELECT NombreCurso FROM curso WHERE ClaveMaestro = (SELECT ClaseMaestro FROM maestros WHERE CorreoElectronico = '$correo')";
    $resultado = mysqli_query($conexion, $sql);

    if (!$resultado || mysqli_num_rows($resultado) == 0) {
        // No se encontraron clases para el maestro
        echo "No se encontraron clases para el maestro.";
        exit();
    }

    // Generar las opciones del select con los nombres de las clases
    $options = "";
    while ($row = mysqli_fetch_assoc($resultado)) {
        $nombreCurso = $row['NombreCurso'];
        $options .= "<option value=\"$nombreCurso\">$nombreCurso</option>";
    }

    // Cerrar la conexión
    mysqli_close($conexion);
?>






