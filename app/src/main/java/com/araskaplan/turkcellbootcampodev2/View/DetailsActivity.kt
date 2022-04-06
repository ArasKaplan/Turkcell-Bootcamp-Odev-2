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

        //BusinessLogic.getPaymentsFromDB(this,paymentTypeId).forEach { BusinessLogic.deletePaymentFromDB(this,it) }
        /*var payment=Payment()
        payment.Date="12.01.2022"
        payment.Amount=45
        payment.PaymentTypeID=paymentTypeId

        BusinessLogic.addPaymentoDB(this,payment)

        RecyclerViewAdder.updateDetailsAdapter(this,paymentTypeId)*/

    }
    fun odemeTipiniDuzenleOnClick(view: View){
        val intent= Intent(this,AddPaymentTypeActivity::class.java)
        intent.putExtra("mode","edit")
        intent.putExtra("pos",typePos)
        startActivity(intent)
    }
}