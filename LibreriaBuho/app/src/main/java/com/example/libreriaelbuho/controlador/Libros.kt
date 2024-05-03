package com.example.libreriaelbuho.controlador

import com.example.libreriaelbuho.modelo.Libro
import com.example.libreriaelbuho.modelo.MLibros

class Libros {
    val mLibros = MLibros()
    fun verLibros(callback: (List<Libro>?) -> Unit) {
        mLibros.obtenerLibro(callback)
    }

    fun filtrarLibro(genero: String, callback: (List<Libro>) -> Unit) {
        mLibros.filtrarLibro(genero) { libro ->
            if (libro.isNotEmpty()) {
                callback(libro)
            } else {
                callback(emptyList())
            }
        }
    }

    fun generarIDLibros(): String {
        val random = java.util.Random()
        val idNumerico = (10000000 + random.nextInt(90000000)).toString()
        return idNumerico
    }

    fun comprobarID(id: String, callback: (String) -> Unit) {
        val mLibros = MLibros()
        mLibros.comprobarID(id) { nuevoID ->

            if (nuevoID.isNotEmpty()) {

                callback(nuevoID)
            } else {
                val nuevoIDGenerado = generarIDLibros()
                callback(nuevoIDGenerado)
            }
        }
    }




}