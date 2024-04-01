package com.example.myapplication

import com.google.firebase.firestore.PropertyName

class MetodosPago (
    @get:PropertyName("correo") val correo: String? = "",
    @get:PropertyName("tipoTarjeta") var tipoTarjeta:String = "",
    @get:PropertyName("noTarjeta") var noTarjeta:String = "",
    @get:PropertyName("titularTarjeta") var titularTarjeta:String = "",
    @get:PropertyName("fechaExpedicion") var fechaExpedicion:String = "",
    @get:PropertyName("ccv") var ccv:String = ""){
    // Agrega un constructor sin argumentos
    constructor() : this("", "", "", "", "", "")
}