package com.example.myapplication

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class ConComprar (private val mVenta: MVenta) {

    val mRopa = MRopa()
    val conInventarioRopa = ConInventarioRopa(mRopa)
    val mDonativos = MDonativos()
    val conDonativos = ConDonaciones(mDonativos)
    val mPedidos = MPedidos()
    val conPedidos = ConPedidos(mPedidos)

    fun contarPrendasCarrito(correoCliente: String?, callback: (Int) -> Unit) {
        mVenta.contarPrendasCarrito(correoCliente) { contador ->
            if (contador > 0) {
                callback(contador)
            } else {
                callback(0)
                println("El carrito está vacío.")
            }
        }
    }


    fun eliminarPrendaCarrito(context: Context, correoCliente: String?) {
        //ESTE
        mVenta.eliminarCarrito(context, correoCliente) { exitoso ->
            if (exitoso) {
                // Dirección eliminada exitosamente
                //Toast.makeText(context, "Prenda eliminada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar la dirección o hubo un problema
               // Toast.makeText(context, "Error al eliminar prenda", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun mostrarPrendasCarrito(correo: String?, callback: (List<RopaCarrito>?) -> Unit) {
        mVenta.obtenerRopaCarrito(correo) { ropa ->
            if (ropa != null) {
                // Aquí puedes realizar cualquier procesamiento adicional en la lista si es necesario
                val cardlist: List<RopaCarrito>? = ropa

                // Llamar al callback con la lista creada
                callback(cardlist)
            } else {
                // Llamar al callback con null si no se pudieron obtener direcciones
                callback(null)
            }
        }
    }


    fun calcularTotalCompra(correoCliente: String?, callback: (Double) -> Unit) {
        mVenta.calcularTotalCompra(correoCliente) { total ->
            // Llamar al callback con el resultado obtenido
            callback(total)
        }
    }

    fun calcularTotalDonativos(correoCliente: String?, callback: (Double) -> Unit) {
        mVenta.calcularTotalDonativos(correoCliente) { total ->
            // Llamar al callback con el resultado obtenido
            callback(total)
        }
    }


    fun eliminarPrendaCarrito2(context: Context, correoCliente: String?, idPrenda: String?) {
        mVenta.eliminarPrendaCarrito(context, correoCliente, idPrenda) { exitoso ->
            if (exitoso) {
                // Dirección eliminada exitosamente
                //Toast.makeText(context, "Prenda eliminada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar la dirección o hubo un problema
                //Toast.makeText(context, "Error al eliminar prenda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun comprobarListaVacia(correoCliente: String?, callback: (Boolean) -> Unit) {
        mVenta.comprobarListaVacia(correoCliente) { listaVacia ->
            callback(listaVacia)
        }
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


    /*fun concatenarDireccion(calle: String?, col: String?, noext: String?, cp: String?, muni: String?, est: String?): String {
        val direccionCompleta = calle + ", " + ", " + col + ", " + noext + ", " + cp + ", " + muni + ", " + est
        return direccionCompleta
    }*/

    /*fun concatenarNombreCliente(correo: String?): String {
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
    }*/

    fun concatenarDireccion(correo: String?, calle: String?, callback: (String?) -> Unit) {
        mVenta.concatenarDireccion(correo, calle) { direccion ->
            callback(direccion)
        }
    }

    fun concatenarNombreCliente(correo: String?, callback: (String?) -> Unit) {
        mVenta.concatenarNombreCliente(correo) { nombre ->
            callback(nombre)
        }
    }

    fun concatenarNombreVendedor(correo: String?, callback: (String?) -> Unit) {
        mVenta.concatenarNombreCliente(correo) { nombre ->
            callback(nombre)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun registrarPedidos(
        idPedido: String,
        correoCliente: String?,
        nombreCliente: String,
        direccionCliente: String,
        totalPedido: String?,
        totalDonacion: String?
    ): String {
        val fecha = LocalDateTime.now()
        val fecha2 = fecha.toString()
        val estado = "Pendiente"
        //val nuevoPedido = PedidosGeneral(idPedido,fecha2,direccionCliente,estado,correoCliente,nombreCliente,totalPedido,totalDonacion)
        //DataRepository.listaPedidosGeneral.add(nuevoPedido)

        //return fecha2

        val pedidosGeneral = PedidosGeneral(
            idPedido,
            fecha2,
            direccionCliente,
            estado,
            correoCliente,
            nombreCliente,
            totalPedido,
            totalDonacion
        )
        mPedidos.agregarPedidoGeneral(this, pedidosGeneral) { exitoso, mensaje ->
            if (exitoso) {
                //Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                //Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        }
        return fecha2
    }

    //lateinit var correoVen:String
    //ESTE ES EL METODO QUE SERVIA
    /*fun prendasPedidos(idPedido: String, idRopa: String?, correoCliente: String?, fechaPedido: String) {
        mRopa.obtenerPrendaPorID(idRopa) { listaRopa ->
            if (listaRopa.isNotEmpty()) {
                val ropa = listaRopa[0] // Suponemos que la lista tiene un solo elemento

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

                mVenta.concatenarNombreCliente(correoVendedor) { nombreCliente ->
                    if (nombreCliente != null) {
                        val correoVen = nombreCliente
                        //val nuevoPedido = PrendasPedido(idPedido,correoVendedor, correoVen, idRopa,nombrePrenda,talla,categoria,genero,precioPrenda,porcentajePrenda,estado,asociacion)

                        conPedidos.agregarPrendasPedido(this, idPedido,correoVendedor,correoVen, idRopa,nombrePrenda,talla,categoria,genero,precioPrenda,porcentajePrenda,estado,asociacion)
                        conDonativos.agregarDonacion(this,correoCliente, correoVendedor,idPedido,idRopa,donativo,asociacion,fechaPedido)
                        println(DataRepository.listaDonativos)
                        conInventarioRopa.editarPrenda2(idRopa,estado,nombrePrenda,talla,categoria,genero,descripcion,precioPrenda,porcentajePrenda,asociacion,donativo)
                    } else {

                    }
                }
                //val correoVen = concatenarNombreVendedor(correoVendedor)



                //esto es lo correcto
                //conPedidos.agregarPrendasPedido(this, idPedido,correoVendedor,correoVen, idRopa,nombrePrenda,talla,categoria,genero,precioPrenda,porcentajePrenda,estado,asociacion)
                //conDonativos.agregarDonacion(this,correoCliente, correoVendedor,idPedido,idRopa,donativo,asociacion,fechaPedido)
                //println(DataRepository.listaDonativos)
                //conInventarioRopa.editarPrenda2(idRopa,estado,nombrePrenda,talla,categoria,genero,descripcion,precioPrenda,porcentajePrenda,asociacion,donativo)

            } else {
                // Manejar el caso en que no se encuentre la prenda con el ID especificado
            }
        }
    }*/

    fun prendasPedidos(idPedido: String, idRopa: String?, correoCliente: String?, fechaPedido: String) {
        mRopa.obtenerPrendaPorID(idRopa) { listaRopa ->
            for (ropa in listaRopa) {
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

                mVenta.concatenarNombreCliente(correoVendedor) { nombreCliente ->
                    if (nombreCliente != null) {
                        val correoVen = nombreCliente


                        conPedidos.agregarPrendasPedido(
                            this,
                            idPedido,
                            correoVendedor,
                            correoVen,
                            idRopa,
                            nombrePrenda,
                            talla,
                            categoria,
                            genero,
                            precioPrenda,
                            porcentajePrenda,
                            estado,
                            asociacion
                        )
                        conDonativos.agregarDonacion(
                            this,
                            correoCliente,
                            correoVendedor,
                            idPedido,
                            idRopa,
                            donativo,
                            asociacion,
                            fechaPedido
                        )
                        println(DataRepository.listaDonativos)
                        conInventarioRopa.editarPrenda2(
                            idRopa,
                            estado,
                            nombrePrenda,
                            talla,
                            categoria,
                            genero,
                            descripcion,
                            precioPrenda,
                            porcentajePrenda,
                            asociacion,
                            donativo
                        )
                    } else {

                    }
                }
            }
        }
    }


    fun guardarPrendas(correoCliente: String?, idPedido: String, fecha: String, nombreCliente: String, direccion: String) {
       val estado = "Pendiente"
        mostrarPrendasCarrito(correoCliente) { listaRopa ->
            if (listaRopa != null) {
                if (listaRopa.isNotEmpty()) {
                    for (ropa in listaRopa) {
                        val idRopa = ropa.ide
                        val correoVendedor = ropa.correoVendedor
                        val nuevoPedido = Pedido(idPedido,fecha,direccion, estado,correoCliente, nombreCliente, correoVendedor, fecha)
                        //mPedidos.agregarPedido(this,nuevoPedido)
                    }
                } else {
                    // Manejar el caso de listaRopa vacía
                }
            }
        }
    }

    /* fun guardarPedidosPorVendedor(
         correoCliente: String?,
         idPedido: String,
         fecha: String,
         direccion: String,
         nombreCliente: String
     ) {
         mPedidos.obtenerPrendasPedido(idPedido) { listaRopa ->
             if (listaRopa != null) {
                 if (listaRopa.isNotEmpty()) {
                     val ropa = listaRopa[0] // Suponemos que la lista tiene un solo elemento

                     val correoVen = ropa.correoVendedor
                     val estado = "Pendiente"
                     val nuevoPedido = Pedido(
                         idPedido,
                         fecha,
                         direccion,
                         estado,
                         correoCliente,
                         nombreCliente,
                         correoVen,
                         fecha
                     )

                     mPedidos.agregarPedido(this, nuevoPedido) { exitoso, mensaje ->
                         if (exitoso) {
                             //Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                         } else {
                             //Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                         }
                     }


                 } else {

                 }
             }
         }
     }*/

    fun guardarPedidosPorVendedor(
        correoCliente: String?,
        idPedido: String,
        fecha: String,
        direccion: String,
        nombreCliente: String
    ) {
        mPedidos.obtenerPrendasPedido(idPedido) { listaRopa ->
            if (listaRopa != null && listaRopa.isNotEmpty()) {
                for (ropa in listaRopa) {
                    val correoVen = ropa.correoVendedor
                    val estado = "Pendiente"
                    val nuevoPedido = Pedido(
                        idPedido,
                        fecha,
                        direccion,
                        estado,
                        correoCliente,
                        nombreCliente,
                        correoVen,
                        fecha
                    )

                    mPedidos.agregarPedido(this, nuevoPedido) { exitoso, mensaje ->
                        if (exitoso) {
                            // Manejar el caso de agregar el pedido exitosamente
                            // Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                        } else {
                            // Manejar el caso de error al agregar el pedido
                            // Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                // Manejar el caso de listaRopa vacía
            }
        }
    }



}

