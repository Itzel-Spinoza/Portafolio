package com.example.myapplication

class PrendasPedido(
    val idPedido: String,
    val correoVendedor: String,
    val nombreVendedor: String,
    val idRopa: String,
    val nombrePrenda: String,
    var talla: String?,
    var categoria: String?,
    var genero: String?,
    val precioPrenda: String,
    val porcentajeDonativo: String,
    val estadoPrenda: String,
    val aso: String?
){
}