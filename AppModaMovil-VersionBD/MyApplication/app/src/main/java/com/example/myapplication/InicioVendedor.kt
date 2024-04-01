package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
class InicioVendedor : AppCompatActivity() {
    lateinit var btnMenu: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_vendedor)
        btnMenu = findViewById(R.id.btnMenu)
        val correo = intent.getStringExtra("email")

        val menubarra = findViewById<BottomNavigationView>(R.id.barraMenuu)
        menubarra.selectedItemId = R.id.btnInicio
        menubarra.selectedItemId = R.id.btnPedidos

        btnMenu.setOnClickListener(View.OnClickListener {
            val intenMenu= Intent(this,MenuVendedor::class.java)
            intenMenu.putExtra("email", correo)
            startActivity(intenMenu)
        })



        // Establece un listener para manejar los clics en los elementos del menú
        menubarra.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnInicio -> {
                    // Abre la actividad de inicio
                    //startActivity(Intent(this, ActividadDeInicio::class.java))
                    true
                }
                R.id.btnRopa -> {
                    // Abre la actividad de perfil
                    val intenInven= Intent(this,RopaVendedor::class.java)
                    intenInven.putExtra("email", correo)
                    startActivity(intenInven)
                    menubarra.selectedItemId = R.id.btnInicio
                    false
                }
                R.id.btnPedidos -> {
                    // Abre la actividad de pedidos
                    //startActivity(Intent(this, ActividadDePedidos::class.java))
                    val intenPedidos = Intent(this,Pedidos::class.java)
                    intenPedidos.putExtra("email", correo)
                    startActivity(intenPedidos)
                    menubarra.selectedItemId = R.id.btnInicio
                    false
                }
                else -> false
            }
        }
    }
}