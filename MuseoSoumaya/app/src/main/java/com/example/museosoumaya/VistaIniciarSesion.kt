package com.example.museosoumaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.museosoumaya.model.MUsuarios
import com.example.museosoumaya.ui.theme.GreyClaro
import com.example.museosoumaya.ui.theme.MuseoSoumayaTheme
import com.example.museosoumaya.viewModel.VMUsuarios
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VentanaLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseoSoumayaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //LoginScreen(navController = navController,correo = correo)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController, correo: String?) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    //val ConIniciarSesion = IniciarSesion()
    val mUsuario = MUsuarios()
    val vmUsuario = VMUsuarios(mUsuario)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Iniciar Sesión",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Bienvenid@. Ingresa con tu cuenta.",
            fontSize = 16.sp
        )
        Image(
            painter = painterResource(id = R.drawable.estatua_removebg_preview),
            contentDescription = null,
            modifier = Modifier.size(250.dp),
            contentScale = ContentScale.Fit
        )

        // Actualizar valores de email y password a través de callbacks
        RoundedTextField1(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardType.Email,
            placeholder = "Correo electrónico",
            value = email,
            onValueChange = { email = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        RoundedTextField5(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            leadingIcon = Icons.Default.Lock,
            placeholder = "Contraseña",
            keyboardType = KeyboardType.Password,
            value = password,
            onValueChange = { password = it },
            iconTint = GreyClaro
        )

        // Mostrar mensaje de error si algún campo está vacío al intentar iniciar sesión
        if (showError) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Por favor, complete todos los campos.",
                color = Color.Red
            )
        }

        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    GlobalScope.launch(Dispatchers.Main) {
                        val autenticacionExitosa = mUsuario.autenticarUsuario(navController,email, password)
                        if (autenticacionExitosa) {

                            val usuario = vmUsuario.obtenerUsuario(email)
                            if (usuario != null) {
                                navController.navigate("vistaInicio/$email/${usuario.nombre}")
                            }
                        } else {
                            showError = true
                        }
                    }
                } else {
                    showError = true
                }
            },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(text = "Ingresar")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MuseoSoumayaTheme {
        val navController = rememberNavController()
        val correo = ""
        LoginScreen(navController = navController, correo = correo)
    }
}