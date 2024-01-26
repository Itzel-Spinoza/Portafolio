package com.example.myapplication
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ConCrearCuenta (private val dataRepository: DataRepository) {

    fun registrar(context: Context, nombre: String, apepat: String, apemat: String, correo: String, contrasena: String, genero: String, tipoCuenta : String) {

        if(verificarUsuarioRegistrado(correo)){
            val nuevoUsuario = Usuario(nombre, apepat, apemat, correo, contrasena, genero, tipoCuenta)
            DataRepository.listaUsuarios.add(nuevoUsuario)
            imprimirUsuarios()

            val intentMensajeCuenta = Intent(context, CuentaCreadaExitosa::class.java)
            context.startActivity(intentMensajeCuenta)
        }else{
            Toast.makeText(context,"Correo ya registado", Toast.LENGTH_SHORT).show()
        }
    }

    fun imprimirUsuarios() {
        for (usuario in DataRepository.listaUsuarios) {
            println("Nombre: ${usuario.nombre}")
            println("Apellido Paterno: ${usuario.apepat}")
            println("Apellido Materno: ${usuario.apemat}")
            println("Correo: ${usuario.correo}")
            println("Contraseña: ${usuario.contrasena}")
            println("Genero: ${usuario.genero}")
            println("Tipo cuenta: ${usuario.tipo}")
            println("----------------------")
        }
    }

    fun verificarUsuarioRegistrado(correo: String): Boolean{
        for(usuario in DataRepository.listaUsuarios){
            if(correo == usuario.correo ){
                return false
            }
        }
        return true
    }

}