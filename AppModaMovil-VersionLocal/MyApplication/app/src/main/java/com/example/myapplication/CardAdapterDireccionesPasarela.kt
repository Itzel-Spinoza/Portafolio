package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapterDireccionesPasarela(private var cardList: List<Direccion>, private val listener: PasarelaDireccion) :
    RecyclerView.Adapter<CardAdapterDireccionesPasarela.CardViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(direccion: Direccion)
    }
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardCalle : TextView = itemView.findViewById(R.id.cardCalle)
        val cardCol : TextView = itemView.findViewById(R.id.cardColonia)
        val cardExt : TextView = itemView.findViewById(R.id.cardNoExt)
        val cardCP : TextView = itemView.findViewById(R.id.cardCP)
        val cardMuni : TextView = itemView.findViewById(R.id.cardMunicipio)
        val cardEst : TextView = itemView.findViewById(R.id.cardEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_direcciones, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]
        holder.cardCalle.text = item.calle
        holder.cardCol.text = item.colonia
        holder.cardExt.text = item.noext
        holder.cardCP.text = item.cp
        holder.cardMuni.text = item.municipio
        holder.cardEst.text = item.estado

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
            Log.d("Adapter", "Item clicked: ${item.calle}")
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun actualizarDatos(nuevaLista: List<Direccion>) {
        cardList = nuevaLista
        notifyDataSetChanged()
    }
}