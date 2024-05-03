package com.example.libreriaelbuho

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.libreriaelbuho.controlador.CarritoViewModel
import com.example.libreriaelbuho.controlador.LibrosViewModel
import com.example.libreriaelbuho.modelo.Carrito
import com.example.libreriaelbuho.ui.theme.Cafe
import com.example.libreriaelbuho.ui.theme.CafeClaro
import com.example.libreriaelbuho.ui.theme.GreyClaro
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme
import kotlinx.coroutines.launch

class VentanaDetallesLibro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibreriaElBuhoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //VentanaInformacionLibro()
                }
            }
        }
    }
}
@Composable
fun VentanaInformacionLibro(navController: NavController,correo: String?,id: String?,titulo: String?, autor: String?, genero: String?, anio: String?, precio: String?, sinopsis: String?) {
    val cantidad2 = precio!!.toFloat()


    val libroVM = LibrosViewModel()
    val carritoVM = CarritoViewModel()

    var cantidad by remember { mutableStateOf("") }
    var precioTotal by remember { mutableStateOf(0f) }
    val viewModelCarrito = CarritoViewModel()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Row para colocar elementos en una fila
        Row(
            modifier = Modifier

        ) {
            // Primera columna para la imagen


            Column(
                modifier = Modifier
                    .weight(1f) // Ocupará la mitad del espacio disponible
                    .padding(8.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.untitled_design__2__removebg_preview),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 0.dp)
                        .clip(shape = RoundedCornerShape(16.dp)) // Bordes redondeados
                )
            }

            // Segunda columna para el texto
            Column(
                modifier = Modifier
                    .weight(1f) // Ocupará la mitad del espacio disponible
                    .padding(8.dp)
            ) {
                Text(
                    text = "$titulo",
                    modifier = Modifier
                        .padding(8.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "$autor",
                    modifier = Modifier
                        .padding(8.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 16.sp
                )
                Card {
                    Text(
                        text = "$genero",
                        modifier = Modifier
                            .padding(8.dp),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Light,
                    )
                }

                Text(
                    text = "$anio",
                    modifier = Modifier
                        .padding(8.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal,
                )

                Text(
                    text = "$$precio",
                    modifier = Modifier
                        .padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    color = Cafe
                )
            }
        }

        // Texto "Sinopsis"
        Text(
            text = "Sinopsis",
            modifier = Modifier
                .padding(top = 45.dp, bottom = 20.dp, start = 8.dp, end = 8.dp),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$sinopsis",
            modifier = Modifier
                .padding(bottom = 30.dp, start = 8.dp, end = 8.dp),
            fontWeight = FontWeight.Light,
            color = Color.Gray,
            textAlign = TextAlign.Justify
        )
        NumberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Create,
            placeholder = "Cantidad",
            value = cantidad,
            onValueChange = { cantidad = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        Button(
            onClick = {
                val cantidadParse = cantidad.toInt()
                viewModelCarrito.viewModelScope.launch {

                    val resultadoMultiplicacion = viewModelCarrito.multiplicar(cantidadParse, cantidad2)
                    precioTotal = resultadoMultiplicacion
                    Log.d("Preciooooo", precioTotal.toString())

                    val nuevoLibro = Carrito(
                        id = id,
                        correoCliente = correo,
                        tituloLibro = titulo,
                        autor = autor,
                        cantidad = cantidadParse,
                        precioLibro = cantidad2,
                        subtotal = precioTotal

                    )
                    carritoVM.addLibros(nuevoLibro)
                }

                //val subtotal = libroVM.subtotalLibros(cantidad,precio)
                //carritoVM.agregarAlCarrito(navController, id, correo, titulo, autor, cantidad, cantidad2,subtotal)


                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CafeClaro,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp)) // Ajusta el espaciado entre el ícono y el texto
                Text(
                    text = "Agregar",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

            }

        }


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    LibreriaElBuhoTheme {
            VentanaInformacionLibro(
                navController = rememberNavController(),
                correo = "correo@example.com",
                id = "123",
                titulo = "Título del libro",
                autor = "Autor del libro",
                genero = "Género del libro",
                anio = "2022",
                precio = "19.99",
                sinopsis = "Sinopsis del libro..."
            )
        }

    }
