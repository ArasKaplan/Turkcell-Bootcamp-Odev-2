package com.araskaplan.turkcellbootcampodev2.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.araskaplan.turkcellbootcampodev2.Model.Payment
import com.araskaplan.turkcellbootcampodev2.R

class PaymentAdapter(var list:ArrayList<Payment>):RecyclerView.Adapter<PaymentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.pastpayments_recv_card,parent,false)
        return PaymentViewHolder(v)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.date.text=list[position].Date
        holder.amount.text=list[position].Amount.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
class PaymentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var date:TextView
    var amount:TextView

    init {
        date=itemView.findViewById(R.id.pastpayment_recv_card_Date)
        amount=itemView.findViewById(R.id.pastpayment_recv_card_Amount)
    }

}