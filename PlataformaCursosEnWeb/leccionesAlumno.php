<!DOCTYPE html>
<html lang="es">



<head>
    <link rel="stylesheet" href="estilos2-alumno.css">
    <link href="fontawesome-free-6.4.0-web/css/fontawesome.css" rel="stylesheet">
    <link href="fontawesome-free-6.4.0-web/css/brands.css" rel="stylesheet">
    <link href="fontawesome-free-6.4.0-web/css/solid.css" rel="stylesheet">
    <title>Inicio</title>
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
                <a href="inicioAlumno.html">
                <i class="fa fa-solid fa-house" style="color: #ffffff"></i>
                <span class="tooltip">Inicio</span>
                </a>
            </li>
            <li>
                <a href="clasDisAlum.html">
                <i class="fa-solid fa-school" style="color: #ffffff;"></i>
                <span class="tooltip">Clases disponibles</span>
            </li>
            <li>
                <a href="claseAlumno.html">
                <i class="fa-solid fa-book" style="color: #ffffff;"></i>
                <span class="tooltip">Mis clases</span>
                </a>
            </li>
            <li>
                <a href="tareaAlumno.html">
                <i class="fa-solid fa-pen" style="color: #ffffff;"></i>
                <span class="tooltip">Tareas</span>
                </a>
            </li>
            <li>
                <i class="fa-solid fa-calendar" style="color: #ffffff;"></i>
                <span class="tooltip">Calendario</span>
            </li>
        </ul>
    </nav>
    <div class="content">
        <div class="fixed-header">
            <h1>Mis clases</h1>
        </div>
        <div class="card">
            <img src="ejem-1.jpg" alt="Imagen del curso">
            <div class="card-body2">
                <h3 class="card-title2">Programacion en Python</h3>
                <p class="card-description">Ingenieria en Sistemas Computacionales</p>
                <p class="card-description">Imparte: Nombre del profesor</p>
                <p class="card-description">Únete a millones de estudiante alrededor del mundo que ya están aprendiendo en
                    Udemy. Aprende Python con los
                    instructores calificados, a tu propio ritmo. Cursos: Desarollo para Android, Android Studio, Diseño de apps
                    para móvil,
                    Java, Diseño de software, Firebase.</p>
                <p class="card-description">50 Lecciones</p>
                <a href="leccionAlumno.html">
                <button id="open-modal5" class="button2"><a href="#">Leccion </a></button>
                </a>
        
            </div>

        </div>
        
  

    </div>



</body>

</html>