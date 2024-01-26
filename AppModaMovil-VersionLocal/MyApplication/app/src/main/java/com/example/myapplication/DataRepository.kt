package com.example.myapplication

object DataRepository {
    val listaUsuarios = mutableListOf<Usuario>(Usuario("Itzel","Espinoza","Lopez", "aaa","123","Femenino","Vendedor"),Usuario("Lizbeth","Lopez","Avila", "sss","123","Femenino","Comprador"))
    val listaDirecciones = mutableListOf<Direccion>(Direccion("aaa","Maclovio Herrera","Francisco villa","111","93610","Martinez de la Torre","Veracruz"))
    val listaMetodosPagos = mutableListOf<MetodosPago>()
    val listaRopa = mutableListOf<Ropa>(Ropa("3434343","aaa","Vestido verde","S","Vestidos","Mujer","Vestido de corte largo","Sin foto","300","10","DUM","30","Subido"),Ropa("124334543","aaa","Vestido blanco","XL","Vestidos","Mujer","Vestido de corte largo","Sin foto","400","10","DUM","40","Vendido"),Ropa("1234323","aaa","Vestido negro","L","Vestidos","Mujer","Vestido mini","Sin foto","400","10","DUM","40","Subido"))
    val listaAsociaciones = mutableListOf<Asociacion>( Asociacion("DUM"),Asociacion("CAID"))
    val listaCategoriasRopa = mutableListOf<Categoria>(Categoria("Vestidos","Mujer"), Categoria("Tops","Mujer"),Categoria("Sueteres","Mujer"),Categoria("Pantalones","Mujer"),Categoria("Sudaderas","Mujer"),Categoria("Faldas","Mujer"),Categoria("Shorts","Mujer"),Categoria("Accesorios","Mujer"), Categoria("Camisas y blusas","Mujer"),Categoria("Jeans","Mujer"),Categoria("Chamarras y abrigos","Mujer"),Categoria("Camisas","Hombre"), Categoria("Playeras y polos","Hombre"), Categoria("Sudaderas","Hombre"),Categoria("Pantalones","Hombre"),Categoria("Shorts","Hombre"),Categoria("Accesorios","Hombre"),Categoria("Chamarras y abrigos","Hombre"),Categoria("Jeans","Hombre"))
    val listaGenero = mutableListOf<Genero>(Genero("Mujer"), Genero("Hombre"))
    val listaTallas = mutableListOf<Talla>(Talla("XS"),Talla("S"),Talla("M"),Talla("L"),Talla("XL"),Talla("Unitalla"))
    val listaPedidos = mutableListOf<Pedido>(Pedido("758392","30/11/2023","Maclovio Herrera", "Pendiente","sss","Lizbeth","aaa","12/12/2023"))
    val listaPrendasPedidos = mutableListOf<PrendasPedido>(PrendasPedido("758392","aaa","Itzel Espinoza López","124334543","Vestido blanco","XL","Vestidos","Mujer","400","10","Vendido","DUM"))
    val listaPedidosGeneral = mutableListOf<PedidosGeneral>(PedidosGeneral("758392","30/11/2023","Maclovio Herrera","Sin entregar","sss","Lizbeth","400","40"))
    val listaDonativos = mutableListOf<Donativos>()
    val listaCarrito = mutableListOf<RopaCarrito>()
}