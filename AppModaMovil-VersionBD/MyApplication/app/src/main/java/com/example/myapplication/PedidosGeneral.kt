package com.example.myapplication
import com.google.firebase.firestore.PropertyName
class PedidosGeneral(
    @get:PropertyName("idPedido") val idPedido: String = "",
    @get:PropertyName("fechaPedido") val fechaPedido: String = "",
    @get:PropertyName("direccionCliente") val direccionCliente: String = "",
    @get:PropertyName("estadoPedido") var estadoPedido: String = "",
    @get:PropertyName("correoCliente") val correoCliente: String? = "",
    @get:PropertyName("nombreCliente") val nombreCliente: String = "",
    @get:PropertyName("totalPedido") val totalPedido: String? = "",
    @get:PropertyName("totalDonacion") val totalDonacion: String? = ""
) {
    constructor() : this("", "", "", "", "", "", "","")
}