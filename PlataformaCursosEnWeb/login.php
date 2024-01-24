<?php
    session_start();
    $host = "localhost";
    $user = "root";
    $pass = "";
    $db = "mysql";

    $conexion = mysqli_connect($host, $user, $pass, $db);
    if (!$conexion) {
        echo "Error de conexión: " . mysqli_connect_error();
        exit();
    }

    $CorreoElectronico = $_POST['CorreoElectronico'];
    $Contrasena = $_POST['Contrasena'];

    // Consulta para verificar las credenciales en la tabla 'alumno'
    $sql_alumno = "SELECT * FROM alumno WHERE CorreoElectronico = '$CorreoElectronico' AND Contrasena='$Contrasena'";
    $result_alumno = mysqli_query($conexion, $sql_alumno);

    // Consulta para verificar las credenciales en la tabla 'maestro'
    $sql_maestro = "SELECT * FROM maestros WHERE CorreoElectronico = '$CorreoElectronico' AND Contrasena='$Contrasena'";
    $result_maestro = mysqli_query($conexion, $sql_maestro);

    if ($result_alumno && mysqli_num_rows($result_alumno) === 1) {
        // Inicio de sesión exitoso para un alumno
        $_SESSION['correo'] = $CorreoElectronico; // Agregar el correo a $_SESSION
        header("Location: inicioAlumno.html");
        exit();
    } elseif ($result_maestro && mysqli_num_rows($result_maestro) === 1) {
        // Inicio de sesión exitoso para un maestro
        $_SESSION['correo'] = $CorreoElectronico; // Agregar el correo a $_SESSION
        header("Location: inicioMaestro.html");
        exit();
    } else {
        // Usuario o contraseña incorrectos
        header("Location: login.html?error=El usuario o la clave son incorrectas");
        exit();
    }
?>
