<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" href="estilos2.css">
    <link href="fontawesome-free-6.4.0-web/css/fontawesome.css" rel="stylesheet">
    <link href="fontawesome-free-6.4.0-web/css/brands.css" rel="stylesheet">
    <link href="fontawesome-free-6.4.0-web/css/solid.css" rel="stylesheet">
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
    <div class="content">
        <div class="fixed-header">
            <h1>Clases impartidas</h1>
        </div>

        <div class="card">
            <!--<img src="imagen_curso.jpg" alt="Imagen del curso">-->
            <div class="card-body2">
              <h3 class="card-title2">Nombre del curso</h3>
              <p class="card-description">Carrera</p>
              <p class="card-description">Descripción del curso Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              <p class="card-description">No. lecciones</p>
              <button id="open-modal34" class="button button2"><a href="leccionesMaestro.html">Ver lecciones</a></button>
            </div>
        </div>
        <div class="options-buttons">
            <button id="open-modal" class="button">Crear clase</button>
            <button id="open-modal2" class="button-canceled">Eliminar clase</button>
        </div>

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
            $user = "root";
            $pass = "";
            $db = "mysql";

            // Establecer conexión con la base de datos
            $conexion = mysqli_connect($host, $user, $pass, $db);
            if (!$conexion) {
                die("Error de conexión: " . mysqli_connect_error());
            }

            // Consulta para obtener los datos de los cursos
            $sql = "SELECT Nombre, Carrera, Descripcion FROM curso WHERE ClaseMaestro = (SELECT ClaseMaestro FROM maestros WHERE CorreoElectronico = '$correo')";
            $resultado = mysqli_query($conexion, $sql);

            // Verificar si se encontraron resultados
            if (mysqli_num_rows($resultado) > 0) {
                // Iterar sobre los resultados y generar el HTML del card para cada curso
                while ($row = mysqli_fetch_assoc($resultado)) {
                    $nombreCurso = $row['Nombre'];
                    $carreraCurso = $row['Carrera'];
                    $descripcionCurso = $row['Descripcion'];

                    // Generar el HTML del card con los datos del curso
                    echo '<div class="card">';
                    echo '  <!--<img src="imagen_curso.jpg" alt="Imagen del curso">-->';
                    echo '  <div class="card-body2">';
                    echo '    <h3 class="card-title2">' . $nombreCurso . '</h3>';
                    echo '    <p class="card-description">' . $carreraCurso . '</p>';
                    echo '    <p class="card-description">' . $descripcionCurso . '</p>';
                    echo '    <button id="open-modal34" class="button button2"><a href="leccionesMaestro.html">Ver lecciones</a></button>';
                    echo '  </div>';
                    echo '</div>';
                }
            } else {
                echo "No se encontraron cursos.";
            }

            // Cerrar la conexión a la base de datos
            mysqli_close($conexion);
        ?>

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

                <input type="submit" value="Crear clase">
            </form>
            </div>
        </div>

        <div id="modal2" class="modal2">
            <div class="modal-content2">
              <span class="close2">&times;</span>
              <h2>Eliminar clase</h2>
              <form action="eliminar_clase.php" method="POST">
                <label for="curso">Selecciona la clase que quieres eliminar:</label>
                <select id="curso" name="curso" required>
                    <?php echo $options; ?>
                </select>
            
                <input type="submit" value="Eliminar clase">
            </form>
            </div>
        </div>        

    </div>
    <script>
    document.addEventListener("DOMContentLoaded", function() {
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
    });
</script>
</body>
</html>