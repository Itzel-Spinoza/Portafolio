package com.example.libreriaelbuho.modelo

class Carrito(
    var id: String? = "",
    var correoCliente: String? = "",
    var tituloLibro: String? = "",
    var autor: String? = "",
    var cantidad: Int = 0,
    var precioLibro: Float = 0f,
    var subtotal: Float = 0f) {
}