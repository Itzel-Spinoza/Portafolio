package com.example.libreriaelbuho

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.libreriaelbuho.ui.theme.Cafe
import com.example.libreriaelbuho.ui.theme.CafeClaro
import com.example.libreriaelbuho.ui.theme.GreyClaro
import com.example.libreriaelbuho.ui.theme.LibreriaElBuhoTheme
import com.example.libreriaelbuho.ui.theme.blancoGris

class VentanaPasarelaEnvio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibreriaElBuhoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting3("Android")
                    cardDireccion()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun agregarDireccionEnvio(navController: NavHostController){
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
                        Text(text = "Datos de envio", fontWeight = FontWeight.Bold)
                    }
                }

            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = blancoGris,
                contentColor = Color.White,
                modifier = Modifier
                    .height(150.dp)
            ) {


                    //AQUI VA EL BOTON
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {navController.navigate("ventanaMetodoPago")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = "Continuar")
                        }
                    }


            }
        }
    ) {
        var mostrarAgregarNuevDir by remember { mutableStateOf(false) }
        var mostrarNuevaDireccion by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 10.dp, bottom = 150.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

                Text(modifier = Modifier
                    .padding(top = 30.dp),
                    text = "Dirección predefinida",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Cafe
                )

                Row {

                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = {  mostrarNuevaDireccion = true  }) {
                            Icon (painter = painterResource(id = R.drawable.boton),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp),
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            // Agrega tus elementos LazyColumn aquí
                            items(10) { index ->
                                cardDireccion()
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }

                }

            if (mostrarNuevaDireccion) {
                nuevaDireccion(onCancel = { mostrarNuevaDireccion = false })
            }


        }
    }
}




@Composable
fun nuevaDireccion(onCancel: () -> Unit){
    var calle by remember { mutableStateOf("") }
    var colonia by remember { mutableStateOf("") }
    var noext by remember { mutableStateOf("") }
    var cp by remember { mutableStateOf("") }
    var municipio by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(modifier = Modifier
            .padding(top = 30.dp),
            text = "Agregar nueva dirrección",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Home,
            keyboardType = KeyboardType.Text,
            placeholder = "Calle",
            value = calle,
            onValueChange = { calle = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Home,
            keyboardType = KeyboardType.Text,
            placeholder = "Colonia",
            value = colonia,
            onValueChange = { colonia = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedTextField1(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f),
                leadingIcon = Icons.Default.Edit,
                keyboardType = KeyboardType.Number,
                placeholder = "No. exterior",
                value = noext,
                onValueChange = { noext = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
            )

            RoundedTextField1(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f),
                leadingIcon = Icons.Default.Edit,
                keyboardType = KeyboardType.Number,
                placeholder = "C.P.",
                value = cp,
                onValueChange = { cp = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
            )
        }

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Edit,
            keyboardType = KeyboardType.Text,
            placeholder = "Municipio",
            value = municipio,
            onValueChange = { municipio = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Edit,
            keyboardType = KeyboardType.Text,
            placeholder = "Estado",
            value = estado,
            onValueChange = { estado = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onCancel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreyClaro,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Cancelar")
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CafeClaro,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Guardar")
            }
        }


    }
}


@Composable
fun cardDireccion(){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .clickable { }
        //.size(width = 240.dp, height = 100.dp)
    ) {
        Column(
            modifier = Modifier
                .size(width = 200.dp, height = 150.dp)
                .padding(0.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Calle",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = CafeClaro,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Colonia:",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp
                )
                Text(
                    text = " Colonia",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 10.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "No ext:",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp
                )
                Text(
                    text = " 454",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 10.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "C.P:",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp
                )
                Text(
                    text = " 93610",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 10.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Municipio, estado",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp
                )
            }
        }


    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    LibreriaElBuhoTheme {
       // Greeting3("Android")
        val navController = rememberNavController()
      agregarDireccionEnvio(navController = navController)
    // cardDireccion()
        //nuevaDireccion()
    }
}