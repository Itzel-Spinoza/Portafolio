package com.example.myapplication

import android.provider.ContactsContract.Data

class ConPedidos(private val dataRepository: DataRepository) {
    fun verPedidos(correo: String?): MutableList<Pedido> {
        val cardList = DataRepository.listaPedidos.filter { it.correoVendedor == correo }.toMutableList()
        return cardList
    }

    fun verPrendasPedido(idPedido: String?): MutableList<Ropa> {

        val idPrendas = DataRepository.listaPrendasPedidos
            .filter { it.idPedido == idPedido }
            .map { it.idRopa }

        val prendasPedido = DataRepository.listaRopa.filter { it.ide in idPrendas }.toMutableList()
        return prendasPedido
    }

    fun marcarCompletadoPedido(idPedido: String?) {
        val pedido = DataRepository.listaPedidos.find { it.idPedido == idPedido }

        if (pedido != null) {
            pedido.estadoPedido = "Entregado"
        } else {
            println("Pedido no encontrado con el ID: $idPedido")
        }
    }

    fun filtrarPedido(estado: String, correo: String?): MutableList<Pedido> {
        val cardList = DataRepository.listaPedidos.filter {
            it.estadoPedido == estado && it.correoVendedor == ( correo ?: "")
        }.toMutableList()

        return cardList
    }

    fun calcularGanancia(idPedido: String?): String {
        var suma = 0.0
        for(prenda in DataRepository.listaPrendasPedidos){
            if(idPedido == prenda.idPedido){
                var ganancia = calcularGananciaOperacion(prenda.precioPrenda,prenda.porcentajeDonativo)
                suma += ganancia.toFloat()
            }
        }
        val suma2 = suma.toString()
        return suma2
    }

    fun calcularGananciaOperacion(precio: String, porcentaje: String): String {
        val precioF = precio.toFloat()
        val porcentajeF = porcentaje.toFloat()

        val descuento = precioF * (porcentajeF/100)
        val precioRopa = precioF - descuento
        val precioRopa2 = precioRopa.toString()
        return(precioRopa2)
    }

}


