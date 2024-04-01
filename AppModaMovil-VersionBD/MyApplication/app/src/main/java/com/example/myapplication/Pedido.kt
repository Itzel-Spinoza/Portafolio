package com.example.myapplication
import com.google.firebase.firestore.PropertyName
class Pedido(
    @get:PropertyName("idPedido") val idPedido: String = "",
    @get:PropertyName("fecha") val fecha: String = "",
    @get:PropertyName("direccion") val direccion: String = "",
    @get:PropertyName("estadoPedido") var estadoPedido: String = "",
    @get:PropertyName("correoCliente") val correoCliente: String? = "",
    @get:PropertyName("nombreCliente") val nombreCliente: String = "",
    @get:PropertyName("correoVendedor") val correoVendedor: String? = "",
    @get:PropertyName("fechaPedido") val fechaPedido: String = ""
) {
    constructor() : this("", "", "", "", "", "", "","")
}