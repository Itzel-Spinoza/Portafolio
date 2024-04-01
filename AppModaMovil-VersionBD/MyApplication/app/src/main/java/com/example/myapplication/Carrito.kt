package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Carrito : AppCompatActivity(), CardAdapterPrendasCarrito.OnItemClickListener  {
    val mVenta = MVenta()
    private val conComprar = ConComprar(mVenta)
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

        recyclerCarrito.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        conComprar.mostrarPrendasCarrito(correo) { ropa ->
            if (ropa != null) {
                adapter = CardAdapterPrendasCarrito(ropa, this)
                recyclerCarrito.adapter = adapter
            } else {
                // Manejar el caso de error
            }
        }

        conComprar.calcularTotalCompra(correo) { totalCompra ->
            totalDeCompra.text = totalCompra.toString()
        }

        conComprar.calcularTotalDonativos(correo) { totalCompraDonativos ->
            totalDonativos.text = totalCompraDonativos.toString()
        }
        btnCarrito.setOnClickListener {
            /*conComprar.comprobarListaVacia(correo) { listaVacia ->
                if (listaVacia) {
                    showToast("No tienes productos en tu carrito.")
                } else {
                    val intent = Intent(this, PasarelaDireccion::class.java)
                    intent.putExtra("email", correo)
                    //intent.putExtra("totalPedido", conComprar.calcularTotalCompra(correo).toString())
                    //intent.putExtra("totalDonativos", conComprar.calcularTotalDonativos(correo).toString())
                    //startActivity(intent)

                    conComprar.calcularTotalCompra(correo) { totalCompra ->
                        intent.putExtra("totalPedido", totalCompra.toString())
                    }

                    conComprar.calcularTotalDonativos(correo) { totalDonativos ->
                        intent.putExtra("totalDonativos", totalDonativos.toString())
                    }
                    startActivity(intent)
                }


            }*/

            conComprar.contarPrendasCarrito(correo) { cantidad ->
                if (cantidad == 0) {
                    showToast("No tienes productos en tu carrito.")
                } else {
                    val intent = Intent(this, PasarelaDireccion::class.java)
                    intent.putExtra("email", correo)
                    //intent.putExtra("totalPedido", conComprar.calcularTotalCompra(correo).toString())
                    //intent.putExtra("totalDonativos", conComprar.calcularTotalDonativos(correo).toString())
                    //startActivity(intent)
                    conComprar.calcularTotalCompra(correo) { totalCompra ->
                        val totalComp = totalCompra.toString()
                        intent.putExtra("totalPedido", totalComp)
                        println("total de compra" + totalComp)

                        // Inside the callback for totalCompra
                        conComprar.calcularTotalDonativos(correo) { totalDonativos ->
                            val totalDon = totalDonativos.toString()
                            intent.putExtra("totalDonativos", totalDon)

                            // Start activity inside the callback for totalDonativos
                            startActivity(intent)
                        }
                    }
                    /*conComprar.calcularTotalCompra(correo) { totalCompra ->
                        val totalComp = totalCompra.toString()
                        intent.putExtra("totalPedido", totalComp)
                        println("total de compra" + totalComp)
                    }

                    conComprar.calcularTotalDonativos(correo) { totalDonativos ->
                        val totalDon = totalDonativos.toString()
                        intent.putExtra("totalDonativos", totalDon)
                    }
                    startActivity(intent)*/
                }
            }

            obtenerDatos(correo)
        }
    }

    override fun onEliminarClick(ropaCarrito: RopaCarrito) {
        val correo = intent.getStringExtra("email")
        val ide = ropaCarrito.ide

        try {
            conComprar.eliminarPrendaCarrito2(this,correo, ide)
            //showToast("Prenda eliminada del carrito.")
            obtenerDatos(correo)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun obtenerDatos(correo: String?) {
        try {
            //val cardList = conComprar.mostrarPrendasCarrito(correo)
            //adapter = CardAdapterPrendasCarrito(cardList, this)
            //recyclerCarrito.adapter = adapter
            conComprar.mostrarPrendasCarrito(correo) { ropa ->
                if (ropa != null) {
                    adapter = CardAdapterPrendasCarrito(ropa, this)
                    recyclerCarrito.adapter = adapter
                } else {
                    // Manejar el caso de error
                }
            }

            conComprar.calcularTotalCompra(correo) { totalCompra ->
                totalDeCompra.text = totalCompra.toString()
            }

            conComprar.calcularTotalDonativos(correo) { totalCompraDonativos ->
                totalDonativos.text = totalCompraDonativos.toString()
            }

            //totalDonativos.text = conComprar.calcularTotalDonativos(correo).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}