package com.example.libreriaelbuho.modelo

import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

class MLibros {

    fun obtenerLibro(callback: (List<Libro>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val libross = firestore.collection("libro")

        libross.get()
            .addOnSuccessListener { querySnapshot ->
                val libro = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(Libro::class.java)
                }
                callback(libro)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                callback(null)
            }
    }

    fun filtrarLibro(genero: String?, callback: (List<Libro>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val libross = firestore.collection("libro")

        libross.whereEqualTo("genero", genero)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val libro = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(Libro::class.java)
                }
                callback(libro)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                callback(emptyList())
            }
    }


    fun comprobarID(id: String, callback: (String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("libro")

        ropaRef.whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { querySnapshot ->

                if (querySnapshot.isEmpty) {
                    callback(id)
                }
            }
    }

    fun agregarLibroCarrito(navController: NavController, libroCarrito: Carrito, ide: String?, correo: String?, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()


        firestore.collection("carrito")
            .whereEqualTo("id", ide)
            .whereEqualTo("correoCliente", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    // La prenda no está en el carrito, así que se puede agregar
                    firestore.collection("carrito")
                        .add(libroCarrito)
                        .addOnSuccessListener {

                            callback(true, "Producto agregado al carrito")
                        }
                        .addOnFailureListener { e ->

                            callback(false, "Error al agregar el producto al carrito")
                            Toast.makeText(navController.context, "Error al agregar prenda al carrito", Toast.LENGTH_SHORT).show()
                            e.printStackTrace()
                        }
                } else {

                    for (document in querySnapshot.documents) {
                        val cantidadActual = document.getLong("cantidad") ?: 0
                        val precio = document.getDouble("precioLibro") ?: 0.0
                        val nuevaCantidad = cantidadActual + 1
                        val nuevoSubtotal = nuevaCantidad * precio

                        val nuevoRopaCarrito = hashMapOf(
                            "cantidad" to nuevaCantidad,
                            "subtotal" to nuevoSubtotal

                        )


                        document.reference.update(nuevoRopaCarrito as Map<String, Any>)
                            .addOnSuccessListener {
                                callback(true, "Cantidad y subtotal actualizados")
                            }
                            .addOnFailureListener { e ->
                                callback(false, "Error al actualizar cantidad y subtotal")
                                Toast.makeText(navController.context, "Error al actualizar cantidad y subtotal", Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }
                    }


                }
            }
            .addOnFailureListener { e ->
                callback(false, "Error al verificar la existencia del producto en el carrito")
                Toast.makeText(navController.context, "Error al verificar la existencia de la prenda en el carrito", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

}