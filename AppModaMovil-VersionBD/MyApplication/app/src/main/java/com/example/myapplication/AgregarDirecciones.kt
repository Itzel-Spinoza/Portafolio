package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AgregarDirecciones : AppCompatActivity(), CardAdapterDirecciones.OnItemClickListener {
    lateinit var recycler: RecyclerView
    lateinit var btnAgregarDir: ImageButton
    lateinit var btnCancelar: Button
    lateinit var btnGuardar: Button
    lateinit var txtAgDir: TextView
    lateinit var txtCalle: TextView
    lateinit var txtCol: TextView
    lateinit var txtNoExt: TextView
    lateinit var txtCP: TextView
    lateinit var txtMuni: TextView
    lateinit var txtEst: TextView

    lateinit var edtCalle: EditText
    lateinit var edtCol: EditText
    lateinit var edtNoExt: EditText
    lateinit var edtCP: EditText
    lateinit var edtMuni: EditText
    lateinit var edtEst: EditText

    lateinit var btnCancelarActDir: Button
    lateinit var btnGuardarActDir: Button
    lateinit var btnEliminarDir: Button
    val mUsuario = MUsuario()
    val conUsuarios = ConUsuarios(mUsuario)
    lateinit var adapter: CardAdapterDirecciones

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_direcciones)



        btnAgregarDir = findViewById(R.id.btnAgregar)
        btnCancelar = findViewById(R.id.btnCancelarDir)
        btnGuardar = findViewById(R.id.btnGuardarDir)
        txtAgDir = findViewById(R.id.txtAgDir)
        txtCalle = findViewById(R.id.txtCalle)
        txtCol = findViewById(R.id.txtCol)
        txtNoExt = findViewById(R.id.edtNoExt) //
        txtCP = findViewById(R.id.txtCP)
        txtMuni = findViewById(R.id.txtMunicipio)
        txtEst = findViewById(R.id.txtEstado)

        edtCalle = findViewById(R.id.edtCalle)
        edtCol = findViewById(R.id.edtColonia)
        edtNoExt = findViewById(R.id.txtNoExt)
        edtCP = findViewById(R.id.edtCP)
        edtMuni = findViewById(R.id.edtMunicipio)
        edtEst = findViewById(R.id.edtEstado)


        btnCancelarActDir = findViewById(R.id.btnCancelarActDir)
        btnGuardarActDir = findViewById(R.id.btnGuardarActDir)
        btnEliminarDir = findViewById(R.id.btnEliminarDir)

        recycler = findViewById(R.id.recycler1)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val correo = intent.getStringExtra("email")

        conUsuarios.direccionesUsuario(correo) { direcciones ->
            if (direcciones != null) {
                adapter = CardAdapterDirecciones(direcciones, this)
                recycler.adapter = adapter
            } else {
                // Manejar el caso de error
            }



            btnAgregarDir.setOnClickListener(View.OnClickListener {
                if (txtAgDir.visibility == View.GONE && txtCalle.visibility == View.GONE && txtCol.visibility == View.GONE
                    && txtNoExt.visibility == View.GONE && txtCP.visibility == View.GONE && txtMuni.visibility == View.GONE &&
                    txtEst.visibility == View.GONE && edtCalle.visibility == View.GONE && edtCol.visibility == View.GONE &&
                    edtNoExt.visibility == View.GONE && edtCP.visibility == View.GONE && edtMuni.visibility == View.GONE &&
                    edtEst.visibility == View.GONE && btnCancelar.visibility == View.GONE && btnGuardar.visibility == View.GONE
                ) {
                    btnCancelar.visibility = View.VISIBLE
                    btnGuardar.visibility = View.VISIBLE
                    txtAgDir.visibility = View.VISIBLE
                    txtCalle.visibility = View.VISIBLE
                    txtCol.visibility = View.VISIBLE
                    txtNoExt.visibility = View.VISIBLE
                    txtCP.visibility = View.VISIBLE
                    txtMuni.visibility = View.VISIBLE
                    txtEst.visibility = View.VISIBLE
                    edtCalle.visibility = View.VISIBLE
                    edtCol.visibility = View.VISIBLE
                    edtNoExt.visibility = View.VISIBLE
                    edtCP.visibility = View.VISIBLE
                    edtMuni.visibility = View.VISIBLE
                    edtEst.visibility = View.VISIBLE
                }
            })

            btnEliminarDir.setOnClickListener(View.OnClickListener {
                val calle = edtCalle.text.toString()
                conUsuarios.eliminarDireccion(this,correo, calle)
                //showToast("Dirección eliminada")

                if (correo != null) {
                    conUsuarios.direccionesUsuario(correo) { direcciones ->
                        if (direcciones != null) {
                            adapter = CardAdapterDirecciones(direcciones, this)
                            recycler.adapter = adapter
                            limpiar()
                        } else {

                        }
                    }
                } else {

                }


                txtAgDir.visibility = View.GONE
                txtCalle.visibility = View.GONE
                txtCol.visibility = View.GONE
                txtNoExt.visibility = View.GONE
                txtCP.visibility = View.GONE
                txtMuni.visibility = View.GONE
                txtEst.visibility = View.GONE
                edtCalle.visibility = View.GONE
                edtCol.visibility = View.GONE
                edtNoExt.visibility = View.GONE
                edtCP.visibility = View.GONE
                edtMuni.visibility = View.GONE
                edtEst.visibility = View.GONE

                btnGuardarActDir.visibility = View.GONE
                btnCancelarActDir.visibility = View.GONE
                btnEliminarDir.visibility = View.GONE
                txtAgDir.visibility = View.GONE
            })

            btnCancelar.setOnClickListener(View.OnClickListener {
                if (txtAgDir.visibility == View.VISIBLE && txtCalle.visibility == View.VISIBLE && txtCol.visibility == View.VISIBLE
                    && txtNoExt.visibility == View.VISIBLE && txtCP.visibility == View.VISIBLE && txtMuni.visibility == View.VISIBLE &&
                    txtEst.visibility == View.VISIBLE && edtCalle.visibility == View.VISIBLE && edtCol.visibility == View.VISIBLE &&
                    edtNoExt.visibility == View.VISIBLE && edtCP.visibility == View.VISIBLE && edtMuni.visibility == View.VISIBLE &&
                    edtEst.visibility == View.VISIBLE && btnCancelar.visibility == View.VISIBLE && btnGuardar.visibility == View.VISIBLE
                ) {
                    limpiar()
                    btnCancelar.visibility = View.GONE
                    btnGuardar.visibility = View.GONE
                    txtAgDir.visibility = View.GONE
                    txtCalle.visibility = View.GONE
                    txtCol.visibility = View.GONE
                    txtNoExt.visibility = View.GONE
                    txtCP.visibility = View.GONE
                    txtMuni.visibility = View.GONE
                    txtEst.visibility = View.GONE
                    edtCalle.visibility = View.GONE
                    edtCol.visibility = View.GONE
                    edtNoExt.visibility = View.GONE
                    edtCP.visibility = View.GONE
                    edtMuni.visibility = View.GONE
                    edtEst.visibility = View.GONE
                }
            })

            btnGuardar.setOnClickListener(View.OnClickListener {
                val calle = edtCalle.text.toString()
                val colonia = edtCol.text.toString()
                val noext = edtNoExt.text.toString()
                val cp = edtCP.text.toString()
                val muni = edtMuni.text.toString()
                val estado = edtEst.text.toString()

                if (calle.isEmpty() || colonia.isEmpty() || noext.isEmpty() || cp.isEmpty() || muni.isEmpty() || estado.isEmpty()) {
                    showToast("No has llenado campos")

                } else {

                    val conUsuarios = ConUsuarios(mUsuario)
                    conUsuarios.agregarDirec(this,correo, calle, colonia, noext, cp, muni, estado)
                    //showToast("Dirección agregada")
                    limpiar()

                    //cardList.add(Direccion(correo,calle, colonia, noext, cp, muni, estado))
                    adapter.notifyDataSetChanged()
                    conUsuarios.imprimirMetodosPago()


                    // Actualizar la lista del adaptador
                    //adapter.actualizarDatos(nuevaLista)

                }
            })

            btnGuardarActDir.setOnClickListener(View.OnClickListener {
                val calle = edtCalle.text.toString()
                val colonia = edtCol.text.toString()
                val noext = edtNoExt.text.toString()
                val cp = edtCP.text.toString()
                val muni = edtMuni.text.toString()
                val estado = edtEst.text.toString()

                if (calle.isEmpty() || colonia.isEmpty() || noext.isEmpty() || cp.isEmpty() || muni.isEmpty() || estado.isEmpty()) {
                    showToast("No has llenado campos")

                } else {

                    val conUsuarios = ConUsuarios(mUsuario)
                    conUsuarios.actualizarDireccion(this,correo, calle, colonia, noext, cp, muni, estado)


                     conUsuarios.direccionesUsuario(correo) { direcciones ->
                         if (direcciones != null) {
                             adapter = CardAdapterDirecciones(direcciones, this)
                             recycler.adapter = adapter
                             adapter.notifyDataSetChanged()
                            }
                        }




                }


            })

            btnCancelarActDir.setOnClickListener(View.OnClickListener {
                limpiar()

                txtAgDir.visibility = View.GONE
                txtCalle.visibility = View.GONE
                txtCol.visibility = View.GONE
                txtNoExt.visibility = View.GONE
                txtCP.visibility = View.GONE
                txtMuni.visibility = View.GONE
                txtEst.visibility = View.GONE
                edtCalle.visibility = View.GONE
                edtCol.visibility = View.GONE
                edtNoExt.visibility = View.GONE
                edtCP.visibility = View.GONE
                edtMuni.visibility = View.GONE
                edtEst.visibility = View.GONE

                btnGuardarActDir.visibility = View.GONE
                btnCancelarActDir.visibility = View.GONE
                btnEliminarDir.visibility = View.GONE
                txtAgDir.visibility = View.GONE

            })

        }
    }

        override fun onResume() {
            super.onResume()
            val correo = intent.getStringExtra("email")
            if (correo != null) {
                obtenerDatos(correo)
            } else {
                // Manejar el caso cuando correo es nulo si es necesario
            }
        }

        override fun onItemClick(direccion: Direccion) {
            //AQUI VA LO DE QUE SE DEBE ABRIR LOS CUADROS DE TEXTO Y MOSTRAR LOS DATOS EN ELLOS.
            val calle = direccion.calle
            val colonia = direccion.colonia
            val noext = direccion.noext
            val cp = direccion.cp
            val muni = direccion.municipio
            val est = direccion.estado
            val correo = direccion.correo

            txtAgDir.visibility = View.VISIBLE
            txtCalle.visibility = View.VISIBLE
            txtCol.visibility = View.VISIBLE
            txtNoExt.visibility = View.VISIBLE
            txtCP.visibility = View.VISIBLE
            txtMuni.visibility = View.VISIBLE
            txtEst.visibility = View.VISIBLE
            edtCalle.visibility = View.VISIBLE
            edtCol.visibility = View.VISIBLE
            edtNoExt.visibility = View.VISIBLE
            edtCP.visibility = View.VISIBLE
            edtMuni.visibility = View.VISIBLE
            edtEst.visibility = View.VISIBLE

            btnGuardarActDir.visibility = View.VISIBLE
            btnCancelarActDir.visibility = View.VISIBLE
            btnEliminarDir.visibility = View.VISIBLE
            txtAgDir.visibility = View.VISIBLE
            txtAgDir.setText("Actualizar dirección")

            btnCancelar.visibility = View.GONE
            btnGuardar.visibility = View.GONE

            edtCalle.setText(calle)
            edtCol.setText(colonia)
            edtNoExt.setText(noext)
            edtCP.setText(cp)
            edtMuni.setText(muni)
            edtEst.setText(est)


        }

        private fun obtenerDatos(correo: String?) {
            if (correo != null) {
                conUsuarios.direccionesUsuario(correo) { direcciones ->
                    if (direcciones != null) {
                        adapter = CardAdapterDirecciones(direcciones, this)
                        recycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    } else {
                        // Manejar el caso de error si es necesario
                    }
                }
            } else {
                // Manejar el caso cuando correo es nulo si es necesario
            }
        }


        fun showToast(str: String) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        }

        fun limpiar() {
            edtCalle.text.clear()
            edtCol.text.clear()
            edtNoExt.text.clear()
            edtCP.text.clear()
            edtMuni.text.clear()
            edtEst.text.clear()

        }

}