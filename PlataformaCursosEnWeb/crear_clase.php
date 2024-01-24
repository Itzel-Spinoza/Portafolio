<!DOCTYPE html>
<html lang="es">
    
    

<head>
    <link rel="stylesheet" href="estilos2.css">
    <link href="fontawesome-free-6.4.0-web/css/fontawesome.css" rel="stylesheet">
    <link href="fontawesome-free-6.4.0-web/css/brands.css" rel="stylesheet">
    <link href="fontawesome-free-6.4.0-web/css/solid.css" rel="stylesheet">

    <style>
        .content {
            overflow: auto;
            height: 1600px; /* Establece la altura deseada para el contenido con scrollbar */
        }
    </style>
  <title>Clases impartidas</title>    
    <meta charset="UTF-8" />
    
</head>

<body>
    <div class="menu-usuario">
        <button class="menu-button"><i class="fa-solid fa-user" style="color: #605caa;"></i></button>
        <ul class="menu-list">
          <li><a href="#">Datos usuario</a></li>
          <li><a href="#">Cerrar sesión</a></li>
        </ul>
    </div>    

    <nav class="sidebar-navigation">
        <ul>
            <li>
                <i class="fa"><img src="imagenes/cloverlogo.png"></i>
                <span class="tooltip">Clover</span>
            </li>		
            <li>
                <a href="inicioMaestro.html">
                <i class="fa fa-solid fa-house" style="color: #ffffff"></i>
                <span class="tooltip">Inicio</span>
                </a>
            </li>
            <li>
                <a href="clasesImpartidas.html">
                <i class="fa-solid fa-book" style="color: #ffffff;"></i>
                <span class="tooltip">Clases impartidas</span>
                </a>
            </li>
            <li>
                <a href="tareasMaestro.html">
                <i class="fa-solid fa-pen" style="color: #ffffff;"></i>
                <span class="tooltip">Tareas</span>
                </a>
            </li>
            <li>
                <a href="clasesImpartidas.html">
                <i class="fa-solid fa-calendar" style="color: #ffffff;"></i>
                <span class="tooltip">Calendario</span>
                </a>
            </li>
        </ul>
    </nav>
    <div class = "content">
        <div class="fixed-header">
            <h1>Clases impartidas</h1>
        </div>


        <div class="card">
            <div class="card-body2">
              <h3 class="card-title2">Nombre del curso
              </h3>
              <p class="card-description">Carrera
              </p>
              <p class="card-description">Descripción del curso Lorem ipsum dolor sit amet, consectetur adipiscing elit.
              </p>
              <button id= "open-modal34" class="button button2"><a href="leccionesMaestro.html">Ver lecciones</a></button>
            </div>
            
        </div>
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

$nombre = $_POST ['nombre'];
$carrera = $_POST ['carrera'];
$descripcion = $_POST ['descripcion'];
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
$sql = "INSERT INTO curso (ClaveCurso, NombreCurso, Carrera, Descripcion, ClaveMaestro) VALUES (UUID(), '$nombre', '$carrera', '$descripcion', '$claveMaestro')";
$resultado = mysqli_query($conexion, $sql);

if ($resultado) {
    // Mostrar mensaje de éxito
    echo "Clase creada exitosamente.";

    // Obtener todas las clases del maestro
    $sqlClases = "SELECT * FROM curso WHERE ClaveMaestro = '$claveMaestro'";
    $resultadoClases = mysqli_query($conexion, $sqlClases);

    if ($resultadoClases && mysqli_num_rows($resultadoClases) > 0) {
        // Mostrar una tarjeta por cada clase creada
        while ($rowClase = mysqli_fetch_assoc($resultadoClases)) {
            $nombreCurso = $rowClase['NombreCurso'];
            $carreraCurso = $rowClase['Carrera'];
            $descripcionCurso = $rowClase['Descripcion'];

            // Mostrar la tarjeta con la información de la clase
            echo '<div class="card">';
            echo '<div class="card-body2">';
            echo "<h3 class='card-title2'>$nombreCurso</h3>";
            echo "<p class='card-description'>Carrera: $carreraCurso</p>";
            echo "<p class='card-description'>Descripción: $descripcionCurso</p>";
            echo "<button id='open-modal34' class='button button2'><a href='crear_leccion2.php'>Ver lecciones</a></button>";
            echo '</div>';
            echo '</div>';
        }
    }
} else {
    // Error en el registro de la clase
    echo "Error en el registro: " . mysqli_error($conexion);
    exit();
}

