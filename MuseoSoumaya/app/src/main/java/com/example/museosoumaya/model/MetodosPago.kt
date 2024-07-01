package com.example.museosoumaya.model

data class MetodosPago(var correo: String = "",
                       var cvc: String = "",
                       var fecha: String = "",
                       var id: String = "",
                       var noTarjeta: String = "",
                       var tipoTarjeta: String = "",
                       var titular: String = ""
    )
