package com.example.museosoumaya.model

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MPedidos {
    fun agregarPedido(context: Context, pedidosBoletos: PedidosBoletos) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("pedidosBoletos")
            .add(pedidosBoletos)
            .addOnSuccessListener {
                // Usuario registrado exitosamente
            }
            .addOnFailureListener { e ->
                // Error al registrar usuario en Firestore
                Toast.makeText(context, "Error al registrar pedido", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun agregarPedidoGeneral(context: Context, pedidosGeneral: PedidosGeneral) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("pedidosGeneral")
            .add(pedidosGeneral)
            .addOnSuccessListener {
                // Usuario registrado exitosamente
            }
            .addOnFailureListener { e ->
                // Error al registrar usuario en Firestore
                Toast.makeText(context, "Error al registrar pedido general", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun agregarTarjeta(context: Context, metodosPago: MetodosPago) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("metodosPago")
            .add(metodosPago)
            .addOnSuccessListener {
                // Usuario registrado exitosamente
            }
            .addOnFailureListener { e ->
                // Error al registrar usuario en Firestore
                Toast.makeText(context, "Error al registrar tarjeta", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun obtenerMetodos(correo: String?, callback: (List<MetodosPago>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val metodo = firestore.collection("metodosPago")

        metodo.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val ropa = querySnapshot.documents.map { doc ->
                    doc.toObject(MetodosPago::class.java)
                }.filterNotNull()
                callback(ropa)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }

    fun concatenarNombreCliente(correo: String?, callback: (String?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Se encontró un usuario con el correo proporcionado
                    val usuario = querySnapshot.documents[0].toObject(Usuario::class.java)
                    val nombreCompleto = "${usuario?.nombre} ${usuario?.apepat} ${usuario?.apemat}"
                    callback(nombreCompleto)
                } else {
                    // No se encontró ningún usuario con el correo proporcionado
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(null)
            }
    }

    fun obtenerPedidosGeneral(correo: String?, callback: (List<PedidosGeneral>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val metodo = firestore.collection("pedidosGeneral")

        metodo.whereEqualTo("correoCliente", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val ropa = querySnapshot.documents.map { doc ->
                    doc.toObject(PedidosGeneral::class.java)
                }.filterNotNull()
                callback(ropa)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }


    fun obtenerCantidadBoletos(folio: String?, tipoBoleto: String?,callback: (String?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val pedidosBol = firestore.collection("pedidosBoletos")

        pedidosBol.whereEqualTo("folio", folio)
            .whereEqualTo("tipoBoleto", tipoBoleto)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Se encontró un usuario con el correo proporcionado
                    val pedidosBol = querySnapshot.documents[0].toObject(PedidosBoletos::class.java)
                    val cantidad = "${pedidosBol?.cantidad}"
                    callback(cantidad)
                } else {
                    // No se encontró ningún usuario con el correo proporcionado
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(null)
            }
    }

}