package com.example.libreriaelbuho.controlador

import com.example.libreriaelbuho.modelo.Carrito
import com.example.libreriaelbuho.modelo.MCarrito

class Carritoo {
    val mCarrito = MCarrito()
        fun verCarrito(callback: (List<Carrito>?) -> Unit) {
        mCarrito.obtenerCarrito(callback)
    }
}