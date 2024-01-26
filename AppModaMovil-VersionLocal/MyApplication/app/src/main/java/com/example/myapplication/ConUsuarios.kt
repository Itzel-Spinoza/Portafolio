package com.example.myapplication

class ConUsuarios (private val dataRepository: DataRepository) {
    fun mostrarUsuario(correo: String?): Usuario? {

        for (usuario in DataRepository.listaUsuarios) {
            if (correo == usuario.correo) {
                val nombre = usuario.nombre
                val apellidopat = usuario.apepat
                val apellidomat = usuario.apemat
                val contra = usuario.contrasena
                val genero = usuario.genero
                val tipo = usuario.tipo

                return Usuario(nombre, apellidopat, apellidomat, correo, contra, genero, tipo)
            }

        }
        return null
    }

    fun actualizarPerfil(correo: String?, nombre: String, apellidopat: String, apellidomat: String): Boolean {
        val usuario = DataRepository.listaUsuarios.find { it.correo == correo }

        if(usuario != null){
            usuario.nombre = nombre
            usuario.apepat = apellidopat
            usuario.apemat = apellidomat
            return true
        }

        return false
    }

    fun actualizarContra(correo: String?, contra: String): Boolean{
        val usuario = DataRepository.listaUsuarios.find { it.correo == correo }
        if(usuario != null){
            usuario.contrasena = contra
            return true
        }

        return false
    }

    fun direccionesUsuario(correo: String?): MutableList<Direccion> {

        val cardList = DataRepository.listaDirecciones.filter { it.correo == correo }.toMutableList()


        return cardList
    }

    fun actualizarDireccion(correo: String?, calle: String, col: String, noext: String, cp: String, municipio: String, estado:String): Boolean {
        val direccion = DataRepository.listaDirecciones.find { it.correo == correo }

        if(direccion != null){
            direccion.calle = calle
            direccion.colonia = col
            direccion.noext = noext
            direccion.cp = cp
            direccion.municipio = municipio
            direccion.estado = estado

            return true
        }

        return false
    }

    fun eliminarDireccion(correo: String?, calle: String) {
        DataRepository.listaDirecciones.removeIf { it.correo == correo && it.calle == calle }
    }
    fun metodosPagosUsuarios(correo: String?): MutableList<MetodosPago>{
        val cardList = DataRepository.listaMetodosPagos.filter { it.correo == correo }.toMutableList()


        return cardList
    }


    fun actualizarMetodoPago(correo: String?, tipoTarjeta: String, noTarjeta: String, titularTarjeta: String, fechaExpedicion: String, ccv: String): Boolean {
        val metoPago = DataRepository.listaMetodosPagos.find { it.correo == correo }

        if(metoPago != null){
            metoPago.tipoTarjeta = tipoTarjeta
            metoPago.noTarjeta = noTarjeta
            metoPago.titularTarjeta = titularTarjeta
            metoPago.fechaExpedicion = fechaExpedicion
            metoPago.ccv = ccv
            return true
        }

        return false
    }

    fun agregarDirec(correo: String?, calle: String, col: String, noext: String, cp: String, municipio: String, estado:String): Direccion {
        //val cardList = DataRepository.listaDirecciones
        val direccion = Direccion(correo, calle, col, noext, cp, municipio, estado)
        DataRepository.listaDirecciones.add(direccion)

        return direccion
    }

    fun agregarMetPago(correo: String?, tipoCuenta: String, noTarj: String, titular: String, fecha:String, ccv:String): MetodosPago{
        val metoPago = MetodosPago(correo,tipoCuenta,noTarj,titular,fecha,ccv)
        DataRepository.listaMetodosPagos.add(metoPago)
        return metoPago
    }

    fun eliminarMetPago(correo: String?, noTarj: String) {
        DataRepository.listaMetodosPagos.removeIf { it.correo == correo && it.noTarjeta == noTarj }
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