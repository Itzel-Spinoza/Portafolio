package com.example.myapplication
import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
data class MIniciarSesion(
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
            val tipo = snapshot.getString("tipo") ?: ""

            return MIniciarSesion(uid, email, contrasena, tipo)
        }
    }

    fun verificar(context: Context, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", email)
            .whereEqualTo("contrasena", contrasena)
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
