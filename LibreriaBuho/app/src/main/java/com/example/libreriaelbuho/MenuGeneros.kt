package com.example.libreriaelbuho

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.libreriaelbuho.ui.theme.Cafe
import com.example.libreriaelbuho.ui.theme.CafeRojizo
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme

class MenuGeneros : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibreriaElBuhoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {




                }
            }
        }
    }
}
@Composable
fun MenuGenerosLibros(navController: NavHostController) {
    val generoJuvenil: String = "Literatura juvenil"
    val generoClasicos: String = "Clásicos"
    val generoInfantil: String = "Literatura infantil"
    val generoFantasia: String = "Fantasía"
    val generoCiencia: String = "Ciencia Ficción"

    val correo = "saphiralopez11@gmail.com"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Géneros",
            fontSize = 25.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Literatura juvenil Card
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = CafeRojizo,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
            modifier = Modifier
                .padding(top = 0.dp, bottom = 15.dp)
                .fillMaxWidth()
                .weight(1f)
                .clickable { navController.navigate("ventanaLibros/$generoJuvenil/$correo") }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Literatura juvenil",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Image(
                    painter = painterResource(id = R.drawable.literaturajuvenil),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .aspectRatio(1f)
                )

            }
        }

        // Clásicos Card
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Cafe,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f)
                .clickable { navController.navigate("ventanaLibros/$generoClasicos/$correo") }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Clásicos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Image(
                    painter = painterResource(id = R.drawable.clasicos),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .aspectRatio(1f)
                )
            }
        }

        // Otro Card (Ejemplo)
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = CafeRojizo,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f)
                .clickable { navController.navigate("ventanaLibros/$generoInfantil/$correo") }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Literatura infantil",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Image(
                    painter = painterResource(id = R.drawable.literatura_infantil),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .aspectRatio(1f)
                )
            }
        }

        // Otro Card (Ejemplo)
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Cafe,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f)
                .clickable { navController.navigate("ventanaLibros/$generoFantasia/$correo") }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Fantasía",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Image(
                    painter = painterResource(id = R.drawable.fantasia),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .aspectRatio(1f)
                )
            }
        }

        // Otro Card (Ejemplo)
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = CafeRojizo,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f)
                .clickable { navController.navigate("ventanaLibros/$generoCiencia/$correo") }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Ciencia Ficción",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Image(
                    painter = painterResource(id = R.drawable.ciencia_ficcion),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .aspectRatio(1f)
                )
            }
        }
    }
}




@SuppressLint("SuspiciousIndentation")
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    val navController = rememberNavController()
        MenuGenerosLibros(navController = navController)
}

