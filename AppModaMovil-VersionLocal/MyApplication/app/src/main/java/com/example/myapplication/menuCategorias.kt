package com.example.myapplication

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.View
import android.widget.Button
import android.widget.ImageView

class menuCategorias : AppCompatActivity() {
    val conInventario = ConInventario(DataRepository)
    lateinit var btnMujer: Button
    lateinit var btnHombre: Button

    lateinit var btnCamisasM: Button
    lateinit var btnPantalonesM: Button
    lateinit var btnShortsM: Button
    lateinit var btnSudaderasM: Button
    lateinit var btnJeansM: Button
    lateinit var btnVestidosM: Button
    lateinit var btnSueteresM: Button
    lateinit var btnAccesoriosM: Button
    lateinit var btnChamarrasM: Button
    lateinit var btnTopsM: Button
    lateinit var btnFaldasM: Button

    lateinit var imgCamisasM: ImageView
    lateinit var imgPantalonesM: ImageView
    lateinit var imgShortsM: ImageView
    lateinit var imgSudaderasM: ImageView
    lateinit var imgJeansM: ImageView
    lateinit var imgVestidosM: ImageView
    lateinit var imgSueteresM: ImageView
    lateinit var imgAccesoriosM: ImageView
    lateinit var imgChamarrasM: ImageView
    lateinit var imgTopsM: ImageView
    lateinit var imgFaldasM: ImageView

    var correo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_categorias)

        btnMujer = findViewById(R.id.btnClasificarMujeres)
        btnHombre = findViewById(R.id.btnClasificarHombres)

        btnCamisasM = findViewById(R.id.btnCamisas)
        btnPantalonesM = findViewById(R.id.btnPantalones)
        btnShortsM = findViewById(R.id.btnShorts)
        btnSudaderasM = findViewById(R.id.btnSudaderas)
        btnJeansM = findViewById(R.id.btnJeans)
        btnVestidosM = findViewById(R.id.btnVestidos)
        btnSueteresM = findViewById(R.id.btnSueteres)
        btnAccesoriosM = findViewById(R.id.btnAccesorios)
        btnChamarrasM = findViewById(R.id.btnChamarras)
        btnTopsM = findViewById(R.id.btnTops)
        btnFaldasM = findViewById(R.id.btnFaldas)

        imgCamisasM = findViewById(R.id.imgCamisas)
        imgPantalonesM = findViewById(R.id.imgPantalones)
        imgShortsM = findViewById(R.id.imgShorts)
        imgSudaderasM = findViewById(R.id.imgSudaderas)
        imgJeansM = findViewById(R.id.imgJeans)
        imgVestidosM = findViewById(R.id.imgVestido)
        imgSueteresM = findViewById(R.id.imgSueter)
        imgAccesoriosM = findViewById(R.id.imgAccesorios)
        imgChamarrasM = findViewById(R.id.imgChamarras)
        imgTopsM = findViewById(R.id.imgTops)
        imgFaldasM = findViewById(R.id.imgFaldas)

        btnCamisasM.setOnClickListener { onButtonClick(1) }
        btnPantalonesM.setOnClickListener { onButtonClick(2) }
        btnShortsM.setOnClickListener { onButtonClick(3) }
        btnSudaderasM.setOnClickListener { onButtonClick(4) }
        btnJeansM.setOnClickListener { onButtonClick(5) }
        btnVestidosM.setOnClickListener { onButtonClick(6) }
        btnSueteresM.setOnClickListener { onButtonClick(7) }
        btnAccesoriosM.setOnClickListener { onButtonClick(8) }
        btnChamarrasM.setOnClickListener { onButtonClick(9) }
        btnTopsM.setOnClickListener { onButtonClick(10) }
        btnFaldasM.setOnClickListener { onButtonClick(11) }

        correo = intent.getStringExtra("email")

        btnMujer.setOnClickListener(View.OnClickListener {
            btnCamisasM.visibility = View.VISIBLE
            btnPantalonesM.visibility = View.VISIBLE
            btnShortsM.visibility = View.VISIBLE
            btnSudaderasM.visibility = View.VISIBLE
            btnJeansM.visibility = View.VISIBLE
            btnVestidosM.visibility = View.VISIBLE
            btnSueteresM.visibility = View.VISIBLE
            btnAccesoriosM.visibility = View.VISIBLE
            btnChamarrasM.visibility = View.VISIBLE
            btnTopsM.visibility = View.VISIBLE
            btnFaldasM.visibility = View.VISIBLE

            imgCamisasM.visibility = View.VISIBLE
            imgPantalonesM.visibility = View.VISIBLE
            imgShortsM.visibility = View.VISIBLE
            imgSudaderasM.visibility = View.VISIBLE
            imgJeansM.visibility = View.VISIBLE
            imgVestidosM.visibility = View.VISIBLE
            imgSueteresM.visibility = View.VISIBLE
            imgAccesoriosM.visibility = View.VISIBLE
            imgChamarrasM.visibility = View.VISIBLE
            imgTopsM.visibility = View.VISIBLE
            imgFaldasM.visibility = View.VISIBLE

        })

        
    }

    private fun onButtonClick(buttonNumber: Int) {
        when (buttonNumber) {
            1 -> {
                val categoria = "Camisas y blusas"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)

            }
            2 -> {
                val categoria = "Pantalones"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            3 -> {
                val categoria = "Shorts"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            4 -> {
                val categoria = "Sudaderas"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            5 -> {
                val categoria = "Jeans"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            6 -> {
                val categoria = "Vestidos"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            7 -> {
                val categoria = "Sueteres"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            8 -> {
                val categoria = "Accesorios"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            9 -> {
                val categoria = "Chamarras y abrigos"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            10 -> {
                val categoria = "Tops"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            11 -> {
                val categoria = "Faldas"
                val genero = "Mujer"
                val intentCatRopa = Intent(this,VerPrendasCategoria::class.java)
                intentCatRopa.putExtra("email", correo)
                intentCatRopa.putExtra("categoria", categoria)
                intentCatRopa.putExtra("genero", genero)
                startActivity(intentCatRopa)
            }
            else -> {

            }
    }
}
    }

