package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapterPrendasPedidosCliente(private var cardList: List<PrendasPedido>, private val listener: VerPedidosCliente) :
    RecyclerView.Adapter<CardAdapterPrendasPedidosCliente.CardViewHolder>() {
    val conInventarioRopa = ConInventarioRopa(DataRepository)
    interface OnItemClickListener {
        fun onItemClick(prendasPedido: PrendasPedido)
    }
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardNombrePrenda : TextView = itemView.findViewById(R.id.txtCardNombrePrenda)
        val cardTalla : TextView = itemView.findViewById(R.id.cardTallaaaa)
        val cardEstado : TextView = itemView.findViewById(R.id.cardEstadoPrenda)
        val cardPrecio : TextView = itemView.findViewById(R.id.cardTotalPrendaaa)
        val cardDonativo : TextView = itemView.findViewById(R.id.cardTotalDonativo)
        val cardNombreVendedor : TextView = itemView.findViewById(R.id.cardNombreVendedor)
        val cardId : TextView = itemView.findViewById(R.id.cardIDPrendaPedido)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_prenda_pedido, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]
        holder.cardId.text = item.idRopa
        holder.cardNombrePrenda.text = item.nombrePrenda
        holder.cardTalla.text = item.talla
        holder.cardEstado.text = item.estadoPrenda
        holder.cardPrecio.text = item.precioPrenda
        holder.cardNombreVendedor.text = item.nombreVendedor
        val donativo = conInventarioRopa.calcularDonativo2(item.precioPrenda,item.porcentajeDonativo)
        val donativo2 = donativo.toString()
        holder.cardDonativo.text = donativo2

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun actualizarDatos(nuevaLista: List<PrendasPedido>) {
        cardList = nuevaLista
        notifyDataSetChanged()
    }

}