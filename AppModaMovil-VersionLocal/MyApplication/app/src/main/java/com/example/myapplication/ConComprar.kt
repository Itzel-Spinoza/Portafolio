package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class ConComprar (private val dataRepository: DataRepository) {

    val conRopa = ConInventarioRopa(DataRepository)
    val conDonativos= ConDonaciones(DataRepository)
    fun contarPrendasCarrito(correoCliente: String?): Int {
        var contador = 0

        for (item in DataRepository.listaCarrito) {
            if (item.correoCliente == correoCliente) {
                contador += 1

            }
        }

        return contador
    }

    fun mostrarPrendasCarrito(correoCliente: String?): MutableList<RopaCarrito> {
        val cardList = DataRepository.listaCarrito.filter {
            it.correoCliente == correoCliente
        }.toMutableList()

        return cardList
    }


    fun calcularTotalCompra(correoCliente: String?): Double {
        var suma = 0.0
        for (item in DataRepository.listaCarrito) {
            if (item.correoCliente == correoCliente) {
                val valor = item.precio
                val valor2 = valor!!.toFloat()
                suma += valor2
            }
        }

        return suma
    }

    fun calcularTotalDonativos(correoCliente: String?): Double {
        var sumaDonativo = 0.0
        for (item in DataRepository.listaCarrito) {
            if (item.correoCliente == correoCliente) {
                val valor = item.donativo
                val valor2 = valor!!.toFloat()
                sumaDonativo += valor2
            }
        }

        return sumaDonativo
    }

    fun eliminarPrendaCarrito(correoCliente: String?) {
        DataRepository.listaCarrito.removeIf { it.correoCliente == correoCliente }
    }

    fun eliminarPrendaCarrito2(correoCliente: String?, idPrenda: String?) {
        DataRepository.listaCarrito.removeIf { it.correoCliente == correoCliente && it.ide == idPrenda}
    }

    fun comprobarListaVacia(correoCliente: String?): Boolean {
        for (item in DataRepository.listaCarrito) {
            if (item.correoCliente == correoCliente) {
                return false
            }
        }

        return true
    }

    fun comprobarID(id: String): String {
        for (pedido in DataRepository.listaPedidosGeneral) {
            if (id == pedido.idPedido) {
                   val ide = generarID()
                return ide
            }

        }
        return id
    }

    fun generarID(): String {
        val random = java.util.Random()
        val idNumerico = (10000000 + random.nextInt(90000000)).toString()
        return idNumerico
    }

    fun concatenarDireccion(calle: String?, col: String?, noext: String?, cp: String?, muni: String?, est: String?): String {
        val direccionCompleta = calle + ", " + ", " + col + ", " + noext + ", " + cp + ", " + muni + ", " + est
        return direccionCompleta
    }

    fun concatenarNombreCliente(correo: String?): String {
        var nombre = ""
        var apepat = ""
        var apemat = ""

        for (cliente in DataRepository.listaUsuarios) {
            if (correo == cliente.correo) {
                nombre = cliente.nombre
                apepat = cliente.apepat
                apemat = cliente.apemat
            }
        }

        return "$nombre $apepat $apemat"
    }

    fun concatenarNombreVendedor(correo: String?): String {
        var nombre = ""
        var apepat = ""
        var apemat = ""

        for (cliente in DataRepository.listaUsuarios) {
            if (correo == cliente.correo) {
                nombre = cliente.nombre
                apepat = cliente.apepat
                apemat = cliente.apemat
            }
        }

        return "$nombre $apepat $apemat"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun registrarPedidos(idPedido:String, correoCliente: String?, nombreCliente:String, direccionCliente: String, totalPedido: String?, totalDonacion: String?): String {
        val fecha = LocalDateTime.now()
        val fecha2 = fecha.toString()
        val estado = "Pendiente"
        val nuevoPedido = PedidosGeneral(idPedido,fecha2,direccionCliente,estado,correoCliente,nombreCliente,totalPedido,totalDonacion)
        DataRepository.listaPedidosGeneral.add(nuevoPedido)

        return fecha2



    }

    fun prendasPedidos(idPedido:String, idRopa: String?, correoCliente: String?, fechaPedido:String){
        for (ropa in DataRepository.listaRopa) {
            if (idRopa == ropa.ide) {
                val nombrePrenda = ropa.nombrePrenda
                val precioPrenda = ropa.precio
                val talla = ropa.talla
                val categoria = ropa.categoria
                val genero = ropa.genero
                val porcentajePrenda = ropa.porcentaje
                val asociacion = ropa.aso
                val correoVendedor = ropa.correo
                val donativo = ropa.donativo
                val descripcion = ropa.descripcion

                val estado = "Vendido"

                val correoVen = concatenarNombreVendedor(correoVendedor)
                val nuevoPedido = PrendasPedido(idPedido,correoVendedor, correoVen, idRopa,nombrePrenda,talla,categoria,genero,precioPrenda,porcentajePrenda,estado,asociacion)
                DataRepository.listaPrendasPedidos.add(nuevoPedido)

                conDonativos.agregarDonacion(correoCliente, correoVendedor,idPedido,idRopa,donativo,asociacion,fechaPedido)
                println(DataRepository.listaDonativos)
                conRopa.editarPrenda2(idRopa,estado,nombrePrenda,talla,categoria,genero,descripcion,precioPrenda,porcentajePrenda,asociacion,donativo)
            }

        }
    }



    fun guardarPrendas(correoCliente: String?, idPedido: String, fecha: String){
        for (carrito in DataRepository.listaCarrito) {
            if (correoCliente == carrito.correoCliente) {
                val idRopa = carrito.ide
                prendasPedidos(idPedido,idRopa,correoCliente, fecha)
            }

        }
    }

    fun guardarPedidosPorVendedor(correoCliente: String?, idPedido: String, fecha:String, direccion:String, nombreCliente:String){
        for (prendas in DataRepository.listaPrendasPedidos) {
            if (idPedido == prendas.idPedido) {
                val correoVen = prendas.correoVendedor
                val estado = "Pendiente"
                val nuevoPedido = Pedido(idPedido,fecha,direccion,estado,correoCliente,nombreCliente,correoVen,fecha)
                DataRepository.listaPedidos.add(nuevoPedido)
            }

        }
    }

}