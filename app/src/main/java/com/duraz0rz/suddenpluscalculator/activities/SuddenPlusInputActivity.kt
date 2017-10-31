package com.duraz0rz.suddenpluscalculator.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.duraz0rz.suddenpluscalculator.R
import java.lang.Integer.parseInt

class SuddenPlusInputActivity : AppCompatActivity() {
    data class FieldValidation (val field : EditText, val errorText : String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudden_plus_input)
    }

    fun calculateSuddenPlusNumbers(view: View) {
        var hasErrors = false
        val minBPMField = findViewById<EditText>(R.id.textMinBPM)
        val maxBPMField = findViewById<EditText>(R.id.textMaxBPM)
        val greenNumberField = findViewById<EditText>(R.id.textGreenNumber)

        val minBPM = parseFieldForPotentialNumber(minBPMField)
        val maxBPM = parseFieldForPotentialNumber(maxBPMField)
        val greenNumber = parseFieldForPotentialNumber(greenNumberField)

        fun checkForEmptyFields(vararg fields : FieldValidation) {
            fields.filter { it.field.text.isNullOrEmpty() }.forEach {
                hasErrors = true
                it.field.error = it.errorText
            }
        }

        fun checkIfMaxBPMIsGreaterThanMinBPM(minBPM : Int?, maxBPM : Int?) {
            if (minBPM != null && maxBPM != null && maxBPM < minBPM) {
                hasErrors = true
                maxBPMField.error = "Max BPM must be greater than min BPM!"
            }
        }

        checkForEmptyFields(
                FieldValidation(minBPMField, "Must enter a BPM number!"),
                FieldValidation(greenNumberField, "Must enter a green number!")
        )

        checkIfMaxBPMIsGreaterThanMinBPM(minBPM, maxBPM)

        if (!hasErrors) {
            val calculateIntent = Intent(this, SuddenPlusTableActivity::class.java).apply {
                putExtra("BPM", minBPM)
                putExtra("MaxBPM", maxBPM)
                putExtra("GreenNumber", greenNumber)
            }
            startActivity(calculateIntent)
        }
    }

    private fun parseFieldForPotentialNumber(field : EditText) : Int? {
        return if (field.text.isNullOrEmpty()) null else parseInt(field.text.toString())
    }
}
