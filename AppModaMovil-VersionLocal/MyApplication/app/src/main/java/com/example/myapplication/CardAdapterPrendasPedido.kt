package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapterPrendasPedido(private var cardList: List<Ropa>, private val listener: VerPedidos) :
    RecyclerView.Adapter<CardAdapterPrendasPedido.CardViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(ropa: Ropa)
    }
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardNombrePrenda : TextView = itemView.findViewById(R.id.cardNombrePrenda)
        val cardTalla : TextView = itemView.findViewById(R.id.cardTallaa)
        val cardEstado : TextView = itemView.findViewById(R.id.cardEstadoo)
        val cardPrecio : TextView = itemView.findViewById(R.id.cardPrecio)
        val cardDonativo : TextView = itemView.findViewById(R.id.cardDonativo)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_ropa_inventario, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]
        holder.cardNombrePrenda.text = item.nombrePrenda
        holder.cardTalla.text = item.talla
        holder.cardEstado.text = item.estado
        holder.cardPrecio.text = item.precio
        holder.cardDonativo.text = item.donativo

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun actualizarDatos(nuevaLista: List<Ropa>) {
        cardList = nuevaLista
        notifyDataSetChanged()
    }

}