package com.example.libreriaelbuho.controlador

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.libreriaelbuho.modelo.Carrito
import com.example.libreriaelbuho.modelo.Libro
import com.example.libreriaelbuho.modelo.MLibros
import com.example.libreriaelbuho.servicioWeb.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CarritoViewModel: ViewModel() {
    val mLibros = MLibros()
    var _listaLibros: ArrayList<Libro> by mutableStateOf(arrayListOf())

    fun agregarAlCarrito(navController: NavController, ide: String?, correoCliente: String?, tituloLibro: String?, autor: String?, cantidad: Int, precio: Float, subtotal: Float) {
        val agregarCarrito = Carrito(ide, correoCliente, tituloLibro, autor, cantidad, precio,subtotal)

        mLibros.agregarLibroCarrito(navController,agregarCarrito,ide,correoCliente) { exito, mensaje ->
            if (exito) {
                // La prenda se agregó al carrito correctamente
                Toast.makeText(navController.context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                // Ocurrió un error o la prenda ya está en el carrito
                Toast.makeText(navController.context, mensaje, Toast.LENGTH_SHORT).show()
            }
        }
    }
    //ESTE SIRVE PARA OBTENER CARRTO
    fun getCarrito() {
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
    fun getCarrito2(callback: (List<Carrito>?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getCarrito2()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val carrito = response.body() // Aquí obtenemos la lista de libros de la respuesta
                        callback(carrito) // Pasamos la lista de libros al callback
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


    fun addLibros(carrito: Carrito) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.addLibroCarrito(carrito)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful && response.body()!!.codigo == "200") {
                    getCarrito()
                }
            }
        }
    }


    //OPERACIONES EN EL SERVIDOR
    suspend fun multiplicar(num1: Int, num2: Float): Float {
        return try {
            Log.d("CarritoViewModel", "num1: $num1, num2: $num2")

            val response = RetrofitClient.webService.multiplicar(num1, num2)
            Log.d("CarritoViewModel", "Response body: ${response.body()}")
            if (response.isSuccessful) {
                val result = response.body()?.result ?: 0f
                Log.d("CarritoViewModel", "Resultado de la multiplicación: $result")
                result
            } else {
                Log.d("CarritoViewModel", "La respuesta no fue exitosa")
                0f
            }
        } catch (e: Exception) {
            Log.e("CarritoViewModel", "Error al multiplicar", e)
            0f
        }
    }

    suspend fun getSumaTotal(): Float {
        return try {
            val response = RetrofitClient.webService.getSumaTotal()
            if (response.isSuccessful) {
                response.body()?.result ?: 0f
            } else {
                0f
            }
        } catch (e: Exception) {
            Log.e("CarritoViewModel", "Error al obtener la suma total", e)
            0f
        }
    }

}