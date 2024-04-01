package com.example.myapplication

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MVenta {
    fun obtenerRopaCarrito(correo: String?, callback: (List<RopaCarrito>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("carrito")

        ropaRef.whereEqualTo("correoCliente", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val ropa = querySnapshot.documents.map { doc ->
                    doc.toObject(RopaCarrito::class.java)
                }.filterNotNull()
                callback(ropa)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }
    fun eliminarPrendaCarrito(context: Context, correo: String?, id: String?, callback: (Boolean) -> Unit) {

        val firestore = FirebaseFirestore.getInstance()
        val direccionesRef = firestore.collection("carrito")

        direccionesRef.whereEqualTo("ide", id)
            .whereEqualTo("correoCliente", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Obtener el documento correspondiente a la dirección
                    val documentoDireccion = querySnapshot.documents[0]

                    // Eliminar el documento
                    documentoDireccion.reference.delete()
                        .addOnSuccessListener {
                            // Dirección eliminada exitosamente en Firestore
                            callback(true)
                        }
                        .addOnFailureListener { e ->
                            // Error al eliminar dirección en Firestore
                            Toast.makeText(context, "Error al eliminar prenda del carrito", Toast.LENGTH_SHORT)
                                .show()
                            e.printStackTrace()
                            callback(false)
                        }
                } else {
                    // Llamar al callback con false si no se pudo encontrar la dirección
                    callback(false)
                }
            }
            .addOnFailureListener { e ->
                // Llamar al callback con false si hubo un error en la obtención de la dirección
                callback(false)
                e.printStackTrace()
            }
    }
    fun eliminarCarrito(context: Context, correo: String?, callback: (Boolean) -> Unit) {

        val firestore = FirebaseFirestore.getInstance()
        val direccionesRef = firestore.collection("carrito")

        direccionesRef.whereEqualTo("correoCliente", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Obtener el documento correspondiente a la dirección
                    val documentoDireccion = querySnapshot.documents[0]

                    // Eliminar el documento
                    documentoDireccion.reference.delete()
                        .addOnSuccessListener {
                            // Dirección eliminada exitosamente en Firestore
                            callback(true)
                        }
                        .addOnFailureListener { e ->
                            // Error al eliminar dirección en Firestore
                            Toast.makeText(context, "Error al eliminar prenda del carrito", Toast.LENGTH_SHORT)
                                .show()
                            e.printStackTrace()
                            callback(false)
                        }
                } else {
                    // Llamar al callback con false si no se pudo encontrar la dirección
                    callback(false)
                }
            }
            .addOnFailureListener { e ->
                // Llamar al callback con false si hubo un error en la obtención de la dirección
                callback(false)
                e.printStackTrace()
            }
    }

    fun calcularTotalCompra(correoCliente: String?, callback: (Double) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val carritoRef = firestore.collection("carrito")

        carritoRef.whereEqualTo("correoCliente", correoCliente)
            .get()
            .addOnSuccessListener { querySnapshot ->
                var suma = 0.0

                for (document in querySnapshot) {
                    val precio = document.getString("precio")
                    if (precio != null) {
                        suma += precio.toDoubleOrNull() ?: 0.0
                    }
                }

                callback(suma)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(0.0)
            }
    }

    fun calcularTotalDonativos(correoCliente: String?, callback: (Double) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val carritoRef = firestore.collection("carrito")

        carritoRef.whereEqualTo("correoCliente", correoCliente)
            .get()
            .addOnSuccessListener { querySnapshot ->
                var suma = 0.0

                for (document in querySnapshot) {
                    val precio = document.getString("donativo")
                    if (precio != null) {
                        suma += precio.toDoubleOrNull() ?: 0.0
                    }
                }

                callback(suma)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(0.0)
            }
    }
    fun obtenerRopaCarrito2(correo: String?, callback: (List<RopaCarrito>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("carrito")

        ropaRef.whereEqualTo("correoCliente", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val ropa = querySnapshot.documents.map { doc ->
                    doc.toObject(RopaCarrito::class.java)
                }.filterNotNull()
                callback(ropa)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(null)
            }
    }

    fun contarPrendasCarrito(correoCliente: String?, callback: (Int) -> Unit) {
        // Obtener la lista del carrito para un cliente específico
        obtenerRopaCarrito(correoCliente) { carrito ->
            if (carrito != null) {
                // Contar las prendas en el carrito
                val contador = carrito.size

                // Llamar al callback con el resultado del contador
                callback(contador)
            } else {
                // Manejar el caso de error obteniendo el carrito
                callback(0)
            }
        }
    }

    fun comprobarListaVacia(correoCliente: String?, callback: (Boolean) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val carritoRef = firestore.collection("carrito")

        carritoRef.whereEqualTo("correoCliente", correoCliente)
            .get()
            .addOnSuccessListener { querySnapshot ->
                // Si se encuentra al menos un documento, la lista no está vacía
                callback(!querySnapshot.isEmpty)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                // En caso de error, considerar que la lista está vacía
                callback(true)
            }
    }

    fun concatenarDireccion(correo: String?, calle: String?, callback: (String?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val usuariosRef = firestore.collection("direccion")

        usuariosRef.whereEqualTo("correo", correo)
            .whereEqualTo("calle", calle)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Se encontró un usuario con el correo proporcionado
                    val usuario = querySnapshot.documents[0].toObject(Direccion::class.java)
                    val nombreCompleto = "${usuario?.calle} ${usuario?.colonia} ${usuario?.noext} ${usuario?.cp} ${usuario?.municipio} ${usuario?.estado}"
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
}