package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Carrito : AppCompatActivity(), CardAdapterPrendasCarrito.OnItemClickListener  {

    private val conComprar = ConComprar(DataRepository)
    private lateinit var adapter: CardAdapterPrendasCarrito
    private lateinit var recyclerCarrito: RecyclerView
    private lateinit var btnCarrito: Button
    private lateinit var totalDeCompra: TextView
    private lateinit var totalDonativos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        val correo = intent.getStringExtra("email")

        recyclerCarrito = findViewById(R.id.recyclerCarrito)
        btnCarrito = findViewById(R.id.btnComprarCarrito)
        totalDeCompra = findViewById(R.id.totalDeCompra)
        totalDonativos = findViewById(R.id.totalDonativos)

        recyclerCarrito.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        btnCarrito.setOnClickListener {
            if (conComprar.comprobarListaVacia(correo)) {
                showToast("No tienes productos en tu carrito.")
            } else {
                val intent = Intent(this, PasarelaDireccion::class.java)
                intent.putExtra("email", correo)
                intent.putExtra("totalPedido", conComprar.calcularTotalCompra(correo).toString())
                intent.putExtra("totalDonativos", conComprar.calcularTotalDonativos(correo).toString())
                startActivity(intent)
            }
        }

        obtenerDatos(correo)
    }

    override fun onEliminarClick(ropaCarrito: RopaCarrito) {
        val correo = intent.getStringExtra("email")
        val ide = ropaCarrito.ide

        try {
            conComprar.eliminarPrendaCarrito2(correo, ide)
            showToast("Prenda eliminada del carrito.")
            obtenerDatos(correo)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun obtenerDatos(correo: String?) {
        try {
            val cardList = conComprar.mostrarPrendasCarrito(correo)
            adapter = CardAdapterPrendasCarrito(cardList, this)
            recyclerCarrito.adapter = adapter

            totalDeCompra.text = conComprar.calcularTotalCompra(correo).toString()
            totalDonativos.text = conComprar.calcularTotalDonativos(correo).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}