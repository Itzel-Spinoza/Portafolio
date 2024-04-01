package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup


class AgregarPrenda : AppCompatActivity() {

    val mRopa = MRopa()
    val conInventarioRopa = ConInventarioRopa(mRopa)

    lateinit var adapter: cardAdapterAsociaciones
    lateinit var recycler: RecyclerView
    lateinit var btnAgregarPrenda: Button
    lateinit var btnCancelar:Button
    lateinit var btnSubir: Button
    lateinit var edtNombrePrenda: EditText
    lateinit var edtDescripcion: EditText
    lateinit var edtPrecioPrenda: EditText
    lateinit var edtPorcentaje: EditText
    lateinit var spnCategoria: Spinner

    lateinit var lblPrecio:TextView
    lateinit var lbldonativo:TextView

    lateinit var grupotalla : MaterialButtonToggleGroup
    lateinit var btnXS: Button
    lateinit var btnS: Button
    lateinit var btnM: Button
    lateinit var btnL: Button
    lateinit var btnXL: Button
    lateinit var btnUni: Button

    lateinit var grupoGenero: MaterialButtonToggleGroup
    lateinit var btnMujer: Button
    lateinit var btnHombre: Button

    var genero : String = ""
    var talla: String = ""
    var donativoDef: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_prenda)

        edtNombrePrenda = findViewById(R.id.edtNombrePrenda)
        edtDescripcion = findViewById(R.id.edtDescrip)
        edtPrecioPrenda = findViewById(R.id.txtPrecio)
        edtPorcentaje = findViewById(R.id.txtPorcentaje)
        lblPrecio = findViewById(R.id.txtPrecioP)
        lbldonativo = findViewById(R.id.txtDon)


        spnCategoria = findViewById<Spinner>(R.id.spnCat)
        grupoGenero = findViewById(R.id.grupo_genero2)
        btnMujer = findViewById(R.id.btnMujer)
        btnHombre = findViewById(R.id.btnHombre)

        grupotalla = findViewById(R.id.grupo_talla)
        btnXS = findViewById(R.id.btnXS)
        btnS = findViewById(R.id.btnS)
        btnM = findViewById(R.id.btnM)
        btnL = findViewById(R.id.btnG)
        btnXL = findViewById(R.id.btnXL)
        btnUni = findViewById(R.id.btnUni)

        btnAgregarPrenda = findViewById(R.id.btnSubirPren)
        btnCancelar = findViewById(R.id.btnCancelarAg)
        btnSubir = findViewById(R.id.btnSubFotos)

        recycler = findViewById(R.id.rviewAso)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val precio2 = edtPrecioPrenda.text.toString()
        val porcentaje2 = edtPorcentaje.text.toString()
        //val usuarios = Usuarios(DataRepository)
        val correo = intent.getStringExtra("email") ?: ""
        val cardList = conInventarioRopa.mostrarAsociaciones()
        adapter = cardAdapterAsociaciones(cardList)
        recycler.adapter = adapter
        var tarjetaSeleccionada: Asociacion? = null
        edtPrecioPrenda.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar esto en este caso
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario implementar esto en este caso
            }
            override fun afterTextChanged(s: Editable?) {
                try {
                    val precio = edtPrecioPrenda.text.toString()
                    if (precio.isNotEmpty()) {
                        lblPrecio.text = precio
                    }
                    else if(precio.isEmpty()){
                        lblPrecio.text = ""
                    }

                } catch (e: NumberFormatException) {
                    // Manejar la excepción si los valores no son números válidos
                    showToast("Ingrese números válidos en los campos de precio y porcentaje.")
                }
            }
        })
        edtPorcentaje.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar esto en este caso
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario implementar esto en este caso
            }
            override fun afterTextChanged(s: Editable?) {
                try {
                    val precio = edtPrecioPrenda.text.toString()
                    val porcentaje = edtPorcentaje.text.toString()

                    // Manejar el caso en que los campos estén vacíos o no sean números válidos
                    if (precio.isNotEmpty() && porcentaje.isNotEmpty()) {
                        val (descuento, precioRopa) = conInventarioRopa.calcularDonativo(precio, porcentaje)
                        lblPrecio.text = precioRopa.toString()
                        lbldonativo.text = descuento.toString()
                        donativoDef = descuento
                    }
                    else if(porcentaje.isEmpty()){
                        lbldonativo.text = ""
                    }
                    else if(precio.isEmpty()){
                        lblPrecio.text = ""
                    }
                } catch (e: NumberFormatException) {
                    // Manejar la excepción si los valores no son números válidos
                    showToast("Ingrese números válidos en los campos de precio y porcentaje.")
                }
            }
        })
        grupotalla.addOnButtonCheckedListener{grupotalla, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    btnXS.id -> {
                        talla = "XS"
                        showToast("Talla XS seleccionada")
                    }
                    btnS.id -> {
                        talla = "S"
                        showToast("Talla S seleccionada")
                    }
                    btnM.id -> {
                        talla = "M"
                        showToast("Talla M seleccionada")
                    }
                    btnL.id -> {
                        talla = "L"
                        showToast("Talla L seleccionada")
                    }
                    btnXL.id -> {
                        talla = "XL"
                        showToast("Talla XL seleccionada")
                    }
                    btnUni.id -> {
                        talla = "Unitalla"
                        showToast("Talla unitalla seleccionada")
                    }

                }
            } else{
                talla = ""

            }
        }
        grupoGenero.addOnButtonCheckedListener{grupoGenero, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    btnHombre.id -> {
                        genero = "Hombre"
                        showToast("Genero masculino seleccionado.")
                        //val cate = conInventarioRopa.mostrarcategoria(genero)
                        //val datos = DataRepository.listaCategoriasRopa

                        val adapterSpin = ArrayAdapter(this, android.R.layout.simple_spinner_item, conInventarioRopa.mostrarcategoria(genero))
                        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spnCategoria.adapter= adapterSpin
                    }

                    btnMujer.id -> {
                        genero = "Mujer"
                        showToast("Genero femenino seleccionado.")
                        val adapterSpin = ArrayAdapter(this, android.R.layout.simple_spinner_item, conInventarioRopa.mostrarcategoria(genero))
                        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spnCategoria.adapter= adapterSpin

                    }

                }
            } else{
                genero = ""
                val sinSeleccion = "No ha seleccionado genero"
                /*val adapterSpin = ArrayAdapter(this, android.R.layout.simple_spinner_item, sinSeleccion)
                adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spnCategoria.adapter= adapterSpin*/
                val adaptadorVacio = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listOf("No has seleccionado genero"))
                adaptadorVacio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spnCategoria.adapter= adaptadorVacio
            }
        }

        btnAgregarPrenda.setOnClickListener(View.OnClickListener {
            val nombreP = edtNombrePrenda.text.toString()
            val catego = spnCategoria.selectedItem.toString()
            val descripcion = edtDescripcion.text.toString()
            val precio = edtPrecioPrenda.text.toString()
            val porcentaje = edtPorcentaje.text.toString()
            val porcentajeF = porcentaje.toInt()
            //val asociacionSeleccionada = tarjetaSeleccionada?.nombreAs
            //val nombreAsociacion = asociacionSeleccionada?.nombreAs ?: ""
            val aso = "perres unidos"
            val foto = "sin foto"
            val estado = "Subido"
            val ide = conInventarioRopa.generarID()
            var idComp: String = ide  // O cualquier valor inicial que desees

            conInventarioRopa.comprobarID(ide) { nuevoID ->
                // Convertir nuevoID a String y asignarlo a idComp
                idComp = nuevoID.toString()
                // Aquí puedes realizar cualquier otra operación necesaria con el nuevo ID convertido a String
            }
            val asociacionSeleccionada = adapter.tarjetaSeleccionada?.nombreAs
            //val asociacionSeleccionada = adapter.nombreAsociaciones(
            if(nombreP.isEmpty()|| descripcion.isEmpty()||precio.isEmpty() ||porcentaje.isEmpty()){
                showToast("No has llenado campos")

            }else if(genero.isEmpty()){
                showToast("No has seleccionado ningún género")
            }else if(catego.isEmpty()){
                showToast("No has seleccionado categoría para la prenda")

            } else if(porcentajeF >= 100 && porcentajeF <= 5 ){
                showToast("No puedes ingresar un porcentaje menor a 5% ni mayor a 100%")
            } else{
                conInventarioRopa.agregarPren(this,idComp,correo,nombreP,talla,catego,genero,descripcion,foto,precio,porcentaje,asociacionSeleccionada,donativoDef, estado)
                showToast("Prenda agregada exitosamente")
                limpiar()
            }

        })
        btnCancelar.setOnClickListener(View.OnClickListener {
            val intentCancelar = Intent(this,RopaVendedor::class.java)
            intentCancelar.putExtra("email", correo)
            startActivity(intentCancelar)
        })
    }

    fun showToast(str:String){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
    }


    fun limpiar(){
        edtNombrePrenda.text.clear()
        edtDescripcion.text.clear()
        edtPrecioPrenda.text.clear()
        edtPorcentaje.text.clear()
        grupoGenero.clearChecked()
        grupotalla.clearChecked()

    }

}