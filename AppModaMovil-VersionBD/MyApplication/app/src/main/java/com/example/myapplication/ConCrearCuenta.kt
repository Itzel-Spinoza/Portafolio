package com.example.myapplication
import android.content.Context
import android.content.Intent
import android.widget.Toast
class ConCrearCuenta(private val mUsuario: MUsuario) {

    fun registrar(context: Context, nombre: String, apepat: String, apemat: String, correo: String, contrasena: String, genero: String, tipoCuenta: String) {

        mUsuario.verificarUsuarioRegistrado(context, correo) { resultado, mensaje ->
            if (resultado) {
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                val nuevoUsuario = Usuario(nombre, apepat, apemat, correo, contrasena, genero, tipoCuenta)
                mUsuario.agregarUsuario(context, nuevoUsuario)

                val intentMensajeCuenta = Intent(context, CuentaCreadaExitosa::class.java)
                context.startActivity(intentMensajeCuenta)
            }
        }
    }
}

