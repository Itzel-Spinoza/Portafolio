package com.example.libreriaelbuho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.libreriaelbuho.controlador.LibrosViewModel
import com.example.libreriaelbuho.modelo.Libro
import com.example.libreriaelbuho.ui.theme.Cafe
import com.example.libreriaelbuho.ui.theme.CafeClaro
import com.example.libreriaelbuho.ui.theme.CafeRojizo
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme
class Inicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibreriaElBuhoTheme {
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
fun InicioScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Cafe,
            ),
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxWidth()
                .fillMaxHeight(.45f)
        ) {
            Text(
                text = "¿Qué quieres leer el día de hoy?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                text = "Populares",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontSize = 15.sp
            )

        }
        Text(
            text = "Novedades",
            modifier = Modifier
                .padding(20.dp),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = CafeClaro
        )

    }

    }

@Composable
fun InicioScreen2(navController: NavHostController, correo: String?, nombre: String?,viewModel: LibrosViewModel) {


    val libros = remember { mutableStateListOf<Libro>() }
    val libros2 = remember { mutableStateListOf<Libro>() }

    val generoJuvenil = "Literatura juvenil"
    LaunchedEffect(Unit) {
        viewModel.getLibros2 { librosResponse ->
            librosResponse?.let { libros.addAll(it) } // Agregamos los libros de la respuesta a la lista libros
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getLibros2 { librosResponse ->
            librosResponse?.let { libros2.addAll(it.filter { libro -> libro.genero == generoJuvenil}) } // Agregamos los libros de la respuesta a la lista libros
        }
    }
    Scaffold(
        bottomBar = {

            BottomAppBar(
                containerColor = CafeClaro,
                contentColor = Color.White,
                actions = {
                    IconButton(onClick = {navController.navigate("inicioScreen")}) {
                        Icon(Icons.Filled.Home, contentDescription = "Inicio")

                    }


                    IconButton(onClick = {navController.navigate("menu")}) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Generos"
                        )
                    }
                    IconButton(onClick = { navController.navigate("carrito")}) {
                        Icon(
                            Icons.Filled.ShoppingCart,
                            contentDescription = "Carrito"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Perfil"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
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
                        .fillMaxHeight(.45f)
                ) {
                    Text(
                        text = "$nombre, ¿Qué quieres leer el día de hoy?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )

                    Text(
                        text = "Populares",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        fontSize = 15.sp
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        items(libros2.chunked(2)) { librosEnFila ->
                            librosEnFila.forEach { libro ->
                                CardLibrosPequeno(navController = navController, libro = libro, correo = correo)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }

                }

                Text(
                    text = "Novedades",
                    modifier = Modifier
                        .padding(20.dp),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    color = CafeClaro
                )


                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    items(libros.chunked(2)) { librosEnFila ->
                        librosEnFila.forEach { libro ->
                            CardLibrosHorizontal(navController = navController, libro = libro, correo = correo)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }

            }
        }
    }







@Composable
fun CardLibrosHorizontal(navController: NavHostController, libro: Libro, correo: String?){

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            //.size(width = 240.dp, height = 100.dp)
            .clickable { navController.navigate("ventanaDetallesLibros/$correo/${libro.id}/${libro.titulo}/${libro.autor}/${libro.genero}/${libro.anio}/${libro.precio}/${libro.sinopsis}") }
    ) {
        Column(
            modifier = Modifier
                .size(width = 150.dp, height = 200.dp)
                .padding(0.dp),
            //horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.untitled_design__2__removebg_preview),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp) // Añadir padding a la izquierda y derecha
                    .padding(top = 10.dp, bottom = 1.dp)
                    .fillMaxSize(),
            )
        }

        Text(
            text = libro.titulo,
            modifier = Modifier
                .padding(start = 15.dp) // Añadir padding a la izquierda y derecha
                .padding(top = 5.dp, bottom = 5.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = libro.autor,
            modifier = Modifier
                .padding(start = 15.dp) // Añadir padding a la izquierda y derecha
                .padding(top = 2.dp, bottom = 5.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace

        )
        Text(
            text = "$ ${libro.precio}",
            modifier = Modifier
                .padding(start = 15.dp) // Añadir padding a la izquierda y derecha
                .padding(top = 2.dp, bottom = 15.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = CafeClaro,
            fontSize = 13.sp
        )
    }
}
@Composable
fun CardLibrosPequeno(navController: NavHostController, libro: Libro, correo: String?) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .clickable { navController.navigate("ventanaDetallesLibros/$correo/${libro.id}/${libro.titulo}/${libro.autor}/${libro.genero}/${libro.anio}/${libro.precio}/${libro.sinopsis}") }
        //.size(width = 240.dp, height = 100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id =R.drawable.untitled_design__2__removebg_preview),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)) // Ajusta el radio de las esquinas según tus necesidades
            )

            Spacer(modifier = Modifier.width(16.dp)) // Espacio entre la imagen y los textos

            Column {
                Text(
                    text = "${libro.titulo}",
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "${libro.autor}",
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 5.dp),
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "$ ${libro.precio}",
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 15.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = CafeClaro,
                    fontSize = 13.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewInicioScreen() {
    LibreriaElBuhoTheme {
        val navController = rememberNavController()
        val correo = "SAP"
        val nombre = "Itzel"
        InicioScreen2(navController = navController, correo = correo, nombre = nombre, viewModel = LibrosViewModel())
        //CardLibrosPequeno()
    }
}