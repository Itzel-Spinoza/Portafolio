package com.example.libreriaelbuho.controlador

import android.widget.Toast
import androidx.navigation.NavController
import com.example.libreriaelbuho.modelo.MUsuario
import com.example.libreriaelbuho.modelo.Usuario


class Registrar (private val mUsuario: MUsuario) {
    fun registrar(navController: NavController, nombre: String, apepat: String, apemat: String, correo: String, contrasena: String, tipoCuenta: String) {

        mUsuario.verificarUsuarioRegistrado(navController, correo) { resultado, mensaje ->
            if (resultado) {
                Toast.makeText(navController.context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                val nuevoUsuario = Usuario(nombre, apepat, apemat, correo, contrasena, tipoCuenta)
                mUsuario.agregarUsuario(navController.context, nuevoUsuario)
                Toast.makeText(navController.context, "Usuario registrado correctamente.", Toast.LENGTH_SHORT).show()

            }
        }
    }
}