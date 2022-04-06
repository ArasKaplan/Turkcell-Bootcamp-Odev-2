package com.araskaplan.turkcellbootcampodev2.DAL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(context: Context,name:String, factory: SQLiteDatabase.CursorFactory?, version:Int):SQLiteOpenHelper(context,name, factory, version){

    override fun onCreate(p0: SQLiteDatabase) {
        val query="CREATE TABLE IF NOT EXISTS PaymentType(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Title TEXT, Period INTEGER, PeriodType INTEGER)"
        p0.execSQL(query)

        val query2="CREATE TABLE IF NOT EXISTS Payment(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Amount INTEGER,Date TEXT, PaymentTypeId INTEGER)"
        p0.execSQL(query2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion==1){

        }else if (oldVersion==2){

        }
    }
}