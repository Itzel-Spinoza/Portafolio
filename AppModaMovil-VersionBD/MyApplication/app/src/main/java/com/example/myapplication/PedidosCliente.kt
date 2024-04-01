package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PedidosCliente : AppCompatActivity() {

    lateinit var adapterPedidos: CardAdapterPedidosCliente
    lateinit var recyclerPedidos: RecyclerView
    val mPedidos = MPedidos()
    val conPedidosCliente = ConPedidosCliente(mPedidos)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_cliente)

        val correo = intent.getStringExtra("email")
        recyclerPedidos = findViewById(R.id.recyclerPedidosCliente)
        recyclerPedidos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //val cardList = conPedidosCliente.verPedidos(correo)

       // adapterPedidos = CardAdapterPedidosCliente(cardList,this)
        //recyclerPedidos.adapter = adapterPedidos
       // adapterPedidos.notifyDataSetChanged()

        conPedidosCliente.verPedidos(correo) { pedidos ->
            if (pedidos != null) {
                adapterPedidos = CardAdapterPedidosCliente(pedidos, this)
                recyclerPedidos.adapter = adapterPedidos
            } else {
                // Manejar el caso de error
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val correo = intent.getStringExtra("email")
        obtenerDatos(correo)
    }
    private fun obtenerDatos(correo: String?) {
        conPedidosCliente.verPedidos(correo) { pedidos ->
            if (pedidos != null) {
                adapterPedidos = CardAdapterPedidosCliente(pedidos, this)
                recyclerPedidos.adapter = adapterPedidos
            } else {
                // Manejar el caso de error
            }
        }
    }

    fun onItemClick(pedidosGeneral:PedidosGeneral) {
        val intent = Intent(this, VerPedidosCliente::class.java)
        intent.putExtra("id", pedidosGeneral.idPedido)
        intent.putExtra("correo",pedidosGeneral.correoCliente)
        intent.putExtra("nombreCliente", pedidosGeneral.nombreCliente)
        intent.putExtra("direccion", pedidosGeneral.direccionCliente)
        intent.putExtra("fechaPedido", pedidosGeneral.fechaPedido)
        intent.putExtra("totalPedido", pedidosGeneral.totalPedido)
        intent.putExtra("totalDonacion", pedidosGeneral.totalDonacion)
        startActivity(intent)
    }
}