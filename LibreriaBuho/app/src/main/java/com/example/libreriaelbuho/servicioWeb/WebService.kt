package com.example.libreriaelbuho.servicioWeb

import com.example.libreriaelbuho.modelo.Carrito
import com.example.libreriaelbuho.modelo.Libro
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {
    @GET("/libro")
    suspend fun getLibros(): Response<LibrosResponse>

    @GET("/libro")
    suspend fun getLibros2(): Response<List<Libro>>

    @POST("/libro")
    suspend fun addLibro(
        @Body libro: Libro
    ): Response<LibrosResponse>

    @PUT("/libro/{id}")
    suspend fun updateLibro(
        @Path("id") id_libro: String,
        @Body libro: Libro
    ): Response<LibrosResponse>

    @DELETE("/libro/{id}")
    suspend fun deleteLibro(
        @Path("id") id_libro: String
    ): Response<LibrosResponse>

    //CARRITO
    @GET("/carrito")
    suspend fun getCarrito(): Response<LibrosResponse>

    @GET("/carrito")
    suspend fun getCarrito2(): Response<List<Carrito>>

    @POST("/carrito")
    suspend fun addLibroCarrito(
        @Body carrito: Carrito
    ): Response<LibrosResponse>

    @PUT("/carrito/{id}")
    suspend fun updateLibroCarrito(
        @Path("id") id_libro: String,
        @Body carrito: Carrito
    ): Response<LibrosResponse>

    @DELETE("/carrito/{id}")
    suspend fun deleteLibroCarrito(
        @Path("id") id_libro: String
    ): Response<LibrosResponse>

    // GET PARA MULTIPLICAR CANTIDAD POR PRECIO
    @GET("/mult/{num1}/{num2}")
    suspend fun multiplicar(
        @Path("num1") num1: Int,
        @Path("num2") num2: Float
    ): Response<ResultadoResponse>

    // Obtener la suma total
    @GET("/sumaTotal")
    suspend fun getSumaTotal(): Response<ResultadoResponse>
}