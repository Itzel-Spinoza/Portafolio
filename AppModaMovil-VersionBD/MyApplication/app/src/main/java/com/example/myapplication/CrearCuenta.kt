package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButtonToggleGroup

class CrearCuenta : AppCompatActivity(){
    lateinit var btnCrearCuenta : Button
    lateinit var btnCancelar : Button
    lateinit var nom: TextView
    lateinit var apepat : TextView
    lateinit var apemat : TextView
    lateinit var correo : TextView
    lateinit var contra : TextView
    lateinit var grupoGenero : MaterialButtonToggleGroup
    lateinit var grupoTipoCuenta : MaterialButtonToggleGroup
    lateinit var generoMasculino : Button
    lateinit var generoFemenino : Button
    lateinit var tipoVendedor : Button
    lateinit var tipoComprador : Button
    var genero : String = ""
    var tipoCuenta : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_cuenta)

        btnCrearCuenta = findViewById(R.id.btnCrearCuenta)
        btnCancelar = findViewById(R.id.btnCancelar)
        grupoGenero = findViewById(R.id.grupo_genero)
        grupoTipoCuenta = findViewById(R.id.grupo_tipoCuenta)
        generoMasculino = findViewById(R.id.btnHombre)
        generoFemenino = findViewById(R.id.btnMujer)
        tipoVendedor = findViewById(R.id.btnVendedor)
        tipoComprador = findViewById(R.id.btnComprador)


        btnCrearCuenta.setOnClickListener(View.OnClickListener {
            nom = findViewById(R.id.edtNombre)
            apepat = findViewById(R.id.edtApePat)
            apemat = findViewById(R.id.edtApeMat)
            correo = findViewById(R.id.edtEmail)
            contra = findViewById(R.id.edtContrasena)

            val nombre = nom.text.toString()
            val apellidoPaterno = apepat.text.toString()
            val apellidoMaterno = apemat.text.toString()
            val email = correo.text.toString()
            val contrasena = contra.text.toString()

            if(nombre.isEmpty()|| apellidoPaterno.isEmpty()||apellidoMaterno.isEmpty()||email.isEmpty()||contrasena.isEmpty()){
                showToast("No has llenado campos")

            }else if(genero.isEmpty()){
                showToast("No has seleccionado ningún género")
            }else if(tipoCuenta.isEmpty()){
                showToast("No has seleccionado ningún tipo de cuenta")

            }else{
                val mUsuario = MUsuario()
                val ConCrearCuenta = ConCrearCuenta(mUsuario)
                ConCrearCuenta.registrar(this,nombre, apellidoPaterno, apellidoMaterno, email, contrasena, genero, tipoCuenta)
            }

        })

        btnCancelar.setOnClickListener(View.OnClickListener {
            val intentCancelar = Intent(this,MainActivity::class.java)
            startActivity(intentCancelar)
        })

        grupoGenero.addOnButtonCheckedListener{grupoGenero, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    generoMasculino.id -> {
                        genero = "Hombre"
                        showToast("Genero masculino seleccionado.")
                    }

                    generoFemenino.id -> {
                        genero = "Mujer"
                        showToast("Genero femenino seleccionado.")
                    }

                }
            } else{
                genero = ""
            }
        }

        grupoTipoCuenta.addOnButtonCheckedListener{grupoTipoCuenta, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    tipoVendedor.id -> {
                        tipoCuenta = "Vendedor"
                        showToast("Seleccionaste cuenta tipo vendedor")
                    }
                    tipoComprador.id -> {
                        tipoCuenta = "Comprador"
                        showToast("Seleccionaste cuenta tipo comprador")
                    }
                }
            } else{
                tipoCuenta = ""
            }
        }


    }
    fun showToast(str:String){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
    }
}