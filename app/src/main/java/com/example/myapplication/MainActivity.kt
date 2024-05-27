package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightLayout = findViewById<TextInputLayout>(R.id.heightText)
        val weightLayout = findViewById<TextInputLayout>(R.id.weightText)
        val height = heightLayout.editText as EditText
        val weight = weightLayout.editText as EditText
        val calculate = findViewById<Button>(R.id.calculateButton)
        val result = findViewById<TextView>(R.id.bmiText)
        val status = findViewById<TextView>(R.id.statusText)

        fun bmiResults(bmi: Double): String {
            return when {
                bmi < 18.5 -> "Niedowaga"
                bmi < 24.9 -> "Waga normalna"
                bmi < 30 -> "Nadwaga"
                else -> "Otyłość"
            }
        }

        calculate.setOnClickListener {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

            if (height.text.toString().isNotEmpty() && weight.text.toString().isNotEmpty()) {
                val heightValue = height.text.toString().toDouble()
                val weightValue = height.text.toString().toDouble()

                if (weightValue > 0.0 && heightValue > 0.0) {
                    val bmiValue = weightValue / (heightValue * heightValue)
                    val roundedBmiValue = String.format("%.2f", bmiValue).toDouble()
                    val bmiStatus = bmiResults(bmiValue)
                    result.text = "Twoje BMI wynosi $roundedBmiValue"
                    status.text = "$bmiStatus"
                } else {
                    Toast.makeText(this, "Wprowadź wartości większe od 0", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}