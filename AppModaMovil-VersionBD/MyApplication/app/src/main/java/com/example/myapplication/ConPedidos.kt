package com.example.myapplication

import android.content.Context
import android.widget.Toast

class ConPedidos(private val mPedidos: MPedidos) {

    fun agregarPrendasPedido(
        context: ConComprar, idPedido:String, correoVendedor:String, correoVen:String, idRopa: String?,
        nombrePrenda:String,
        talla:String,
        categoria:String,
        genero:String,
        precioPrenda:String,
        porcentajePrenda:String,
        estado:String,
        asociacion: String?
    ){
        val ropa = PrendasPedido(idPedido,correoVendedor, correoVen, idRopa,nombrePrenda,talla,categoria,genero,precioPrenda,porcentajePrenda,estado,asociacion)
        mPedidos.agregarPrendasPedido(context, ropa) { exitoso, mensaje ->
            if (exitoso) {
                //Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                //Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun verPedidos(correo: String?, callback: (List<Pedido>?) -> Unit) {
        mPedidos.verPedidos(correo) { pedido ->
            if (pedido != null) {
                // Aquí puedes realizar cualquier procesamiento adicional en la lista si es necesario
                val cardlist: List<Pedido>? = pedido

                // Llamar al callback con la lista creada
                callback(cardlist)
            } else {
                // Llamar al callback con null si no se pudieron obtener direcciones
                callback(null)
            }
        }
    }
    fun verPrendasPedido(correo: String?, idPedido: String?, callback: (List<Ropa>?) -> Unit) {
        mPedidos.verPrendasVendedor(correo,idPedido) { ropa ->
            if (ropa != null) {
                // Aquí puedes realizar cualquier procesamiento adicional en la lista si es necesario
                val cardlist: List<Ropa>? = ropa

                // Llamar al callback con la lista creada
                callback(cardlist)
            } else {
                // Llamar al callback con null si no se pudieron obtener direcciones
                callback(null)
            }
        }
    }

   /* fun marcarCompletadoPedido(idPedido: String?) {
        val pedido = DataRepository.listaPedidos.find { it.idPedido == idPedido }

        if (pedido != null) {
            // Actualiza el campo "estado" a "Entregado"
            pedido.estadoPedido = "Entregado"
        } else {
            // Manejar el caso en que el pedido no se encuentre
            println("Pedido no encontrado con el ID: $idPedido")
        }
    }*/

    fun marcarCompletadoPedido(context: Context, idPedido: String?, estado: String) {
        mPedidos.actualizarEstadoPedido(idPedido, estado) { exitoso, mensaje ->
            if (exitoso) {
                // Manejar el caso de actualización exitosa
                Toast.makeText(context, "Pedido completado correctamente.", Toast.LENGTH_SHORT).show()
            } else {
                // Manejar el caso de error en la actualización
                Toast.makeText(context, "Error al completar el pedido: $mensaje", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun marcarCompletadoPedidoGeneral(context: Context, idPedido: String?, estado: String) {
        mPedidos.actualizarEstadoPedido(idPedido, estado) { exitoso, mensaje ->
            if (exitoso) {
                // Manejar el caso de actualización exitosa
                //Toast.makeText(context, "Pedido completado correctamente.", Toast.LENGTH_SHORT).show()
            } else {
                // Manejar el caso de error en la actualización
                //Toast.makeText(context, "Error al completar el pedido: $mensaje", Toast.LENGTH_SHORT).show()
            }
        }
    }



    fun filtrarPedido(estado: String, correo: String?, callback: (List<Pedido>) -> Unit) {
        mPedidos.filtrarPedidos(correo, estado) { pedido ->
            if (pedido.isNotEmpty()) {
                // Ropa encontrada
                callback(pedido)
            } else {
                // Ropa no encontrada
                callback(emptyList())
            }
        }
    }

    /*fun calcularGanancia(idPedido: String?): String {
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
    }*/

    fun calcularGanancia(idPedido: String?, callback: (String) -> Unit) {
        mPedidos.obtenerPrendasPorPedido(idPedido ?: "") { prendas ->
            var suma = 0.0
            for (prenda in prendas) {
                val ganancia = mPedidos.calcularGananciaOperacion(prenda.precioPrenda, prenda.porcentajeDonativo)
                suma += ganancia
            }
            val suma2 = suma.toString()
            callback(suma2)
        }
    }
}


