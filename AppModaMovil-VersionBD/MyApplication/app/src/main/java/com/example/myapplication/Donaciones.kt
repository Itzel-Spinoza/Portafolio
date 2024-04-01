package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Donaciones : AppCompatActivity() {
    lateinit var adapterDonaciones: CardAdapterDonaciones
    lateinit var recyclerDonaciones: RecyclerView
    val mDonativos = MDonativos()
    val conDonaciones = ConDonaciones(mDonativos)
    val tipo: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donaciones)

        val correo = intent.getStringExtra("email")
        recyclerDonaciones = findViewById(R.id.recyclerDonacion)
        recyclerDonaciones.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //val cardList = conDonaciones.mostrarDonacionesDepCorreo(correo)

        //adapterDonaciones = CardAdapterDonaciones(cardList,this)
        //recyclerDonaciones.adapter = adapterDonaciones
        //adapterDonaciones.notifyDataSetChanged()


        conDonaciones.verificarTipo(correo, this) { resultado ->
            val tipoUsuario = resultado
            if (tipoUsuario == "Comprador"){
                conDonaciones.mostrarDonacionesComprador(correo) { donaciones ->
                    if (donaciones != null) {
                        adapterDonaciones = CardAdapterDonaciones(donaciones, this)
                        recyclerDonaciones.adapter = adapterDonaciones
                    } else {

                    }
                }
            }else{
                conDonaciones.mostrarDonacionesVendedor(correo) { donaciones ->
                    if (donaciones != null) {
                        adapterDonaciones = CardAdapterDonaciones(donaciones, this)
                        recyclerDonaciones.adapter = adapterDonaciones
                    } else {

                    }
                }
            }


        }



    }

    override fun onResume() {
        super.onResume()
        val correo = intent.getStringExtra("email")
        obtenerDatos(correo)
    }
    private fun obtenerDatos(correo: String?) {
        //val cardList = conDonaciones.mostrarDonacionesDepCorreo(correo)
        //adapterDonaciones = CardAdapterDonaciones(cardList,this)
        //recyclerDonaciones.adapter = adapterDonaciones
        //adapterDonaciones.notifyDataSetChanged()
        conDonaciones.verificarTipo(correo, this) { resultado ->
            val tipoUsuario = resultado
            if (tipoUsuario == "Comprador"){
                conDonaciones.mostrarDonacionesComprador(correo) { donaciones ->
                    if (donaciones != null) {
                        adapterDonaciones = CardAdapterDonaciones(donaciones, this)
                        recyclerDonaciones.adapter = adapterDonaciones
                    } else {

                    }
                }
            }else{
                conDonaciones.mostrarDonacionesVendedor(correo) { donaciones ->
                    if (donaciones != null) {
                        adapterDonaciones = CardAdapterDonaciones(donaciones, this)
                        recyclerDonaciones.adapter = adapterDonaciones
                    } else {

                    }
                }
            }


        }

    }
}