package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapterDonaciones (private var cardList: List<Donativos>, private val listener: Donaciones) :
    RecyclerView.Adapter<CardAdapterDonaciones.CardViewHolder>() {
    val conDonaciones = ConDonaciones(DataRepository)
    interface OnItemClickListener {
        fun onItemClick(donativos: Donativos)
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardIdDonacion : TextView = itemView.findViewById(R.id.txtIdDonacion)
        val cardIdPedido : TextView = itemView.findViewById(R.id.txtIdPedido)
        val cardIdPrenda : TextView = itemView.findViewById(R.id.txtIdPrenda)
        val cardNombrePrenda : TextView = itemView.findViewById(R.id.txtNombrePrendaDon)
        val cardFechaPedido : TextView = itemView.findViewById(R.id.txtFechaPedidoDon)
        val cardNombreAsociacion : TextView = itemView.findViewById(R.id.txtAsociacionDon)
        val cardTotalDonativo : TextView = itemView.findViewById(R.id.txtTotalDon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_donaciones, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]

        holder.cardIdDonacion.text = item.idDonativo
        holder.cardIdPedido.text = item.idPedido
        holder.cardIdPrenda.text = item.idPrenda
        val idPren = item.idPrenda
        val nombrePrenda = conDonaciones.buscarPrendaNombre(idPren)
        holder.cardNombrePrenda.text = nombrePrenda
        holder.cardFechaPedido.text = item.fechaPedido
        holder.cardNombreAsociacion.text = item.nombreAsociacion
        holder.cardTotalDonativo.text = item.totalDonativo

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun actualizarDatos(nuevaLista: List<Donativos>) {
        cardList = nuevaLista
        notifyDataSetChanged()
    }
}