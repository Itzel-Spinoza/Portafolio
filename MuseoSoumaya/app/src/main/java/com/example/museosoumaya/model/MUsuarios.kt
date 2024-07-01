package com.example.museosoumaya.model


import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MUsuarios {
    suspend fun registrarUsuario(
        navController: NavController,
        correo: String,
        contra: String
    ): Boolean {
        val auth = FirebaseAuth.getInstance()
        return try {
            val authResult = auth.createUserWithEmailAndPassword(correo, contra).await()
            true
        } catch (e: FirebaseAuthException) {
            when (e.errorCode) {
                "auth/email-already-in-use" -> {
                    showUIToast(navController.context, "El correo electrónico ya está en uso")
                }
                "auth/weak-password" -> {
                    showUIToast(navController.context, "La contraseña no cumple con los requisitos mínimos")
                }
                else -> {
                    showUIToast(navController.context, "Error al registrar usuario: ${e.errorCode}")
                }
            }
            false
        } catch (e: Exception) {
            showUIToast(navController.context, "Error al registrar usuario")
            false
        }
    }

     fun showUIToast(context: Context, message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }



    suspend fun autenticarUsuario(navController: NavController,correo: String, contra: String): Boolean {
        val auth = FirebaseAuth.getInstance()
        return try {
            val authResult = auth.signInWithEmailAndPassword(correo, contra).await()
            true
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()
            when (e.errorCode) {
                "auth/user-not-found" -> {
                    showUIToast(navController.context, "Usuario no encontrado")
                }
                "auth/wrong-password" -> {
                    showUIToast(navController.context, "Contraseña incorrecta")
                }
                else -> {
                    showUIToast(navController.context, "Error al autenticar usuario: ${e.errorCode}")
                }
            }
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun agregarUsuario(context: Context, usuario: Usuario) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("usuario")
            .add(usuario)
            .addOnSuccessListener {
                // Usuario registrado exitosamente
                Toast.makeText(context, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                // Error al registrar usuario en Firestore
                Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun verificarUsuarioRegistrado(correo: String, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Usuario ya registrado
                    callback(true, "Correo ya registrado")
                } else {
                    // Usuario no registrado
                    callback(false, "")
                }
            }
            .addOnFailureListener { exception ->
                // Error al verificar usuario
                exception.printStackTrace()
                callback(false, "Error al verificar datos del usuario")
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

    fun verificarNombreUsuario(navController: NavController, correo: String?, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Usuario autenticado correctamente
                    val nombre = querySnapshot.documents[0].getString("nombre") ?: ""
                    callback(true, nombre)
                } else {
                    // Usuario no encontrado o credenciales incorrectas
                    callback(false, "")
                }
            }
            .addOnFailureListener { e ->

            }
    }

    fun actualizarNombreUsuario(correo: String?, nombre: String, apepat: String, apemat: String, callback: (Boolean) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Obtener el ID del documento del usuario
                    val usuarioId = querySnapshot.documents[0].id

                    // Crear un mapa con los datos actualizados
                    val datosActualizados = hashMapOf(
                        "nombre" to nombre,
                        "apepat" to apepat,
                        "apemat" to apemat
                    )

                    // Actualizar los datos del usuario en la base de datos
                    usuariosRef.document(usuarioId)
                        .update(datosActualizados as Map<String, Any>)
                        .addOnSuccessListener {
                            callback(true) // Llamar al callback con true si la actualización fue exitosa
                        }
                        .addOnFailureListener { e ->
                            callback(false) // Llamar al callback con false si hubo un error en la actualización
                            e.printStackTrace()
                        }
                } else {
                    callback(false) // Llamar al callback con false si no se pudo encontrar el usuario
                }
            }
            .addOnFailureListener { e ->
                callback(false) // Llamar al callback con false si hubo un error en la obtención del usuario
                e.printStackTrace()
            }
    }


}


