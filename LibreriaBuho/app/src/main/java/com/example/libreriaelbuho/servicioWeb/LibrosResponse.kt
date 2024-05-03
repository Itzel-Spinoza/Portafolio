package com.example.libreriaelbuho.servicioWeb

import com.example.libreriaelbuho.modelo.Libro

data class LibrosResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Libro>
)
