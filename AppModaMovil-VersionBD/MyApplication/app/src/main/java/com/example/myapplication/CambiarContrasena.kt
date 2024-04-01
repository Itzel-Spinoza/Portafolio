package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CambiarContrasena : AppCompatActivity() {
    lateinit var btnGuardarContra: Button
    lateinit var nuevaContra: EditText
    lateinit var confContra: EditText
    val mUsuario = MUsuario()
    val conUsuarios = ConUsuarios(mUsuario)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)
        btnGuardarContra = findViewById(R.id.btnGuardarContra)
        nuevaContra = findViewById(R.id.edtNuevaContrasena)
        confContra = findViewById(R.id.edtConfirmarContrasena)
        val correo = intent.getStringExtra("email")


        btnGuardarContra.setOnClickListener(View.OnClickListener {
            val contra = nuevaContra.text.toString()
            val confcontra = confContra.text.toString()
            if(contra.isEmpty() || confcontra.isEmpty()){
                showToast("No has llenado todos los campos")
            }else if(contra != confcontra){
                showToast("No coincide la contraseña en ambos campos")
            }else{
                conUsuarios.actualizarContra(this,correo,contra)
                //conUsuarios.imprimirUsuarios()
                //showToast("Contraseña actualizada")
            }
        })



    }

    fun showToast(str:String){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
    }
}