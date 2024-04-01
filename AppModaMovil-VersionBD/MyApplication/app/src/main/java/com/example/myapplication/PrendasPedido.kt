package com.example.myapplication

import com.google.firebase.firestore.PropertyName

class PrendasPedido(
    @get:PropertyName("idPedido") val idPedido: String = "",
    @get:PropertyName("correoVendedor") val correoVendedor: String = "",
    @get:PropertyName("nombreVendedor") val nombreVendedor: String = "",
    @get:PropertyName("idRopa") val idRopa: String? = "",
    @get:PropertyName("nombrePrenda") val nombrePrenda: String = "",
    @get:PropertyName("talla") var talla: String? = "",
    @get:PropertyName("categoria") var categoria: String? = "",
    @get:PropertyName("genero") var genero: String? = "",
    @get:PropertyName("precioPrenda") val precioPrenda: String = "",
    @get:PropertyName("porcentajeDonativo") val porcentajeDonativo: String = "",
    @get:PropertyName("estadoPrenda") val estadoPrenda: String = "",
    @get:PropertyName("aso") val aso: String? = ""
){
    constructor() : this("", "", "", "", "", "", "","","","","","")
}