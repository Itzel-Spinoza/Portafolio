package com.example.myapplication
import android.content.Context
import android.widget.Toast
import java.util.UUID


class ConInventarioRopa (private val mRopa: MRopa)  {
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

    fun agregarPren(context: Context, ide: String, correo:String, nombrePrenda:String, talla:String, categoria:String, genero: String, descripcion:String, foto:String, precio: String, porcentaje:String, aso:String?, donativo: String, estado:String){
        val ropa = Ropa(ide, correo,nombrePrenda,talla,categoria,genero,descripcion,foto,precio,porcentaje,aso,donativo,estado)
        mRopa.agregarPrenda(context, ropa) { exitoso, mensaje ->
            if (exitoso) {
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        }
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

    fun editarPrenda(
        context: Context,
        id: String?, nombrePrenda: String, talla: String, categoria: String,
        genero: String, descripcion:String, precio: String, porcentaje: String, aso: String?, donativo: String
    ) {
        mRopa.actualizarPrenda(id, nombrePrenda, talla, categoria, genero, descripcion, precio,porcentaje,aso,donativo ) { exitoso ->
            if (exitoso) {
                // Perfil actualizado exitosamente
                Toast.makeText(context, "Prenda actualizada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar el usuario o hubo un problema
                Toast.makeText(context, "Error al actualizar prenda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun editarPrenda2(id: String?, estado: String, nombrePrenda: String, talla: String, categoria: String,genero: String, descripcion:String, precio: String, porcentaje: String, aso: String?, donativo: String){
        mRopa.actualizarPrenda2(id, estado, nombrePrenda, talla, categoria, genero, descripcion, precio,porcentaje,aso,donativo ) { exitoso ->
            if (exitoso) {
                // Perfil actualizado exitosamente
                //Toast.makeText(context, "Prenda actualizada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar el usuario o hubo un problema
                //Toast.makeText(context, "Error al actualizar prenda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun eliminarPrenda(context: Context,ide: String){
        mRopa.eliminarPrenda(context,ide) { exitoso ->
            if (exitoso) {
                // Dirección eliminada exitosamente
                Toast.makeText(context, "Prenda eliminada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                // No se pudo encontrar la dirección o hubo un problema
                Toast.makeText(context, "Error al eliminar prenda", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun filtrarRopa(estado: String, correo: String?, callback: (List<Ropa>) -> Unit) {
        mRopa.filtrarRopa(correo, estado) { ropa ->
            if (ropa.isNotEmpty()) {
                // Ropa encontrada
                callback(ropa)
            } else {
                // Ropa no encontrada
                callback(emptyList())
            }
        }
    }



    fun comprobarID(id: String, callback: (String) -> Unit) {
        mRopa.comprobarID(id) { nuevoID ->
            // Verificar si se recibió un nuevo ID del callback
            if (nuevoID.isNotEmpty()) {
                // Se llamó al callback con un nuevo ID, lo devolvemos
                callback(nuevoID)
            } else {
                // No se recibió un nuevo ID, generamos uno nuevo
                val nuevoIDGenerado = generarID()
                callback(nuevoIDGenerado)
            }
        }
    }

    fun generarID(): String {
        val id = UUID.randomUUID().toString()
       return id
    }

    fun verPrendasRopa(correo: String?, callback: (List<Ropa>?) -> Unit) {
        mRopa.obtenerRopa(correo) { ropa ->
            if (ropa != null) {
                // Aquí puedes realizar cualquier procesamiento adicional en la lista si es necesario
                val cardlist: List<Ropa>? = ropa

                // Llamar al callback con la lista creada
                callback(cardlist)
            } else {
                // Llamar al callback con null si no se pudieron obtener direcciones
                callback(null)
            }
        }
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