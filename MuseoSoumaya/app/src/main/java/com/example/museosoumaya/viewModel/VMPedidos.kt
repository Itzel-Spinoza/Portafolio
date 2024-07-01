package com.example.museosoumaya.viewModel

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import com.example.museosoumaya.model.MPedidos
import com.example.museosoumaya.model.MetodosPago
import com.example.museosoumaya.model.PedidosBoletos
import com.example.museosoumaya.model.PedidosGeneral
import java.time.LocalTime

class VMPedidos (private val mPedidos: MPedidos) {

    fun registrarPedido(navController: NavController, cantidad: String?, correoCliente: String?, folio: String?, precioUnitario: String, subtotal: String?, tipoBoleto: String) {
        val nuevoPedido = PedidosBoletos(cantidad, correoCliente, folio, precioUnitario, subtotal, tipoBoleto)
        mPedidos.agregarPedido(navController.context, nuevoPedido)
        Toast.makeText(navController.context, "Pedido registrado correctamente.", Toast.LENGTH_SHORT).show()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun registrarPedidoGeneral(navController: NavController, correoCliente: String?, fechaBoletos: String?, folio: String?, nombreCliente: String, total: String) {
        val horaVenta = LocalTime.now().toString()
        val nuevoPedido = PedidosGeneral(correoCliente, fechaBoletos, folio, horaVenta, nombreCliente, total)
        mPedidos.agregarPedidoGeneral(navController.context, nuevoPedido)
        Toast.makeText(navController.context, "Pedido registrado correctamente.", Toast.LENGTH_SHORT).show()
    }

    fun registrarTarjeta(navController: NavController, correo: String, cvc: String, fecha: String, noTarjeta: String, tipoTarjeta: String) {
        val idTarjeta = generarIDTarjeta()
        val nuevaTarjeta = MetodosPago(correo,cvc,fecha,idTarjeta,noTarjeta,tipoTarjeta)
        mPedidos.agregarTarjeta(navController.context, nuevaTarjeta)
        Toast.makeText(navController.context, "Nueva tarjeta registrada correctamente.", Toast.LENGTH_SHORT).show()
    }

    fun verMetodosDePago(correo: String?, callback: (List<MetodosPago>?) -> Unit) {
        mPedidos.obtenerMetodos(correo) { metodos ->
            // Llamar al callback con los métodos de pago obtenidos
            callback(metodos)
        }
    }

    fun concatenarNombreCliente(correo: String?, callback: (String?) -> Unit) {
        mPedidos.concatenarNombreCliente(correo) { nombre ->
            callback(nombre)
        }
    }
    fun generarIDTarjeta(): String {
        val random = java.util.Random()
        val idNumerico = (10000000 + random.nextInt(90000000)).toString()
        return idNumerico
    }
    fun verPedidosGeneral(correo: String?, callback: (List<PedidosGeneral>?) -> Unit) {
        mPedidos.obtenerPedidosGeneral(correo) { pedidosGeneral ->
            // Llamar al callback con los métodos de pago obtenidos
            callback(pedidosGeneral)
        }
    }

    fun controladorObtenerCantidadBoletos(folio: String?, tipoBoleto: String?, callback: (String?) -> Unit) {
        mPedidos.obtenerCantidadBoletos(folio, tipoBoleto) { cantidad ->
            // Llamar al callback con la cantidad de boletos obtenida
            callback(cantidad)
        }
    }


}