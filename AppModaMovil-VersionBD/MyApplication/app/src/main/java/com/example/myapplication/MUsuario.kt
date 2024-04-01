package com.example.myapplication
import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MUsuario {

    fun agregarUsuario(context: Context, usuario: Usuario) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("usuario")
            .add(usuario)
            .addOnSuccessListener {
                // Usuario registrado exitosamente
            }
            .addOnFailureListener { e ->
                // Error al registrar usuario en Firestore
                Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun verificarUsuarioRegistrado(
        context: Context,
        correo: String,
        callback: (Boolean, String) -> Unit
    ) {
        val firestore = FirebaseFirestore.getInstance()

        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Usuario autenticado correctamente
                    callback(true, "Correo ya registrado")
                } else {
                    // Usuario no encontrado o credenciales incorrectas
                    callback(false, "")
                }
            }
            .addOnFailureListener { _ ->
                Toast.makeText(context, "Error al verificar datos del usuario", Toast.LENGTH_SHORT)
                    .show()
                callback(false, "")
            }
    }

    fun obtenerUsuario(correo: String?, callback: (Usuario?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val usuariosRef = firestore.collection("usuario")

        usuariosRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Usuario encontrado
                    val usuario = querySnapshot.documents[0].toObject(Usuario::class.java)
                    callback(usuario)
                } else {
                    // Usuario no encontrado
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(null)
            }
    }

    fun actualizarContrasena(correo: String?, contrasena: String, callback: (Boolean) -> Unit) {
        obtenerUsuario(correo) { usuario ->
            if (usuario != null) {
                // Actualizar los atributos directamente
                val usuarioData = hashMapOf(
                    "contrasena" to contrasena,
                )

                // Invocar al método que actualiza el usuario en tu almacenamiento (Firebase Firestore u otro)
                val firestore = FirebaseFirestore.getInstance()
                val usuariosRef = firestore.collection("usuario")

                usuariosRef.whereEqualTo("correo", correo)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            // Obtener el documento correspondiente al usuario
                            val documentoUsuario = querySnapshot.documents[0]

                            // Actualizar el documento con los nuevos datos
                            documentoUsuario.reference.update(usuarioData as Map<String, Any>)
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
                            // Llamar al callback con false si no se pudo encontrar el usuario
                            callback(false)
                        }
                    }
                    .addOnFailureListener { e ->
                        // Llamar al callback con false si hubo un error en la obtención del usuario
                        callback(false)
                        e.printStackTrace()
                    }
            } else {
                // Llamar al callback con false si no se pudo encontrar el usuario
                callback(false)
            }
        }
    }

    fun actualizarPerfil(
        correo: String?,
        nombre: String,
        apellidopat: String,
        apellidomat: String,
        callback: (Boolean) -> Unit
    ) {
        obtenerUsuario(correo) { usuario ->
            if (usuario != null) {
                // Actualizar los atributos directamente
                val usuarioData = hashMapOf(
                    "nombre" to nombre,
                    "apepat" to apellidopat,
                    "apemat" to apellidomat
                )

                // Invocar al método que actualiza el usuario en tu almacenamiento (Firebase Firestore u otro)
                val firestore = FirebaseFirestore.getInstance()
                val usuariosRef = firestore.collection("usuario")

                usuariosRef.whereEqualTo("correo", correo)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            // Obtener el documento correspondiente al usuario
                            val documentoUsuario = querySnapshot.documents[0]

                            // Actualizar el documento con los nuevos datos
                            documentoUsuario.reference.update(usuarioData as Map<String, Any>)
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
                            // Llamar al callback con false si no se pudo encontrar el usuario
                            callback(false)
                        }
                    }
                    .addOnFailureListener { e ->
                        // Llamar al callback con false si hubo un error en la obtención del usuario
                        callback(false)
                        e.printStackTrace()
                    }
            } else {
                // Llamar al callback con false si no se pudo encontrar el usuario
                callback(false)
            }
        }
    }


    fun obtenerDirecciones(correo: String?, callback: (List<Direccion>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val direccionesRef = firestore.collection("direccion")

        direccionesRef.whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val direcciones = querySnapshot.documents.map { doc ->
                    doc.toObject(Direccion::class.java)
                }.filterNotNull()
                callback(direcciones)
            }
            .addOnFailureListener { e ->
                // Manejar la excepción
                e.printStackTrace()
                callback(emptyList())
            }
    }

    fun actualizarDireccion(
        correo: String?,
        calle: String,
        col: String,
        noext: String,
        cp: String,
        municipio: String,
        estado: String,
        callback: (Boolean) -> Unit
    ) {
        // Invocar al método que actualiza el usuario en tu almacenamiento (Firebase Firestore u otro)
        val firestore = FirebaseFirestore.getInstance()
        val direcciones = firestore.collection("direccion")
        val usuarioData = hashMapOf(
            "calle" to calle,
            "colonia" to col,
            "noext" to noext,
            "cp" to cp,
            "municipio" to municipio,
            "estado" to estado
        )

        direcciones.whereEqualTo("correo", correo)
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


    fun eliminarDireccion(context: Context, correo: String?, calle: String, callback: (Boolean) -> Unit) {

        val firestore = FirebaseFirestore.getInstance()
        val direccionesRef = firestore.collection("direccion")

        direccionesRef.whereEqualTo("correo", correo)
            .whereEqualTo("calle", calle)
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

    fun agregarDireccion(context: Context, direccion: Direccion, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("direccion")
            .add(direccion)
            .addOnSuccessListener {
                // Dirección agregada exitosamente
                callback(true, "Dirección agregada exitosamente")
            }
            .addOnFailureListener { e ->
                // Error al registrar dirección en Firestore
                callback(false, "Error al agregar la dirección")
                Toast.makeText(context, "Error al registrar dirección", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }

    fun agregarMetodoPago(context: Context, metodosPago: MetodosPago, callback: (Boolean, String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("metodospago")
            .add(metodosPago)
            .addOnSuccessListener {
                // Dirección agregada exitosamente
                callback(true, "Metodo de pago agregado exitosamente")
            }
            .addOnFailureListener { e ->
                // Error al registrar dirección en Firestore
                callback(false, "Error al agregar el metodo de pago")
                Toast.makeText(context, "Error al registrar metodo de pago", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
    }


        fun obtenerMetodosPago(correo: String?, callback: (List<MetodosPago>) -> Unit) {
            val firestore = FirebaseFirestore.getInstance()
            val metodosPagoRef = firestore.collection("metodospago")

            metodosPagoRef.whereEqualTo("correo", correo)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val metodosPago = querySnapshot.documents.map { doc ->
                        doc.toObject(MetodosPago::class.java)
                    }.filterNotNull()
                    callback(metodosPago)
                }
                .addOnFailureListener { e ->
                    // Manejar la excepción
                    e.printStackTrace()
                    callback(emptyList())
                }
        }

        fun actualizarMetodoPago(correo: String?, tipoTarjeta: String, noTarjeta: String, titularTarjeta: String, fechaExpedicion: String, ccv: String,
                                 callback: (Boolean) -> Unit) {
            val firestore = FirebaseFirestore.getInstance()
            val metodoPag = firestore.collection("metodospago")
            val usuarioData = hashMapOf(
                "noTarjeta" to noTarjeta,
                "tipoTarjeta" to tipoTarjeta,
                "titularTarjeta" to titularTarjeta,
                "fechaExpedicion" to fechaExpedicion,
                "ccv" to ccv,
            )

            metodoPag.whereEqualTo("correo", correo)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        // Obtener el documento correspondiente a la dirección
                        val documentoPago = querySnapshot.documents[0]

                        // Actualizar el documento con los nuevos datos
                        documentoPago.reference.update(usuarioData as Map<String, Any>)
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

        fun eliminarMetodoPago(context: Context, correo: String?, noTarjeta: String, callback: (Boolean) -> Unit) {

            val firestore = FirebaseFirestore.getInstance()
            val direccionesRef = firestore.collection("metodospago")

            direccionesRef.whereEqualTo("correo", correo)
                .whereEqualTo("noTarjeta", noTarjeta)
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
                                Toast.makeText(context, "Error al eliminar tarjeta", Toast.LENGTH_SHORT)
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


}

