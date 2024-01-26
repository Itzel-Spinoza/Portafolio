package com.example.myapplication

import android.content.Context
import android.widget.Toast

class ConInventario (private val dataRepository: DataRepository) {
    fun mostrarCategoria(categoria: String?, genero: String?): MutableList<Ropa> {
        val cardList = DataRepository.listaRopa.filter {
            it.genero == genero && it.categoria == categoria && it.estado != "Vendido"
        }.toMutableList()

        return cardList
    }

    fun devolverNombreVendedor(correo: String?): String? {
        var nombreCompleto: String? = null

        for (usuario in DataRepository.listaUsuarios) {
            if (correo == usuario.correo) {
                val nombre = usuario.nombre
                val apePat = usuario.apepat
                val apeMat = usuario.apemat
                nombreCompleto = "$nombre $apePat $apeMat"
                break
            }
        }

        return nombreCompleto
    }

    fun agregarAlCarrito(context: Context, ide: String?, correoCliente: String?, correoVendedor: String?, nombrePrenda: String?, talla: String?, categoria: String?, genero: String?, descripcion: String?, foto: String?, precio: String?, porcentaje: String?, aso: String?, donativo: String?, estado: String?, nombreVen: String?){
        val agregarCarrito = RopaCarrito(
            ide, correoCliente, correoVendedor, nombrePrenda, talla, categoria, genero,
            descripcion, foto, precio, porcentaje, aso, donativo, estado, nombreVen
        )

        var productoYaEnCarrito = false

        for (carrito in DataRepository.listaCarrito) {
            if (ide == carrito.ide) {
                productoYaEnCarrito = true
                break
            }
        }

        if (!productoYaEnCarrito) {
            DataRepository.listaCarrito.add(agregarCarrito)
            Toast.makeText(context, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Ya tienes el producto en el carrito", Toast.LENGTH_SHORT).show()
        }


    }
}