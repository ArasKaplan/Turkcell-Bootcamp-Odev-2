package com.araskaplan.turkcellbootcampodev2.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.araskaplan.turkcellbootcampodev2.BLL.BusinessLogic
import com.araskaplan.turkcellbootcampodev2.BLL.RecyclerViewAdder
import com.araskaplan.turkcellbootcampodev2.Model.PaymentType
import com.araskaplan.turkcellbootcampodev2.R
import com.araskaplan.turkcellbootcampodev2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var paymentType=PaymentType()
        paymentType.PeriodType="gun"
        paymentType.Period=3
        paymentType.Title="elek"

        //BusinessLogic.getPaymentTypesFromDB(this).forEach { BusinessLogic.deletePaymentType(this,it) }

        RecyclerViewAdder.initMainActivityRECV(binding,this)
        //BusinessLogic.addPaymentTypetoDB(this,paymentType)
        //RecyclerViewAdder.updateMainActAdapter(this)
    }
    fun newPaymentType(view:View){
        val intent= Intent(this@MainActivity,AddPaymentTypeActivity::class.java)
        startActivity(intent)
    }

}