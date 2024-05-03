package com.example.libreriaelbuho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.libreriaelbuho.controlador.IniciarSesion
import com.example.libreriaelbuho.controlador.Registrar
import com.example.libreriaelbuho.modelo.MUsuario
import com.example.libreriaelbuho.ui.theme.Cafe
import com.example.libreriaelbuho.ui.theme.GreyClaro
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme

class VentanaCrearCuenta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibreriaElBuhoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RegisterScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var apepat by remember { mutableStateOf("") }
    var apemat by remember { mutableStateOf("") }
    var correoo by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    var confcontra by remember { mutableStateOf("") }

    var showError by remember { mutableStateOf(false) }
    var showError2 by remember { mutableStateOf(false) }

    val mUsuario = MUsuario()
    val ConRegistrar = Registrar(mUsuario)

    val tipoCuenta = "Cliente"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Título libreria
        Text(modifier = Modifier
            .padding(10.dp),
            text = "El Búho",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = Cafe)

        //Titulo iniciar sesion
        Text(modifier = Modifier
            .padding(16.dp),
            text = "Crear cuenta",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        // Email TextField
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

        // Email TextField
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

        // Email TextField
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
        // Email TextField
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

        // Password TextField
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

        // Password TextField
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
        // Mostrar mensaje de error si algún campo está vacío al intentar iniciar sesión
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
            onClick = { if (nombre.isEmpty() || apepat.isEmpty() || apemat.isEmpty() || correoo.isEmpty() || contra.isEmpty() || confcontra.isEmpty()) {
                showError = true
                showError2 = false
            } else {
                if(contra == confcontra){
                    showError = false
                    showError2 = false
                    ConRegistrar.registrar(navController = navController,nombre, apepat, apemat, correoo,contra,tipoCuenta)
                    nombre = ""
                    apepat = ""
                    apemat = ""
                    correoo = ""
                    contra = ""
                    confcontra = ""


                }else{
                    showError = false
                    showError2 = true
                }
            }},
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
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Cafe,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Cancelar")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField2(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    textColor: Color = GreyClaro,
    iconTint: Color = GreyClaro
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        modifier = modifier,
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = null, tint = iconTint)
            }
        },
        label = { Text(text = placeholder, style = TextStyle(color = textColor)) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF8B4513),
            unfocusedBorderColor = Color(0xFF8B4513),
            //textColor = Color.Transparent, // Set text color to transparent
            cursorColor = textColor // Set cursor color to the desired text color
        ),
        textStyle = TextStyle(color = textColor) // Set text color here as well
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LibreriaElBuhoTheme {
        val navController = rememberNavController()
        RegisterScreen(navController = navController)
    }
}