package com.example.myapplication

class ConPedidosCliente(private val dataRepository: DataRepository) {
    fun verPedidos(correo: String?): MutableList<PedidosGeneral> {
        val cardList = DataRepository.listaPedidosGeneral.filter { it.correoCliente == correo }.toMutableList()
        return cardList
    }

        fun verPrendasPedido(idPedido: String?): MutableList<PrendasPedido> {

            val prendas = DataRepository.listaPrendasPedidos
                .filter { it.idPedido == idPedido }
                .map { prendasPedido ->
                    DataRepository.listaPrendasPedidos.firstOrNull { it.idRopa == prendasPedido.idRopa }
                }
                .filterNotNull()
                .toMutableList()

            return prendas
    }

}