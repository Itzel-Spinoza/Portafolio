package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Donaciones : AppCompatActivity() {
    lateinit var adapterDonaciones: CardAdapterDonaciones
    lateinit var recyclerDonaciones: RecyclerView
    val conDonaciones = ConDonaciones(DataRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donaciones)

        val correo = intent.getStringExtra("email")
        recyclerDonaciones = findViewById(R.id.recyclerDonacion)
        recyclerDonaciones.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val cardList = conDonaciones.mostrarDonacionesDepCorreo(correo)

        adapterDonaciones = CardAdapterDonaciones(cardList,this)
        recyclerDonaciones.adapter = adapterDonaciones
        adapterDonaciones.notifyDataSetChanged()


    }

    override fun onResume() {
        super.onResume()
        val correo = intent.getStringExtra("email")
        obtenerDatos(correo)
    }
    private fun obtenerDatos(correo: String?) {
        val cardList = conDonaciones.mostrarDonacionesDepCorreo(correo)
        adapterDonaciones = CardAdapterDonaciones(cardList,this)
        recyclerDonaciones.adapter = adapterDonaciones
        adapterDonaciones.notifyDataSetChanged()
    }
}