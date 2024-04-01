package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerPedidos : AppCompatActivity() {
    val mPedidos = MPedidos()
    val conPedidos = ConPedidos(mPedidos)
    lateinit var txtIdPedido: TextView
    lateinit var direccionPedido: TextView
    lateinit var nombreCliente: TextView
    lateinit var fechaPedido: TextView
    lateinit var txtGanancia: TextView
    lateinit var recyclerRopaPedido: RecyclerView
    lateinit var adapterRopaPedido: CardAdapterPrendasPedido
    lateinit var botonPedidoCompleto : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pedidos)

        txtGanancia = findViewById(R.id.txtTotalGanancias)
        txtIdPedido = findViewById(R.id.IDLabel)
        direccionPedido = findViewById(R.id.lblDirección)
        nombreCliente = findViewById(R.id.labelClientee)
        fechaPedido = findViewById(R.id.lblFechaPedido)
        botonPedidoCompleto = findViewById(R.id.btnCompPedido)
        recyclerRopaPedido = findViewById(R.id.recyclerPrendasCat)
        recyclerRopaPedido.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        val idPedido = intent.getStringExtra("id")
        val direccion = intent.getStringExtra("direccion")
        val cliente = intent.getStringExtra("nombreCliente")
        val fecha = intent.getStringExtra("fechaPedido")
        val correo = intent.getStringExtra("email")

        //val ganancia = conPedidos.calcularGanancia(idPedido)
        //val cardList = conPedidos.verPrendasPedido(idPedido)
        //adapterRopaPedido = CardAdapterPrendasPedido(cardList,this)
        //recyclerRopaPedido.adapter = adapterRopaPedido
        //adapterRopaPedido.notifyDataSetChanged()
        conPedidos.verPrendasPedido(correo,idPedido) { pedidos ->
            if (pedidos != null) {
                adapterRopaPedido = CardAdapterPrendasPedido(pedidos, this)
                recyclerRopaPedido.adapter = adapterRopaPedido
            } else {
                // Manejar el caso de error
            }
        }


            conPedidos.calcularGanancia(idPedido) { resultado ->
                // Aquí manejas el resultado obtenido
                // Puedes mostrarlo en un TextView, por ejemplo
                txtGanancia.setText(resultado)
            }

        //txtGanancia.setText(ganancia)
        txtIdPedido.setText(idPedido)
        direccionPedido.setText(direccion)
        nombreCliente.setText(cliente)
        fechaPedido.setText(fecha)

        botonPedidoCompleto.setOnClickListener(View.OnClickListener {
            val estado = "Entregado"
            conPedidos.marcarCompletadoPedido(this,idPedido,estado)

            conPedidos.marcarCompletadoPedidoGeneral(this,idPedido,estado)

        })



    }
}