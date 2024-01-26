package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerPedidosCliente : AppCompatActivity() {
    val conPedidosCliente = ConPedidosCliente(DataRepository)
    lateinit var txtIdPedido: TextView
    lateinit var direccionPedido: TextView
    lateinit var nombreCliente: TextView
    lateinit var fechaPedido: TextView
    lateinit var txtDonacion: TextView
    lateinit var txtTotalPedido: TextView
    lateinit var recyclerRopaPedido: RecyclerView
    lateinit var adapterRopaPedido: CardAdapterPrendasPedidosCliente
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pedidos_cliente)

        txtIdPedido = findViewById(R.id.IDLabel)
        direccionPedido = findViewById(R.id.lblDirección)
        nombreCliente = findViewById(R.id.labelClientee)
        fechaPedido = findViewById(R.id.lblFechaPedido)
        txtDonacion = findViewById(R.id.donativoClientePedido)
        txtTotalPedido = findViewById(R.id.totalPedidoCliente)
        recyclerRopaPedido = findViewById(R.id.recyclerPrendasCat)

        recyclerRopaPedido.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val idPedido = intent.getStringExtra("id")

        val direccion = intent.getStringExtra("direccion")
        val cliente = intent.getStringExtra("nombreCliente")
        val fecha = intent.getStringExtra("fechaPedido")
        val totalPedido = intent.getStringExtra("totalPedido")
        val totalDonacion = intent.getStringExtra("totalDonacion")

        val cardList = conPedidosCliente.verPrendasPedido(idPedido)
        adapterRopaPedido = CardAdapterPrendasPedidosCliente(cardList,this)
        recyclerRopaPedido.adapter = adapterRopaPedido
        adapterRopaPedido.notifyDataSetChanged()

        txtTotalPedido.setText("$" + totalPedido)
        txtDonacion.setText("$" + totalDonacion)
        txtIdPedido.setText(idPedido)
        direccionPedido.setText(direccion)
        nombreCliente.setText(cliente)
        fechaPedido.setText(fecha)
    }
}