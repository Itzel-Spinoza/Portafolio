package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class VentanaPedidoRealizado : AppCompatActivity() {

    lateinit var btnVolverInicio: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_pedido_realizado)

        btnVolverInicio = findViewById(R.id.btnIrInicio)

        val correo = intent.getStringExtra("email")

        btnVolverInicio.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Inicio::class.java)
            intent.putExtra("email", correo)
            startActivity(intent)
        })
    }
}