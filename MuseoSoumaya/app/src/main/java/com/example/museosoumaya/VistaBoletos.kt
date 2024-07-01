package com.example.museosoumaya

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.museosoumaya.ui.theme.GreyClaro
import com.example.museosoumaya.ui.theme.MuseoSoumayaTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museosoumaya.model.MPedidos
import com.example.museosoumaya.model.MetodosPago
import com.example.museosoumaya.model.PedidosGeneral
import com.example.museosoumaya.viewModel.VMBoletos
import com.example.museosoumaya.viewModel.VMPedidos


class VistaBoletos : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseoSoumayaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    //VentanaTipoBoleto()
                }
            }
        }
    }
}



//VENTANA SELECCIONAR BOLETO A COMPRAR
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentanaTipoBoleto(navController: NavController, correo: String?) {
    var cantidad1 by remember { mutableStateOf("0") }
    var cantidad2 by remember { mutableStateOf("0") }

    var subtotalNino by remember { mutableStateOf("000.00") }
    var subtotalAdulto by remember { mutableStateOf("000.00") }

    var totalPedido by remember { mutableStateOf("000.00") }

    var date by remember { mutableStateOf("0") }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showCalendar by remember { mutableStateOf(false) }

    var fechaaa by remember { mutableStateOf("0") }
    val vmBoletos = VMBoletos()
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
                        Text(text = "Boletos", fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier
                    .height(150.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row{
                        Text(text = "Total:",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black)
                        Text(text = "$ ${totalPedido}",
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray)
                    }
                    //AGREGAR BOTONES CONTINUAR Y CANCELAR EN ESTA PARTE
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
                            onClick = { val folio = vmBoletos.generarIDPedido()
                                val boletoTipoNino = "nino"
                                val boletoTipoAdulto = "adulto"
                                navController.navigate("vistaPagarBoleto/$correo/$folio/$cantidad1/$cantidad2/$subtotalNino/$subtotalAdulto/$boletoTipoNino/$boletoTipoAdulto/$fechaaa")},
                            modifier = Modifier
                                .weight(1f)
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Continuar")
                        }
                    }


                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Selecciona la cantidad y tipo de boletos:",
                fontSize = 16.sp
            )

            Row {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Niños   ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "$20",
                    fontSize = 16.sp

                )
                IconButton(
                    onClick = {val numero: Int = cantidad1.toInt()
                        cantidad1 = vmBoletos.restarBoletos(numero).toString()
                        val nuevaCantidad: Int = cantidad1.toInt()
                        val subtotal: Int = subtotalNino.toInt()
                        subtotalNino = vmBoletos.restarBoletosNino(subtotal,nuevaCantidad).toString()
                        totalPedido = vmBoletos.totalBoletos(subtotalNino, subtotalAdulto)
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.boton_menos),
                            contentDescription = "Imagen del botón"
                        )
                    }
                )

                Box(modifier = Modifier.width(50.dp)) {
                    TextField(
                        value = cantidad1,
                        onValueChange = { newValue -> cantidad1 = newValue },
                        label = { Text("") },
                        modifier = Modifier.width(100.dp)
                    )
                }
                IconButton(
                    onClick = {
                        val numero: Int = cantidad1.toInt()
                        cantidad1 = vmBoletos.sumarBoletos(numero).toString()
                        val nuevaCantidad: Int = cantidad1.toInt()
                        subtotalNino = vmBoletos.sumarBoletosNino(nuevaCantidad).toString()
                        totalPedido = vmBoletos.totalBoletos(subtotalNino, subtotalAdulto)
                        },
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.boton_mas__1_),
                            contentDescription = "Imagen del botón"
                        )
                    }
                )
            }


            Row {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Adultos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "$40",
                    fontSize = 16.sp
                )
                IconButton(
                    onClick = {val numero: Int = cantidad2.toInt()
                        cantidad2 = vmBoletos.restarBoletos(numero).toString()
                        val nuevaCantidad: Int = cantidad2.toInt()
                        val subtotal: Int = subtotalAdulto.toInt()
                        subtotalAdulto = vmBoletos.restarBoletosAdulto(subtotal,nuevaCantidad).toString()
                        totalPedido = vmBoletos.totalBoletos(subtotalNino, subtotalAdulto)
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.boton_menos),
                            contentDescription = "Imagen del botón"
                        )
                    }
                )
                Box(modifier = Modifier.width(50.dp)) {
                    TextField(
                        value = cantidad2,
                        onValueChange = { newValue -> cantidad2 = newValue },
                        label = { Text("") },
                        modifier = Modifier.width(100.dp)
                    )
                }

                IconButton(
                    onClick = { val numero: Int = cantidad2.toInt()
                        cantidad2 = vmBoletos.sumarBoletos(numero).toString()
                        val nuevaCantidad: Int = cantidad2.toInt()
                        subtotalAdulto = vmBoletos.sumarBoletosAdulto(nuevaCantidad).toString()
                        totalPedido = vmBoletos.totalBoletos(subtotalNino, subtotalAdulto)
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.boton_mas__1_),
                            contentDescription = "Imagen del botón"
                        )
                    }
                )
            }

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Selecciona la fecha del día de tu visita:",
                fontSize = 16.sp
            )

            //AGREGAR CALENDARIOOOOOOO
            val date = remember { mutableStateOf(LocalDate.now())}
            CustomDatePicker(
                value = date.value,
                onValueChange = {date.value = it
                    fechaaa = it.toString()}
            )
           // fechaaa = date.toString()
            Text(
                modifier = Modifier.padding(top= 20.dp, bottom = 16.dp),
                text = "Boletos seleccionados",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row {
                Text(
                    modifier = Modifier.padding(top = 40.dp, start=0.dp),
                    text = "Tipo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 40.dp, start=30.dp),
                    text = "Cantidad",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 40.dp, start=30.dp),
                    text = "Subtotal",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Niño",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "$cantidad1",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "$ ${subtotalNino}",
                    fontSize = 16.sp
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Adulto",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "$cantidad2",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "$ ${subtotalAdulto}",
                    fontSize = 16.sp
                )
            }
        }
    }
}

