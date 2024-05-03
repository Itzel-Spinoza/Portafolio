package com.example.libreriaelbuho

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.libreriaelbuho.controlador.CarritoViewModel
import com.example.libreriaelbuho.controlador.IniciarSesion
import com.example.libreriaelbuho.controlador.LibrosViewModel
import com.example.libreriaelbuho.modelo.Libro
import com.example.libreriaelbuho.ui.theme.Cafe
import com.example.libreriaelbuho.ui.theme.GreyClaro
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibreriaElBuhoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White


                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "loginScreen") {
                        composable("loginScreen") { backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            LoginScreen(navController = navController,correo = correo)
                        }


                        composable("registroScreen") {
                            RegisterScreen(navController = navController)
                        }

                        composable("inicioScreen/{correo}/{nombre}"){backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            val nombre = backStackEntry.arguments?.getString("nombre")
                            InicioScreen2(navController = navController,correo = correo, nombre = nombre, viewModel = LibrosViewModel())
                        }

                        composable("menu"){
                            MenuGenerosLibros(navController = navController)
                        }

                        composable("carrito"){
                            CarritoCompra(navController = navController, viewModel = CarritoViewModel())
                        }

                        composable("ventanaLibros/{genero}/{correo}"){backStackEntry ->
                            val genero = backStackEntry.arguments?.getString("genero")
                            val correo = backStackEntry.arguments?.getString("correo")
                            val viewModel = LibrosViewModel()
                            genero?.let { VerLibrosPorGenero(navController = navController, genero = genero,viewModel = viewModel,correo = correo) }
                        }

                        composable("ventanaDetallesLibros/{correo}/{id}/{titulo}/{autor}/{genero}/{anio}/{precio}/{sinopsis}"){backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            val id = backStackEntry.arguments?.getString("id")
                            val titulo = backStackEntry.arguments?.getString("titulo")
                            val autor = backStackEntry.arguments?.getString("autor")
                            val genero = backStackEntry.arguments?.getString("genero")
                            val anio = backStackEntry.arguments?.getString("anio")
                            val precio = backStackEntry.arguments?.getString("precio")
                            val sinopsis = backStackEntry.arguments?.getString("sinopsis")
                            VentanaInformacionLibro(navController = navController,correo=correo,id=id,titulo=titulo,autor=autor, genero = genero, anio = anio, precio = precio, sinopsis = sinopsis)
                        }

                        composable("ventanaEnvios"){
                            agregarDireccionEnvio(navController = navController)
                        }

                        composable("ventanaMetodoPago"){
                            agregarMetodoPago()
                        }

                        composable("ventanaInicioAdmin/{correo}/{nombre}"){backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            val nombre = backStackEntry.arguments?.getString("nombre")
                            inicioAdmin(navController = navController,correo = correo, nombre = nombre)
                        }

                        composable("ventanaMenuInventario/{correo}"){backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            inventarioGeneral(navController = navController, viewModel = LibrosViewModel(),correo = correo)
                        }

                        composable("ventanaAgregarNuevoLibro"){
                            val viewModel = LibrosViewModel() // Instancia del ViewModel
                            val listaLibros = remember { arrayListOf<Libro>() } // Lista de libros (vacía en este caso)

                            agregarNuevoLibro(listaLibros = listaLibros, viewModel = viewModel)

                        }
                    }

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
    val ConIniciarSesion = IniciarSesion()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "El Búho",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Cafe
        )

        Image(
            painter = painterResource(id = R.drawable.buho_removebg_preview),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Libros, libros y más libros",
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = "Iniciar sesión",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
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
                if (email.isEmpty() || password.isEmpty()) {
                    showError = true
                } else {
                    showError = false
                    ConIniciarSesion.iniciarSesion(navController = navController,email,password)

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

        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = "Si no tienes una cuenta",
            fontSize = 14.sp
        )

        Button(
            onClick = { navController.navigate("registroScreen") },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Cafe,
                contentColor = Color.White
            )
        ) {
            Text(text = "Crear cuenta")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    val correo = ""
    LoginScreen(navController = navController, correo = correo)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField1(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    textColor: Color = GreyClaro,
    iconTint: Color = GreyClaro,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
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

            cursorColor = textColor
        ),
        textStyle = TextStyle(color = textColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField5(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Password,
    textColor: Color = GreyClaro,
    iconTint: Color = GreyClaro,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = null, tint = iconTint)
            }
        },
        label = { Text(text = placeholder, style = TextStyle(color = textColor)) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = PasswordVisualTransformation(),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF8B4513),
            unfocusedBorderColor = Color(0xFF8B4513),
            cursorColor = textColor
        ),
        textStyle = TextStyle(color = textColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    placeholder: String,
    textColor: Color = GreyClaro,
    iconTint: Color = GreyClaro,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            val filteredValue = newValue.filter { it.isDigit() }
            onValueChange(filteredValue)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = placeholder, style = TextStyle(color = textColor)) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF8B4513),
            unfocusedBorderColor = Color(0xFF8B4513),
            cursorColor = textColor
        ),
        textStyle = TextStyle(color = textColor),
        singleLine = true
    )
}