// Cerrar la conexión
mysqli_close($conexion);
?>
        


        <div class="options-buttons">
            <button id= "open-modal" class="button">Crear clase</button>
            <button id= "open-modal2" class="button-canceled">Eliminar clase</button>
        </div>
        <div id="modal" class="modal">
            <div class="modal-content">
              <span class="close">&times;</span>
              <h2>Nueva clase</h2>
            <form action="crear_clase.php" method="POST">
                <!-- Contenido del formulario -->
                <label for="nombre">Nombre del curso:</label>
                <input type="text" id="nombre" name="nombre" required>
        
                <label for="carrera">Carrera:</label>
                <select id="carrera" name="carrera" required>
                    <option value="industrial">Ingeniería Industrial</option>
                    <option value="sistemas">Ingeniería en Sistemas Computacionales</option>
                    <option value="bioquimica">Ingeniería Bioquímica</option>
                    <option value="electro">Ingeniería Electromécanica</option>
                    <option value="civil">Ingeniería Civil</option>
                    <option value="tics">Ingeniería en Tecnologías de la Información y Comunicación</option>
                    <option value="ambiental">Ingeniería Ambiental</option>
                    <option value="gestion">Ingeniería en Gestión Empresarial</option>
                    <option value="petrolera">Ingeniería Petrolera</option>
                </select>
               
        
                <label for="descripcion">Descripción: </label>
                <input type="text" id="descripcion" name="descripcion" required>

                <a href="crear_clase.php"><input type="submit" value="Crear clase"></a>
            </form>
            </div>
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
$sql = "INSERT INTO curso (ClaveCurso, NombreCurso, Carrera, Descripcion, ClaveMaestro) VALUES (UUID(), '$nombre', '$carrera', '$descripcion', '$claveMaestro')";
$resultado = mysqli_query($conexion, $sql);

if ($resultado) {
    // Mostrar mensaje de éxito
    echo "Clase creada exitosamente.";

} else {
    // Error en el registro de la clase
    echo "Error en el registro: " . mysqli_error($conexion);
    exit();
}

// Cerrar la conexión
mysqli_close($conexion);
?>
        </div>

        <div id="modal2" class="modal2">
            <div class="modal-content2">
              <span class="close2">&times;</span>
              <h2>Eliminar clase</h2>
              <form action="crear_clase.php" method="POST">
                <label for="curso">Selecciona la clase que quieres eliminar:</label>
                <input type="text" name="curso_eliminar" required>
                </select>
            
                <input type="submit" value="Eliminar clase">
               
<?php
//session_start();
//$correo = $_SESSION['correo'];

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
$sql = "INSERT INTO curso (ClaveCurso, NombreCurso, Carrera, Descripcion, ClaveMaestro) VALUES (UUID(), '$nombre', '$carrera', '$descripcion', '$claveMaestro')";
$resultado = mysqli_query($conexion, $sql);

if ($resultado) {
    // Mostrar mensaje de éxito
    echo "Clase creada exitosamente.";

} else {
    // Error en el registro de la clase
    echo "Error en el registro: " . mysqli_error($conexion);
    exit();
}

     // Eliminar la clase
       /* $sql = "DELETE FROM curso WHERE NombreCurso = '$curso_eliminar'";
        if (mysqli_query($conexion, $sql)) {
            echo "Clase eliminada correctamente.";
        } else {
            echo "Error al eliminar la clase: " . mysqli_error($conexion);
        }*/

if (isset($_POST['curso_eliminar'])) {
    $curso_eliminar = $_POST['curso_eliminar'];

    // Eliminar la clase
    $sql = "DELETE FROM curso WHERE NombreCurso = '$curso_eliminar'";
    if (mysqli_query($conexion, $sql)) {
        echo "Clase eliminada correctamente.";
    } else {
        echo "Error al eliminar la clase: " . mysqli_error($conexion);
    }
} else {
    echo "Error: El parámetro 'curso_eliminar' no se ha proporcionado.";
}        


// Cerrar la conexión
mysqli_close($conexion);
?>
               
            </form>
            </div>
        </div>        

    </div>
    <script src="materiasmaestro_mostrar.php"></script>
    <script>
        document.getElementById("open-modal").addEventListener("click", function() {
            document.getElementById("modal").style.display = "block";
        });
  
        document.getElementsByClassName("close")[0].addEventListener("click", function() {
            document.getElementById("modal").style.display = "none";
        });

        document.getElementById("open-modal2").addEventListener("click", function() {
            document.getElementById("modal2").style.display = "block";
        });
  
        document.getElementsByClassName("close2")[0].addEventListener("click", function() {
            document.getElementById("modal2").style.display = "none";
        });

    </script>
    
</body>
</html>



