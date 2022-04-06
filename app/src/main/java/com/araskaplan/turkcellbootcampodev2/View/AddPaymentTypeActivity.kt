package com.araskaplan.turkcellbootcampodev2.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.marginTop
import com.araskaplan.turkcellbootcampodev2.BLL.BusinessLogic
import com.araskaplan.turkcellbootcampodev2.BLL.RecyclerViewAdder
import com.araskaplan.turkcellbootcampodev2.Model.PaymentType
import com.araskaplan.turkcellbootcampodev2.R
import com.araskaplan.turkcellbootcampodev2.databinding.ActivityAddPaymenttypeBinding
import kotlin.properties.Delegates

class AddPaymentTypeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPaymenttypeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddPaymenttypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addNewPaymentTypeActivitySwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked){
                binding.addNewPaymentTypeActivityPeriodNum.isEnabled=false
                binding.addNewPaymentTypeSpinner.isEnabled=false
            }else{
                binding.addNewPaymentTypeActivityPeriodNum.isEnabled=true
                binding.addNewPaymentTypeSpinner.isEnabled=true
            }
        }
        initializeButtons()

    }
    fun initializeButtons(){
        if (intent.getStringExtra("mode").equals("new")){
            binding.addNewPaymentTypeActivityDelete.isInvisible=true
        }else{
            val pos=intent.getIntExtra("pos",-1)
            val tempPaymentType=BusinessLogic.getPaymentTypesFromDB(this)[pos]

            binding.addNewPaymentTypeActivityTitle.setText(tempPaymentType.Title)
            if (tempPaymentType.PeriodType!=null){
                binding.addNewPaymentTypeActivitySwitch.isChecked=false
                binding.addNewPaymentTypeActivityPeriodNum.setText(tempPaymentType.Period.toString())
                when(tempPaymentType.PeriodType){
                    "Gun"->binding.addNewPaymentTypeSpinner.setSelection(0)
                    "Ay"->binding.addNewPaymentTypeSpinner.setSelection(1)
                    "Yil"->binding.addNewPaymentTypeSpinner.setSelection(2)
                }
            }else{
                binding.addNewPaymentTypeActivitySwitch.isChecked=true
            }
        }
    }
    fun ekleButtonOnClickListener(view: View) {
        if (intent.getStringExtra("mode").equals("new")){
            var paymentType = PaymentType()
            paymentType.Title = binding.addNewPaymentTypeActivityTitle.text.toString()
            if (!binding.addNewPaymentTypeActivitySwitch.isChecked) {
                paymentType.Period = binding.addNewPaymentTypeActivityPeriodNum.text.toString().toInt()
                paymentType.PeriodType = binding.addNewPaymentTypeSpinner.selectedItem.toString()
            }
            BusinessLogic.addPaymentTypetoDB(this, paymentType)
            RecyclerViewAdder.updateMainActAdapter(this)
            finish()
        }else{
            var pos=intent.getIntExtra("pos",-1)
            var tempPaymentType=BusinessLogic.getPaymentTypesFromDB(this)[pos]
            tempPaymentType.Title=binding.addNewPaymentTypeActivityTitle.text.toString()
            if (binding.addNewPaymentTypeActivitySwitch.isChecked){
                tempPaymentType.Period= null
                tempPaymentType.PeriodType=null
            }else{
                tempPaymentType.Period=binding.addNewPaymentTypeActivityPeriodNum.text.toString().toInt()
                tempPaymentType.PeriodType=binding.addNewPaymentTypeSpinner.selectedItem.toString()
            }
            BusinessLogic.editPaymentType(this,tempPaymentType)
            RecyclerViewAdder.updateMainActAdapter(this)
            finish()
        }

    }

    fun deleteButtonOnClickListener(view:View){
        var pos=intent.getIntExtra("pos",-1)
        var tempPaymentType=BusinessLogic.getPaymentTypesFromDB(this)[pos]
        BusinessLogic.deletePaymentType(this,tempPaymentType)
        BusinessLogic.getPaymentsFromDB(this,tempPaymentType.Id).forEach { BusinessLogic.deletePaymentFromDB(this,it.Id) }
        RecyclerViewAdder.updateDetailsAdapter(this,-1)
        RecyclerViewAdder.updateMainActAdapter(this)
        finish()
    }
}