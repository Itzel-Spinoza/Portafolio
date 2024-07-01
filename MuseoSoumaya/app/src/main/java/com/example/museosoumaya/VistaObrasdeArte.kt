package com.example.museosoumaya

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.museosoumaya.model.EstiloArte
import com.example.museosoumaya.model.ObraArte
import com.example.museosoumaya.ui.theme.MuseoSoumayaTheme
import com.example.museosoumaya.viewModel.VMObrasDeArte

class VistaObrasdeArte : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseoSoumayaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //ObrasEnGeneral()
                }
            }
        }
    }
}

val estilosDeArte = listOf(
    EstiloArte(
        nombre = "Renacimiento",
        descripcion = "El Renacimiento fue un periodo de la historia que siguió a la Edad Media y precedió al Barroco. Se caracterizó por un renovado interés en el arte, la ciencia y la cultura en general."
    ),
    EstiloArte(
        nombre = "Barroco",
        descripcion = "El Barroco fue un estilo artístico predominante en el siglo XVII, caracterizado por la exuberancia, la dramatización y la emotividad. Se manifestó en pintura, escultura, arquitectura y música."
    ),
    EstiloArte(
        nombre = "Impresionismo",
        descripcion = "El Impresionismo fue un movimiento artístico que surgió en Francia a finales del siglo XIX. Se caracterizó por la representación de la luz y el color en la naturaleza, así como por pinceladas sueltas y la captura de momentos fugaces."
    )
)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObrasEnGeneral(navController: NavHostController, viewModel: VMObrasDeArte,correo: String?) {
    val obras = remember { mutableStateListOf<ObraArte>() }
    val correoo = correo
    LaunchedEffect(Unit) {
        viewModel.verObrasDeArte { obrasResponse ->
            obrasResponse?.let { obras.addAll(it) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
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
                        Text(text = "Obras de arte", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, top = 80.dp)
        ) {
            item {
                Text(
                    text = "Estilos de Arte",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre los botones
                ) {
                    items(estilosDeArte) { estiloDeArte ->
                        Card(
                            modifier = Modifier
                                .width(150.dp)
                                .height(70.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = estiloDeArte.nombre,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Obras de Arte",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(obras.chunked(2)) { obrasEnFila ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    obrasEnFila.forEach { obra ->
                        CardObraArteDef(item = obra, navController = navController, correo = correo)
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun CardObraArteDef(item: ObraArte, navController: NavHostController, correo: String?) {
    val url = item.urlImagen
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
                .clickable { /*navController.navigate("vista/$correo/${item.idObra}/${item.titulo}/${item.urlImagen}/${item.descripcionObra}/${item.autor}/${item.anio}/${item.estiloDeArte}/${item.sala}")*/
                    navController.navigate("vistaDetallesObra/$correo/${item.idObra}/${item.titulo}/${item.descripcionObra}/${item.autor}/${item.anio}/${item.estiloDeArte}/${item.sala}")},
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(item.urlImagen),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { /* Acción del botón */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(bottom = 16.dp),
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.marcador),
                            contentDescription = "Imagen del botón"
                        )
                    }
                )
            }
        }
        Text(
            text = item.titulo,
            textAlign = TextAlign.Left,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .padding(top = 8.dp)
                .widthIn(max = 150.dp)
        )
        Text(
            text = item.autor,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}


@Composable
fun CardObraArteDefPorVer(item: ObraArte, navController: NavHostController, correo: String?) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .clickable { /*navController.navigate("vista/$correo/${item.idObra}/${item.titulo}/${item.urlImagen}/${item.descripcionObra}/${item.autor}/${item.anio}/${item.estiloDeArte}/${item.sala}")*/
                navController.navigate("vistaDetallesObra/$correo/${item.idObra}/${item.titulo}/${item.descripcionObra}/${item.autor}/${item.anio}/${item.estiloDeArte}/${item.sala}")},
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            Image(
                painter = rememberImagePainter(item.urlImagen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { /* Acción del botón */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(bottom = 16.dp),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.marcador),
                        contentDescription = "Imagen del botón"
                    )
                }
            )

        }
    }
    Box(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = item.titulo,
                textAlign = TextAlign.Left,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .widthIn(max = 150.dp)
            )
            Text(
                text = item.autor,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.ExtraLight
            )
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
}


@Composable
fun DetallesObra(navController: NavController, correo: String?,id: String?,titulo: String?,urlImagen: String?,descripcion: String?,autor: String?,anio: String?,estilo: String?,sala: String?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "$titulo",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top=20.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$autor",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Box {
            Image(
                painter = rememberImagePainter("$urlImagen"),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .width(350.dp)
                    .height(400.dp),

                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { /* Acción del botón */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(bottom = 16.dp),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.marcador),
                        contentDescription = "Imagen del botón"
                    )
                }
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Año: $anio",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Text(
                    text = "Estilo: $estilo",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Se encuentra en la sala: $sala",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth() // Asegura que el Box ocupe todo el ancho disponible
        ) {
            Text(
                text = "Descripción: $descripcion",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Light
            )
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth() // Asegura que el Box ocupe todo el ancho disponible
        ) {
            Text(
                text = "$estilo",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Justify, // Justifica el texto dentro del Box
                color = Color.Black,
                fontWeight = FontWeight.Light
            )
        }
    }
}


