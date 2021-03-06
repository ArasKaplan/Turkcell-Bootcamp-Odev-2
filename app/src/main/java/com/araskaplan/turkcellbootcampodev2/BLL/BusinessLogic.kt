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
        fun getSpecifiedPaymentTypeFromDB(context: Context,paymentTypeId: Int):ArrayList<PaymentType>{
            val DAO=DatabaseOperator(context)
            return DAO.getSinglePaymentType(paymentTypeId)
        }




        fun addPaymentoDB(context: Context,payment: Payment){
            val DAO=DatabaseOperator(context)
            DAO.addPayment(payment)
        }
        fun getPaymentsFromDB(context: Context,paymentTypeId: Int):ArrayList<Payment>{
            val DAO=DatabaseOperator(context)
            return DAO.getAllPayments(paymentTypeId)
        }

        fun editPayment(context: Context,payment: Payment){
            //unnecessary
        }
        fun deletePaymentFromDB(context: Context,paymentId: Int):Boolean{
            val DAO=DatabaseOperator(context)
            DAO.deletePayment(paymentId)
            return true
        }
    }
}