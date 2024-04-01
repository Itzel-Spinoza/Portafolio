package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditarPerfil : AppCompatActivity() {
    lateinit var btnGuardar: Button
    lateinit var nombre: EditText
    lateinit var apellidoPat: EditText
    lateinit var apellidoMat: EditText
    val mUsuario = MUsuario()
    val conUsuarios = ConUsuarios(mUsuario)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        btnGuardar = findViewById(R.id.btnGuardarPerfil)
        nombre = findViewById(R.id.nombre)
        apellidoPat = findViewById(R.id.apellidoPat)
        apellidoMat = findViewById(R.id.apellidoMat)

        val correo = intent.getStringExtra("email")

        conUsuarios.obtenerUsuario(correo) { usuario ->
            if (usuario != null) {
                runOnUiThread {
                    val nombreUsuario = usuario.nombre
                    val apellidoPaterno = usuario.apepat
                    val apellidoMaterno = usuario.apemat

                    nombre.setText(nombreUsuario)
                    apellidoPat.setText(apellidoPaterno)
                    apellidoMat.setText(apellidoMaterno)
                }
            } else {
                showToast("Error al obtener usuario")
            }
        }

        btnGuardar.setOnClickListener(View.OnClickListener {

            val nombre = nombre.text.toString()
            val apellidoPaterno = apellidoPat.text.toString()
            val apellidoMaterno = apellidoMat.text.toString()
            if(nombre.isEmpty()|| apellidoPaterno.isEmpty()||apellidoMaterno.isEmpty()){
                showToast("No has llenado todos los campos")
            }else{
                conUsuarios.actualizarPerfil(this, correo,nombre,apellidoPaterno,apellidoMaterno)
                //showToast("Datos actualizados")
                //conUsuarios.imprimirUsuarios()
            }

        })
    }
    fun showToast(str:String){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
    }
    override fun onResume() {
        super.onResume()

    }
}