package com.example.myapplication
import com.google.firebase.firestore.PropertyName

data class Direccion(
    @get:PropertyName("correo") var correo: String? = "",
    @get:PropertyName("calle") var calle: String? = "",
    @get:PropertyName("colonia") var colonia: String? = "",
    @get:PropertyName("noext") var noext: String? = "",
    @get:PropertyName("cp") var cp: String? = "",
    @get:PropertyName("municipio") var municipio: String? = "",
    @get:PropertyName("estado") var estado: String? = ""
) {
    // Agrega un constructor sin argumentos
    constructor() : this("", "", "", "", "", "", "")
}