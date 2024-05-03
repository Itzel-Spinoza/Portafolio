package com.example.libreriaelbuho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.libreriaelbuho.ui.theme.CafeClaro
import com.example.libreriaelbuho.ui.theme.CafeRojizo
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme

class InicioAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibreriaElBuhoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //inicioAdmin("Android")
                }
            }
        }
    }
}


@Composable
fun inicioAdmin(navController: NavHostController, correo: String?, nombre: String?){


        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxSize()
                .padding(0.dp),
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = CafeRojizo,
                ),
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 50.dp),
                modifier = Modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(.20f)
            ) {
                Text(
                    text = "$nombre, bienvenid@",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp
                )

                Text(
                    text = "¿Qué harás el día de hoy?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=5.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 15.sp
                )
            }

            Text(
                text = "Selecciona una opción",
                modifier = Modifier
                    .padding(20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = CafeClaro
            )


            Button(
                onClick = {navController.navigate("")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CafeClaro,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                    Text(text = "Perfil empleado")
            }

            Button(
                onClick = {navController.navigate("ventanaMenuInventario/{correo}")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CafeClaro,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Inventario")
            }

            Button(
                onClick = {navController.navigate("")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CafeClaro,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Pedidos")
            }

            Button(
                onClick = {navController.navigate("")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Cerrar sesión")
            }


    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    LibreriaElBuhoTheme {
        val navController = rememberNavController()
        val correo = ""
        val nombre = "Itzel"
        inicioAdmin(navController = navController, correo = correo, nombre = nombre)
    }
}