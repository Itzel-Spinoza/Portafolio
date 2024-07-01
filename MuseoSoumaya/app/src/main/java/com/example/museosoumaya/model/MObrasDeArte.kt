package com.example.museosoumaya.model
import com.google.firebase.firestore.FirebaseFirestore
class MObrasDeArte {

    fun obtenerObraDeArte(callback: (List<ObraArte>?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val obrasss = firestore.collection("obrasDeArte")

        obrasss.get()
            .addOnSuccessListener { querySnapshot ->
                val obra = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(ObraArte::class.java)
                }
                callback(obra)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                callback(null)
            }
    }

    fun filtrarLibro(estilo: String?, callback: (List<ObraArte>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val obrasss = firestore.collection("obrasDeArte")

        obrasss.whereEqualTo("estiloDeArte", estilo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val obra = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(ObraArte::class.java)
                }
                callback(obra)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                callback(emptyList())
            }
    }
}