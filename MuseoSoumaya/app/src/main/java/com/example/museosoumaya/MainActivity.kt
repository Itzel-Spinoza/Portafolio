package com.example.museosoumaya

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.museosoumaya.ui.theme.Cafe
import com.example.museosoumaya.ui.theme.GreyClaro
import com.example.museosoumaya.ui.theme.MuseoSoumayaTheme
import com.example.museosoumaya.viewModel.VMObrasDeArte
import com.example.museosoumaya.viewModel.VMPedidos

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseoSoumayaTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "vistaBienvenida") {
                        composable("vistaBienvenida") { backStackEntry ->
                            //val correo = backStackEntry.arguments?.getString("correo")
                            VistaBienvenida(navController = navController)
                        }
                        composable("loginScreen") { backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            LoginScreen(navController = navController, correo = correo)
                        }

                        composable("vistaRegistro") {
                            VentanaRegistro(navController = navController)
                        }

                        composable("vistaInicio/{correo}/{nombre}") {backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            val nombre = backStackEntry.arguments?.getString("nombre")
                            Inicio(navController = navController,correo = correo, nombre = nombre)
                        }

                        composable("vistaObrasDeArte/{correo}") {backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            ObrasEnGeneral(navController = navController, viewModel = VMObrasDeArte(),correo = correo)
                        }
                       composable("vistaDetallesObra/{correo}/{id}/{titulo}/{descripcion}/{autor}/{anio}/{estiloDeArte}/{sala}") {backStackEntry ->
                           val correo = backStackEntry.arguments?.getString("correo")
                           val id = backStackEntry.arguments?.getString("id")
                           val titulo = backStackEntry.arguments?.getString("titulo")
                           val descripcion = backStackEntry.arguments?.getString("descripcion")
                           val autor = backStackEntry.arguments?.getString("autor")
                           val anio = backStackEntry.arguments?.getString("anio")
                           val estiloDeArte = backStackEntry.arguments?.getString("estiloDeArte")
                           val sala = backStackEntry.arguments?.getString("sala")
                           //val url = backStackEntry.arguments?.getString("url")
                           //val urlImagen = backStackEntry.arguments?.getString("urlImagen")
                           val url = remember {
                               val args = requireNotNull(navController.previousBackStackEntry).arguments
                               args?.getString("url")
                           }
                            InformacionObra(navController = navController,correo = correo,id=id,titulo=titulo,descripcion = descripcion, autor = autor, anio = anio, estilo= estiloDeArte,sala = sala)
                        }
                        //DETALLES DE LA OBRA (YA CON PASO DE PARAMETROS)

                        composable("vista/{correo}/{id}/{titulo}/{urlImagen}/{descripcion}/{autor}/{anio}/{estiloDeArte}/{sala}"){backStackEntry ->
                            //val correo = backStackEntry.arguments?.getString("correo")
                            val correo = backStackEntry.arguments?.getString("correo")
                            val id = backStackEntry.arguments?.getString("id")
                            val titulo = backStackEntry.arguments?.getString("titulo")
                            val urlImagen = backStackEntry.arguments?.getString("urlImagen")
                            val descripcion = backStackEntry.arguments?.getString("descripcion")
                            val autor = backStackEntry.arguments?.getString("autor")
                            val anio = backStackEntry.arguments?.getString("anio")
                            val estiloDeArte = backStackEntry.arguments?.getString("estiloDeArte")
                            val sala = backStackEntry.arguments?.getString("sala")


                            DetallesObra(navController = navController,correo=correo,id=id,titulo=titulo,urlImagen=urlImagen,descripcion = descripcion, autor = autor, anio = anio, estilo= estiloDeArte,sala = sala)
                            //VentanaInformacionLibro(navController = navController,correo=correo,id=id,titulo=titulo,autor=autor, genero = genero, anio = anio, precio = precio, sinopsis = sinopsis)
                        }

                        composable("vistaTotalBoleto/{correo}") {backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")

                            VentanaTipoBoleto(navController = navController,correo=correo)
                        }
                        composable("vistaPagarBoleto/{correo}/{folio}/{cantidad}/{cantidad2}/{subtotal}/{subtotal2}/{boletoTipoNino}/{boletoTipoAdulto}/{fecha}") {backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            val folio = backStackEntry.arguments?.getString("folio")
                            val cantidad = backStackEntry.arguments?.getString("cantidad")
                            val cantidad2 = backStackEntry.arguments?.getString("cantidad2")
                            val subtotal = backStackEntry.arguments?.getString("subtotal")
                            val subtotal2 = backStackEntry.arguments?.getString("subtotal2")
                            val boletoTipoNino = backStackEntry.arguments?.getString("boletoTipoNino")
                            val boletoTipoAdulto = backStackEntry.arguments?.getString("boletoTipoAdulto")
                            val fecha = backStackEntry.arguments?.getString("fecha")

                            VentanaDatosPagoCliente(navController = navController,correo=correo,folio=folio,cantidad=cantidad,cantidad2=cantidad2,subtotal=subtotal,subtotal2=subtotal2,boletoTipoNino=boletoTipoNino,boletoTipoAdulto=boletoTipoAdulto,fecha=fecha)
                        }

                        composable("vistaMenuPerfil/{correo}/{nombre}") {backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            val nombre = backStackEntry.arguments?.getString("nombre")
                            MenuPerfil(navController = navController, correo=correo, nombre = nombre)
                        }
                        composable("vistaBoletosComprados/{correo}") {backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            VerBoletosComprados(navController = navController, correo=correo)
                        }
                        composable("vistaPerfil/{correo}") {backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            EditarPerfil(navController = navController, correo=correo)
                        }
                        composable("obrasPorVer/{correo}") {backStackEntry ->
                            val correo = backStackEntry.arguments?.getString("correo")
                            ObrasPorVer(navController = navController,correo=correo)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun VistaBienvenida(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    //val ConIniciarSesion = IniciarSesion()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Museo Soumaya",
            fontFamily = FontFamily.Monospace,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Image(
            painter = painterResource(id = R.drawable.soumaya_removebg_preview),
            contentDescription = null,
            modifier = Modifier.size(350.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Arte para todos",
            fontSize = 16.sp
        )

        Button(
            onClick = {navController.navigate("loginScreen") },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(text = "Iniciar Sesión")
        }

        Button(
            onClick = { navController.navigate("vistaRegistro")},
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Crear cuenta")
        }

    }
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
            focusedBorderColor = Color(0xff000000),
            unfocusedBorderColor = Color(0xffbdbcbc),

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
            focusedBorderColor = Color(0xff000000),
            unfocusedBorderColor = Color(0xffbdbcbc),
            cursorColor = textColor
        ),
        textStyle = TextStyle(color = textColor)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MuseoSoumayaTheme {
        val navController = rememberNavController()
        VistaBienvenida(navController = navController)
    }
}