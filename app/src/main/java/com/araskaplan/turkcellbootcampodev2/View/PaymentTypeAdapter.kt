package com.araskaplan.turkcellbootcampodev2.View

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.araskaplan.turkcellbootcampodev2.Model.PaymentType
import com.araskaplan.turkcellbootcampodev2.R

class PaymentTypeAdapter(var list:ArrayList<PaymentType>):RecyclerView.Adapter<PaymentTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentTypeViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.paymenttype_recv_card,parent,false)
        return PaymentTypeViewHolder(v)
    }

    override fun onBindViewHolder(holder: PaymentTypeViewHolder, position: Int) {
        holder.paymentTypeTitle.text=list[position].Title
        holder.paymentTypeCycle.text=list[position].Period.toString()
        holder.paymentTypePeriod.text=list[position].PeriodType
        holder.itemView.setOnClickListener {
            var intent= Intent(holder.itemView.context,DetailsActivity::class.java)
            intent.putExtra("pos",position)
            intent.putExtra("Id",list[position].Id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class PaymentTypeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var paymentTypeTitle:TextView
    var paymentTypePeriod:TextView
    var paymentTypeCycle:TextView
    var paymentTypeButton:Button
    init {
        paymentTypeTitle=itemView.findViewById(R.id.paymentType_recv_card_title)
        paymentTypePeriod=itemView.findViewById(R.id.paymentType_recv_card_period)
        paymentTypeCycle=itemView.findViewById(R.id.paymentType_recv_card_cycle)
        paymentTypeButton=itemView.findViewById(R.id.paymentType_recv_card_button)

    }


}