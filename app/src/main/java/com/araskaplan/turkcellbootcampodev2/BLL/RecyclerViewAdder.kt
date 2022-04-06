package com.araskaplan.turkcellbootcampodev2.BLL

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.araskaplan.turkcellbootcampodev2.DAL.DatabaseOperator
import com.araskaplan.turkcellbootcampodev2.Model.Payment
import com.araskaplan.turkcellbootcampodev2.Model.PaymentType
import com.araskaplan.turkcellbootcampodev2.View.PaymentTypeAdapter
import com.araskaplan.turkcellbootcampodev2.databinding.ActivityMainBinding

class RecyclerViewAdder{
    companion object{
        fun mainActivityRecV(binding: ActivityMainBinding,context: Context,list:ArrayList<PaymentType>){
            binding.mainActivityRecV.apply {
                layoutManager=LinearLayoutManager(context).apply {
                    orientation=LinearLayoutManager.VERTICAL
                }
                adapter=PaymentTypeAdapter(list)
            }
        }
    }
}