package com.araskaplan.turkcellbootcampodev2.BLL

import android.content.Context
import com.araskaplan.turkcellbootcampodev2.Model.Payment
import com.araskaplan.turkcellbootcampodev2.Model.PaymentType

class BusinessLogic {

    companion object{
        fun addPaymentTypetoDB(context: Context,paymentType: PaymentType){

        }
        fun getPaymentTypesFromDB(context: Context):ArrayList<PaymentType>{

            return arrayListOf()
        }
        fun editPaymentType(){

        }
        fun deletePaymentType():Boolean{

            return true
        }

        fun addPaymentoDB(){

        }
        fun getPaymentsFromDB():ArrayList<Payment>{

            return arrayListOf()
        }
        fun editPayment(){

        }
        fun deletePaymentFromDB():Boolean{

            return true
        }
    }
}