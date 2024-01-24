<?php
// Obtener el valor del campo 'radio' del formulario
$tipoUsuario = $_POST['radio'];

// Establecer la conexión con la base de datos
$host = "localhost";
$user = "root";
$pass = "";
$db = "proyecto";

$conexion = mysqli_connect($host, $user, $pass, $db);
if (!$conexion) {
    die("Error de conexión: " . mysqli_connect_error());
}

// Obtener los datos del formulario
$nombre = $_POST['nombre'];
$apellidop = $_POST['apellidop'];
$apellidom = $_POST['apellidom'];
$nocontrol = $_POST['nocontrol'];
$semestre = $_POST['semestre'];
$correo = $_POST['correo'];
$contrasena = $_POST['contrasena'];
$clave = $_POST['clave'];
$carrera = $_POST['carrera'];

// Verificar el tipo de usuario y ejecutar la consulta SQL correspondiente
if ($tipoUsuario === 'alumno') {
    $sql = "INSERT INTO alumno (NoControl, Nombre, ApellidoPaterno, ApellidoMaterno, Semestre, Carrera, CorreoElectronico, Contrasena) VALUES ('$nocontrol','$nombre','$apellidop','$apellidom','$semestre','$carrera', '$correo','$contrasena')";
    $resultado = mysqli_query($conexion, $sql);
} elseif ($tipoUsuario === 'profesor') {
    $sql = "INSERT INTO maestros (ClaseMaestro, Nombre, CorreoElectronico, Contrasena) VALUES ('$clave','$nombre','$correo','$contrasena')";
    $resultado = mysqli_query($conexion, $sql);

} else {
    // Tipo de usuario inválido
    echo "Tipo de usuario inválido";
    exit();
}


if ($resultado) {
    // Registro exitoso, redirigir al usuario a una página de éxito
    header("Location: registro_exitoso.html");
    exit();
} else {
    // Error en la ejecución de la consulta
    echo "Error en el registro: " . mysqli_error($conexion);
    exit();
}

// Cerrar la conexión
mysqli_close($conexion);
