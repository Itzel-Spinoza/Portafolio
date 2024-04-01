package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Pedidos : AppCompatActivity() {
    lateinit var adapterPedidos: CardAdapterPedidos
    lateinit var recyclerPedidos: RecyclerView
    val mPedidos = MPedidos()
    val conPedidos = ConPedidos(mPedidos)
    lateinit var btnPendientes: Button
    lateinit var btnEntregados: Button
    lateinit var btnTodos: Button
    lateinit var txtEtiquetaFiltro: TextView

    lateinit var correo:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)
        btnPendientes = findViewById(R.id.btnPendientes)
        btnEntregados = findViewById(R.id.btnEntregado)
        btnTodos = findViewById(R.id.btnTodo)
        txtEtiquetaFiltro = findViewById(R.id.txtBoton)

        correo = intent.getStringExtra("email").toString()

        recyclerPedidos = findViewById(R.id.recyclerPedidos)
        recyclerPedidos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        conPedidos.verPedidos(correo) { pedidos ->
            if (pedidos != null) {
                adapterPedidos = CardAdapterPedidos(pedidos, this)
                recyclerPedidos.adapter = adapterPedidos
            } else {
                // Manejar el caso de error
            }
        }
        //val cardList = conPedidos.verPedidos(correo)

        //adapterPedidos = CardAdapterPedidos(cardList,this)
        //recyclerPedidos.adapter = adapterPedidos
        //adapterPedidos.notifyDataSetChanged()

        btnTodos.setOnClickListener(View.OnClickListener {
            txtEtiquetaFiltro.setText("Todo")
            conPedidos.verPedidos(correo) { pedidos ->
                if (pedidos != null) {
                    adapterPedidos = CardAdapterPedidos(pedidos, this)
                    recyclerPedidos.adapter = adapterPedidos
                } else {
                    // Manejar el caso de error
                }
            }
        })

        btnEntregados.setOnClickListener(View.OnClickListener {
            txtEtiquetaFiltro.setText("Entregados")
            val estado = "Entregado"
            conPedidos.filtrarPedido(estado,correo) { pedidos ->
                if (pedidos != null) {
                    adapterPedidos = CardAdapterPedidos(pedidos, this)
                    recyclerPedidos.adapter = adapterPedidos
                } else {
                    // Manejar el caso de error
                }
            }
        })

        btnPendientes.setOnClickListener(View.OnClickListener {
            txtEtiquetaFiltro.setText("Pendientes")
            val estado = "Pendiente"
            conPedidos.filtrarPedido(estado,correo) { pedidos ->
                if (pedidos != null) {
                    adapterPedidos = CardAdapterPedidos(pedidos, this)
                    recyclerPedidos.adapter = adapterPedidos
                } else {
                    // Manejar el caso de error
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        val correo = intent.getStringExtra("email")
        obtenerDatos(correo)
    }
    private fun obtenerDatos(correo: String?) {
        conPedidos.verPedidos(correo) { pedidos ->
            if (pedidos != null) {
                adapterPedidos = CardAdapterPedidos(pedidos, this)
                recyclerPedidos.adapter = adapterPedidos
            } else {
                // Manejar el caso de error
            }
        }
    }

    fun onItemClick(pedido: Pedido) {
        val intent = Intent(this, VerPedidos::class.java)
        intent.putExtra("id", pedido.idPedido)
        intent.putExtra("correo",pedido.correoVendedor)
        intent.putExtra("nombreCliente", pedido.nombreCliente)
        intent.putExtra("direccion", pedido.direccion)
        intent.putExtra("fechaPedido", pedido.fechaPedido)
        intent.putExtra("email", correo)
        startActivity(intent)
    }

}