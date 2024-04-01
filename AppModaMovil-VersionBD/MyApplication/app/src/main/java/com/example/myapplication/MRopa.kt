package com.example.myapplication

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MRopa {
    fun obtenerRopa(correo: String?, callback: (List<Ropa>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("ropa")

        ropaRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val ropa = querySnapshot.documents.map { doc ->
                    doc.toObject(Ropa::class.java)
                }.filterNotNull()
                callback(ropa)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }

    fun comprobarID(id: String, callback: (String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("ropa")

        ropaRef.whereEqualTo("ide", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                // Si no hay documentos con el mismo ID, llamamos al callback
                if (querySnapshot.isEmpty) {
                    callback(id)
                } else {
                    // Si hay documentos con el mismo ID, podrías realizar alguna acción
                    // o simplemente no hacer nada, ya que en este caso no necesitas llamar al callback.
                }
            }
    }

    fun agregarPrenda(context: Context, ropa: Ropa, callback: (Boolean, String) -> Unit){
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("ropa")
            .add(ropa)
            .addOnSuccessListener {
                // Dirección agregada exitosamente
                callback(true, "Preda agregada exitosamente")
            }
            .addOnFailureListener { e ->
                // Error al registrar dirección en Firestore
                callback(false, "Error al agregar la prenda")
                Toast.makeText(context, "Error al registrar prenda", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun actualizarPrenda(
        id: String?, nombrePrenda: String, talla: String, categoria: String,genero: String, descripcion:String, precio: String, porcentaje: String, aso: String?, donativo: String,
        callback: (Boolean) -> Unit
    ) {
        val firestore = FirebaseFirestore.getInstance()
        val ropa = firestore.collection("ropa")
        val usuarioData = hashMapOf(
            "nombrePrenda" to nombrePrenda,
            "talla" to talla,
            "categoria" to categoria,
            "genero" to genero,
            "descripcion" to descripcion,
            "precio" to precio,
            "porcentaje" to porcentaje,
            "aso" to aso,
            "donativo" to donativo,

        )

        ropa.whereEqualTo("ide", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Obtener el documento correspondiente a la dirección
                    val documentoDireccion = querySnapshot.documents[0]

                    // Actualizar el documento con los nuevos datos
                    documentoDireccion.reference.update(usuarioData as Map<String, Any>)
                        .addOnSuccessListener {
                            // Llamar al callback con true para indicar que la actualización fue exitosa
                            callback(true)
                        }
                        .addOnFailureListener { e ->
                            // Llamar al callback con false si hubo un error
                            callback(false)
                            e.printStackTrace()
                        }
                } else {
                    // Llamar al callback con false si no se pudo encontrar la dirección
                    callback(false)
                }
            }
            .addOnFailureListener { e ->
                // Llamar al callback con false si hubo un error en la obtención de la dirección
                callback(false)
                e.printStackTrace()
            }
    }
    fun actualizarPrenda2(
        id: String?, estado: String, nombrePrenda: String, talla: String, categoria: String,genero: String, descripcion:String, precio: String, porcentaje: String, aso: String?, donativo: String,
        callback: (Boolean) -> Unit
    ) {
        val firestore = FirebaseFirestore.getInstance()
        val ropa = firestore.collection("ropa")
        val usuarioData = hashMapOf(
            "nombrePrenda" to nombrePrenda,
            "talla" to talla,
            "categoria" to categoria,
            "genero" to genero,
            "descripcion" to descripcion,
            "precio" to precio,
            "porcentaje" to porcentaje,
            "estado" to estado,
            "aso" to aso,
            "donativo" to donativo,

            )

        ropa.whereEqualTo("ide", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Obtener el documento correspondiente a la dirección
                    val documentoDireccion = querySnapshot.documents[0]

                    // Actualizar el documento con los nuevos datos
                    documentoDireccion.reference.update(usuarioData as Map<String, Any>)
                        .addOnSuccessListener {
                            // Llamar al callback con true para indicar que la actualización fue exitosa
                            callback(true)
                        }
                        .addOnFailureListener { e ->
                            // Llamar al callback con false si hubo un error
                            callback(false)
                            e.printStackTrace()
                        }
                } else {
                    // Llamar al callback con false si no se pudo encontrar la dirección
                    callback(false)
                }
            }
            .addOnFailureListener { e ->
                // Llamar al callback con false si hubo un error en la obtención de la dirección
                callback(false)
                e.printStackTrace()
            }
    }
    fun eliminarPrenda(context: Context, id: String?, callback: (Boolean) -> Unit) {

        val firestore = FirebaseFirestore.getInstance()
        val direccionesRef = firestore.collection("ropa")

        direccionesRef.whereEqualTo("ide", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Obtener el documento correspondiente a la dirección
                    val documentoDireccion = querySnapshot.documents[0]

                    // Eliminar el documento
                    documentoDireccion.reference.delete()
                        .addOnSuccessListener {
                            // Dirección eliminada exitosamente en Firestore
                            callback(true)
                        }
                        .addOnFailureListener { e ->
                            // Error al eliminar dirección en Firestore
                            Toast.makeText(context, "Error al eliminar dirección", Toast.LENGTH_SHORT)
                                .show()
                            e.printStackTrace()
                            callback(false)
                        }
                } else {
                    // Llamar al callback con false si no se pudo encontrar la dirección
                    callback(false)
                }
            }
            .addOnFailureListener { e ->
                // Llamar al callback con false si hubo un error en la obtención de la dirección
                callback(false)
                e.printStackTrace()
            }
    }

    fun filtrarRopa(correo: String?, estado: String, callback: (List<Ropa>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("ropa")

        ropaRef.whereEqualTo("correo", correo)
            .whereEqualTo("estado", estado)
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

    fun mostrarRopaCate(categoria: String?, estado: String?, genero: String?, callback: (List<Ropa>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("ropa")

        ropaRef.whereEqualTo("categoria", categoria)
            .whereEqualTo("estado", estado)
            .whereEqualTo("genero", genero)
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
    fun obtenerNombreVendedor(correo: String?, callback: (String?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Se encontró un usuario con el correo proporcionado
                    val usuario = querySnapshot.documents[0].toObject(Usuario::class.java)
                    val nombreCompleto = "${usuario?.nombre} ${usuario?.apepat} ${usuario?.apemat}"
                    callback(nombreCompleto)
                } else {
                    // No se encontró ningún usuario con el correo proporcionado
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(null)
            }
    }

    fun agregarPrendaCarrito(context: Context, ropaCarrito: RopaCarrito, ide: String?, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        // Realizar una consulta para verificar si la prenda ya está en el carrito
        firestore.collection("carrito")
            .whereEqualTo("ide", ide)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    // La prenda no está en el carrito, así que se puede agregar
                    firestore.collection("carrito")
                        .add(ropaCarrito)
                        .addOnSuccessListener {
                            // Prenda agregada exitosamente al carrito
                            callback(true, "Producto agregado al carrito")
                        }
                        .addOnFailureListener { e ->
                            // Error al agregar la prenda al carrito
                            callback(false, "Error al agregar el producto al carrito")
                            Toast.makeText(context, "Error al agregar prenda al carrito", Toast.LENGTH_SHORT).show()
                            e.printStackTrace()
                        }
                } else {
                    // La prenda ya está en el carrito
                    callback(false, "Ya tienes el producto en el carrito")
                }
            }
            .addOnFailureListener { e ->
                // Error al realizar la consulta
                callback(false, "Error al verificar la existencia del producto en el carrito")
                Toast.makeText(context, "Error al verificar la existencia de la prenda en el carrito", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun obtenerPrendaPorID(id: String?, callback: (List<Ropa>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val ropaRef = firestore.collection("ropa")

        ropaRef.whereEqualTo("ide", id)
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


}