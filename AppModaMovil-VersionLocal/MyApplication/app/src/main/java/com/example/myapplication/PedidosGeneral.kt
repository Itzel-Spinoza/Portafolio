package com.example.myapplication

class PedidosGeneral(
    val idPedido: String,
    val fechaPedido: String,
    val direccionCliente: String,
    var estadoPedido: String,
    val correoCliente: String?,
    val nombreCliente: String,
    val totalPedido: String?,
    val totalDonacion: String?
) {
}