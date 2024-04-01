package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var btnIniciarSesion: Button
    lateinit var btnCrearCuenta: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnCrearCuenta = findViewById(R.id.btnRegistrarse)

        btnIniciarSesion.setOnClickListener(View.OnClickListener {
            val intentIniciarSesion = Intent(this,IniciarSesion::class.java)
            startActivity(intentIniciarSesion)
        })

        btnCrearCuenta.setOnClickListener(View.OnClickListener {
            val intenCrearCuenta = Intent(this,CrearCuenta::class.java)
            startActivity(intenCrearCuenta)
        })

    }
}