@Composable
fun InformacionObra(navController: NavController, correo: String?, id: String?, titulo: String?,descripcion: String?,autor: String?,anio: String?,estilo: String?,sala: String?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "$titulo",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top=20.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$autor",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Box {
            Image(
                painter = rememberImagePainter("urlImagen"),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .width(350.dp)
                    .height(400.dp),

                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { /* Acción del botón */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(bottom = 16.dp),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.marcador),
                        contentDescription = "Imagen del botón"
                    )
                }
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Año: $anio",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Text(
                    text = "Estilo: $estilo",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Se encuentra en la sala: $sala",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth() // Asegura que el Box ocupe todo el ancho disponible
        ) {
            Text(
                text = "Descripción:",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Light
            )
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth() // Asegura que el Box ocupe todo el ancho disponible
        ) {
            Text(
                text = "$descripcion",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Justify, // Justifica el texto dentro del Box
                color = Color.Black,
                fontWeight = FontWeight.Light
            )
        }
    }
}

//OBRAS POR VER
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObrasPorVer(navController: NavHostController, correo: String?) {
    val items = listOf(
        ObraArte("https://upload.wikimedia.org/wikipedia/commons/c/c1/La_puerta_del_Infierno_.jpg", "La puerta del infierno","MIAU","0","0","0","0","0")
        , ObraArte("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnPQHW6_41TX4j8vALbJP5hbPakDWRMz7HQxef6vhTtQ&s", "Cabeza de san juan bautista","0","0","0","0","0","0")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
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
                        Text(text = "Obras por ver", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White, // Cambia el color de fondo a negro
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                //CARDS CON LAS OBRAS POR VER
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                    verticalArrangement = Arrangement.Center, // Centra verticalmente el contenido
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(items) { obraDeArte ->
                        CardObraArteDefPorVer(item = obraDeArte, navController = navController, correo = correo)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    MuseoSoumayaTheme {
        val navController = rememberNavController()
        //ObrasEnGeneral(navController = navController, )
      /* val items = listOf(
            ObraArte("https://upload.wikimedia.org/wikipedia/commons/c/c1/La_puerta_del_Infierno_.jpg", "La puerta del infierno","MIAU","0","0","0","0","0","0")
            , ObraArte("https://upload.wikimedia.org/wikipedia/commons/8/85/99_Cabeza-de-San-Juan-Bautista-3.jpg", "Cabeza de san juan bautista","0","0","0","0","0","0","0")
        )
        DetallesObra(navController = navController)*/
        //ObrasPorVer(navController = navController)
        val obraDeArte = ObraArte(
            urlImagen = "https://ejemplo.com/imagen.jpg",
            titulo = "Título de la obra",
            autor = "Autor de la obra"
        )
        val correo = "correooo"
        CardObraArteDef(item = obraDeArte, navController = navController, correo = correo)
    }
}