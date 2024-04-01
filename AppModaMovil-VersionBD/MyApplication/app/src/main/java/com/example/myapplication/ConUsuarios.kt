package com.example.myapplication

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class ConUsuarios (private val mUsuario: MUsuario) {
    private val firestore = FirebaseFirestore.getInstance()
    fun obtenerUsuario(correo: String?, callback: (Usuario?) -> Unit) {
        mUsuario.obtenerUsuario(correo) { usuario ->
            if (usuario != null) {
                // Usuario encontrado
                callback(usuario)
            } else {
                // Usuario no encontrado
                callback(null)
            }
        }
    }

    fun actualizarPerfil(context: Context, correo: String?, nombre: String, apellidopat: String, apellidomat: String) {
        mUsuario.actualizarPerfil(correo, nombre, apellidopat, apellidomat) { exitoso ->
            if (exitoso) {
                // Perfil actualizado exitosamente
                Toast.makeText(context, "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar el usuario o hubo un problema
                Toast.makeText(context, "Error al actualizar perfil", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun actualizarContra(context: Context,correo: String?, contra: String) {
        mUsuario.actualizarContrasena(correo, contra) { exitoso ->
            if (exitoso) {
                // Perfil actualizado exitosamente
                Toast.makeText(context, "Contraseña actualizada", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar el usuario o hubo un problema
                Toast.makeText(context, "Error al actualizar contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun direccionesUsuario(correo: String?, callback: (List<Direccion>?) -> Unit) {
        mUsuario.obtenerDirecciones(correo) { direcciones ->
            if (direcciones != null) {
                // Aquí puedes realizar cualquier procesamiento adicional en la lista si es necesario
                val cardlist: List<Direccion> = direcciones

                // Llamar al callback con la lista creada
                callback(cardlist)
            } else {
                // Llamar al callback con null si no se pudieron obtener direcciones
                callback(null)
            }
        }
    }


    fun agregarDirec(
        context: Context,
        correo: String?,
        calle: String,
        col: String,
        noext: String,
        cp: String,
        municipio: String,
        estado: String
    ) {
        val direccion = Direccion(correo, calle, col, noext, cp, municipio, estado)
        mUsuario.agregarDireccion(context, direccion) { exitoso, mensaje ->
            if (exitoso) {
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        }

    }




    fun actualizarDireccion(context: Context, correo: String?, calle: String, col: String, noext: String, cp: String, municipio: String, estado: String) {
        mUsuario.actualizarDireccion(correo, calle, col, noext, cp, municipio, estado) { exitoso ->
            if (exitoso) {
                // Perfil actualizado exitosamente
                Toast.makeText(context, "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar el usuario o hubo un problema
                Toast.makeText(context, "Error al actualizar perfil", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun eliminarDireccion(context: Context, correo: String?, calle: String) {
        mUsuario.eliminarDireccion(context,correo, calle) { exitoso ->
            if (exitoso) {
                // Dirección eliminada exitosamente
                Toast.makeText(context, "Dirección eliminada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar la dirección o hubo un problema
                Toast.makeText(context, "Error al eliminar dirección", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun metodosPagosUsuarios(correo: String?, callback: (List<MetodosPago>?) -> Unit) {
        mUsuario.obtenerMetodosPago(correo) { metodosPago ->
            if (metodosPago != null) {
                // Aquí puedes realizar cualquier procesamiento adicional en la lista si es necesario
                val cardlist: List<MetodosPago> = metodosPago

                // Llamar al callback con la lista creada
                callback(cardlist)
            } else {
                // Llamar al callback con null si no se pudieron obtener direcciones
                callback(null)
            }
        }
    }


    fun actualizarMetodoPago(context: Context, correo: String?, tipoTarjeta: String, noTarjeta: String, titularTarjeta: String, fechaExpedicion: String, ccv: String){

        mUsuario.actualizarMetodoPago(correo, tipoTarjeta, noTarjeta, titularTarjeta, fechaExpedicion, ccv) { exitoso ->
            if (exitoso) {
                // Perfil actualizado exitosamente
                Toast.makeText(context, "Tarjeta actualizada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar el usuario o hubo un problema
                Toast.makeText(context, "Error al actualizar tarjeta", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun agregarMetPago(context: Context,correo: String?, tipoCuenta: String, noTarj: String, titular: String, fecha:String, ccv:String) {

        val metodo = MetodosPago(correo, tipoCuenta, noTarj, titular, fecha, ccv)
        mUsuario.agregarMetodoPago(context, metodo) { exitoso, mensaje ->
            if (exitoso) {
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun eliminarMetPago(context: Context,correo: String?, noTarj: String) {
        mUsuario.eliminarMetodoPago(context,correo, noTarj) { exitoso ->
            if (exitoso) {
                // Dirección eliminada exitosamente
                Toast.makeText(context, "Dirección eliminada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar la dirección o hubo un problema
                Toast.makeText(context, "Error al eliminar dirección", Toast.LENGTH_SHORT).show()
            }
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

    fun imprimirDirecciones(){
        for(direccion in DataRepository.listaDirecciones){
            println("Correo: ${direccion.correo}")
            println("Calle: ${direccion.calle}")
            println("Colonia: ${direccion.colonia}")
            println("No. ext: ${direccion.noext}")
            println("CP: ${direccion.cp}")
            println("Municipio: ${direccion.municipio}")
            println("Estado: ${direccion.estado}")

            println("----------------------")
        }
    }

    fun imprimirMetodosPago(){
        for(metodo in DataRepository.listaMetodosPagos){
            println("Correo: ${metodo.correo}")
            println("No tarjeta: ${metodo.noTarjeta}")
            println("Titular: ${metodo.titularTarjeta}")
            println("Fecha expiraación: ${metodo.fechaExpedicion}")
            println("CCV: ${metodo.ccv}")
        }
    }
}