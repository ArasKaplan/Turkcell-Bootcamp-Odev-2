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

    fun checkInputs():Boolean{
        var checkInputs=false
        if (binding.addPaymentActivityAmount.text.toString().toInt()<=0 || binding.addPaymentActivityDate.text.equals("")||
                !binding.addPaymentActivityDate.text.toString().matches("[0-9]{2}(\\.)[0-9]{2}(\\.)(2)[0-9]{3}".toRegex())){
            checkInputs=true
        }
        return checkInputs
    }

    fun initializeComponents(){
        title=intent.getStringExtra("Title").toString()
        paymentTypeId=intent.getIntExtra("Id",-1)

        binding.addPaymentActivityTitle.text=title
        var temp=binding.addPaymentActivityCalendar.date
        binding.addPaymentActivityCalendar.setOnDateChangeListener { calendarView, year, month, day ->
            var sampleText=""
            if (day>9){
                sampleText=sampleText+"${day}"
            }else{
                sampleText=sampleText+"0${day}"
            }
            sampleText=sampleText+"."
            if (month>9){
                sampleText=sampleText+"${month+1}"
            }else{
                sampleText=sampleText+"0${month+1}"
            }
            sampleText=sampleText+".${year}"
            binding.addPaymentActivityDate.setText(sampleText)
        }
        binding.addPaymentActivityCalendar.setDate(temp)
    }

    fun ekleOnClickListener(view: View){
        if (!checkInputs()){
            var payment=Payment()
            payment.PaymentTypeID=paymentTypeId
            payment.Amount=binding.addPaymentActivityAmount.text.toString().toInt()
            payment.Date=binding.addPaymentActivityDate.text.toString()

            BusinessLogic.addPaymentoDB(this,payment)
            if (!intent.getStringExtra("from").equals("main")){
                RecyclerViewAdder.updateDetailsAdapter(this,paymentTypeId)
            }
            finish()
        }
        Toast.makeText(this,"Girdilerinizi Kontrol Ediniz",Toast.LENGTH_SHORT).show()
    }
}