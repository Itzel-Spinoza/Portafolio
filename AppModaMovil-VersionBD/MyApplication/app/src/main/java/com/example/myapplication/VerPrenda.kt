package com.example.myapplication

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerPrenda : AppCompatActivity() {
    val mRopa = MRopa()
    val conInventario = ConInventario(mRopa)
    val mVenta = MVenta()
    private val conComprar = ConComprar(mVenta)
    lateinit var txtNombrePrenda: TextView
    lateinit var txtNombreVendedor: TextView
    lateinit var txtPrecioPrenda: TextView
    lateinit var txtTalla: TextView
    lateinit var txtDescripcion: TextView
    lateinit var txtNombreAsociacion: TextView
    lateinit var txtPorcentaje: TextView
    lateinit var txtDonatvio: TextView
    lateinit var btnAgregarPrenda: Button
    //var nombree = ""
    lateinit var nombree: String
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

       // val nombree = conInventario.devolverNombreVendedor(correo)



        conInventario.devolverNombreVendedor(correo) { nombreCompleto ->
            if (nombreCompleto != null) {
                nombree = nombreCompleto
                txtNombreVendedor.setText(nombree)
            } else {
                // Manejar el caso cuando no se encuentra el nombre
            }
        }
        //txtNombreVendedor.setText(nombree)
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