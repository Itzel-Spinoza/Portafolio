package com.example.museosoumaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.museosoumaya.model.MPedidos
import com.example.museosoumaya.model.MUsuarios
import com.example.museosoumaya.ui.theme.GreyClaro
import com.example.museosoumaya.ui.theme.MuseoSoumayaTheme
import com.example.museosoumaya.viewModel.VMBoletos
import com.example.museosoumaya.viewModel.VMUsuarios

class VistaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseoSoumayaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MenuPerfil()
                }
            }
        }
    }
}
//MENU PERFIL
@Composable
fun MenuPerfil(navController: NavController,  correo: String?,  nombre: String?) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black, // Cambia el color de fondo a negro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    //.fillMaxHeight(100.dp)
                    .size(width = 200.dp, height = 200.dp)
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.usuario), // Aquí cargamos el recurso drawable como un Painter
                        contentDescription = null,
                        modifier = Modifier
                            .padding(15.dp)
                            .height(100.dp)
                            .width(100.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier.padding(0.dp),
                        text = "Cuenta de",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(0.dp),
                        text = "$nombre",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp),
                modifier = Modifier
                    .padding(bottom = 0.dp)
                    .fillMaxWidth()
                    //.fillMaxHeight(100.dp)
                    .size(width = 200.dp, height = 650.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Button(
                        onClick = {navController.navigate("vistaPerfil/$correo") },
                        modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Gray
                        )
                    ) {
                        Text(text = "Perfil")
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Gray
                        )
                    ) {
                        Text(text = "Notificaciones")
                    }
                    Button(
                        onClick = {navController.navigate("vistaBoletosComprados/$correo") },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Gray
                        )
                    ) {
                        Text(text = "Mis boletos")
                    }
                    Button(
                        onClick = {navController.navigate("obrasPorVer/$correo")},
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Gray
                        )
                    ) {
                        Text(text = "Obras por ver")
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Gray
                        )
                    ) {
                        Text(text = "Cerrar sesion")
                    }
                }
            }
        }
    }
}
//VENTANA PARA EDITAR PERFIL
@Composable
fun EditarPerfil(navController: NavController,  correo: String?){
    var nombre by remember { mutableStateOf("") }
    var apePat by remember { mutableStateOf("") }
    var apeMat by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    val mUsuarios = MUsuarios()
    val viewModelUsuarios = VMUsuarios(mUsuarios)
    LaunchedEffect(correo) {
        // Cargar los datos del usuario
        val usuario = viewModelUsuarios.obtenerUsuario(correo)
        // Asignar los datos del usuario a las variables locales
        nombre = usuario?.nombre ?: ""
        apePat = usuario?.apepat ?: ""
        apeMat = usuario?.apemat ?: ""
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black, // Cambia el color de fondo a negro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    //.fillMaxHeight(100.dp)
                    .size(width = 200.dp, height = 200.dp)
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.usuario), // Aquí cargamos el recurso drawable como un Painter
                        contentDescription = null,
                        modifier = Modifier
                            .padding(15.dp)
                            .height(100.dp)
                            .width(100.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier.padding(0.dp),
                        text = "Cuenta de",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(0.dp),
                        text = "$nombre",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp),
                modifier = Modifier
                    .padding(bottom = 0.dp)
                    .fillMaxWidth()
                    //.fillMaxHeight(100.dp)
                    .size(width = 200.dp, height = 650.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Perfil",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    RoundedTextField1(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        leadingIcon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email,
                        placeholder = "Nombre",
                        value = nombre,
                        onValueChange = { nombre = it },
                        textColor = GreyClaro,
                        iconTint = GreyClaro
                    )
                    RoundedTextField1(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        leadingIcon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email,
                        placeholder = "Apellido paterno",
                        value = apePat,
                        onValueChange = { apePat = it },
                        textColor = GreyClaro,
                        iconTint = GreyClaro
                    )
                    RoundedTextField1(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        leadingIcon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email,
                        placeholder = "Apellido materno",
                        value = apeMat,
                        onValueChange = { apeMat = it },
                        textColor = GreyClaro,
                        iconTint = GreyClaro
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { /* Acción del botón */ },
                            modifier = Modifier
                                .weight(1f)
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Cancelar")
                        }

                        Button(
                            onClick = {viewModelUsuarios.actualizarUsuario(navController, correo, nombre, apePat, apeMat)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Guardar")
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    MuseoSoumayaTheme {
       val navController = rememberNavController()
       // MenuPerfil(navController = navController)
        EditarPerfil(navController = navController, correo = "saphira")
    }
}