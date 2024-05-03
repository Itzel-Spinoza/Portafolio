package com.example.libreriaelbuho.modelo

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

class MUsuario {
    fun agregarUsuario(context: Context, usuario: Usuario) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("usuario")
            .add(usuario)
            .addOnSuccessListener {
                // Usuario registrado exitosamente
            }
            .addOnFailureListener { e ->
                // Error al registrar usuario en Firestore
                Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun verificarUsuarioRegistrado(
        navController: NavController,
        correo: String,
        callback: (Boolean, String) -> Unit
    ) {
        val firestore = FirebaseFirestore.getInstance()

        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Usuario autenticado correctamente
                    callback(true, "Correo ya registrado")
                } else {
                    // Usuario no encontrado o credenciales incorrectas
                    callback(false, "")
                }
            }
            .addOnFailureListener { _ ->
                Toast.makeText(navController.context, "Error al verificar datos del usuario", Toast.LENGTH_SHORT)
                .show()
                callback(false, "")
            }
    }

    fun obtenerUsuario(correo: String?, callback: (Usuario?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Usuario encontrado
                    val usuario = querySnapshot.documents[0].toObject(Usuario::class.java)
                    callback(usuario)
                } else {
                    // Usuario no encontrado
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(null)
            }
    }


    fun actualizarContrasena(correo: String?, contrasena: String, callback: (Boolean) -> Unit) {
        obtenerUsuario(correo) { usuario ->
            if (usuario != null) {
                // Actualizar los atributos directamente
                val usuarioData = hashMapOf(
                    "contrasena" to contrasena,
                )

                // Invocar al método que actualiza el usuario en tu almacenamiento (Firebase Firestore u otro)
                val firestore = FirebaseFirestore.getInstance()
                val usuariosRef = firestore.collection("usuario")

                usuariosRef.whereEqualTo("correo", correo)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            // Obtener el documento correspondiente al usuario
                            val documentoUsuario = querySnapshot.documents[0]

                            // Actualizar el documento con los nuevos datos
                            documentoUsuario.reference.update(usuarioData as Map<String, Any>)
                                .addOnSuccessListener {
                                    // Llamar al callback con true para indicar que la actualización fue exitosa
                                    callback(true)
                                }
                                .addOnFailureListener { e ->
                                    // Llamar al callback con false si hubo un error
                                    callback(false)
                                    e.printStackTrace()
                                }
                        } else {
                            // Llamar al callback con false si no se pudo encontrar el usuario
                            callback(false)
                        }
                    }
                    .addOnFailureListener { e ->
                        // Llamar al callback con false si hubo un error en la obtención del usuario
                        callback(false)
                        e.printStackTrace()
                    }
            } else {
                // Llamar al callback con false si no se pudo encontrar el usuario
                callback(false)
            }
        }
    }

    fun actualizarPerfil(
        correo: String?,
        nombre: String,
        apellidopat: String,
        apellidomat: String,
        callback: (Boolean) -> Unit
    ) {
        obtenerUsuario(correo) { usuario ->
            if (usuario != null) {
                // Actualizar los atributos directamente
                val usuarioData = hashMapOf(
                    "nombre" to nombre,
                    "apepat" to apellidopat,
                    "apemat" to apellidomat
                )

                // Invocar al método que actualiza el usuario en tu almacenamiento (Firebase Firestore u otro)
                val firestore = FirebaseFirestore.getInstance()
                val usuariosRef = firestore.collection("usuario")

                usuariosRef.whereEqualTo("correo", correo)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            // Obtener el documento correspondiente al usuario
                            val documentoUsuario = querySnapshot.documents[0]

                            // Actualizar el documento con los nuevos datos
                            documentoUsuario.reference.update(usuarioData as Map<String, Any>)
                                .addOnSuccessListener {
                                    // Llamar al callback con true para indicar que la actualización fue exitosa
                                    callback(true)
                                }
                                .addOnFailureListener { e ->
                                    // Llamar al callback con false si hubo un error
                                    callback(false)
                                    e.printStackTrace()
                                }
                        } else {
                            // Llamar al callback con false si no se pudo encontrar el usuario
                            callback(false)
                        }
                    }
                    .addOnFailureListener { e ->
                        // Llamar al callback con false si hubo un error en la obtención del usuario
                        callback(false)
                        e.printStackTrace()
                    }
            } else {
                // Llamar al callback con false si no se pudo encontrar el usuario
                callback(false)
            }
        }
    }
}

