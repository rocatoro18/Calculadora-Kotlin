package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false

    }

    fun onClear(view: View){
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(tvInput?.text.toString())){
            tvInput?.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    //"-99"
                    //"99"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    var splitValue = tvValue.split("-")
                    var firstValue = splitValue[0] // 100
                    var secondValue = splitValue[1] // 1
                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }
                    var result = firstValue.toDouble() - secondValue.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                } else if (tvValue.contains("+")){
                    var splitValue = tvValue.split("+")
                    var firstValue = splitValue[0] // 100
                    var secondValue = splitValue[1] // 1
                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }
                    var result = firstValue.toDouble() + secondValue.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                } else if (tvValue.contains("*")){
                    var splitValue = tvValue.split("*")
                    var firstValue = splitValue[0] // 100
                    var secondValue = splitValue[1] // 1
                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }
                    var result = firstValue.toDouble() * secondValue.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                } else if (tvValue.contains("/")){
                    var splitValue = tvValue.split("/")
                    var firstValue = splitValue[0] // 100
                    var secondValue = splitValue[1] // 1
                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }
                    var result = firstValue.toDouble() / secondValue.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if(value.contains(".0"))
            value = value.substring(0,value.length-2)

        return value
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        } else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

}