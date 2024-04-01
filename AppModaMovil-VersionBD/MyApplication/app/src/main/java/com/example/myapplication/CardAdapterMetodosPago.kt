package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapterMetodosPago(private var cardList: List<MetodosPago>, private val listener: AgregarDatosPago) :
    RecyclerView.Adapter<CardAdapterMetodosPago.CardViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(metodosPago: MetodosPago)
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTipo : TextView = itemView.findViewById(R.id.cardTipoTarj)
        val cardNotarjeta : TextView = itemView.findViewById(R.id.cardNoTarj)
        val cardTitular : TextView = itemView.findViewById(R.id.cardTitularTarj)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pagos, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]
        holder.cardTipo.text = item.tipoTarjeta
        holder.cardNotarjeta.text = item.noTarjeta
        holder.cardTitular.text = item.titularTarjeta

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
            Log.d("Adapter", "Item clicked: ${item.noTarjeta}")
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun actualizarDatos(nuevaLista: List<MetodosPago>) {
        cardList = nuevaLista
        notifyDataSetChanged()
    }
}