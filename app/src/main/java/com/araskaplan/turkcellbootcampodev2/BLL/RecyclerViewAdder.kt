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
        private lateinit var mainActRecVAdapter:PaymentTypeAdapter
        lateinit var mainBinding: ActivityMainBinding
        private lateinit var paymentTypeList:ArrayList<PaymentType>

        fun initMainActivityRECV(binding: ActivityMainBinding,context: Context){
            mainBinding=binding
            mainActRecVAdapter=PaymentTypeAdapter(BusinessLogic.getPaymentTypesFromDB(context))

            binding.mainActivityRecV.apply {
                layoutManager=LinearLayoutManager(context).apply {
                    orientation=LinearLayoutManager.VERTICAL
                }
                adapter= mainActRecVAdapter
            }
        }
        fun getMainActivityAdapter():PaymentTypeAdapter{
            return mainActRecVAdapter
        }
        fun updateMainActAdapter(context: Context){
            mainActRecVAdapter= PaymentTypeAdapter(BusinessLogic.getPaymentTypesFromDB(context))
            mainBinding.mainActivityRecV.adapter= mainActRecVAdapter
        }
    }
}