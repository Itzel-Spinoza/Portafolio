package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class CuentaCreadaExitosa : AppCompatActivity() {
    lateinit var btnIniciarSesion: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta_creada_exitosa)

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener(View.OnClickListener {
            val intenIniSesion = Intent(this,IniciarSesion::class.java)
            startActivity(intenIniSesion)
        })
    }
}