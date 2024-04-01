package com.example.myapplication

class ConPedidosCliente(private val mPedidos: MPedidos) {
    fun verPedidos(correo: String?, callback: (List<PedidosGeneral>?) -> Unit) {
        mPedidos.verPedidosCliente(correo) { pedido ->
            if (pedido != null) {
                // Aquí puedes realizar cualquier procesamiento adicional en la lista si es necesario
                val cardlist: List<PedidosGeneral>? = pedido

                // Llamar al callback con la lista creada
                callback(cardlist)
            } else {
                // Llamar al callback con null si no se pudieron obtener direcciones
                callback(null)
            }
        }
    }

      /*  fun verPrendasPedido(idPedido: String?): MutableList<PrendasPedido> {

            val prendas = DataRepository.listaPrendasPedidos
                .filter { it.idPedido == idPedido }
                .map { prendasPedido ->
                    // Aquí debes obtener la instancia completa de PrendasPedido
                    // basada en el idRopa del objeto PrendasPedido
                    DataRepository.listaPrendasPedidos.firstOrNull { it.idRopa == prendasPedido.idRopa }
                }
                .filterNotNull()
                .toMutableList()

            return prendas
    }*/


    fun verPrendasPedido(idPedido: String?, callback: (List<PrendasPedido>?) -> Unit) {
        mPedidos.obtenerPrendasPedido(idPedido) { pedido ->
            if (pedido != null) {
                // Aquí puedes realizar cualquier procesamiento adicional en la lista si es necesario
                val cardlist: List<PrendasPedido>? = pedido

                // Llamar al callback con la lista creada
                callback(cardlist)
            } else {
                // Llamar al callback con null si no se pudieron obtener direcciones
                callback(null)
            }
        }
    }

}