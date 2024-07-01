package com.example.museosoumaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.museosoumaya.model.MUsuarios
import com.example.museosoumaya.ui.theme.Cafe
import com.example.museosoumaya.ui.theme.GreyClaro
import com.example.museosoumaya.ui.theme.MuseoSoumayaTheme
import com.example.museosoumaya.viewModel.VMUsuarios
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VistaCrearCuenta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseoSoumayaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                }
            }
        }
    }
}


@Composable
fun VentanaRegistro(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var apepat by remember { mutableStateOf("") }
    var apemat by remember { mutableStateOf("") }
    var correoo by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    var confcontra by remember { mutableStateOf("") }

    var showError by remember { mutableStateOf(false) }
    var showError2 by remember { mutableStateOf(false) }

    val mUsuario = MUsuarios()
    val VMUsuarios = VMUsuarios(mUsuario)

    val tipoCuenta = "Cliente"
    var registroExitoso by remember { mutableStateOf(false) } // Estado para verificar el éxito del registro


    suspend fun handleRegistro() {
        if (correoo.isNotEmpty() && contra.isNotEmpty() && confcontra.isNotEmpty()) {
            if (confcontra == contra) {
                val registroExitoso = mUsuario.registrarUsuario(navController, correoo, contra)
                if (registroExitoso) {
                    showError = false
                    showError2 = false
                    VMUsuarios.registrar(navController, nombre, apepat, apemat, correoo, tipoCuenta)
                    nombre = ""
                    apepat = ""
                    apemat = ""
                    correoo = ""
                    contra = ""
                    confcontra = ""

                } else {
                    showError = false
                    showError2 = true // Mostrar error si el registro falla
                }
            } else {
                showError = false
                showError2 = true // Mostrar error si las contraseñas no coinciden
            }
        } else {
            showError = true // Mostrar error si algún campo está vacío
            showError2 = false
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(modifier = Modifier
            .padding(10.dp),
            text = "Crear cuenta",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray)

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Create,
            placeholder = "Nombre(s)",
            value = nombre,
            onValueChange = { nombre = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Create,
            placeholder = "Apellido paterno",
            value = apepat,
            onValueChange = { apepat = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )


        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Create,
            placeholder = "Apellido materno",
            value = apemat,
            onValueChange = { apemat = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Email,
            placeholder = "Correo electrónico",
            value = correoo,
            onValueChange = { correoo = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )


        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Lock,
            placeholder = "Contraseña",
            value = contra,
            onValueChange = { contra = it },
            keyboardType = KeyboardType.Password,
            iconTint = GreyClaro
        )


        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Lock,
            placeholder = "Confirmar contraseña",
            value = confcontra,
            onValueChange = { confcontra = it },
            keyboardType = KeyboardType.Password,
            iconTint = GreyClaro
        )

        if (showError) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Por favor, complete todos los campos.",
                color = Color.Red
            )
        }
        if (showError2) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Las contraseñas no coinciden en ambos campos.",
                color = Color.Red
            )
        }
        Button(
            onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    handleRegistro()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Registrar")

        }


        Button(
            onClick = {navController.navigate("vistaBienvenida")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Cancelar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    MuseoSoumayaTheme {
        val navController = rememberNavController()
        VentanaRegistro(navController = navController)
    }
}