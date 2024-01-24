const registerButton = document.getElementById("register");
const loginButton = document.getElementById("login");
const container = document.getElementById("container");

registerButton.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});

loginButton.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

function showAlumnoFields() {
    var profesorFields = ["nombre", "apellidop", "apellidom", "carrera","nocontrol", "semestre", "correo", "contraseña"];
    var fields = ["nombre", "apellidop", "apellidom", "carrera","nocontrol", "semestre", "correo", "contraseña"];

    for (var i = 0; i < profesorFields.length; i++) {
        var field = document.getElementById(profesorFields[i]);
        field.style.display = "none";
    }

    for (var i = 0; i < fields.length; i++) {
        var field = document.getElementById(fields[i]);
        field.style.display = "block";
    }
}

function showProfesorFields() {
    var profesorFields = ["clase", "nombre", "correo", "contraseña"];
    var fields = ["nombre", "apellidop", "apellidom", "nocontrol", "semestre", "correo", "contraseña"];

    for (var i = 0; i < fields.length; i++) {
        var field = document.getElementById(fields[i]);
        field.style.display = "none";
    }

    for (var i = 0; i < profesorFields.length; i++) {
        var field = document.getElementById(profesorFields[i]);
        field.style.display = "block";
    }
}