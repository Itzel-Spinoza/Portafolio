package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerPrendasCategoria : AppCompatActivity(), CardAdapterRopaCat.OnItemClickListener{
    lateinit var txtNombreCategoria: TextView
    lateinit var recyclerRopaCategoria: RecyclerView
    val conInventario = ConInventario(DataRepository)
    lateinit var adapter: CardAdapterRopaCat
    var correo: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_prendas_categoria)

        correo = intent.getStringExtra("email")
        val genero = intent.getStringExtra("genero")
        val categoria = intent.getStringExtra("categoria")
        txtNombreCategoria = findViewById(R.id.txtCategoriaPrenda)

        recyclerRopaCategoria = findViewById(R.id.recyclerPrendasCat)

        recyclerRopaCategoria.layoutManager = GridLayoutManager(this, 2)

        val cardList = conInventario.mostrarCategoria(categoria,genero)
        adapter = CardAdapterRopaCat(cardList,this)
        recyclerRopaCategoria.adapter = adapter
        adapter.notifyDataSetChanged()

        txtNombreCategoria.setText(categoria)


    }

    override fun onItemClick(ropa: Ropa) {
        val intent = Intent(this, VerPrenda::class.java)
        intent.putExtra("id", ropa.ide)
        intent.putExtra("correo",ropa.correo)
        intent.putExtra("nombrePrenda", ropa.nombrePrenda)
        intent.putExtra("foto", ropa.foto)
        intent.putExtra("aso",ropa.aso)
        intent.putExtra("talla", ropa.talla)
        intent.putExtra("categoria", ropa.categoria)
        intent.putExtra("genero",ropa.genero)
        intent.putExtra("descripcion", ropa.descripcion)
        intent.putExtra("precio", ropa.precio)
        intent.putExtra("estado", ropa.estado)
        intent.putExtra("aso",ropa.aso)
        intent.putExtra("porcentaje",ropa.porcentaje)
        intent.putExtra("donativo",ropa.donativo)
        intent.putExtra("email", correo)
        startActivity(intent)
    }
}