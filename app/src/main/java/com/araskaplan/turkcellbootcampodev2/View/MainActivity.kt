package com.araskaplan.turkcellbootcampodev2.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        paymentType.Title="asd"
        paymentType.Period=4
        paymentType.PeriodType="asdsadas"


        RecyclerViewAdder.mainActivityRecV(binding,this, arrayListOf(paymentType))


    }
    fun newPaymentType(view:View){
        val intent= Intent(this@MainActivity,AddPaymentTypeActivity::class.java)
        startActivity(intent)
    }

}