package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapterPedidos(private var cardList: List<Pedido>, private val listener: Pedidos) :
    RecyclerView.Adapter<CardAdapterPedidos.CardViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(pedido: Pedido)
    }
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardIdPedido : TextView = itemView.findViewById(R.id.cardIdPrenda)
        val cardNombreCliente: TextView = itemView.findViewById(R.id.cardNombreCliente)
        val cardDireccion: TextView = itemView.findViewById(R.id.cardDireccion)
        val cardEstadoPedido: TextView = itemView.findViewById(R.id.estadoPedido)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pedidos, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]

        holder.cardIdPedido.text = item.idPedido
        holder.cardNombreCliente.text = item.correoCliente
        holder.cardDireccion.text = item.direccion
        holder.cardEstadoPedido.text = item.estadoPedido

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
            Log.d("Adapter", "Item clicked: ${item.idPedido}")
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun actualizarDatos(nuevaLista: List<Pedido>) {
        cardList = nuevaLista
        notifyDataSetChanged()
    }
}