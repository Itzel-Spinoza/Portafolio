package com.example.museosoumaya.viewModel

class VMBoletos {
    fun sumarBoletos(cantidad: Int): Int {
        val cantidad2 = cantidad + 1
        return cantidad2
    }
    
    fun restarBoletos(cantidad: Int): Int {
        val cantidad2 : Int
        if(cantidad <= 0){
            cantidad2 = 0
        }else{
            cantidad2 = cantidad - 1
        }
        return cantidad2
    }

    fun sumarBoletosAdulto(cantidad: Int): Int {
        val precio = 40
        val subtotalBoletos : Int
        subtotalBoletos = precio * cantidad
        return subtotalBoletos
    }
    fun sumarBoletosNino(cantidad: Int): Int {
        val precio = 20
        val subtotalBoletos : Int
        subtotalBoletos = precio * cantidad
        return subtotalBoletos
    }

    fun restarBoletosAdulto(subtotalAdulto: Int, cantidad: Int): Int {
        val precio = 40
        val subtotalBoletos : Int
        if(cantidad <= 0){
            subtotalBoletos = 0
        }else{
            subtotalBoletos = subtotalAdulto - precio

        }
        return subtotalBoletos
    }
    fun restarBoletosNino(subtotalNino: Int, cantidad: Int): Int {
        val precio = 20
        val subtotalBoletos : Int
        if(cantidad <= 0){
            subtotalBoletos = 0
        }else{
            subtotalBoletos = subtotalNino - precio

        }
        return subtotalBoletos
    }

    fun totalBoletos(subtotalNino: String, subtotalAdulto: String): String {
        val adulto: Double = subtotalAdulto.toDouble()
        val nino: Double = subtotalNino.toDouble()
        val total = nino + adulto
        return String.format("%.2f", total) // Formateamos el total como una cadena de texto con dos decimales
    }

    fun totalBoletos2(subtotalNino: String?, subtotalAdulto: String?): String {
        val adulto: Double = subtotalAdulto?.toDouble() ?: 0.0
        val nino: Double = subtotalNino?.toDouble() ?: 0.0
        val total = nino + adulto
        return String.format("%.2f", total) // Formateamos el total como una cadena de texto con dos decimales
    }


    fun generarIDPedido(): String {
        val random = java.util.Random()
        val idNumerico = (10000000 + random.nextInt(90000000)).toString()
        return idNumerico
    }

}