//VENTANA PAGAR BOLETOS
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VentanaDatosPagoCliente(navController: NavController,  correo: String?, folio: String?, cantidad: String?, cantidad2: String?, subtotal: String?, subtotal2: String?, boletoTipoNino: String?, boletoTipoAdulto: String?, fecha: String?) {
    val mPedidos = MPedidos()
    val viewModel = VMPedidos(mPedidos)
    val viewModelBoletos =VMBoletos()
    val metodos = remember { mutableStateListOf<MetodosPago>() }
    val correoo = correo
    var metodos2 by remember { mutableStateOf<MetodosPago?>(null) }
    // Estado para controlar si se muestra la nueva tarjeta
    var mostrarNuevaTarjeta by remember { mutableStateOf(false) }

    var tarjetaSeleccionada by remember { mutableStateOf(false) }

    val precioTotal = viewModelBoletos.totalBoletos2(subtotal,subtotal2)

    LaunchedEffect(Unit) {
        viewModel.verMetodosDePago(correo) { obrasResponse ->
            obrasResponse?.let { metodos.addAll(it) }
        }
    }

    fun onCardClicked(item: MetodosPago) {
        // Realiza las acciones necesarias cuando se hace clic en un card
        tarjetaSeleccionada = true
        metodos2 = item
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
                            Text(text = "Pagar boletos", fontWeight = FontWeight.Bold)
                        }
                    }
                )
            }){
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black, // Cambia el color de fondo a negro
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top= 50.dp),
                ) {
                    Row {

                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp, top = 55.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = { mostrarNuevaTarjeta = true },
                                modifier = Modifier
                                    .padding(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Gray
                                )
                            ) {
                                Text(text = "+")
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
                                    .padding(16.dp)
                            ) {
                                items(metodos.size) { index ->
                                    val metodoPago = metodos[index]
                                    cardTarjeta(item = metodoPago, onCardClicked = ::onCardClicked)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                // Agrega tus elementos LazyColumn aquí
                               /* items(10) { index ->
                                    cardTarjeta()
                                    Spacer(modifier = Modifier.width(16.dp))
                                }*/
                            }
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        shape = RoundedCornerShape(50.dp, 0.dp, 0.dp, 0.dp),
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth()
                            //.fillMaxHeight(100.dp)
                            .size(width = 200.dp, height = 640.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.dp, top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {

                            if (mostrarNuevaTarjeta && !tarjetaSeleccionada) {
                                nuevaTarjeta(onCancel = { mostrarNuevaTarjeta = false }, labelTarjeta = "Agregar nueva tarjeta")
                            }

                            if (tarjetaSeleccionada) {
                                metodos2?.let { it1 -> verTarjeta(onCancel = { mostrarNuevaTarjeta = false }, item = it1) }
                            }


                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Black,
                                ),
                                shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp),
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .fillMaxWidth()
                                    //.fillMaxHeight(100.dp)
                                    .size(width = 200.dp, height = 200.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = {
                                            viewModel.concatenarNombreCliente(correo) { nombre ->
                                                val nombreCliente = nombre ?: "Nombre no disponible" // Si nombre es null, asigna "Nombre no disponible"
                                                // Aquí puedes utilizar la variable nombreCliente como desees
                                                viewModel.registrarPedido(
                                                    navController = navController,
                                                    cantidad = cantidad , correoCliente = correo, folio = folio, precioUnitario = "20",
                                                    subtotal = subtotal, tipoBoleto = "Nino")
                                                viewModel.registrarPedido(
                                                    navController = navController,
                                                    cantidad = cantidad2 , correoCliente = correo, folio = folio, precioUnitario = "40",
                                                    subtotal = subtotal, tipoBoleto = "Adulto")

                                                viewModel.registrarPedidoGeneral(
                                                    navController = navController,
                                                    correoCliente = correo, fechaBoletos = fecha, folio = folio, nombreCliente = nombreCliente, total = precioTotal)
                                            }

                                        },
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(10.dp, top = 50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Gray,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text(text = "Pagar")
                                    }
                                    Text(
                                        text = "$ $precioTotal",
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(10.dp, top = 50.dp),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )

                                }
                            }
                        }

                    }

                }
            }
        }

    }



