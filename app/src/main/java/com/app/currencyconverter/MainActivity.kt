package com.app.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"
    private val saudiRiyal = "Saudi Riyal"
    private val americanDollar = "American Dollar"
    private val AED = "AED"
    private val Euro = "Euro"
    lateinit var convertButton: Button
    lateinit var  amountEditText: TextInputEditText
    lateinit var resultEditText: TextInputEditText
    lateinit var fromDropDownMenu : AutoCompleteTextView
    lateinit var  toDropDownMenu :AutoCompleteTextView

    val values = mapOf(
        americanDollar to 1.0 ,
        saudiRiyal to 3.75 ,
        AED to 3.67,
        Euro to 0.93
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        populateDropDownList()

        convertButton.setOnClickListener {
            if(amountEditText.text.toString().isNotEmpty()) {
                val amount = amountEditText.getText().toString().toDouble()
                val toCurrencyValue = values[toDropDownMenu.getText().toString()]
                val fromCurrencyValue = values[fromDropDownMenu.text.toString()]
                val result = amount.times(toCurrencyValue!!.div(fromCurrencyValue!!))
                resultEditText.setText(result.toString())




                Log.d(Tag, "amount is equal : " + amount)
                Log.d(Tag, "selected Currency is  : " + toCurrencyValue)
                Log.d(Tag, "result is equal : " + result)
            }else{
                amountEditText.setError("Amount Filled required")
//          val snackbar= Snackbar.make(fromDropDownMenu,"Amount Filled required",Snackbar.LENGTH_SHORT)
//                snackbar.show()
//                snackbar.setAction("ok"){
//
//                }
            }
        }
    }
    private fun initializeViews(){
        convertButton = findViewById(R.id.Convertbutton)
        amountEditText  = findViewById(R.id.amount_textviewinput)
        resultEditText = findViewById(R.id.result_editText)
        fromDropDownMenu = findViewById(R.id.from_autoComplete)
        toDropDownMenu = findViewById(R.id.to_autoComplete)
    }

    private fun populateDropDownList(){
        val listOfCountry = listOf(saudiRiyal,americanDollar,AED,Euro)
        val adapter = ArrayAdapter(this,R.layout.drop_down_list_item,listOfCountry)
        fromDropDownMenu.setAdapter(adater)
        toDropDownMenu.setAdapter(adapter)
    }

}