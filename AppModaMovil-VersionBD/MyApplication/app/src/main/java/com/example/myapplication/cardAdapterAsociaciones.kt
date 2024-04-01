package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class cardAdapterAsociaciones(private val cardList: List<Asociacion>) :
    RecyclerView.Adapter<cardAdapterAsociaciones.CardViewHolder>() {
    //var selectedCard: Asociacion? = null
    var selectedItem = -1
    var selectedCard: Asociacion? = null
    //var selectedItem: Asociacion? = null
    var tarjetaSeleccionada: Asociacion? = null

    val mRopa = MRopa()
    val conInventarioRopa = ConInventarioRopa(mRopa)

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardAsociacion: TextView = itemView.findViewById(R.id.cardNomAsociacion)
        val cardView: CardView = itemView.findViewById(R.id.cardPedido)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_asociaciones, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]
        holder.cardAsociacion.text = item.nombreAs


        // Configura el listener para la selección de la tarjeta
        holder.cardView.setOnClickListener {
            val previousSelectedItem = selectedItem
            selectedItem = position
            notifyItemChanged(previousSelectedItem)
            notifyItemChanged(selectedItem)
            tarjetaSeleccionada = item

            println("Nombre de la asociación seleccionada: ${tarjetaSeleccionada?.nombreAs}")


        }

        // Establece el fondo seleccionado o no seleccionado
        if (position == selectedItem) {
            holder.itemView.setBackgroundResource(R.drawable.card_seleccionado)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.card_no_seleccionado)
        }



    }


    override fun getItemCount(): Int {
        return cardList.size
    }
}