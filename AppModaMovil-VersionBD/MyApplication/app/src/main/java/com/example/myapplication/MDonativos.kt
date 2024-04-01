package com.example.myapplication

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MDonativos {
    fun agregarDonacion(context: ConComprar, donativos: Donativos, callback: (Boolean, String) -> Unit){
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("donativos")
            .add(donativos)
            .addOnSuccessListener {
                // Dirección agregada exitosamente
                callback(true, "Donacion realizada.")
            }
            .addOnFailureListener { e ->
                // Error al registrar dirección en Firestore
                callback(false, "Error al agregar donacion")
                //Toast.makeText(context, "Error al registrar donacion", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }
    fun comprobarID(id: String, callback: (String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("donativos")

        ropaRef.whereEqualTo("idDonativo", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                // Si no hay documentos con el mismo ID, llamamos al callback
                if (querySnapshot.isEmpty) {
                    callback(id)
                } else {
                    // Si hay documentos con el mismo ID, podrías realizar alguna acción
                    // o simplemente no hacer nada, ya que en este caso no necesitas llamar al callback.
                }
            }
    }


    fun obtenerPrendaPorID(id: String?, callback: (String?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val usuariosRef = firestore.collection("ropa")

        usuariosRef.whereEqualTo("ide", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Se encontró un usuario con el correo proporcionado
                    val usuario = querySnapshot.documents[0].toObject(Ropa::class.java)
                    val nombrePrenda = "${usuario?.nombrePrenda}"
                    callback(nombrePrenda)
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

    fun obtenerDonacionesVendedor(correo: String?, callback: (List<Donativos>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val donativosRef = firestore.collection("donativos")

        val query = donativosRef.whereEqualTo("correoVendedor", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val donativos = querySnapshot.documents.map { doc ->
                    doc.toObject(Donativos::class.java)
                }.filterNotNull()
                callback(donativos)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(null)
            }
    }

    fun obtenerDonacionesComprador(correo: String?, callback: (List<Donativos>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val donativosRef = firestore.collection("donativos")

        val query = donativosRef.whereEqualTo("correoCliente", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val donativos = querySnapshot.documents.map { doc ->
                    doc.toObject(Donativos::class.java)
                }.filterNotNull()
                callback(donativos)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(null)
            }
    }
    fun verificarTipoUsuario(correo: String?, context: Context, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Usuario autenticado correctamente
                    val tipoUsuario = querySnapshot.documents[0].getString("tipo") ?: ""
                    callback(true, tipoUsuario)
                } else {
                    // Usuario no encontrado o credenciales incorrectas
                    callback(false, "")
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error al verificar datos del usuario", Toast.LENGTH_SHORT).show()
                callback(false, "")
            }
    }



}