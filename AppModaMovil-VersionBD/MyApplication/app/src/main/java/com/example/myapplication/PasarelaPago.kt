package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PasarelaPago : AppCompatActivity() {
    lateinit var recycler2: RecyclerView
    lateinit var btnAgregarMet: ImageButton

    lateinit var btnCancelar: Button
    lateinit var btnGuardar: Button
    lateinit var txtAgMet: TextView
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

    lateinit var btnFinalizarPedido: Button

    var tipoTarjeta : String = ""
    val mUsuario = MUsuario()
    val conUsuarios = ConUsuarios(mUsuario)
    val mVenta = MVenta()
    private val conComprar = ConComprar(mVenta)
    lateinit var adapter: CardAdapterMetPagoPasarela
    val calendar = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasarela_pago)
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

         var direccion: String
         var nombreCompleto: String
        btnFinalizarPedido = findViewById(R.id.btnContinuarPasDir)
        //val usuarios = Usuarios(DataRepository)
        val correo = intent.getStringExtra("email")

        val calle = intent.getStringExtra("calle")
        val colonia = intent.getStringExtra("col")
        val noext = intent.getStringExtra("noext")
        val cp = intent.getStringExtra("cp")
        val municipio = intent.getStringExtra("muni")
        val estado = intent.getStringExtra("estado")
        val totalPago = intent.getStringExtra("totalPedido")
        println(totalPago)
        val totalDonativo = intent.getStringExtra("totalDonativos")
        println(totalDonativo)



        /*val cardList = conUsuarios.metodosPagosUsuarios(correo)
        adapter = CardAdapterMetPagoPasarela(cardList,this)
        recycler2.adapter = adapter
        adapter.notifyDataSetChanged()*/
        conUsuarios.metodosPagosUsuarios(correo) { metodosPago ->
            if (metodosPago != null) {
                adapter = CardAdapterMetPagoPasarela(metodosPago, this)
                recycler2.adapter = adapter

            } else {
                // Manejar el caso de error
                showToast("No se obtuvieron métodos de pago")
            }
        }
        edtFecha.setOnClickListener {
            mostrarDatePicker()
        }

        btnFinalizarPedido.setOnClickListener(View.OnClickListener {
            val noTarjeta = edtNoTar.text.toString()
            val ccv = edtCCV.text.toString()
            val fechaTarj = edtFecha.text.toString()
            val titularTarj = edtTitular.text.toString()

            if(noTarjeta.isEmpty() || ccv.isEmpty()||fechaTarj.isEmpty()||titularTarj.isEmpty()){
                showToast("Te faltan llenar campos")
            }else if(noTarjeta.isEmpty() && ccv.isEmpty() && fechaTarj.isEmpty() && titularTarj.isEmpty()) {
                showToast("No has seleccionado método de pago")
            }else {
                var direccion: String
                var nombreCompleto: String
                //val direccion = conComprar.concatenarDireccion(calle, colonia, noext, cp, municipio, estado)

                conComprar.concatenarDireccion(correo, calle) { direccionCompleta ->
                    if (direccionCompleta != null) {
                        direccion = direccionCompleta
                    } else {

                    }
                }

                conComprar.concatenarNombreCliente(correo) { nombreCliente ->
                    if (nombreCliente != null) {
                        nombreCompleto = nombreCliente
                    } else {

                    }
                }
                conComprar.concatenarDireccion(correo, calle) { direccionCompleta ->
                    if (direccionCompleta != null) {
                        val direccion = direccionCompleta

                        conComprar.concatenarNombreCliente(correo) { nombreCliente ->
                            if (nombreCliente != null) {
                                val nombreCompleto = nombreCliente

                                // Ahora que tienes ambas variables inicializadas, puedes llamar a registrarPedidos
                                val id = conComprar.generarID()
                                val id2 = conComprar.comprobarID(id)
                                val fecha = conComprar.registrarPedidos(
                                    id2,
                                    correo,
                                    nombreCompleto,
                                    direccion,
                                    totalPago,
                                    totalDonativo
                                )

                                println(fecha)
                                println(totalPago)
                                conComprar.guardarPrendas(correo, id2, fecha,nombreCompleto,direccion)
                                //conComprar.prendasPedidos(id2, )
                                conComprar.guardarPedidosPorVendedor(correo, id2, fecha, direccion, nombreCompleto)
                                conComprar.eliminarPrendaCarrito(this,correo)

                                val intent = Intent(this, VentanaPedidoRealizado::class.java)
                                intent.putExtra("email", correo)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                        }
                    }
                }

                //val nombreCompleto = conComprar.concatenarNombreCliente(correo)
                /*val id = conComprar.generarID()
                val id2 = conComprar.comprobarID(id)
                println(id2)
                val fecha = conComprar.registrarPedidos(
                    id2,
                    correo,
                    nombreCompleto,
                    direccion,
                    totalPago,
                    totalDonativo
                )

                println(fecha)
                println(totalPago)
                conComprar.guardarPrendas(correo, id2, fecha)
                conComprar.guardarPedidosPorVendedor(correo, id2, fecha, direccion, nombreCompleto)

                conComprar.eliminarPrendaCarrito(correo)

                val intent = Intent(this, VentanaPedidoRealizado::class.java)
                intent.putExtra("email", correo)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)*/
            }

        })

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
                val conUsuarios = ConUsuarios(mUsuario)
                conUsuarios.actualizarMetodoPago(this,correo, tipoTarjeta, noTarjeta, titular, fechaCad, ccv)
                //showToast("Tarjeta actualizada")


                // Actualizar la lista del adaptador
                //val nuevaLista = conUsuarios.metodosPagosUsuarios(correo)
               // adapter.actualizarDatos(nuevaLista)

                conUsuarios.metodosPagosUsuarios(correo) { metodosPago ->
                    if (metodosPago != null) {
                        adapter = CardAdapterMetPagoPasarela(metodosPago, this)
                        recycler2.adapter = adapter
                        adapter.notifyDataSetChanged()
                    } else {
                        // Manejar el caso de error
                    }
                }
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
            conUsuarios.eliminarMetPago(this,correo,noTarj)
            showToast("Método de pago eliminado")

            //val nuevaLista = conUsuarios.metodosPagosUsuarios(correo)
           //adapter.actualizarDatos(nuevaLista)
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

            conUsuarios.metodosPagosUsuarios(correo) { metodosPago ->
                if (metodosPago != null) {
                    adapter = CardAdapterMetPagoPasarela(metodosPago, this)
                    recycler2.adapter = adapter
                    adapter.notifyDataSetChanged()
                } else {
                    // Manejar el caso de error
                }
            }
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
                    // Todas las validaciones pasaron, puedes agregar la tarjeta

                    conUsuarios.agregarMetPago(this,correo, tipoTarjeta, noTarjeta, titular, fechaCad, ccv)
                    showToast("Tarjeta Agregada")
                    limpiar()
                    conUsuarios.metodosPagosUsuarios(correo) { metodosPago ->
                        if (metodosPago != null) {
                            adapter = CardAdapterMetPagoPasarela(metodosPago, this)
                            recycler2.adapter = adapter
                            adapter.notifyDataSetChanged()
                        } else {
                            // Manejar el caso de error
                        }
                    }
                    //cardList.add(MetodosPago(correo, tipoTarjeta, noTarjeta, titular, fechaCad, ccv))
                    //adapter.notifyDataSetChanged()
                    //conUsuarios.imprimirMetodosPago()
                    //val nuevaLista = conUsuarios.metodosPagosUsuarios(correo)

                    // Actualizar la lista del adaptador
                    //adapter.actualizarDatos(nuevaLista)
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

    override fun onBackPressed() {
        // Ir a la pantalla de inicio
        val intent = Intent(this, Inicio::class.java)
        val correo = intent.getStringExtra("email")
        intent.putExtra("email", correo)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
    override fun onResume() {
        super.onResume()
        val correo = intent.getStringExtra("email")
        obtenerDatos(correo)
    }

    fun onItemClick(metodosPago: MetodosPago) {
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
        txtEtiqueta.setText("Tarjeta seleccionada")

        edtNoTar.setText(numtarjeta)
        edtTitular.setText(titular)
        edtFecha.setText(fecha)
        edtCCV.setText(ccv)

    }
    private fun obtenerDatos(correo: String?) {
        /*val cardList = conUsuarios.metodosPagosUsuarios(correo)
        adapter = CardAdapterMetPagoPasarela(cardList,this)
        recycler2.adapter = adapter
        adapter.notifyDataSetChanged()*/
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