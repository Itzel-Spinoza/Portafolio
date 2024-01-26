package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class Inicio : AppCompatActivity() {
    lateinit var btnMenu: Button
    val conComprar = ConComprar(DataRepository)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_comprador)

        btnMenu = findViewById(R.id.btnMenu)

        val correo = intent.getStringExtra("email")

        val menubarra = findViewById<BottomNavigationView>(R.id.barraComprador)
        menubarra.selectedItemId = R.id.btnInicio

        val menuCarrito = menubarra.menu.findItem(R.id.menuCarrito)

        val elementosCarrito = conComprar.contarPrendasCarrito(correo)

        menuCarrito.title = getString(R.string.btn_carrito) + " ($elementosCarrito)"

        btnMenu.setOnClickListener(View.OnClickListener {
            val intenMenu= Intent(this,MenuComprador1::class.java)
            intenMenu.putExtra("email", correo)
            startActivity(intenMenu)
        })

        menubarra.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuInicio -> {

                    true
                }
                R.id.menuRopa -> {

                    val intenRopa= Intent(this,menuCategorias::class.java)
                    intenRopa.putExtra("email", correo)
                    startActivity(intenRopa)
                    menubarra.selectedItemId = R.id.btnInicio
                    false
                }
                R.id.menuCarrito -> {

                    val intentCarrito = Intent(this,Carrito::class.java)
                    intentCarrito.putExtra("email", correo)
                    startActivity(intentCarrito)
                    menubarra.selectedItemId = R.id.btnInicio
                    false
                }
                else -> false
            }
        }
    }
    override fun onResume() {
        super.onResume()
        val correo = intent.getStringExtra("email")
        val menubarra = findViewById<BottomNavigationView>(R.id.barraComprador)
        val menuCarrito = menubarra.menu.findItem(R.id.menuCarrito)

        val elementosCarrito = conComprar.contarPrendasCarrito(correo)

        menuCarrito.title = getString(R.string.btn_carrito) + " ($elementosCarrito)"
    }

}