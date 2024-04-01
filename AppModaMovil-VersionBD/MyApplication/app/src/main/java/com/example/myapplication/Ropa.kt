package com.example.myapplication

import com.google.firebase.firestore.PropertyName

class Ropa(
    @get:PropertyName("ide") val ide: String = "",
    @get:PropertyName("correo") val correo: String = "",
    @get:PropertyName("nombrePrenda") var nombrePrenda: String = "",
    @get:PropertyName("talla") var talla: String = "",
    @get:PropertyName("categoria") var categoria: String = "",
    @get:PropertyName("genero") var genero: String = "",
    @get:PropertyName("descripcion") var descripcion: String = "",
    @get:PropertyName("foto") val foto: String = "",
    @get:PropertyName("precio") var precio: String = "",
    @get:PropertyName("porcentaje") var porcentaje: String = "",
    @get:PropertyName("aso") var aso: String? = "",
    @get:PropertyName("donativo") var donativo: String = "",
    @get:PropertyName("estado") var estado: String = ""
) {
    constructor() : this("", "", "", "", "", "", "","","","","","","")
}