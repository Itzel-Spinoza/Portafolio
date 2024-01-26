package com.example.myapplication

class Pedido(
    val idPedido: String,
    val fecha: String,
    val direccion: String,
    var estadoPedido: String,
    val correoCliente: String?,
    val nombreCliente: String,
    val correoVendedor: String,
    val fechaPedido: String
) {

}