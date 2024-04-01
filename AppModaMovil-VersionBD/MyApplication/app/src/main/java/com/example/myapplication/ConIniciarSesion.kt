package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
class ConIniciarSesion {

    private val firestore = FirebaseFirestore.getInstance()

    fun iniciarSesion(context: Context, correo: String, contrasena: String) {
        val modelo = MIniciarSesion(null, correo, contrasena, "")

        modelo.verificar(context) { resultado, tipoUsuario ->
            if (resultado) {
                // Usuario autenticado correctamente
                val intentInicio = if (tipoUsuario == "Vendedor") {
                    Intent(context, InicioVendedor::class.java)
                } else {
                    Intent(context, Inicio::class.java)
                }
                intentInicio.putExtra("email", correo)
                context.startActivity(intentInicio)
                Toast.makeText(context, "Usuario autentificado.", Toast.LENGTH_SHORT).show()
            } else {
                // Usuario no encontrado o credenciales incorrectas
                Toast.makeText(
                    context,
                    "Error. Usuario no encontrado o incorrecto.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun verificarTipo(context: Context, correo: String, contrasena: String) {
        val modelo = MIniciarSesion(null, correo, contrasena, "")

        modelo.verificar(context) { resultado, tipoUsuario ->
            if (resultado) {
                // Usuario autenticado correctamente
                val intentInicio = if (tipoUsuario == "Vendedor") {
                    Intent(context, InicioVendedor::class.java)
                } else {
                    Intent(context, Inicio::class.java)
                }
                intentInicio.putExtra("email", correo)
                context.startActivity(intentInicio)
                Toast.makeText(context, "Usuario autentificado.", Toast.LENGTH_SHORT).show()
            } else {
                // Usuario no encontrado o credenciales incorrectas
                Toast.makeText(
                    context,
                    "Error. Usuario no encontrado o incorrecto.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}













