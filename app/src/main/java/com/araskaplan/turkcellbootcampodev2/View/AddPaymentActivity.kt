package com.araskaplan.turkcellbootcampodev2.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.araskaplan.turkcellbootcampodev2.BLL.BusinessLogic
import com.araskaplan.turkcellbootcampodev2.BLL.RecyclerViewAdder
import com.araskaplan.turkcellbootcampodev2.Model.Payment
import com.araskaplan.turkcellbootcampodev2.R
import com.araskaplan.turkcellbootcampodev2.databinding.ActivityAddPaymentBinding

class AddPaymentActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddPaymentBinding
    lateinit var title:String
    var paymentTypeId=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initializeComponents()


    }
    fun initializeComponents(){
        title=intent.getStringExtra("Title").toString()
        paymentTypeId=intent.getIntExtra("Id",-1)

        binding.addPaymentActivityTitle.text=title
        var temp=binding.addPaymentActivityCalendar.date


        binding.addPaymentActivityCalendar.setOnDateChangeListener { calendarView, year, month, day ->
            binding.addPaymentActivityDate.setText("${day}.${month}.${year}")
        }
        binding.addPaymentActivityCalendar.setDate(temp)
    }

    fun ekleOnClickListener(view: View){
        var payment=Payment()
        payment.PaymentTypeID=paymentTypeId
        payment.Amount=binding.addPaymentActivityAmount.text.toString().toInt()
        payment.Date=binding.addPaymentActivityDate.text.toString()

        BusinessLogic.addPaymentoDB(this,payment)
        RecyclerViewAdder.updateDetailsAdapter(this,paymentTypeId)
        finish()
    }
}