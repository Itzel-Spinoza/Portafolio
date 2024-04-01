package com.example.myapplication

import android.content.Context
import android.widget.Toast

class ConDonaciones (private val mDonaciones: MDonativos) {
    fun mostrarDonacionesVendedor(correo: String?, callback: (List<Donativos>?) -> Unit) {

           mDonaciones.obtenerDonacionesVendedor(correo) { donaciones ->
               if (donaciones != null) {
                   val cardlist: List<Donativos>? = donaciones
                   callback(cardlist)
               } else {
                   callback(null)
               }
           }



    }

    fun mostrarDonacionesComprador(correo: String?, callback: (List<Donativos>?) -> Unit) {
        mDonaciones.obtenerDonacionesComprador(correo) { donaciones ->
            if (donaciones != null) {
                val cardlist: List<Donativos>? = donaciones
                callback(cardlist)
            } else {
                callback(null)
                }
            }
    }
    fun verificarTipo(correo: String?, context: Context, callback: (String) -> Unit) {
        mDonaciones.verificarTipoUsuario(correo, context) { resultado, tipoUsuario ->
            if (resultado) {
                // Usuario autenticado correctamente
                val tipo = if (tipoUsuario == "Vendedor") {
                    "Vendedor"
                } else {
                    "Comprador"
                }
                callback(tipo)
            } else {
                // Usuario no encontrado o credenciales incorrectas
                Toast.makeText(
                    context,
                    "Error. Usuario no encontrado o incorrecto.",
                    Toast.LENGTH_SHORT
                ).show()

                // En este caso, podrías devolver un valor predeterminado o manejarlo de otra manera
                callback("Error")
            }
        }
    }


    /*fun comprobarID(id: String): String {
        for (pedido in DataRepository.listaDonativos) {
            if (id == pedido.idDonativo) {
                val ide = generarID()
                return ide
            }

        }
        return id
    }*/

    fun comprobarID(id: String, callback: (String) -> Unit) {
        mDonaciones.comprobarID(id) { nuevoID ->
            // Verificar si se recibió un nuevo ID del callback
            if (nuevoID.isNotEmpty()) {
                // Se llamó al callback con un nuevo ID, lo devolvemos
                callback(nuevoID)
            } else {
                // No se recibió un nuevo ID, generamos uno nuevo
                val nuevoIDGenerado = generarID()
                callback(nuevoIDGenerado)
            }
        }
    }
    fun generarID(): String {
        val random = java.util.Random()
        val idNumerico = (10000000 + random.nextInt(90000000)).toString()
        return idNumerico
    }

    /*fun agregarDonacion(correoCliente: String?, correoVendedor:String, idPedido:String, idPrenda: String?, totalDonativo:String, nombreAso: String?, fechaPedido:String){
        val id = generarID()
        val id2 = comprobarID(id)
        val nuevaDonacion = Donativos(id2, correoCliente, correoVendedor, idPedido,idPrenda,totalDonativo,nombreAso,fechaPedido)
        DataRepository.listaDonativos.add(nuevaDonacion)
        imprimirDonacion()
    }*/
    //lateinit var id2: String
    fun agregarDonacion(context: ConComprar, correoCliente: String?, correoVendedor:String, idPedido:String, idPrenda: String?, totalDonativo:String, nombreAso: String?, fechaPedido:String){

        val id = generarID()
        //var id2 = comprobarID(id)
        comprobarID(id) { nuevoID ->
            val id2 = nuevoID.toString()
            val donativo = Donativos(id2, correoCliente, correoVendedor, idPedido,idPrenda,totalDonativo,nombreAso,fechaPedido)
            mDonaciones.agregarDonacion(context, donativo) { exitoso, mensaje ->
                if (exitoso) {
                    //Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                } else {
                    //Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun buscarPrendaNombre(id: String?, callback: (String?) -> Unit) {
        mDonaciones.obtenerPrendaPorID(id) { nombre ->
            callback(nombre)
        }
    }

    fun imprimirDonacion(){
        for(ropa in DataRepository.listaDonativos){
            println("ID: ${ropa.idDonativo}")
            println("ID pedido: ${ropa.idPedido}")
            println("ID prenda: ${ropa.idPrenda}")
            println("Correo cliente: ${ropa.correoCliente}")
            println("Correo vendedor: ${ropa.correoVendedor}")
            println("Fecha: ${ropa.fechaPedido}")
            println("Asociacion: ${ropa.nombreAsociacion}")
            println("Total donacion: ${ropa.totalDonativo}")
            println("----------------------")
        }
    }
}