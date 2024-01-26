package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.app.DatePickerDialog
import android.content.Intent
import android.util.Log
import android.widget.Spinner

class AgregarDatosPago : AppCompatActivity(), CardAdapterMetodosPago.OnItemClickListener  {
    lateinit var recycler2: RecyclerView
    lateinit var btnAgregarMet: ImageButton

    lateinit var btnCancelar: Button
    lateinit var btnGuardar: Button
    lateinit var txtAgMet:TextView
    lateinit var txtNoTar: TextView
    lateinit var txtTitular: TextView
    lateinit var txtFecha: TextView
    lateinit var txtCCV: TextView
    lateinit var txtEtiqueta: TextView

    lateinit var edtNoTar: EditText
    lateinit var edtTitular: EditText
    lateinit var edtFecha: EditText
    lateinit var edtCCV: EditText

    lateinit var tipoTarj : MaterialButtonToggleGroup
    lateinit var btnCredito: Button
    lateinit var btnDebito: Button

    lateinit var btnGuardarAct: Button
    lateinit var btnCancelarAct: Button
    lateinit var btnEliminarMet: Button
    lateinit var lblTipoTarjeta: TextView
    lateinit var spinTipoTarjeta: Spinner

    var tipoTarjeta : String = ""
    val conUsuarios = ConUsuarios(DataRepository)
    lateinit var adapter: CardAdapterMetodosPago
    val calendar = Calendar.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_datos_pago)

        btnAgregarMet = findViewById(R.id.btnAgregarMetodo)
        btnCancelar = findViewById(R.id.btnCancelarMet)
        btnGuardar = findViewById(R.id.btnGuardarMet)
        txtNoTar = findViewById(R.id.txtNoTarjeta)
        txtTitular = findViewById(R.id.txtTitularTarjeta)
        txtFecha = findViewById(R.id.txtFechaExp)
        txtCCV = findViewById(R.id.txtCCB) //
        txtAgMet = findViewById(R.id.txtAgMetPago)

        txtEtiqueta = findViewById(R.id.txtAgMetPago)

        edtNoTar = findViewById(R.id.edtNoTarjeta)
        edtTitular = findViewById(R.id.edtTitularTarjeta)
        edtFecha = findViewById(R.id.edtFechaExp)
        edtCCV = findViewById(R.id.edtCBB)

        tipoTarj = findViewById(R.id.grupo_tipoTarjeta)
        btnCredito = findViewById(R.id.btnTCredito)
        btnDebito = findViewById(R.id.btnTDebito)

        recycler2 = findViewById(R.id.recycler2)
        recycler2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnGuardarAct = findViewById(R.id.btnGuardarCambiosTarj)
        btnCancelarAct = findViewById(R.id.btnCancelarAct)
        btnEliminarMet = findViewById(R.id.btnEliminarMetodo)
        lblTipoTarjeta = findViewById(R.id.tipoTarjeta)
        spinTipoTarjeta = findViewById(R.id.spinTipoTarjeta)

        //val usuarios = Usuarios(DataRepository)
        val correo = intent.getStringExtra("email")
        val cardList = conUsuarios.metodosPagosUsuarios(correo)
        adapter = CardAdapterMetodosPago(cardList,this)
        recycler2.adapter = adapter
        adapter.notifyDataSetChanged()

        edtFecha.setOnClickListener {
            mostrarDatePicker()
        }
        btnAgregarMet.setOnClickListener(View.OnClickListener {
            txtEtiqueta.setText("Agregar nueva tarjeta")
            if (txtAgMet.visibility == View.GONE && txtNoTar.visibility == View.GONE && txtTitular.visibility == View.GONE
                && txtFecha.visibility == View.GONE && txtCCV.visibility == View.GONE &&
                edtNoTar.visibility == View.GONE && edtTitular.visibility == View.GONE && edtFecha.visibility == View.GONE &&
                edtCCV.visibility == View.GONE && btnCancelar.visibility == View.GONE && btnGuardar.visibility == View.GONE
                && tipoTarj.visibility == View.GONE) {
                btnCancelar.visibility = View.VISIBLE
                btnGuardar.visibility = View.VISIBLE
                txtAgMet.visibility = View.VISIBLE
                txtNoTar.visibility = View.VISIBLE
                txtTitular.visibility = View.VISIBLE
                txtFecha.visibility = View.VISIBLE
                txtCCV.visibility = View.VISIBLE
                edtNoTar.visibility = View.VISIBLE
                edtTitular.visibility = View.VISIBLE
                edtFecha.visibility = View.VISIBLE
                edtCCV.visibility = View.VISIBLE
                tipoTarj.visibility = View.VISIBLE

                btnGuardarAct.visibility = View.GONE
                btnCancelarAct.visibility = View.GONE
                btnEliminarMet.visibility = View.GONE
                lblTipoTarjeta.visibility = View.GONE
                spinTipoTarjeta.visibility = View.GONE

            }
        })

        btnCancelar.setOnClickListener(View.OnClickListener {
            if (txtAgMet.visibility == View.VISIBLE && txtNoTar.visibility == View.VISIBLE && txtTitular.visibility == View.VISIBLE
                && txtFecha.visibility == View.VISIBLE && txtCCV.visibility == View.VISIBLE &&
                edtNoTar.visibility == View.VISIBLE && edtTitular.visibility == View.VISIBLE && edtFecha.visibility == View.VISIBLE &&
                edtCCV.visibility == View.VISIBLE && btnCancelar.visibility == View.VISIBLE && btnGuardar.visibility == View.VISIBLE
                && tipoTarj.visibility == View.VISIBLE) {
                btnCancelar.visibility = View.GONE
                btnGuardar.visibility = View.GONE
                txtAgMet.visibility = View.GONE
                txtNoTar.visibility = View.GONE
                txtTitular.visibility = View.GONE
                txtFecha.visibility = View.GONE
                txtCCV.visibility = View.GONE
                edtNoTar.visibility = View.GONE
                edtTitular.visibility = View.GONE
                edtFecha.visibility = View.GONE
                edtCCV.visibility = View.GONE
                tipoTarj.visibility = View.GONE

            }
        })

        btnGuardarAct.setOnClickListener(View.OnClickListener {
            val noTarjeta = edtNoTar.text.toString()
            val titular = edtTitular.text.toString()
            val ccv = edtCCV.text.toString()
            val fechaCad = edtFecha.text.toString()

            if(noTarjeta.isEmpty() || titular.isEmpty() || ccv.isEmpty() || fechaCad.isEmpty()){
                showToast("No has llenado campos")
            } else {
                val conUsuarios = ConUsuarios(DataRepository)
                conUsuarios.actualizarMetodoPago(correo, tipoTarjeta, noTarjeta, titular, fechaCad, ccv)
                showToast("Tarjeta actualizada")

                val nuevaLista = conUsuarios.metodosPagosUsuarios(correo)
                adapter.actualizarDatos(nuevaLista)
            }
        })

        btnCancelarAct.setOnClickListener(View.OnClickListener {
            limpiar()

            txtAgMet.visibility = View.GONE
            txtNoTar.visibility = View.GONE
            txtTitular.visibility = View.GONE
            txtFecha.visibility = View.GONE
            txtCCV.visibility = View.GONE
            edtNoTar.visibility = View.GONE
            edtTitular.visibility = View.GONE
            edtFecha.visibility = View.GONE
            edtCCV.visibility = View.GONE
            btnCancelar.visibility = View.GONE
            btnGuardar.visibility = View.GONE
            tipoTarj.visibility = View.GONE

            btnGuardarAct.visibility = View.GONE
            btnCancelarAct.visibility = View.GONE
            btnEliminarMet.visibility = View.GONE
            lblTipoTarjeta.visibility = View.GONE
            spinTipoTarjeta.visibility = View.GONE
        })

        btnEliminarMet.setOnClickListener(View.OnClickListener {
            val noTarj = edtNoTar.text.toString()
            conUsuarios.eliminarMetPago(correo,noTarj)
            showToast("Método de pago eliminado")

            val nuevaLista = conUsuarios.metodosPagosUsuarios(correo)
            adapter.actualizarDatos(nuevaLista)
            limpiar()

            txtAgMet.visibility = View.GONE
            txtNoTar.visibility = View.GONE
            txtTitular.visibility = View.GONE
            txtFecha.visibility = View.GONE
            txtCCV.visibility = View.GONE
            edtNoTar.visibility = View.GONE
            edtTitular.visibility = View.GONE
            edtFecha.visibility = View.GONE
            edtCCV.visibility = View.GONE
            btnCancelar.visibility = View.GONE
            btnGuardar.visibility = View.GONE
            tipoTarj.visibility = View.GONE

            btnGuardarAct.visibility = View.GONE
            btnCancelarAct.visibility = View.GONE
            btnEliminarMet.visibility = View.GONE
            lblTipoTarjeta.visibility = View.GONE
            spinTipoTarjeta.visibility = View.GONE
        })


        btnGuardar.setOnClickListener(View.OnClickListener {
            val noTarjeta = edtNoTar.text.toString()
            val titular = edtTitular.text.toString()
            val ccv = edtCCV.text.toString()
            val fechaCad = edtFecha.text.toString()


            if (noTarjeta.isEmpty() || titular.isEmpty() || ccv.isEmpty() || fechaCad.isEmpty()) {
                showToast("No has llenado todos los campos")
            } else if (noTarjeta.length != 16) {
                showToast("El número de tarjeta debe tener 16 dígitos")
            } else if (ccv.length != 3) {
                showToast("El CCV debe tener exactamente 3 dígitos")
            } else if (tipoTarjeta.isEmpty()) {
                showToast("No has seleccionado ningún tipo de tarjeta")
            } else {
                // Validación: Fecha de caducidad no puede ser menor a la fecha actual
                val currentDate = Calendar.getInstance().time
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val selectedDate = dateFormat.parse(fechaCad)

                if (selectedDate != null && selectedDate.before(currentDate)) {
                    showToast("La tarjeta ha caducado. Selecciona una fecha válida.")
                    Log.d("Fecha", "Fecha actual: $currentDate, Fecha seleccionada: $selectedDate")
                } else {
                    val conUsuarios = ConUsuarios(DataRepository)
                    conUsuarios.agregarMetPago(correo, tipoTarjeta, noTarjeta, titular, fechaCad, ccv)
                    showToast("Tarjeta Agregada")
                    limpiar()

                    cardList.add(MetodosPago(correo, tipoTarjeta, noTarjeta, titular, fechaCad, ccv))
                    adapter.notifyDataSetChanged()
                    conUsuarios.imprimirMetodosPago()
                    val nuevaLista = conUsuarios.metodosPagosUsuarios(correo)

                    adapter.actualizarDatos(nuevaLista)
                }
            }

        })



        tipoTarj.addOnButtonCheckedListener{tipoTarj, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    btnCredito.id -> {
                        tipoTarjeta = "Tarjeta de Credito"

                    }
                    btnDebito.id -> {
                        tipoTarjeta = "Tarjeta de Débito"

                    }
                }
            }else {
                tipoTarjeta = ""
            }


        }

    }

    override fun onResume() {
        super.onResume()
        val correo = intent.getStringExtra("email")
        obtenerDatos(correo)
    }

    override fun onItemClick(metodosPago: MetodosPago) {
        //AQUI VA LO DE QUE SE DEBE ABRIR LOS CUADROS DE TEXTO Y MOSTRAR LOS DATOS EN ELLOS.
        val numtarjeta = metodosPago.noTarjeta
        val titular = metodosPago.titularTarjeta
        val ccv = metodosPago.ccv
        val correo = metodosPago.correo
        val fecha = metodosPago.fechaExpedicion
        val tipoTarjeta = metodosPago.tipoTarjeta

        txtAgMet.visibility = View.VISIBLE
        txtNoTar.visibility = View.VISIBLE
        txtTitular.visibility = View.VISIBLE
        txtFecha.visibility = View.VISIBLE
        txtCCV.visibility = View.VISIBLE
        edtNoTar.visibility = View.VISIBLE
        edtTitular.visibility = View.VISIBLE
        edtFecha.visibility = View.VISIBLE
        edtCCV.visibility = View.VISIBLE
        btnCancelar.visibility = View.GONE
        btnGuardar.visibility = View.GONE
        tipoTarj.visibility = View.GONE

        btnGuardarAct.visibility = View.VISIBLE
        btnCancelarAct.visibility = View.VISIBLE
        btnEliminarMet.visibility = View.VISIBLE
        lblTipoTarjeta.visibility = View.VISIBLE
        spinTipoTarjeta.visibility = View.VISIBLE
        txtEtiqueta.setText("Actualizar tarjeta")

        edtNoTar.setText(numtarjeta)
        edtTitular.setText(titular)
        edtFecha.setText(fecha)
        edtCCV.setText(ccv)

    }
    private fun obtenerDatos(correo: String?) {
        val cardList = conUsuarios.metodosPagosUsuarios(correo)
        adapter = CardAdapterMetodosPago(cardList,this)
        recycler2.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun showToast(str:String){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarDatePicker() {
        val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val fechaSeleccionada = dateFormat.format(calendar.time)
            edtFecha.setText(fechaSeleccionada)
        }

        val datePickerDialog = DatePickerDialog(
            this,
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }
    fun limpiar(){
        edtNoTar.text.clear()
        edtTitular.text.clear()
        edtFecha.text.clear()
        edtCCV.text.clear()


    }

    }