//COMPOSABLE PARA EL CARD DE LAS TARJETAS
@Composable
fun cardTarjeta(item: MetodosPago, onCardClicked: (MetodosPago) -> Unit){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .clickable { onCardClicked(item)}
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
                    text = "${item.tipoTarjeta}",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
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
                    text = "No. tarjeta:",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp
                )
                Text(
                    text = "${item.noTarjeta}",
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
                    text = "Titular:",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp
                )
                Text(
                    text = "${item.titular}",
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 10.sp
                )
            }

        }


    }
}

//VENTANA PARA AGREGAR NUEVA TARJETA
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun nuevaTarjeta(onCancel: () -> Unit, labelTarjeta:String){
    var expirationDate by remember { mutableStateOf("") }
    var isDatePickerShown by remember { mutableStateOf(false) }

    var tipoTar by remember { mutableStateOf("") }
    var noTarjeta by remember { mutableStateOf("") }
    var titularTarjeta by remember { mutableStateOf("") }
    var ccv by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(modifier = Modifier
            .padding(top = 0.dp),
            text = "$labelTarjeta",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Edit,
            keyboardType = KeyboardType.Number,
            placeholder = "Número de tarjeta",
            value = noTarjeta,
            onValueChange = { noTarjeta = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Edit,
            keyboardType = KeyboardType.Text,
            placeholder = "Titular de la tarjeta",
            value = titularTarjeta,
            onValueChange = { titularTarjeta = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val date = remember { mutableStateOf(LocalDate.now())}
            CustomDatePicker(
                value = date.value,
                onValueChange = {date.value = it}
            )

            RoundedTextField5(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp),
                leadingIcon = Icons.Default.Edit,
                keyboardType = KeyboardType.Number,
                placeholder = "CCV",
                value = ccv,
                onValueChange = { ccv = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
            )
        }
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
                    containerColor = Color.Gray,
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
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Guardar")
            }
        }
    }

}


//VENTANA PARA AGREGAR NUEVA TARJETA
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun verTarjeta(onCancel: () -> Unit, item: MetodosPago){
    var expirationDate by remember { mutableStateOf("") }
    var isDatePickerShown by remember { mutableStateOf(false) }

    var tipoTar by remember { mutableStateOf("") }
    var noTarjeta by remember { mutableStateOf("") }
    var titularTarjeta by remember { mutableStateOf("") }
    var ccv by remember { mutableStateOf("") }

    tipoTar = item.tipoTarjeta
    noTarjeta = item.noTarjeta
    titularTarjeta = item.titular
    ccv = item.cvc

    Column(
        modifier = Modifier
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(modifier = Modifier
            .padding(top = 0.dp),
            text = "Seleccionar tarjeta",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Edit,
            keyboardType = KeyboardType.Number,
            placeholder = "Número de tarjeta",
            value = noTarjeta,
            onValueChange = { noTarjeta = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )

        RoundedTextField1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = Icons.Default.Edit,
            keyboardType = KeyboardType.Text,
            placeholder = "Titular de la tarjeta",
            value = titularTarjeta,
            onValueChange = { titularTarjeta = it },
            textColor = GreyClaro,
            iconTint = GreyClaro
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val date = remember { mutableStateOf(LocalDate.parse(item.fecha))}
            CustomDatePicker(
                value = date.value,
                onValueChange = {date.value = it}
            )

            RoundedTextField5(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp),
                leadingIcon = Icons.Default.Edit,
                keyboardType = KeyboardType.Number,
                placeholder = "CCV",
                value = ccv,
                onValueChange = { ccv = it },
                textColor = GreyClaro,
                iconTint = GreyClaro
            )
        }
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
                    containerColor = Color.Gray,
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
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Guardar")
            }
        }
    }

}

