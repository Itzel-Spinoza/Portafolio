package com.example.libreriaelbuho

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.libreriaelbuho.controlador.Libros
import com.example.libreriaelbuho.controlador.LibrosViewModel
import com.example.libreriaelbuho.modelo.Libro
import com.example.libreriaelbuho.ui.theme.Cafe
import com.example.libreriaelbuho.ui.theme.CafeClaro
import com.example.libreriaelbuho.ui.theme.CafeRojizo
import com.example.libreriaelbuho.ui.theme.GreyClaro
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme


class VentanaAdminInventario : ComponentActivity() {
    val viewModel by viewModels<LibrosViewModel>()


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

@SuppressLint("SuspiciousIndentation")
@Composable
fun agregarNuevoLibro(listaLibros: ArrayList<Libro>, viewModel: LibrosViewModel){
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    val estado = "Disponible"


    // Lista de elementos para el spinner
    val items = listOf("Literatura juvenil", "Clásicos", "Literatura infantil", "Fantasía","Ciencia ficción")

    // Estado para controlar si el menú desplegable está abierto o cerrado
    val expanded = remember { mutableStateOf(false) }

    // Estado para mantener el índice del elemento seleccionado
    val selectedIndex = remember { mutableStateOf(0) }

    /// URI de la imagen seleccionada
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Función para manejar la URI de la imagen seleccionada
    val onImageSelected: (Uri) -> Unit = { uri ->
        selectedImageUri = uri
    }

    // Función para alternar entre el estado de expandido y contraído del menú desplegable
    val onToggleDropdown: () -> Unit = {
        expanded.value = !expanded.value
    }
    val librosCon = Libros()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = "Agregar nuevo libro",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

            RoundedTextField1(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                leadingIcon = Icons.Default.Create,
                placeholder = "Titulo libro",
                value = titulo,
                onValueChange = { titulo = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
            )
            RoundedTextField1(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                leadingIcon = Icons.Default.Create,
                placeholder = "Autor",
                value = autor,
                onValueChange = { autor = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
            )
            RoundedTextField1(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                leadingIcon = Icons.Default.Create,
                placeholder = "Sinopsis",
                value = sinopsis,
                onValueChange = { sinopsis = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
            )

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Selecciona el genero del libro:",
                textAlign = TextAlign.Left,
                fontSize = 16.sp
            )

            // Botón que muestra el elemento seleccionado y permite abrir/cerrar el menú desplegable
            Button(
                onClick = onToggleDropdown,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Cafe,
                    contentColor = Color.White
                )
            ) {
                Text(text = items[selectedIndex.value])
            }

            // DropdownMenu que muestra la lista de elementos cuando el botón es presionado
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(onClick = {
                        selectedIndex.value = index
                        genero = item
                        expanded.value = false
                    }) {
                        Text(text = item)
                    }


                }
            }

            NumberTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                leadingIcon = Icons.Default.Create,
                placeholder = "Anio de publicacion",
                value = anio,
                onValueChange = { anio = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
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

            NumberTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                leadingIcon = Icons.Default.Create,
                placeholder = "Precio",
                value = precio,
                onValueChange = { precio = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
            )


            Button(
                onClick = {

                    val id = librosCon.generarIDLibros()
                    val nuevoLibro = Libro(
                        id = viewModel.generarIDLibros(),
                        titulo = titulo,
                        autor = autor,
                        sinopsis = sinopsis,
                        anio = anio,
                        genero = genero,
                        cantidad = cantidad,
                        precio = precio,
                        estado = estado
                    )
                    viewModel.addLibros(nuevoLibro)
                    println("ID: $id")
                    println("Título: $titulo")
                    println("Autor: $autor")
                    println("Sinopsis: $sinopsis")
                    println("Año: $anio")
                    println("Género: $genero")
                    println("Cantidad: $cantidad")
                    println("Precio: $precio")
                    println("Estado: $estado")

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Cafe,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Guardar")
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Cancelar")
            }
        }
    }


@Composable
fun inventarioGeneral(navController: NavHostController, viewModel: LibrosViewModel,correo: String?) {

    val libros = remember { mutableStateListOf<Libro>() }


    LaunchedEffect(Unit) {
        viewModel.getLibros2 { librosResponse ->
            librosResponse?.let { libros.addAll(it) } // Agregamos los libros de la respuesta a la lista libros
        }
    }

    Scaffold(
        bottomBar = {

            BottomAppBar(
                containerColor = CafeClaro,
                contentColor = Color.White,
                actions = {
                    Button(
                        onClick = {navController.navigate("ventanaAgregarNuevoLibro")},
                        modifier = Modifier
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(text = "Agregar libro")
                    }



                }
            )
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Inventario",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = CafeRojizo,
                fontSize = 20.sp
            )
            Text(
                text = "Género",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                fontSize = 15.sp
            )

            LazyRow(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                items(6) { index ->
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
                        Text(text = "Todo")
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
                        Text(text = "Literatura juvenil")
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
                        Text(text = "Clásicos")
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
                        Text(text = "Literatura infantil")
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
                        Text(text = "Fantasía")
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
                        Text(text = "Ciencia ficción")
                    }
                }
            }

            Text(
                text = "Nombre género",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 15.sp
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                itemsIndexed(libros.chunked(2)) { _, librosEnFila ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        librosEnFila.forEach { libro ->
                            CardLibrosHorizontal(navController = navController, libro = libro, correo = correo)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }


        }
    }
}
@Composable
fun ImageUploadButton(onImageSelected: (Uri) -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)

            val activity = context as Activity
            activity.startActivityForResult(intent, REQUEST_IMAGE_PICK)
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Seleccionar imagen")
    }
}


// Constante para identificar el resultado de la selección de imágenes
const val REQUEST_IMAGE_PICK = 1001






@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    LibreriaElBuhoTheme {

        val viewModel = LibrosViewModel()
        val listaLibros = remember { arrayListOf<Libro>() }


        agregarNuevoLibro(listaLibros = listaLibros, viewModel = viewModel)

    }
}