package com.example.libreriaelbuho.modelo

import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class MIniciarSesion (
    val uid: String?,
    val email: String,
    val contrasena: String,
    val tipo: String
) {

    companion object {

        fun fromFirebase(snapshot: QueryDocumentSnapshot): MIniciarSesion {
            val uid = snapshot.id
            val email = snapshot.getString("correo") ?: ""
            val contrasena = snapshot.getString("contrasena") ?: ""
            val tipo = snapshot.getString("tipoUsuario") ?: ""

            return MIniciarSesion(uid, email, contrasena, tipo)
        }
    }

    fun verificar(navController: NavController, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", email)
            .whereEqualTo("contrasena", contrasena)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Usuario autenticado correctamente
                    val tipoUsuario = querySnapshot.documents[0].getString("tipoUsuario") ?: ""
                    callback(true, tipoUsuario)
                } else {
                    // Usuario no encontrado o credenciales incorrectas
                    callback(false, "")
                }
            }
            .addOnFailureListener { e ->

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

}