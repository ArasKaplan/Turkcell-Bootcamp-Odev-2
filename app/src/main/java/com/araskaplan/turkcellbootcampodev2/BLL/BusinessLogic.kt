package com.araskaplan.turkcellbootcampodev2.BLL

import android.content.Context
import com.araskaplan.turkcellbootcampodev2.DAL.DatabaseOperator
import com.araskaplan.turkcellbootcampodev2.Model.Payment
import com.araskaplan.turkcellbootcampodev2.Model.PaymentType

class BusinessLogic {

    companion object{
        fun addPaymentTypetoDB(context: Context,paymentType: PaymentType){
            val DAO=DatabaseOperator(context)
            DAO.addPaymentType(paymentType)
        }
        fun getPaymentTypesFromDB(context: Context):ArrayList<PaymentType>{
            val DAO=DatabaseOperator(context)
            return DAO.getAllPaymentTypes()
        }
        fun editPaymentType(context: Context,paymentType: PaymentType){
            val DAO=DatabaseOperator(context)
            DAO.editPaymentType(paymentType)
        }
        fun deletePaymentType(context: Context,paymentType: PaymentType):Boolean{
            val DAO=DatabaseOperator(context)
            DAO.deletePaymentType(paymentType)
            return true
        }




        fun addPaymentoDB(context: Context,payment: Payment){
            val DAO=DatabaseOperator(context)
            DAO.addPayment(payment)
        }
        fun getPaymentsFromDB(context: Context,paymentType: PaymentType?=null):ArrayList<Payment>{
            val DAO=DatabaseOperator(context)
            DAO.getPayments(paymentType)
            return arrayListOf()
        }
        fun editPayment(context: Context,payment: Payment){
            //unnecessary
        }
        fun deletePaymentFromDB(context: Context,payment: Payment):Boolean{
            val DAO=DatabaseOperator(context)
            DAO.deletePayment(payment)
            return true
        }
    }
}