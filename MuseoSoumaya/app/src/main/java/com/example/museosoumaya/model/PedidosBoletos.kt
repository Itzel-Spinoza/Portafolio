package com.example.museosoumaya.model

data class PedidosBoletos(
    var cantidad: String? = "",
    var correoCliente: String? = "",
    var folio: String? = "",
    var precioUnitario: String = "",
    var subtotal: String? = "",
    var tipoBoleto: String = "")