//VENTANA PARA VER BOLETOS COMPRADOS
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VerBoletosComprados(navController: NavController,  correo: String?){


    val mPedidos = MPedidos()
    val viewModel = VMPedidos(mPedidos)
    val viewModelBoletos =VMBoletos()
    val boletosComprados = remember { mutableStateListOf<PedidosGeneral>() }
    LaunchedEffect(Unit) {
        viewModel.verPedidosGeneral(correo) { obrasResponse ->
            obrasResponse?.let { boletosComprados.addAll(it) }
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
                        Text(text = "Boletos", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black, // Cambia el color de fondo a negro
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(text = "Activos")
                    }

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(text = "Expirados")
                    }
                }
                Text(
                    text = "Activos",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
                //CARDS CON LOS BOLETOS COMPRADOS
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    /*items(10) { index ->
                        CardBoleto()
                        Spacer(modifier = Modifier.height(16.dp))
                    }*/

                    items(boletosComprados.size) { index ->
                        val pedidoGeneral = boletosComprados[index]
                        CardBoleto(item = pedidoGeneral)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            }
        }
    }

//CARD BOLETOS
@Composable
fun CardBoleto(item: PedidosGeneral){
    val mPedidos = MPedidos()
    val viewModel = VMPedidos(mPedidos)
    val viewModelBoletos =VMBoletos()

    var cantidad1 by remember { mutableStateOf("0") }
    var cantidad2 by remember { mutableStateOf("0") }

    LaunchedEffect(item.folio) {
        // Actualiza la cantidad de boletos para cada tipo de boleto
        viewModel.controladorObtenerCantidadBoletos(folio = item.folio, tipoBoleto = "Nino") { cantidad ->
            cantidad?.let {
                cantidad1 = it
            } ?: run {
                // Manejar el caso en que cantidad es nula
                cantidad1 = "0"
            }
        }

        viewModel.controladorObtenerCantidadBoletos(folio = item.folio, tipoBoleto = "Adulto") { cantidad ->
            cantidad?.let {
                cantidad2 = it
            } ?: run {
                // Manejar el caso en que cantidad es nula
                cantidad2 = "0"
            }
        }
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
        modifier = Modifier
            .padding(bottom = 0.dp)
            .fillMaxWidth()
            //.fillMaxHeight(100.dp)
            .size(width = 200.dp, height = 400.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    text = "${item.fechaBoletos}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    text = "VIGENTES",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

            }
            Row(
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    text = "Folio",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    text = "Total",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    text = "${item.folio}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    text = "${item.total}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    text = "Tipo(s) boleto(s) y cantidad",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

            }
            Row(
                modifier = Modifier
                    .padding(top = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    text = "Adulto($cantidad2),nino($cantidad1)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

            }
            Button(
                onClick = {},
                modifier = Modifier
                    .width(300.dp)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Ver código")
            }

        }
    }
}

//COMPOSABLES PARA CREAR EL CALENDARIO PARA SELECCIONAR FECHAS
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    value: LocalDate,
    onValueChange: (LocalDate) -> Unit
) {
    val open = remember { mutableStateOf(false) }
    val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Gray,
        unfocusedBorderColor = Color.Black,
        cursorColor = Color.Black
    )

    RoundedTextFieldDatePicker(
        value = value,
        onValueChange = onValueChange,
        openValue = open,
        placeholder = "Placeholder text",
        enabled = false,
        textColor = GreyClaro,
        iconTint = GreyClaro,
        outlinedTextFieldColors = outlinedTextFieldColors
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextFieldDatePicker(
    value: LocalDate,
    onValueChange: (LocalDate) -> Unit,
    openValue: MutableState<Boolean>,
    placeholder: String,
    enabled: Boolean = true,
    textColor: Color = Color.Black,
    iconTint: Color = Color.Black,
    outlinedTextFieldColors: TextFieldColors
) {
    if (openValue.value) {
        CalendarDialog(
            state = rememberUseCaseState(visible = true, true, onCloseRequest = { openValue.value = false }),
            config = CalendarConfig(
                yearSelection = true,
                style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
                selectedDate = value
            ) { newDate ->
                onValueChange(newDate)
            }
        )
    }

    OutlinedTextField(
        value = value.format(DateTimeFormatter.ISO_DATE),
        onValueChange = {},
        modifier = Modifier.clickable { openValue.value = true },
        enabled = enabled,
        textStyle = TextStyle(color = textColor),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray, // Cambiado a Color.Gray
            unfocusedBorderColor = Color.Black,

            cursorColor = textColor
        ),
        singleLine = true,
        readOnly = true,
        placeholder = { Text(text = placeholder, style = TextStyle(color = textColor)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = iconTint
            )
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    MuseoSoumayaTheme {
        val navController = rememberNavController()
        val correo = "saphiralopez11@gmail.com"
        VentanaTipoBoleto(navController = navController,correo=correo)
        //VentanaDatosPagoCliente()
        /*nuevaTarjeta {

        }*/
        //VerBoletosComprados()
        //CardBoleto()
    }
}