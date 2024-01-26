package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Pedidos : AppCompatActivity() {
    lateinit var adapterPedidos: CardAdapterPedidos
    lateinit var recyclerPedidos: RecyclerView
    val conPedidos = ConPedidos(DataRepository)
    lateinit var btnPendientes: Button
    lateinit var btnEntregados: Button
    lateinit var btnTodos: Button
    lateinit var txtEtiquetaFiltro: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)
        btnPendientes = findViewById(R.id.btnPendientes)
        btnEntregados = findViewById(R.id.btnEntregado)
        btnTodos = findViewById(R.id.btnTodo)
        txtEtiquetaFiltro = findViewById(R.id.txtBoton)

        val correo = intent.getStringExtra("email")

        recyclerPedidos = findViewById(R.id.recyclerPedidos)
        recyclerPedidos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val cardList = conPedidos.verPedidos(correo)

        adapterPedidos = CardAdapterPedidos(cardList,this)
        recyclerPedidos.adapter = adapterPedidos
        adapterPedidos.notifyDataSetChanged()

        btnTodos.setOnClickListener(View.OnClickListener {
            txtEtiquetaFiltro.setText("Todo")
            val cardList = conPedidos.verPedidos(correo)
            adapterPedidos.actualizarDatos(cardList)
        })

        btnEntregados.setOnClickListener(View.OnClickListener {
            txtEtiquetaFiltro.setText("Entregados")
            val estado = "Entregado"
            val cardList = conPedidos.filtrarPedido(estado,correo)
            adapterPedidos.actualizarDatos(cardList)
        })

        btnPendientes.setOnClickListener(View.OnClickListener {
            txtEtiquetaFiltro.setText("Pendientes")
            val estado = "Pendiente"
            val cardList = conPedidos.filtrarPedido(estado,correo)
            adapterPedidos.actualizarDatos(cardList)
        })

    }

    override fun onResume() {
        super.onResume()
        val correo = intent.getStringExtra("email")
        obtenerDatos(correo)
    }
    private fun obtenerDatos(correo: String?) {
        val cardList = conPedidos.verPedidos(correo)
        adapterPedidos = CardAdapterPedidos(cardList,this)
        recyclerPedidos.adapter = adapterPedidos
        adapterPedidos.notifyDataSetChanged()
    }

    fun onItemClick(pedido: Pedido) {
        val intent = Intent(this, VerPedidos::class.java)
        intent.putExtra("id", pedido.idPedido)
        intent.putExtra("correo",pedido.correoVendedor)
        intent.putExtra("nombreCliente", pedido.nombreCliente)
        intent.putExtra("direccion", pedido.direccion)
        intent.putExtra("fechaPedido", pedido.fecha)
        startActivity(intent)
    }

}