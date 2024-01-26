package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class VerPrenda : AppCompatActivity() {
    val conInventario = ConInventario(DataRepository)
    val conComprar = ConComprar(DataRepository)
    lateinit var txtNombrePrenda: TextView
    lateinit var txtNombreVendedor: TextView
    lateinit var txtPrecioPrenda: TextView
    lateinit var txtTalla: TextView
    lateinit var txtDescripcion: TextView
    lateinit var txtNombreAsociacion: TextView
    lateinit var txtPorcentaje: TextView
    lateinit var txtDonatvio: TextView
    lateinit var btnAgregarPrenda: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_prenda)
        val correoCliente = intent.getStringExtra("email")
        txtNombrePrenda = findViewById(R.id.txtNombrePrendaVer)
        txtNombreVendedor = findViewById(R.id.txtNombreVendedor)
        txtPrecioPrenda = findViewById(R.id.txtPrecioPrendaVer)
        txtTalla = findViewById(R.id.txtTallaVer)
        txtDescripcion = findViewById(R.id.txtDescripcionPrendaa)
        txtNombreAsociacion = findViewById(R.id.txtAsociacionDonativo)
        txtPorcentaje = findViewById(R.id.txtPorcentajeDonativo)
        txtDonatvio = findViewById(R.id.txtPrecioDonativo)
        btnAgregarPrenda = findViewById(R.id.btnAgregarProductoCarrito)
        txtDescripcion.inputType = InputType.TYPE_NULL
        val id = intent.getStringExtra("id")
        val correo = intent.getStringExtra("correo")
        val nombrePrenda = intent.getStringExtra("nombrePrenda")
        val talla = intent.getStringExtra("talla")
        val categoria = intent.getStringExtra("categoria")
        val genero = intent.getStringExtra("genero")
        val descripcion = intent.getStringExtra("descripcion")
        val precio = intent.getStringExtra("precio")
        val estado = intent.getStringExtra("estado")
        val asociacion = intent.getStringExtra("aso")
        val porcentaje = intent.getStringExtra("porcentaje")
        var donativo = intent.getStringExtra("donativo")
        val foto = intent.getStringExtra("foto")
        val aso = intent.getStringExtra("aso")
        makeEditTextsEditable(false)
        txtNombrePrenda.setText(nombrePrenda)
        val nombree = conInventario.devolverNombreVendedor(correo)
        txtNombreVendedor.setText(nombree)
        txtPrecioPrenda.setText(precio)
        txtTalla.setText(talla)
        txtDescripcion.setText(descripcion)
        txtNombreAsociacion.setText(asociacion)
        txtPorcentaje.setText(porcentaje + "%")
        txtDonatvio.setText("$" + donativo)


        btnAgregarPrenda.setOnClickListener(View.OnClickListener {
            conInventario.agregarAlCarrito(this,id,correoCliente,correo,nombrePrenda,talla,categoria,genero,descripcion,foto,precio,porcentaje,aso,donativo,estado,nombree)

        })
    }

    fun makeEditTextsEditable(editable: Boolean) {
        txtDescripcion.isEnabled = editable


    }
    fun showToast(str:String){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
    }


}