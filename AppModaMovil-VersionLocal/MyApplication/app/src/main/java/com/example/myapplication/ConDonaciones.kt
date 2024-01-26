package com.example.myapplication

class ConDonaciones (private val dataRepository: DataRepository) {
    fun mostrarDonacionesDepCorreo(correo: String?): MutableList<Donativos> {
        if (tipoUsuario(correo)== true){
            val cardList = DataRepository.listaDonativos.filter { it.correoVendedor == correo }.toMutableList()
            return cardList
        }else{
            val cardList = DataRepository.listaDonativos.filter { it.correoCliente == correo }.toMutableList()
            return cardList
        }

    }

    fun tipoUsuario(correo: String?): Boolean {
        for (usuario in DataRepository.listaUsuarios) {
            if (correo == usuario.correo && usuario.tipo == "Vendedor") {
                return true
            }
        }
        return false
    }


    fun comprobarID(id: String): String {
        for (pedido in DataRepository.listaDonativos) {
            if (id == pedido.idDonativo) {
                val ide = generarID()
                return ide
            }

        }
        return id
    }

    fun generarID(): String {
        val random = java.util.Random()
        val idNumerico = (10000000 + random.nextInt(90000000)).toString()
        return idNumerico
    }

    fun agregarDonacion(correoCliente: String?, correoVendedor:String, idPedido:String, idPrenda:String, totalDonativo:String, nombreAso: String?, fechaPedido:String){
        val id = generarID()
        val id2 = comprobarID(id)
        val nuevaDonacion = Donativos(id2, correoCliente, correoVendedor, idPedido,idPrenda,totalDonativo,nombreAso,fechaPedido)
        DataRepository.listaDonativos.add(nuevaDonacion)
        imprimirDonacion()
    }

    fun buscarPrendaNombre(idRopa:String): String? {
        var nombre: String? = null
        for (ropa in DataRepository.listaRopa) {
            if (idRopa == ropa.ide) {
                nombre = ropa.nombrePrenda
            }
        }
        return nombre
    }

    fun imprimirDonacion(){
        for(ropa in DataRepository.listaDonativos){
            println("ID: ${ropa.idDonativo}")
            println("ID pedido: ${ropa.idPedido}")
            println("ID prenda: ${ropa.idPrenda}")
            println("Correo cliente: ${ropa.correoCliente}")
            println("Correo vendedor: ${ropa.correoVendedor}")
            println("Fecha: ${ropa.fechaPedido}")
            println("Asociacion: ${ropa.nombreAsociacion}")
            println("Total donacion: ${ropa.totalDonativo}")
            println("----------------------")
        }
    }
}