package com.example.libreriaelbuho.controlador

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libreriaelbuho.modelo.Libro
import com.example.libreriaelbuho.servicioWeb.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibrosViewModel: ViewModel() {

    var _listaLibros: ArrayList<Libro> by mutableStateOf(arrayListOf())

    fun getLibros() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getLibros()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaLibros = response.body()!!.data
                    //println(_listaLibros)
                }
            }
        }
    }

    //ESTE SIRVE PARA OBTENER LIBROS
    fun getLibros2(callback: (List<Libro>?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getLibros2()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val libros = response.body() // Aquí obtenemos la lista de libros de la respuesta
                        callback(libros) // Pasamos la lista de libros al callback
                    } else {
                        // Manejar el caso en que la respuesta no sea exitosa
                        callback(null)
                    }
                }
            } catch (e: Exception) {
                // Manejar cualquier excepción que ocurra durante la llamada a la API
                Log.e("ObtenerLibros", "Error al obtener libros", e)
                callback(null)
            }
        }
    }


    fun addLibros(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.addLibro(libro)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getLibros()
                }
            }
        }
    }


    fun updateLibros(idLibro: String, libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.updateLibro(idLibro, libro)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getLibros()
                }
            }
        }
    }

    fun deleteLibros(idLibro: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.deleteLibro(idLibro)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getLibros()
                }
            }
        }
    }


    //PROCESOS SIN EL SERVIDOR
    fun generarIDLibros(): String {
        val random = java.util.Random()
        val idNumerico = (10000000 + random.nextInt(90000000)).toString()
        return idNumerico
    }

    fun subtotalLibros(cantidad: Int, precio: String?): Float {
        val cantidadParse = cantidad.toFloat()
        val precioParse = precio?.toFloat()

        val subtotal = cantidadParse * precioParse!!

        return subtotal

    }


}