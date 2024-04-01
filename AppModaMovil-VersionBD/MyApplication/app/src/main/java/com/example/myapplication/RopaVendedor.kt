package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RopaVendedor : AppCompatActivity(), cardAdapterRopa.OnItemClickListener {
    lateinit var btnNuevaPrenda: Button
    lateinit var btnTodo: Button
    lateinit var btnSubido: Button
    lateinit var btnVendido: Button
    lateinit var txtEtiquetaRopa: TextView


    lateinit var recycler: RecyclerView
    val mRopa = MRopa()
    val conInventarioRopa = ConInventarioRopa(mRopa)
    lateinit var adapter: cardAdapterRopa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_ropa)

        val correo = intent.getStringExtra("email")

        btnNuevaPrenda = findViewById(R.id.btnAgregarPrenda)
        btnVendido = findViewById(R.id.btnVendidas)
        btnTodo = findViewById(R.id.btnTodo)
        btnSubido = findViewById(R.id.btnSubidas)

        txtEtiquetaRopa = findViewById(R.id.txtRopaSubida)

        recycler = findViewById(R.id.recyclerRopa)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Usa la función verPrendasRopaFiltradas en lugar de verPrendasRopa
        conInventarioRopa.verPrendasRopa(correo) { ropa ->
            if (ropa != null) {
                adapter = cardAdapterRopa(ropa, this)
                recycler.adapter = adapter
            } else {
                // Manejar el caso de error
            }
        }
            btnNuevaPrenda.setOnClickListener(View.OnClickListener {
                val intenAgRopa = Intent(this, AgregarPrenda::class.java)
                intenAgRopa.putExtra("email", correo)
                startActivity(intenAgRopa)
            })

            btnSubido.setOnClickListener(View.OnClickListener {
                val estado = "Subido"
                //val cardList = conInventarioRopa.filtrarRopa(estado, correo)
                //adapter.actualizarDatos(cardList)
                txtEtiquetaRopa.setText("Ropa subida")

                conInventarioRopa.filtrarRopa(estado,correo) { ropa ->
                    if (ropa != null) {
                        adapter = cardAdapterRopa(ropa, this)
                        recycler.adapter = adapter
                    } else {
                        // Manejar el caso de error
                    }
                }
            })

            btnTodo.setOnClickListener(View.OnClickListener {
                //val cardList = conInventarioRopa.verPrendasRopa(correo)
                //adapter.actualizarDatos(cardList)

                conInventarioRopa.verPrendasRopa(correo) { ropa ->
                    if (ropa != null) {
                        adapter = cardAdapterRopa(ropa, this)
                        recycler.adapter = adapter
                    } else {
                        // Manejar el caso de error
                    }
                }
                txtEtiquetaRopa.setText("Toda la ropa")
            })


            btnVendido.setOnClickListener(View.OnClickListener {
                val estado = "Vendido"
                conInventarioRopa.filtrarRopa(estado,correo) { ropa ->
                    if (ropa != null) {
                        adapter = cardAdapterRopa(ropa, this)
                        recycler.adapter = adapter
                    } else {
                        // Manejar el caso de error
                    }
                }
                txtEtiquetaRopa.setText("Ropa vendida")
            })

    }
        override fun onResume() {
            super.onResume()
            val correo = intent.getStringExtra("email")
            obtenerDatos(correo)
        }

        override fun onItemClick(ropa: Ropa) {
            val intent = Intent(this, EditarPrenda::class.java)
            intent.putExtra("id", ropa.ide)
            intent.putExtra("correo", ropa.correo)
            intent.putExtra("nombrePrenda", ropa.nombrePrenda)
            intent.putExtra("talla", ropa.talla)
            intent.putExtra("categoria", ropa.categoria)
            intent.putExtra("genero", ropa.genero)
            intent.putExtra("descripcion", ropa.descripcion)
            intent.putExtra("precio", ropa.precio)
            intent.putExtra("estado", ropa.estado)
            intent.putExtra("aso", ropa.aso)
            intent.putExtra("porcentaje", ropa.porcentaje)
            intent.putExtra("donativo", ropa.donativo)
            startActivity(intent)
        }

        private fun obtenerDatos(correo: String?) {
            conInventarioRopa.verPrendasRopa(correo) { ropa ->
                if (ropa != null) {
                    adapter = cardAdapterRopa(ropa, this)
                    recycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                } else {
                    // Manejar el caso de error
                }
            }
        }


}