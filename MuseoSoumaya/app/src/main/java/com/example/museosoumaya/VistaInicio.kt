package com.example.museosoumaya

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.museosoumaya.ui.theme.MuseoSoumayaTheme
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter


class VistaInicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseoSoumayaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Inicio()
                }
            }
        }
    }
}

@Composable
fun AutoScrollSlider(
    images: List<Int>,
    shape: RoundedCornerShape
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(images.size) { index ->
            val imageResId = images[index]
            ImageSliderItem(imageResId = imageResId, shape = shape)
        }
    }
}


@Composable
fun ImageSliderItem(imageResId: Int, shape: RoundedCornerShape) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(300.dp)
            .height(200.dp),
        shape = shape
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    }
}

@Composable
fun MyAutoScrollSlider() {
    val images = listOf(
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3
    )

    AutoScrollSlider(images, shape = RoundedCornerShape(16.dp))
}
@Composable
fun LazyRowWithCards() {
    val items = listOf(
        Item("https://upload.wikimedia.org/wikipedia/commons/c/c1/La_puerta_del_Infierno_.jpg", "La puerta del infierno")
               , Item("https://upload.wikimedia.org/wikipedia/commons/8/85/99_Cabeza-de-San-Juan-Bautista-3.jpg", "Cabeza de san juan bautista")
    )

    LazyRow {
        items(items.size) { index ->
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardObra(item = items[index])
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = items[index].text,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
    }
}

data class Item(val imageUrl: String, val text: String) // Cambiado a URL de imagen

@Composable
fun CardObra(item: Item) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(item.imageUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
@Composable
fun Inicio(navController: NavHostController, correo: String?, nombre: String?) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White // Agrega elevación para una apariencia más elevada
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, // Distribuye el espacio entre los iconos
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    MyIconButton(
                        onClick = {/*navController.navigate("inicioScreen")*/},
                        icon = painterResource(R.drawable.home), // Reemplaza con el recurso de imagen deseado
                        contentDescription = "Inicio",
                        label = "Inicio"
                    )

                    MyIconButton(
                        onClick = {
                            Log.d(TAG, "El valor de la variable es: $correo")

                            navController.navigate("vistaObrasDeArte/$correo")},
                        icon = painterResource(R.drawable.art), // Reemplaza con el recurso de imagen deseado
                        contentDescription = "Menú",
                        label = "Obras de arte"
                    )

                    MyIconButton(
                        onClick = {navController.navigate("vistaTotalBoleto/$correo")},
                        icon = painterResource(R.drawable.boletos_de_avion), // Reemplaza con el recurso de imagen deseado
                        contentDescription = "Carrito",
                        label = "Boletos"
                    )

                    MyIconButton(
                        onClick = {navController.navigate("vistaMenuPerfil/$correo/$nombre")},
                        icon = painterResource(R.drawable.usuario), // Reemplaza con el recurso de imagen deseado
                        contentDescription = "Perfil",
                        label = "Perfil"
                    )


                }
            }
        },
    ) {  innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Text(
                text = "Hola $nombre",
                modifier = Modifier
                    .padding(30.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            val images = listOf(
                R.drawable.soumaya_removebg_preview,
                R.drawable.soumaya_removebg_preview,
                R.drawable.soumaya_removebg_preview
            )

           MyAutoScrollSlider()
            Text(
                text = "Compra tus boletos anticipadamente, y olvidate de hacer fila",
                modifier = Modifier
                    .padding(20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )


            Button(
                onClick = {navController.navigate("vistaTotalBoleto")},
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.boleto),
                        contentDescription = "Imagen del botón",
                        modifier = Modifier.size(24.dp) // Tamaño de la imagen
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Espacio entre la imagen y el texto
                    Text(text = "Compra boletos")
                }
            }


            Text(
                text = "Obras destacadas",
                modifier = Modifier
                    .padding(20.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            LazyRowWithCards()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Imagen a la izquierda
                    Image(
                        painter = painterResource(id = R.drawable.obra_removebg_preview),
                        contentDescription = "Imagen",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(0.dp)
                    )

                    // Texto a la derecha
                    Column(
                        modifier = Modifier.padding(0.dp)
                    ) {
                        Text(
                            text = "Horarios",
                            color = Color.Black,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Todos los días de:",
                            color = Color.Gray
                        )

                        Text(
                            text = "10:30 a 18:30h",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun MyIconButton(
    onClick: () -> Unit,
    icon: Painter, // La imagen del botón
    contentDescription: String, // Descripción de la imagen
    iconSize: Dp = 30.dp,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(iconSize)
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    MuseoSoumayaTheme {
        /* val images = listOf(
             R.drawable.a1,
             R.drawable.a2,
             R.drawable.a3
         )

         AutoScrollSlider(images, shape = RoundedCornerShape(16.dp))*/
        val navController = rememberNavController()
        val correo = "saphira"
        val nombre = "Itzel"
        Inicio(navController = navController, correo = correo, nombre = nombre)
    }
}
