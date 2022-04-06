package com.araskaplan.turkcellbootcampodev2.BLL

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.araskaplan.turkcellbootcampodev2.DAL.DatabaseOperator
import com.araskaplan.turkcellbootcampodev2.Model.Payment
import com.araskaplan.turkcellbootcampodev2.Model.PaymentType
import com.araskaplan.turkcellbootcampodev2.View.DetailsActivity
import com.araskaplan.turkcellbootcampodev2.View.PaymentAdapter
import com.araskaplan.turkcellbootcampodev2.View.PaymentTypeAdapter
import com.araskaplan.turkcellbootcampodev2.databinding.ActivityDetailsBinding
import com.araskaplan.turkcellbootcampodev2.databinding.ActivityMainBinding

class RecyclerViewAdder{
    companion object{
        private lateinit var mainActRecVAdapter:PaymentTypeAdapter
        private lateinit var mainBinding: ActivityMainBinding

        private lateinit var detailsActRecVAdapter:PaymentAdapter
        private lateinit var detailsBinding: ActivityDetailsBinding

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
        fun updateMainActAdapter(context: Context){
            mainActRecVAdapter= PaymentTypeAdapter(BusinessLogic.getPaymentTypesFromDB(context))
            mainBinding.mainActivityRecV.adapter= mainActRecVAdapter
        }
        fun initDetailsActivityRECV(binding:ActivityDetailsBinding,context: Context,paymentTypeId:Int){
            detailsBinding=binding
            detailsActRecVAdapter= PaymentAdapter(BusinessLogic.getPaymentsFromDB(context,paymentTypeId))

            binding.detailsActivityRecV.apply {
                layoutManager=LinearLayoutManager(context).apply {
                    orientation=LinearLayoutManager.VERTICAL
                }
                adapter= detailsActRecVAdapter
            }
        }
        fun updateDetailsAdapter(context: Context,paymentTypeId: Int){
            detailsActRecVAdapter= PaymentAdapter(BusinessLogic.getPaymentsFromDB(context,paymentTypeId))
            detailsBinding.detailsActivityRecV.adapter= detailsActRecVAdapter
        }
    }
}