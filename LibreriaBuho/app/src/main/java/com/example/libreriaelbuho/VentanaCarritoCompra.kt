package com.example.libreriaelbuho

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.libreriaelbuho.controlador.CarritoViewModel
import com.example.libreriaelbuho.modelo.Carrito
import com.example.libreriaelbuho.ui.theme.CafeClaro
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme
import kotlinx.coroutines.launch

class VentanaCarritoCompra : ComponentActivity() {
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
                    NavHost(navController = navController, startDestination = "carritoCom") {
                        composable("carritoCom") {
                            val viewModel = CarritoViewModel() // Instancia del ViewModel
                            //val listaLibros = remember { arrayListOf<Carrito>() }
                            CarritoCompra(navController = navController, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoCompra(navController: NavHostController, viewModel: CarritoViewModel) {
    val carrito = remember { mutableStateListOf<Carrito>() }


    LaunchedEffect(Unit) {
        viewModel.getCarrito2 { librosResponse ->
            librosResponse?.let { carrito.addAll(it) } // Agregamos los libros de la respuesta a la lista libros
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CafeClaro,
                    titleContentColor = Color.White
                ),

                title = {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Carrito de compra", fontWeight = FontWeight.Bold)
                    }
                }

            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = CafeClaro,
                contentColor = Color.White,
                modifier = Modifier
                    .height(200.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                Row {

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        var Total by remember { mutableStateOf(0f) }
                        LaunchedEffect(Unit) {
                            viewModel.viewModelScope.launch {
                                val resultadoTotal = viewModel.getSumaTotal()
                                Total =resultadoTotal

                            }
                        }

                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Total: $ $Total",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                    }

                }

                //AQUI VA EL BOTON
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {navController.navigate("ventanaEnvios")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(text = "Comprar")
                    }
                }
                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp, top = 50.dp, bottom = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //LazyColumn(content = )

            //LISTT CON LOS CARDS DE PRODUCTOS

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(carrito.chunked(2)) { librosEnFila ->
                    librosEnFila.forEach { libro ->
                        CardProductoCarrito(navController = navController, carrito = libro)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            //
        }
    }

}


@Composable
fun CardProductoCarrito(navController: NavHostController, carrito: Carrito){
    var precioTotal by remember { mutableStateOf(0f) }
    val viewModelCarrito = CarritoViewModel()
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
        //.size(width = 240.dp, height = 100.dp)
    ) {
        Column(
            modifier = Modifier
                .size(width = 400.dp, height = 200.dp)
                .padding(0.dp),
        ){
            Row(
                modifier = Modifier

            ) {
                // Primera columna para la imagen


                Column(
                    modifier = Modifier
                        .weight(1f) // Ocupará la mitad del espacio disponible
                        .padding(top = 10.dp, start = 8.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.untitled_design__2__removebg_preview),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(0.dp)
                            .clip(shape = RoundedCornerShape(16.dp)) // Bordes redondeados
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(2f) // Ocupará la mitad del espacio disponible
                        .padding(8.dp)
                ) {

                    var cantidadLibro2 by remember { mutableStateOf(carrito.cantidad) }
                    var precioUni by remember { mutableStateOf(carrito.precioLibro) }

                    val precioLibroString = carrito.precioLibro
                    val precioLibro = precioLibroString?.toInt()



                    LaunchedEffect(Unit) {
                        val resultadoMultiplicacion = viewModelCarrito.multiplicar(cantidadLibro2, precioUni)
                        // Hacer algo con el resultado, como almacenarlo en un MutableState
                        // Por ejemplo:
                        precioTotal = resultadoMultiplicacion

                        Log.d("UI", "Cantidad libro: $cantidadLibro2")
                        Log.d("UI", "Precio: $precioUni")
                        Log.d("UI", "Resultado de la multiplicación: $precioTotal")
                    }



                    Text(text = "${carrito.tituloLibro}")
                    Text(text = "${carrito.autor}")
                    Text(text = "$ $precioLibro",
                        modifier = Modifier
                            .padding()
                    )
                    Text(text = "Cantidad: ${carrito.cantidad}")
                    Text(text = "Subtotal: $precioTotal")

                }

                Column(
                    modifier = Modifier
                        .weight(2f) // Ocupará la mitad del espacio disponible
                        .padding(start = 100.dp)
                ) {


                }
            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    LibreriaElBuhoTheme {
        val navController = rememberNavController()
        //CarritoCompra(navController = navController)
       // CardProductoCarrito(navController)
    }
}