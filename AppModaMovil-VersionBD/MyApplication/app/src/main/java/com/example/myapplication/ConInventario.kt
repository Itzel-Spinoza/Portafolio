package com.example.myapplication

import android.content.Context
import android.widget.Toast

class ConInventario (private val mRopa: MRopa) {
    fun mostrarCategoria(
        categoria: String?,
        genero: String?,
        estado: String?,
        callback: (List<Ropa>) -> Unit
    ) {

        mRopa.mostrarRopaCate(categoria, estado, genero) { ropa ->
            if (ropa.isNotEmpty()) {
                // Ropa encontrada
                callback(ropa)
            } else {
                // Ropa no encontrada
                callback(emptyList())
            }
        }

    }





    fun agregarAlCarrito(context: Context, ide: String?, correoCliente: String?, correoVendedor: String?, nombrePrenda: String?, talla: String?, categoria: String?, genero: String?, descripcion: String?, foto: String?, precio: String?, porcentaje: String?, aso: String?, donativo: String?, estado: String?, nombreVen: String?) {
        val agregarCarrito = RopaCarrito(
            ide, correoCliente, correoVendedor, nombrePrenda, talla, categoria, genero,
            descripcion, foto, precio, porcentaje, aso, donativo, estado, nombreVen
        )

        mRopa.agregarPrendaCarrito(context,agregarCarrito,ide) { exito, mensaje ->
            if (exito) {
                // La prenda se agregó al carrito correctamente
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                // Ocurrió un error o la prenda ya está en el carrito
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun devolverNombreVendedor(correo: String?, callback: (String?) -> Unit) {
        mRopa.obtenerNombreVendedor(correo) { nombreCompleto ->
            callback(nombreCompleto)
        }
    }
}