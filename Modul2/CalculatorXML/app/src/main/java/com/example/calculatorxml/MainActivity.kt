package com.example.calculatorxml

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.materialswitch.MaterialSwitch
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private var tipPercent = 15.0
    private var roundUp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etAmount = findViewById<EditText>(R.id.etAmount)
        val spinner = findViewById<AutoCompleteTextView>(R.id.spinnerTip)
        val switchRound = findViewById<MaterialSwitch>(R.id.switchRound)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        tipPercent = savedInstanceState?.getDouble("TIP") ?: 15.0
        roundUp = savedInstanceState?.getBoolean("ROUND") ?: false
        switchRound.isChecked = roundUp

        val percentages = listOf(15, 18, 20)
        val items = percentages.map { "$it%" }
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, items)
        spinner.setAdapter(adapter)
        val index = percentages.indexOf(tipPercent.toInt()).coerceAtLeast(0)
        spinner.setText(items[index], false)


        spinner.setOnItemClickListener { _, _, position, _ ->
            tipPercent = percentages[position].toDouble()
            calculate(etAmount, tvResult)
        }
        switchRound.setOnCheckedChangeListener { _, isChecked ->
            roundUp = isChecked
            calculate(etAmount, tvResult)
        }

        etAmount.addTextChangedListener {
            calculate(etAmount, tvResult)
        }
    }

    private fun calculate(et: EditText, tv: TextView) {
        val amount = et.text.toString().toDoubleOrNull() ?: 0.0
        var tip = (tipPercent / 100) * amount

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        val formatted = NumberFormat.getCurrencyInstance().format(tip)
        tv.text = getString(R.string.tip_amount, formatted)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("TIP", tipPercent)
        outState.putBoolean("ROUND", roundUp)
    }
}