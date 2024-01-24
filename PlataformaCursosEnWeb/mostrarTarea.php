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

// Obtener las tareas de la base de datos
$sql = "SELECT TituloTarea, Descripcion, Fecha, HoraEntrega FROM Tarea";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $tituloTarea = $row["TituloTarea"];
        $descripcion = $row["Descripcion"];
        $fecha = $row["Fecha"];
        $horaEntrega = $row["HoraEntrega"];

        // Generar el código HTML para mostrar la tarea
        echo '<div class="card">
            <div class="textBox">
                <div class="textContent">
                    <span>Entregado</span>
                    <p>' . $tituloTarea . '</p>
                    <hr class="separator">

                    <label for="datetime">Fecha y Hora:</label>
                    <input type="datetime-local" id="datetime" name="datetime" value="' . $fecha . ' ' . $horaEntrega . '" readonly>
                </div>
                <div class="card2">
                    <div class="chat-header">Descripción</div>
                    <div class="chat-window">
                        <ul class="message-list">
                            <li>' . $descripcion . '</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>';
    }
} else {
    echo "No se encontraron tareas";
}

$conn->close();
?>
