package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class EditarPrenda : AppCompatActivity() {
    val conInventarioRopa = ConInventarioRopa(DataRepository)
    lateinit var txtID: TextView
    lateinit var txtNombrePren: EditText
    lateinit var txtDescrip: EditText
    lateinit var txtPrecio: EditText
    lateinit var txtDonativoPrenda: TextView
    lateinit var txtGanancia: TextView
    lateinit var txtEstadoPren: TextView
    lateinit var txtPorcentaje: EditText

    lateinit var btnEditarPrenda: Button
    lateinit var btnEliminarPrenda: Button
    lateinit var btnGuardarCambios: Button

    lateinit var spinTallaaa: Spinner
    lateinit var spinAsociacionn: Spinner
    lateinit var spinCategoria: Spinner
    lateinit var spinGenero: Spinner
    var spinnersEditable = false
    var donativoDef: String = ""
    var gananciaDef: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_prenda)

        txtID = findViewById(R.id.txtID)
        txtNombrePren = findViewById(R.id.txtNombrePrenda)
        txtDescrip = findViewById(R.id.txtDescripcionPrenda)
        //txtTalla = findViewById(R.id.txtTallaPrenda)
        txtPrecio = findViewById(R.id.txtPrecioPrenda)
        txtDonativoPrenda = findViewById(R.id.txtDonativoPrenda)
        txtGanancia = findViewById(R.id.txtGananciaPrenda)
        txtEstadoPren = findViewById(R.id.txtEstadoPrenda)
        //txtAso = findViewById(R.id.txtAsociacionDon)
        txtPorcentaje = findViewById(R.id.txtPorcentajeP)

        btnEditarPrenda = findViewById(R.id.btnEditarPrenda)
        btnGuardarCambios = findViewById(R.id.btnGuardarCambiosPrenda)
        btnEliminarPrenda = findViewById(R.id.btnEliminarPrendaAg)

        spinTallaaa = findViewById(R.id.spinnerTallasss)
        spinAsociacionn = findViewById(R.id.spinAsociacionnnn)
        spinCategoria = findViewById(R.id.spinerCate)
        spinGenero = findViewById(R.id.spinerGenero)



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
        var ganancia = conInventarioRopa.calcularGanancia(precio.toString(),porcentaje.toString())



        val adapterGenero = ArrayAdapter(this, android.R.layout.simple_spinner_item, conInventarioRopa.mostrarGenero())
        adapterGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinGenero.adapter= adapterGenero


        val adapterCate = ArrayAdapter(this, android.R.layout.simple_spinner_item, conInventarioRopa.mostrarcategoria(genero))
        adapterCate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinCategoria.adapter= adapterCate

        val adapterTalla = ArrayAdapter(this, android.R.layout.simple_spinner_item, conInventarioRopa.mostrarTallas())
        adapterTalla.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinTallaaa.adapter= adapterTalla

        val adapterAso = ArrayAdapter(this, android.R.layout.simple_spinner_item, conInventarioRopa.mostrarAsociaciones2())
        adapterAso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinAsociacionn.adapter= adapterAso

        spinTallaaa.setSelection(adapterTalla.getPosition(talla))
        spinAsociacionn.setSelection(adapterAso.getPosition(asociacion))
        spinCategoria.setSelection(adapterCate.getPosition(categoria))
        spinGenero.setSelection(adapterGenero.getPosition(genero))

        makeEditTextsEditable(false)
        makeSpinnersEditable(false)
        txtID.text = id
        txtNombrePren.setText(nombrePrenda)
        txtDescrip.setText(descripcion)
        txtPrecio.setText(precio)
        txtEstadoPren.text = estado
        txtPorcentaje.setText(porcentaje)
        txtDonativoPrenda.text = donativo
        txtGanancia.text = ganancia

        spinGenero.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val selectedGenero = parentView.getItemAtPosition(position).toString()

                val adapterCate = ArrayAdapter(this@EditarPrenda, android.R.layout.simple_spinner_item, conInventarioRopa.mostrarcategoria(selectedGenero))
                adapterCate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinCategoria.adapter = adapterCate
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
            }
        })


        txtPrecio.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario implementar
            }
            override fun afterTextChanged(s: Editable?) {
                try {
                    val precio = txtPrecio.text.toString()
                    val porcentaje = txtPorcentaje.text.toString()

                    if (precio.isNotEmpty() && porcentaje.isNotEmpty()) {
                        val ganancia2 = conInventarioRopa.calcularGanancia(precio, porcentaje)
                        val (descuento, precioRopa) = conInventarioRopa.calcularDonativo(
                            precio,
                            porcentaje
                        )
                        txtDonativoPrenda.text = descuento.toString()
                        donativoDef = descuento
                        txtGanancia.text = ganancia2.toString()
                        gananciaDef = ganancia2
                    } else if (porcentaje.isEmpty()) {
                        txtDonativoPrenda.text = ""
                    } else if (precio.isEmpty()) {
                        txtDonativoPrenda.text = ""
                    }
                } catch (e: NumberFormatException) {
                    showToast("Ingrese números válidos en los campos de precio y porcentaje.")
                }
            }
        })
        txtPorcentaje.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario implementar
            }
            override fun afterTextChanged(s: Editable?) {
                try {
                    val precio = txtPrecio.text.toString()
                    val porcentaje = txtPorcentaje.text.toString()

                    if (precio.isNotEmpty() && porcentaje.isNotEmpty()) {
                        val ganancia2 = conInventarioRopa.calcularGanancia(precio, porcentaje)
                        val (descuento, precioRopa) = conInventarioRopa.calcularDonativo(precio, porcentaje)
                        txtDonativoPrenda.text = descuento.toString()
                        donativoDef = descuento
                        txtGanancia.text = ganancia2.toString()
                        gananciaDef = ganancia2
                    }
                    else if(porcentaje.isEmpty()){
                        txtDonativoPrenda.text = ""
                    }
                    else if(precio.isEmpty()){
                        txtDonativoPrenda.text = ""
                    }
                } catch (e: NumberFormatException) {
                    showToast("Ingrese números válidos en los campos de precio y porcentaje.")
                }
            }
        })
        btnEditarPrenda.setOnClickListener {
            makeEditTextsEditable(true)
            spinnersEditable = true
            makeSpinnersEditable(true)
        }

        btnEliminarPrenda.setOnClickListener(View.OnClickListener {
            val idPrenda = txtID.text.toString()
            conInventarioRopa.eliminarPrenda(idPrenda)
            showToast("Prenda eliminada.")
            val intentVolver = Intent(this, RopaVendedor::class.java)
            intentVolver.putExtra("email", correo)
            startActivity(intentVolver)
            
        })

        btnGuardarCambios.setOnClickListener{
            val idPrenda = txtID.text.toString()
            val nombrePrenda = txtNombrePren.text.toString()
            val descrip = txtDescrip.text.toString()
            val precio = txtPrecio.text.toString()
            val donativo = txtDonativoPrenda.text.toString()
            val gananciaa = txtGanancia.text.toString()
            val porcentaje = txtPorcentaje.text.toString()
            val catego = spinCategoria.selectedItem.toString()
            val talla = spinTallaaa.selectedItem.toString()
            val asociacion = spinAsociacionn.selectedItem.toString()
            val genero = spinGenero.selectedItem.toString()

            makeEditTextsEditable(false)
            spinnersEditable = false
            makeSpinnersEditable(false)


            conInventarioRopa.editarPrenda(id,nombrePrenda,talla,catego,genero,descrip,precio,porcentaje,asociacion,donativo)
            showToast("Prenda actualizada.")
        }


    }

    fun makeEditTextsEditable(editable: Boolean) {
        txtNombrePren.isEnabled = editable
        txtDescrip.isEnabled = editable
        txtPrecio.isEnabled = editable
        txtPorcentaje.isEnabled = editable


    }
    fun makeSpinnersEditable(editable: Boolean) {
        spinTallaaa.isEnabled = editable
        spinAsociacionn.isEnabled = editable
        spinCategoria.isEnabled = editable
        spinGenero.isEnabled = editable
    }

    fun showToast(str:String){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
    }

}