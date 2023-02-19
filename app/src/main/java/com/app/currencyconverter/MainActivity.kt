package com.app.currencyconverter

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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

        amountEditText.addTextChangedListener{
            calculateResult()
        }
        fromDropDownMenu.setOnItemClickListener { adapterView, view, i, l ->
            calculateResult()
        }
        toDropDownMenu.setOnItemClickListener { adapterView, view, i, l ->
            calculateResult()
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
        fromDropDownMenu.setAdapter(adapter)
        toDropDownMenu.setAdapter(adapter)
    }
    private fun calculateResult(){
        if(amountEditText.text.toString().isNotEmpty()) {
            val amount = amountEditText.getText().toString().toDouble()
            val toCurrencyValue = values[toDropDownMenu.getText().toString()]
            val fromCurrencyValue = values[fromDropDownMenu.text.toString()]
            val result = amount.times(toCurrencyValue!!.div(fromCurrencyValue!!))
            val formatedResult = String.format("%.2f",result)
            resultEditText.setText(formatedResult)

        }else{
            amountEditText.setError("Amount Filled required")
        }
    }

}