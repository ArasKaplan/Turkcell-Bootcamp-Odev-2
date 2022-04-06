package com.araskaplan.turkcellbootcampodev2.View

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    fun checkInputs():Boolean{
        if (binding.addNewPaymentTypeActivityTitle.text.toString().equals("") || binding.addNewPaymentTypeActivityTitle.text.toString().equals("")){
            return true
        }
        if (!binding.addNewPaymentTypeActivitySwitch.isChecked){
            when(binding.addNewPaymentTypeSpinner.selectedItem){
                "Gun"->if (binding.addNewPaymentTypeActivityPeriodNum.text.toString().toInt()>=30 || binding.addNewPaymentTypeActivityPeriodNum.text.toString().toInt()<=0) return true
                "Ay"->if (binding.addNewPaymentTypeActivityPeriodNum.text.toString().toInt()>=12 || binding.addNewPaymentTypeActivityPeriodNum.text.toString().toInt()<=0) return true
            }
        }
        return false
    }
    fun ekleButtonOnClickListener(view: View) {
        if (!checkInputs()){
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
        }else{
            Toast.makeText(this,"Girdilerinizi Kontrol Ediniz",Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteButtonOnClickListener(view:View){
        val adb=AlertDialog.Builder(this)
            .setTitle("Uyari")
            .setMessage("Bu odeme tipini silmek istediginizden emin misiniz?")
            .setNegativeButton("Hayir",null)
            .setPositiveButton("Evet", DialogInterface.OnClickListener { dialogInterface, i ->
                var pos=intent.getIntExtra("pos",-1)
                var tempPaymentType=BusinessLogic.getPaymentTypesFromDB(this)[pos]
                BusinessLogic.deletePaymentType(this,tempPaymentType)
                BusinessLogic.getPaymentsFromDB(this,tempPaymentType.Id).forEach { BusinessLogic.deletePaymentFromDB(this,it.Id) }
                RecyclerViewAdder.updateDetailsAdapter(this,-1)
                RecyclerViewAdder.updateMainActAdapter(this)
                finish()
            }).show()
    }
}