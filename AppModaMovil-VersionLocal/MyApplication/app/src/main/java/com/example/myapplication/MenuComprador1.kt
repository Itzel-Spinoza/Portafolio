package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MenuComprador1 : AppCompatActivity() {
    lateinit var btnPedidos: Button
    lateinit var btnDonativos: Button
    lateinit var btnEditarPerfil: Button
    lateinit var btnCambiarContrasena: Button
    lateinit var btnDirecciones: Button
    lateinit var btnDatosPago: Button
    lateinit var btnCerrarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_comprador1)

        btnPedidos = findViewById(R.id.btnPedidos)
        btnDonativos = findViewById(R.id.btnDonativos)
        btnEditarPerfil = findViewById(R.id.btnEditarPerfil)
        btnCambiarContrasena = findViewById(R.id.btnCambiarContrasena)
        btnDirecciones = findViewById(R.id.btnDirecciones)
        btnDatosPago = findViewById(R.id.btnDatosPago)
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)

        val correo = intent.getStringExtra("email")

        btnCerrarSesion.setOnClickListener(View.OnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)

        })

        btnDonativos.setOnClickListener(View.OnClickListener {
            val intenUsuario= Intent(this,Donaciones::class.java)
            intenUsuario.putExtra("email", correo)
            startActivity(intenUsuario)
        })

        btnPedidos.setOnClickListener(View.OnClickListener {
            val intenUsuario= Intent(this,PedidosCliente::class.java)
            intenUsuario.putExtra("email", correo)
            startActivity(intenUsuario)
        })
        btnEditarPerfil.setOnClickListener(View.OnClickListener {
            val intenUsuario= Intent(this,EditarPerfil::class.java)
            intenUsuario.putExtra("email", correo)
            startActivity(intenUsuario)
        })

        btnCambiarContrasena.setOnClickListener(View.OnClickListener {
            val intenCambiarContrasena= Intent(this,CambiarContrasena::class.java)
            intenCambiarContrasena.putExtra("email", correo)
            startActivity(intenCambiarContrasena)
        })

        btnDirecciones.setOnClickListener(View.OnClickListener {
            val intenDirecciones = Intent(this,AgregarDirecciones::class.java)
            intenDirecciones.putExtra("email",correo)
            startActivity(intenDirecciones)
        })

        btnDatosPago.setOnClickListener(View.OnClickListener {
            val intenMetPag = Intent(this,AgregarDatosPago::class.java)
            intenMetPag.putExtra("email",correo)
            startActivity(intenMetPag)
        })
    }
}