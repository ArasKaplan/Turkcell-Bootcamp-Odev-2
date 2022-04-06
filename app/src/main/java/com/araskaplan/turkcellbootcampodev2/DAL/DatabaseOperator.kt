package com.araskaplan.turkcellbootcampodev2.DAL

import android.annotation.SuppressLint
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

    private fun getAllPaymentTypesPrivate():Cursor{
        val query="Select * From PaymentType"
        return dataBase!!.rawQuery(query,null)
    }

    @SuppressLint("Range")
    fun getAllPaymentTypes():ArrayList<PaymentType>{
        val paymentTypeList = ArrayList<PaymentType>()
        var paymentType:PaymentType
        open()

        var c:Cursor=getAllPaymentTypesPrivate()

        if(c.moveToFirst()){
            do{
                paymentType = PaymentType()
                paymentType.Id = c.getInt(c.getColumnIndex("Id"))
                paymentType.Title = c.getString(c.getColumnIndex("Title"))
                paymentType.Period=c.getInt(c.getColumnIndex("Period"))
                paymentType.PeriodType=c.getString(c.getColumnIndex("PeriodType"))
                paymentTypeList.add(paymentType)
            }while (c.moveToNext())
        }
        close()
        return paymentTypeList
    }

    @SuppressLint("Range")
    fun getAllPayments(paymentTypeId:Int):ArrayList<Payment>{
        val paymentList = ArrayList<Payment>()
        var payment:Payment
        open()

        var c:Cursor=getPayments(paymentTypeId)

        if (c.moveToFirst()){
            do {
                payment= Payment()
                payment.Id=c.getInt(c.getColumnIndex("Id"))
                payment.Amount=c.getInt(c.getColumnIndex("Amount"))
                payment.Date=c.getString(c.getColumnIndex("Date"))
                payment.PaymentTypeID=c.getInt(c.getColumnIndex("PaymentTypeId"))
                paymentList.add(payment)
            }while (c.moveToNext())
        }
        close()
        return paymentList
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


    private fun getPayments(paymentTypeId: Int): Cursor {
        val query="Select * from Payment Where PaymentTypeId = ?"
        return dataBase!!.rawQuery(query, arrayOf(paymentTypeId.toString()))

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
    fun deletePayment(paymentId: Int){
        open()
        dataBase!!.delete("Payment","Id = ?", arrayOf(paymentId.toString()))
        close()
    }
}