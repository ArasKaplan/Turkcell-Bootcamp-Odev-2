package com.araskaplan.turkcellbootcampodev2.DAL

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.araskaplan.turkcellbootcampodev2.Model.Payment
import com.araskaplan.turkcellbootcampodev2.Model.PaymentType

class DatabaseOperator(context: Context) {
    var dataBase:SQLiteDatabase?=null
    var dbOpenHelper :DatabaseOpenHelper

    init {
        dbOpenHelper= DatabaseOpenHelper(context,"TurkcellBootcampOdev2",null,1)
    }
    fun open(){
        dataBase=dbOpenHelper.writableDatabase
    }
    fun close(){
        if (dataBase!=null && dataBase!!.isOpen){
            dataBase!!.close()
        }
    }

    fun getPaymentTypes():Cursor{
        val query="Select * From PaymentType"
        return dataBase!!.rawQuery(query,null)
    }

    fun addPaymentType(paymentType: PaymentType){
        val cv=ContentValues()
        cv.put("Title",paymentType.Title)
        cv.put("Period",paymentType.Period)
        cv.put("PeriodType",paymentType.PeriodType)

        open()
        dataBase!!.insert("PaymentType",null,cv)
        close()
    }

    fun editPaymentType(paymentType: PaymentType){
        val cv=ContentValues()
        cv.put("Title",paymentType.Title)
        cv.put("Period",paymentType.Period)
        cv.put("PeriodType",paymentType.PeriodType)

        open()
        dataBase!!.update("PaymentType",cv,"Id = ?", arrayOf(paymentType.Id.toString()))
        close()

    }
    fun deletePaymentType(paymentType: PaymentType){
        open()
        dataBase!!.delete("PaymentType","Id = ?", arrayOf(paymentType.Id.toString()))
        close()
    }


    fun getPayments(paymentType: PaymentType? = null): Cursor {
        if (paymentType!=null){
            val query="Select * from Payment Where PaymentTypeId = ?"
            return dataBase!!.rawQuery(query, arrayOf(paymentType.Id.toString()))
        }
        val query = "Select * from Payment"
        return dataBase!!.rawQuery(query,null)
    }
    fun addPayment(payment: Payment){
        val cv=ContentValues()
        cv.put("Amount",payment.Amount)
        cv.put("Date",payment.Date)
        cv.put("PaymentTypeId",payment.PaymentTypeID)

        open()
        dataBase!!.insert("Payment",null,cv)
        close()
    }
    fun editPayment(){
        //might be unneeded
    }
    fun deletePayment(payment: Payment){
        open()
        dataBase!!.delete("Payment","Id = ?", arrayOf(payment.Id.toString()))
        close()
    }
}