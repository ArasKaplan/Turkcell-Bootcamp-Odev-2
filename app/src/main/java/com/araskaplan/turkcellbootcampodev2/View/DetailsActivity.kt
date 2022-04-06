package com.araskaplan.turkcellbootcampodev2.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.araskaplan.turkcellbootcampodev2.BLL.BusinessLogic
import com.araskaplan.turkcellbootcampodev2.BLL.RecyclerViewAdder
import com.araskaplan.turkcellbootcampodev2.Model.Payment
import com.araskaplan.turkcellbootcampodev2.R
import com.araskaplan.turkcellbootcampodev2.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    var typePos=-1
    var paymentTypeId=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        typePos=intent.getIntExtra("pos",-1)
        paymentTypeId=intent.getIntExtra("Id",-1)

        RecyclerViewAdder.initDetailsActivityRECV(binding,this,paymentTypeId)

        //BusinessLogic.getPaymentsFromDB(this,paymentTypeId).forEach { BusinessLogic.deletePaymentFromDB(this,it.Id) }
        /*var payment=Payment()
        payment.Date="12.01.2022"
        payment.Amount=450
        payment.PaymentTypeID=paymentTypeId

        BusinessLogic.addPaymentoDB(this,payment)

        RecyclerViewAdder.updateDetailsAdapter(this,paymentTypeId)*/

    }

    override fun onRestart() {
        super.onRestart()
        if (BusinessLogic.getPaymentsFromDB(this,paymentTypeId).size==0){
            finish()
        }
    }



    fun odemeTipiniDuzenleOnClick(view: View){
        val intent= Intent(this,AddPaymentTypeActivity::class.java)
        intent.putExtra("mode","edit")
        intent.putExtra("pos",typePos)
        startActivity(intent)
    }
    fun odemeEkleOnClickListener(view:View){
        val intent=Intent(this,AddPaymentActivity::class.java)
        intent.putExtra("Id",paymentTypeId)
        intent.putExtra("Title",BusinessLogic.getPaymentTypesFromDB(this)[typePos].Title)
        startActivity(intent)
    }
}