package com.example.myapplication

import com.google.firebase.firestore.PropertyName

class Donativos(
    @get:PropertyName("idDonativo") val idDonativo: String = "",
    @get:PropertyName("correoCliente") val correoCliente: String? = "",
    @get:PropertyName("correoVendedor") val correoVendedor: String = "",
    @get:PropertyName("idPedido") val idPedido: String = "",
    @get:PropertyName("idPrenda") val idPrenda: String? = "",
    @get:PropertyName("totalDonativo") val totalDonativo: String = "",
    @get:PropertyName("nombreAsociacion") val nombreAsociacion: String? = "",
    @get:PropertyName("fechaPedido") val fechaPedido: String = ""
    ) {
    constructor() : this("", "", "", "", "", "", "","")
}