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
        var hasErrors = false

        fun parseFieldForPotentialNumber(field : EditText, errorText : String) : Int? {
            if (field.text.isNullOrEmpty()) {
                hasErrors = true
                field.error = errorText
                return null
            }
            return parseInt(field.text.toString())
        }

        val minBPM : Int? = parseFieldForPotentialNumber(findViewById(R.id.textMinBPM), errorText = "Must enter a BPM number!")
        val greenNumber : Int? = parseFieldForPotentialNumber(findViewById(R.id.textGreenNumber), errorText = "Must enter a green number!")

        if (!hasErrors) {
            val calculateIntent = Intent(this, SuddenPlusTable::class.java).apply {
                putExtra("BPM", minBPM)
                putExtra("GreenNumber", greenNumber)
            }
            startActivity(calculateIntent)
        }
    }

}
