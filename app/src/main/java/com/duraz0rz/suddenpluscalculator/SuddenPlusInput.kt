package com.duraz0rz.suddenpluscalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import java.lang.Integer.parseInt

class SuddenPlusInput : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudden_plus_input)
    }

    fun calculateSuddenPlusNumbers(view: View) {
        val minBPMTextField = findViewById<EditText>(R.id.textMinBPM)
        val greenNumberTextField = findViewById<EditText>(R.id.textGreenNumber)
        val minBPMText = minBPMTextField.text.toString()
        val greenNumberText = greenNumberTextField.text.toString()

        if (minBPMText.isEmpty()) minBPMTextField.error = "Must enter a BPM number!"
        if (greenNumberText.isEmpty()) greenNumberTextField.error = "Must enter a green number!"

        if (minBPMText.isNotEmpty() && greenNumberText.isNotEmpty()) {
            val calculateIntent = Intent(this, SuddenPlusTable::class.java)
            calculateIntent.putExtra("BPM", parseInt(minBPMText))
            calculateIntent.putExtra("GreenNumber", parseInt(greenNumberText))
            startActivity(calculateIntent)
        }
    }
}
