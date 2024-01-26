package com.example.myapplication

import android.content.Context
import android.content.Intent
import com.example.myapplication.ConCrearCuenta
import android.widget.Toast

class ConIniciarSesion (private val dataRepository: DataRepository) {
    var usuarioEncontrado = false


    fun ingresar(context: Context, correo: String, contrasena: String) {
        var usuarioEncontrado = false

        for (usuario in DataRepository.listaUsuarios) {
            if (correo == usuario.correo && contrasena == usuario.contrasena) {
                usuarioEncontrado = true
                if (usuario.tipo == "Vendedor") {
                    Toast.makeText(context, "Usuario autentificado.", Toast.LENGTH_SHORT).show()
                    val intentInicio = Intent(context, InicioVendedor::class.java)
                    intentInicio.putExtra("email", correo)
                    context.startActivity(intentInicio)

                } else if (usuario.tipo == "Comprador") {
                    Toast.makeText(context, "Usuario autentificado.", Toast.LENGTH_SHORT).show()
                    val intentInicio = Intent(context, Inicio::class.java)
                    intentInicio.putExtra("email", correo)
                    context.startActivity(intentInicio)

                    break
                }
            }

            if (!usuarioEncontrado) {
                Toast.makeText(
                    context,
                    "Error. Usuario no encontrado o incorrecto.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}








