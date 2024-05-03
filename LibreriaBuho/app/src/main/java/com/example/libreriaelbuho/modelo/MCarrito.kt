package com.example.libreriaelbuho.modelo

import com.google.firebase.firestore.FirebaseFirestore

class MCarrito {

    fun obtenerCarrito(callback: (List<Carrito>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val producto = firestore.collection("carrito")

        producto.get()
            .addOnSuccessListener { querySnapshot ->
                val carrito = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(Carrito::class.java)
                }
                callback(carrito)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                callback(null)
            }
    }




}