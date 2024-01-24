<!DOCTYPE html>
<html lang="es">
    
    

<head>
    <link rel="stylesheet" href="estilos2.css">
    <link href="fontawesome-free-6.4.0-web/css/fontawesome.css" rel="stylesheet">
    <link href="fontawesome-free-6.4.0-web/css/brands.css" rel="stylesheet">
    <link href="fontawesome-free-6.4.0-web/css/solid.css" rel="stylesheet">


<style>
    /* Estilo para el contenedor */
    .content {
        overflow-y: scroll;
        height: 600px;
       
    }
    
</style>
  <title>Lecciones</title>    
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
            <h1>Lecciones</h1>
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

$query = "SELECT NombreLecciones, Descripcion, Fecha, clavecurso, RecursosExtra FROM lecciones";
$result = mysqli_query($conexion, $query);

// Iterar sobre los resultados y mostrar los datos en HTML
while ($row = mysqli_fetch_assoc($result)) {
    $nombreLeccion = $row['NombreLecciones'];
    $descripcion = $row['Descripcion'];
    $fecha = $row['Fecha'];
    $claveCurso = $row['clavecurso'];
    $recursoExtra = $row['RecursosExtra'];
    ?>

    <div class="card">
        <!--<img src="imagen_curso.jpg" alt="Imagen del curso">-->
        <div class="card-body2">
            <h3 class="card-title2"><?php echo $nombreLeccion; ?></h3>
            <p class="card-description"><?php echo $descripcion; ?></p>
            <p class="card-description"><?php echo $fecha; ?></p>
            <p class="card-description"><?php echo $claveCurso; ?></p>
            <p class="card-description"><?php echo $recursoExtra; ?></p>
        </div>
    </div>

    <?php
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Obtener los datos del formulario
    $clave = uniqid(); // Generar una clave única utilizando uniqid()
    $nombre = $_POST['nombre'];
    $descripcion = $_POST['descripcion'];
    $fechaHora = $_POST['fecha-hora'];
    $recursosExtra = $_POST['recursosextra'];

    // Preparar la consulta SQL para insertar los datos en la tabla
    $query = "INSERT INTO lecciones (ClaveLecciones, NombreLecciones, Descripcion, Fecha, clavecurso, RecursosExtra)
              VALUES ('$clave', '$nombre', '$descripcion', '$fechaHora', '$claveCurso', '$recursosExtra')";

    // Ejecutar la consulta
    $result = mysqli_query($conexion, $query);

    // Verificar si la inserción fue exitosa
    if ($result) {
        // Inserción exitosa
        echo "La lección se ha creado correctamente.";
    } else {
        // Error en la inserción
        echo "Error al crear la lección: " . mysqli_error($conexion);
    }
}
        
        // Cerrar la conexión
mysqli_close($conexion);
?>

        <div class="options-buttons">
    <button id="open-modal" class="button">Alumnos inscritos</button>
    <button id="open-modal4" class="button-canceled">Eliminar lección</button>
    <button id="open-modal3" class="button">Añadir lección</button>
</div>

<div id="modal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Nueva lección</h2>
        <form action="" method="POST">
            <!-- Contenido del formulario -->

            <label for="nombre">Nombre lección:</label>
            <input type="text" id="nombre" name="nombre" required>

            <label for="descripcion">Descripción:</label>
            <input type="text" id="descripcion" name="descripcion" required>

            <label for="fecha-hora">Fecha y Hora:</label>
            <input type="datetime-local" id="fecha-hora" name="fecha-hora" required>

            <label for="recursosextra">Recursos extra:</label>
            <input type="text" id="recursosextra" name="recursosextra">

            <input type="submit" value="Crear lección">
        </form>
    </div>
</div>
        
        
        

        <div id="modal2" class="modal2">
            <div class="modal-content2">
              <span class="close2">&times;</span>
              <h2>Alumnos inscritos</h2>
            <form>
                <!-- Contenido del formulario -->
                <label for="curso">Listado de los alumnos del curso:</label>
             
                <table>
                  <tr>
                    <th>Número de control</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    
                  </tr>
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <!-- Agrega más filas para más alumnos -->
                </table>
               

                <button id= "open-eliminar-alumno" class="button">Eliminar alumno</button>
            </form>
            </div>
        </div>        

        <div id="modal3" class="modal3">
            <div class="modal-content3">
              <span class="close3">&times;</span>
              <h2>Eliminar lección</h2>
            <form>
                <!-- Contenido del formulario -->
                <label for="curso">Selecciona la lección que quieres eliminar:</label>
                <select id="curso" name="curso" required>
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
               

                <input type="submit" value="Eliminar clase">
            </form>
            </div>
        </div>
            <div id="modal3" class="modal3">
                <div class="modal-content3">
                  <span class="close3">&times;</span>
                  <h2>Eliminar lección</h2>
                <form>
                    <!-- Contenido del formulario -->
                    <label for="curso">Selecciona la lección que quieres eliminar:</label>
                    <select id="curso" name="curso" required>
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
                   
    
                    <input type="submit" value="Eliminar clase">
                </form>
                </div>            
            </div>        

            <div id="modal4" class="modal4">
                <div class="modal-content4">
                  <span class="close4">&times;</span>
                  <h2>Eliminar alumno</h2>
                <form>
                    <!-- Contenido del formulario -->
                    <label for="curso">Selecciona el alumno que quieres eliminar:</label>
                    <select id="curso" name="curso" required>
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
                   
    
                    <input type="submit" value="Eliminar alumno">
                </form>
                </div>            
            </div>               

    </div>
    <script>

        document.getElementById("open-modal3").addEventListener("click", function() {
            document.getElementById("modal").style.display = "block";
        });
  
        document.getElementsByClassName("close")[0].addEventListener("click", function() {
            document.getElementById("modal").style.display = "none";
        });

        document.getElementById("open-modal").addEventListener("click", function() {
            document.getElementById("modal2").style.display = "block";
        });
  
        document.getElementsByClassName("close2")[0].addEventListener("click", function() {
            document.getElementById("modal2").style.display = "none";
        });

        document.getElementById("open-modal4").addEventListener("click", function() {
            document.getElementById("modal3").style.display = "block";
        });
  
        document.getElementsByClassName("close3")[0].addEventListener("click", function() {
            document.getElementById("modal3").style.display = "none";
        });

        document.getElementById("open-eliminar-alumno").addEventListener("click", function() {
            document.getElementById("modal4").style.display = "block";
        });
  
        document.getElementsByClassName("close4")[0].addEventListener("click", function() {
            document.getElementById("modal4").style.display = "none";
        });


        // Obtener el campo de fecha y hora
        var campoFechaHora = document.getElementById("fecha-hora");

        // Obtener la fecha y hora actual
        var fechaHoraActual = new Date();

        // Formatear la fecha y hora actual en formato ISO (YYYY-MM-DDTHH:mm)
        var formatoISO = fechaHoraActual.toISOString().slice(0, 16);

        // Asignar el valor formateado al campo de fecha y hora
        campoFechaHora.value = formatoISO;

    </script>
</body>
</html>



