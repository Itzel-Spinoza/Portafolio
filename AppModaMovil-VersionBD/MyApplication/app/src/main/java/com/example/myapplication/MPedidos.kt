package com.example.myapplication

import com.google.firebase.firestore.FirebaseFirestore

class MPedidos {
    fun agregarPrendasPedido(context: ConComprar, prendasPedido: PrendasPedido, callback: (Boolean, String) -> Unit){
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("prendasPedido")
            .add(prendasPedido)
            .addOnSuccessListener {

                callback(true, "Preda agregada exitosamente")
            }
            .addOnFailureListener { e ->

                callback(false, "Error al agregar la prenda")
                //Toast.makeText(context, "Error al registrar prenda", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun agregarPedidoGeneral(context: ConComprar, pedidosGeneral: PedidosGeneral, callback: (Boolean, String) -> Unit){
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("pedidosGeneral")
            .add(pedidosGeneral)
            .addOnSuccessListener {

                callback(true, "Preda agregada exitosamente")
            }
            .addOnFailureListener { e ->

                callback(false, "Error al agregar la prenda")
                //Toast.makeText(context, "Error al registrar prenda", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun agregarPedido(context: ConComprar,pedido: Pedido, callback: (Boolean, String) -> Unit){
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("pedido")
            .add(pedido)
            .addOnSuccessListener {

                callback(true, "Pedido agregado exitosamente")
            }
            .addOnFailureListener { e ->

                callback(false, "Error al agregar el pedido")
                //Toast.makeText(context, "Error al registrar prenda", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun obtenerPrendasPedido(id: String?, callback: (List<PrendasPedido>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("prendasPedido")

        ropaRef.whereEqualTo("idPedido", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val ropa = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(PrendasPedido::class.java)
                }
                callback(ropa)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }

    fun verPedidos(correo: String?, callback: (List<Pedido>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("pedido")

        ropaRef.whereEqualTo("correoVendedor", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val ropa = querySnapshot.documents.map { doc ->
                    doc.toObject(Pedido::class.java)
                }.filterNotNull()
                callback(ropa)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }

    fun filtrarPedidos(correo: String?, estado: String, callback: (List<Pedido>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("pedido")

        ropaRef.whereEqualTo("correoVendedor", correo)
            .whereEqualTo("estadoPedido", estado)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val pedido = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(Pedido::class.java)
                }
                callback(pedido)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }

    fun verPedidosCliente(correo: String?, callback: (List<PedidosGeneral>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("pedidosGeneral")

        ropaRef.whereEqualTo("correoCliente", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val pedidos = querySnapshot.documents.map { doc ->
                    doc.toObject(PedidosGeneral::class.java)
                }.filterNotNull()
                callback(pedidos)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }

    fun verPrendasVendedor(correo: String?, idPedido: String?,callback: (List<Ropa>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("prendasPedido")

        ropaRef.whereEqualTo("correoVendedor", correo)
            .whereEqualTo("idPedido", idPedido)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val ropa = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(Ropa::class.java)
                }
                callback(ropa)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }

    fun calcularGananciaOperacion(precioPrenda: String, porcentajeDonativo: String): Double {
        // Implementa aquí la lógica para calcular la ganancia de una operación
        // Utiliza precioPrenda y porcentajeDonativo según tus requerimientos
        // Por ejemplo:
        val precioPrenda2 = precioPrenda.toFloat()
        val porcentajeDon = porcentajeDonativo.toFloat()
        val ganancia = precioPrenda2 * (porcentajeDon / 100.0)
        return ganancia
    }

    fun obtenerPrendasPorPedido(idPedido: String, callback: (List<PrendasPedido>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("prendasPedido")

        ropaRef.whereEqualTo("idPedido", idPedido)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val prendas = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(PrendasPedido::class.java)
                }
                callback(prendas)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                callback(emptyList())
            }
    }


    fun actualizarEstadoPedido(id: String?, estado: String, callback: (Boolean, Any?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val pedidosRef = firestore.collection("pedido")

        val usuarioData = hashMapOf(
            "estadoPedido" to estado
        )

        pedidosRef.whereEqualTo("idPedido", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Obtener el documento correspondiente al pedido
                    val documentoPedido = querySnapshot.documents[0]

                    // Actualizar el documento con los nuevos datos
                    documentoPedido.reference.update(usuarioData as Map<String, Any>)
                        .addOnSuccessListener {
                            // Llamar al callback con true para indicar que la actualización fue exitosa
                            callback(true, null)
                        }
                        .addOnFailureListener { e ->
                            // Llamar al callback con false y el error si hubo un problema durante la actualización
                            callback(false, e.message)
                            e.printStackTrace()
                        }
                } else {
                    // Llamar al callback con false si no se pudo encontrar el pedido
                    callback(false, "Pedido no encontrado")
                }
            }
            .addOnFailureListener { e ->
                // Llamar al callback con false y el error si hubo un problema en la obtención del pedido
                callback(false, e.message)
                e.printStackTrace()
            }
    }

    fun actualizarEstadoPedidoGenerales(id: String?, estado: String, callback: (Boolean, Any?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val pedidosRef = firestore.collection("pedidosGeneral")

        val usuarioData = hashMapOf(
            "estadoPedido" to estado
        )

        pedidosRef.whereEqualTo("idPedido", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Obtener el documento correspondiente al pedido
                    val documentoPedido = querySnapshot.documents[0]

                    // Actualizar el documento con los nuevos datos
                    documentoPedido.reference.update(usuarioData as Map<String, Any>)
                        .addOnSuccessListener {
                            // Llamar al callback con true para indicar que la actualización fue exitosa
                            callback(true, null)
                        }
                        .addOnFailureListener { e ->
                            // Llamar al callback con false y el error si hubo un problema durante la actualización
                            callback(false, e.message)
                            e.printStackTrace()
                        }
                } else {
                    // Llamar al callback con false si no se pudo encontrar el pedido
                    callback(false, "Pedido no encontrado")
                }
            }
            .addOnFailureListener { e ->
                // Llamar al callback con false y el error si hubo un problema en la obtención del pedido
                callback(false, e.message)
                e.printStackTrace()
            }
    }

}