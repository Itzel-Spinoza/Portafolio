package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapterPrendasCarrito(private var cardList: List<RopaCarrito>, private val listener: Carrito) :
    RecyclerView.Adapter<CardAdapterPrendasCarrito.CardViewHolder>() {
    interface OnItemClickListener {
        //fun onItemClick(ropaCarrito: RopaCarrito)
        fun onEliminarClick(ropaCarrito: RopaCarrito)
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardNombrePrenda: TextView = itemView.findViewById(R.id.txtCardNombrePrenda)
        val cardTalla: TextView = itemView.findViewById(R.id.cardTallaaaa)
        val cardPrecio: TextView = itemView.findViewById(R.id.cardTotalPrendaaa)
        val cardDonativo: TextView = itemView.findViewById(R.id.cardTotalDonativo)
        val cardNombreVendedor: TextView = itemView.findViewById(R.id.cardNombreVendedor)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminarPrendaCarrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_ropa_carrito, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]
        holder.cardNombrePrenda.text = item.nombrePrenda
        holder.cardTalla.text = item.talla
        holder.cardNombreVendedor.text = item.nombreVendedor
        holder.cardPrecio.text = item.precio
        holder.cardDonativo.text = item.donativo

        holder.btnEliminar.setOnClickListener {
            listener.onEliminarClick(item)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun actualizarDatos(nuevaLista: List<RopaCarrito>) {
        cardList = nuevaLista
        notifyDataSetChanged()
    }

}