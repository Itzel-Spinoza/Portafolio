package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class IniciarSesion : AppCompatActivity(){
    lateinit var btnIniciarSesion: Button
    lateinit var correo : TextView
    lateinit var contra : TextView
    val ConIniciarSesion = ConIniciarSesion(DataRepository)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciar_sesion)

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener(View.OnClickListener {

            correo = findViewById(R.id.edtEmail)
            contra = findViewById(R.id.edtContra)
            val email = correo.text.toString()
            val contrasena = contra.text.toString()

            if(email.isEmpty() || contrasena.isEmpty()){
                showToast("No has llenado campos")
            }else{
                ConIniciarSesion.ingresar(this,email,contrasena)

            }

        })
    }
    fun showToast(str:String){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
    }
}