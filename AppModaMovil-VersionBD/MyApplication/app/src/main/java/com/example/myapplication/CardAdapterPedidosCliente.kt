package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapterPedidosCliente(private var cardList: List<PedidosGeneral>, private val listener: PedidosCliente) :
    RecyclerView.Adapter<CardAdapterPedidosCliente.CardViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(pedido: Pedido)
    }
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardIdPedido : TextView = itemView.findViewById(R.id.cardIdPrenda)
        val cardFechaPedido: TextView = itemView.findViewById(R.id.fechaDelPedido)
        val cardTotalPedido: TextView = itemView.findViewById(R.id.totalPedidooo)
        val cardEstadoPedido: TextView = itemView.findViewById(R.id.estadoPedido)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pedidos_cliente, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]

        holder.cardIdPedido.text = item.idPedido
        holder.cardFechaPedido.text = item.fechaPedido
        holder.cardTotalPedido.text = item.totalPedido
        holder.cardEstadoPedido.text = item.estadoPedido

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
            Log.d("Adapter", "Item clicked: ${item.idPedido}")
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun actualizarDatos(nuevaLista: List<PedidosGeneral>) {
        cardList = nuevaLista
        notifyDataSetChanged()
    }
}