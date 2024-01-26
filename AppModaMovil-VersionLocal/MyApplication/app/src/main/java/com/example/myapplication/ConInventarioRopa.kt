package com.example.myapplication
import android.content.Context
import java.util.UUID


class ConInventarioRopa (private val dataRepository: DataRepository)  {
    fun mostrarAsociaciones(): MutableList<Asociacion>{
        val cardList = DataRepository.listaAsociaciones
        return cardList
    }

    fun mostrarGenero(): MutableList<String> {
        val genero = mutableListOf<String>()
        for (gene in DataRepository.listaGenero){
            val nombreGene = gene.nombre
            genero.add(nombreGene)
        }
        return genero
    }

    fun mostrarTallas(): MutableList<String>{
        val tallas = mutableListOf<String>()
        for (talla in DataRepository.listaTallas){
            val nomTalla = talla.talla
            tallas.add(nomTalla)
        }
        return tallas
    }

    fun mostrarAsociaciones2(): MutableList<String>{
        val aso = mutableListOf<String>()
        for (asociacion in DataRepository.listaAsociaciones){
            val nomaso = asociacion.nombreAs
            aso.add(nomaso)
        }
        return aso
    }

    fun mostrarcategoria(genero: String?): List<String> {

        val categorias = mutableListOf<String>()

        for (cate in DataRepository.listaCategoriasRopa) {
            if (genero == cate.genero) {
                val nombreCate = cate.nombreCat
                categorias.add(nombreCate)
            }
        }

        return categorias
    }

    fun agregarPren(context: Context, ide:String, correo:String, nombrePrenda:String, talla:String, categoria:String, genero: String, descripcion:String, foto:String, precio: String, porcentaje:String, aso:String?, donativo: String, estado:String){
            val nuevaPrenda = Ropa(ide,correo,nombrePrenda,talla,categoria,genero,descripcion,foto,precio,porcentaje,aso,donativo,estado)
            DataRepository.listaRopa.add(nuevaPrenda)
            imprimirInventario()
    }

    fun calcularDonativo(precio:String,porcentaje: String): Pair<String,String> {
        val precioF = precio.toFloat()
        val porcentajeF = porcentaje.toFloat()

        val descuento = precioF * (porcentajeF/100)
        val precioRopa = precioF - descuento

        val descuento2 = descuento.toString()
        val precioRopa2 = precioRopa.toString()
        return Pair(descuento2, precioRopa2)

    }
    fun calcularGanancia(precio: String, porcentaje: String): String {
        val precioF = precio.toFloat()
        val porcentajeF = porcentaje.toFloat()

        val descuento = precioF * (porcentajeF/100)
        val precioRopa = precioF - descuento
        val precioRopa2 = precioRopa.toString()
        return(precioRopa2)
    }

    fun calcularDonativo2(precio:String,porcentaje: String): String {
        val precioF = precio.toFloat()
        val porcentajeF = porcentaje.toFloat()

        val descuento = precioF * (porcentajeF/100)
        val precioRopa = precioF - descuento

        val descuento2 = descuento.toString()
        val precioRopa2 = precioRopa.toString()
        return descuento2

    }

    fun editarPrenda(id: String?, nombrePrenda: String, talla: String, categoria: String,genero: String, descripcion:String, precio: String, porcentaje: String, aso: String?, donativo: String): Boolean {
        val ropa = DataRepository.listaRopa.find { it.ide == id }

        if(ropa != null){
            ropa.nombrePrenda = nombrePrenda
            ropa.talla = talla
            ropa.categoria = categoria
            ropa.genero = genero
            ropa.descripcion = descripcion
            ropa.precio = precio
            ropa.porcentaje = porcentaje
            ropa.aso = aso
            ropa.donativo = donativo
            return true
        }

        return false
    }

    fun editarPrenda2(id: String?, estado: String, nombrePrenda: String, talla: String, categoria: String,genero: String, descripcion:String, precio: String, porcentaje: String, aso: String?, donativo: String): Boolean {
        val ropa = DataRepository.listaRopa.find { it.ide == id }

        if(ropa != null){
            ropa.nombrePrenda = nombrePrenda
            ropa.estado = estado
            ropa.talla = talla
            ropa.categoria = categoria
            ropa.genero = genero
            ropa.descripcion = descripcion
            ropa.precio = precio
            ropa.porcentaje = porcentaje
            ropa.aso = aso
            ropa.donativo = donativo
            return true
        }

        return false
    }

    fun eliminarPrenda(ide: String){
        DataRepository.listaRopa.removeIf { it.ide == ide }
    }
    fun filtrarRopa(estado: String, correo: String?): MutableList<Ropa> {
        val cardList = DataRepository.listaRopa.filter {
            it.estado == estado && it.correo == (correo ?: "")
        }.toMutableList()

        return cardList
    }


    fun comprobarID(id: String): String {
        for (ropa in DataRepository.listaRopa) {
            if (id == ropa.ide) {
                val ide = generarID()
                return ide
            }

        }
        return id
    }

    fun generarID(): String {
        val id = UUID.randomUUID().toString()
       return id
    }
    fun verPrendasRopa(correo: String?): MutableList<Ropa> {

        val cardList = DataRepository.listaRopa.filter { it.correo == correo }.toMutableList()


        return cardList
    }
    fun imprimirInventario(){
        for(ropa in DataRepository.listaRopa){
            println("ID: ${ropa.ide}")
            println("Nombre prenda: ${ropa.nombrePrenda}")
            println("Descripción: ${ropa.descripcion}")
            println("Precio: ${ropa.precio}")
            println("Porcentaje: ${ropa.porcentaje}")
            println("Talla: ${ropa.talla}")
            println("Categoría: ${ropa.categoria}")
            println("Foto: ${ropa.foto}")
            println("Correo: ${ropa.correo}")
            println("Aso: ${ropa.aso}")
            println("Donativo: ${ropa.donativo}")
            println("Estado: ${ropa.estado}")
            println("----------------------")
        }
    }

}