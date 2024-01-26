package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditarPerfil : AppCompatActivity() {
    lateinit var btnGuardar: Button
    lateinit var nombre: EditText
    lateinit var apellidoPat: EditText
    lateinit var apellidoMat: EditText
    val conUsuarios = ConUsuarios(DataRepository)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        btnGuardar = findViewById(R.id.btnGuardarPerfil)
        nombre = findViewById(R.id.nombre)
        apellidoPat = findViewById(R.id.apellidoPat)
        apellidoMat = findViewById(R.id.apellidoMat)

        val correo = intent.getStringExtra("email")

        val usuario = conUsuarios.mostrarUsuario(correo)
        if (usuario != null) {
            val nombreUsuario = usuario.nombre
            val apellidoPaterno = usuario.apepat
            val apellidoMaterno = usuario.apemat
            //val contrasena = usuario.contrasena


            nombre.setText(nombreUsuario)
            apellidoPat.setText(apellidoPaterno)
            apellidoMat.setText(apellidoMaterno)

        } else {
            showToast("Error")
        }

        btnGuardar.setOnClickListener(View.OnClickListener {

            val nombre = nombre.text.toString()
            val apellidoPaterno = apellidoPat.text.toString()
            val apellidoMaterno = apellidoMat.text.toString()
            if(nombre.isEmpty()|| apellidoPaterno.isEmpty()||apellidoMaterno.isEmpty()){
                showToast("No has llenado todos los campos")
            }else{
                conUsuarios.actualizarPerfil(correo, nombre, apellidoPaterno, apellidoMaterno)
                showToast("Datos actualizados")
                conUsuarios.imprimirUsuarios()
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