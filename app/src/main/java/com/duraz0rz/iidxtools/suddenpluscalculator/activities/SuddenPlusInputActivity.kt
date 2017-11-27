package com.duraz0rz.iidxtools.suddenpluscalculator.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.duraz0rz.iidxtools.R
import java.lang.Integer.parseInt

class SuddenPlusInputActivity : AppCompatActivity() {
    private lateinit var minBPMField: EditText
    private lateinit var maxBPMField: EditText
    private lateinit var greenNumberField: EditText
    private lateinit var liftField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudden_plus_input)

        minBPMField = findViewById(R.id.textMinBPM)
        maxBPMField = findViewById(R.id.textMaxBPM)
        greenNumberField = findViewById(R.id.textGreenNumber)
        liftField = findViewById(R.id.textLift)

        liftField.setOnEditorActionListener { textView, _, _ ->
            calculateSuddenPlusNumbers(textView)
            true
        }
    }

    fun calculateSuddenPlusNumbers(view: View) {
        val hasErrors = minBpmIsEmpty() or greenNumberIsEmpty() or maxBpmIsGreaterThanMinBpm()

        if (!hasErrors) {
            val minBPM = parseInt(minBPMField.text.toString())
            val maxBPM = if (maxBPMField.text.isNullOrEmpty()) null else parseInt(maxBPMField.text.toString())
            val greenNumber = parseInt(greenNumberField.text.toString())

            val calculateIntent = Intent(this, SuddenPlusTableActivity::class.java).apply {
                putExtra("BPM", minBPM)
                if (maxBPM != null) {
                    putExtra("MaxBPM", maxBPM)
                }
                putExtra("GreenNumber", greenNumber)
            }
            startActivity(calculateIntent)
        }
    }

    private fun minBpmIsEmpty(): Boolean {
        val minBpmField = findViewById<EditText>(R.id.textMinBPM)
        val hasErrors = fieldIsEmpty(minBpmField)
        if (hasErrors) {
            minBpmField.error = "Must enter a BPM number!"
        }
        return hasErrors
    }

    private fun greenNumberIsEmpty(): Boolean {
        val greenNumberField = findViewById<EditText>(R.id.textGreenNumber)
        val hasErrors = fieldIsEmpty(greenNumberField)
        if (hasErrors) {
            greenNumberField.error = "Must enter a green number!"
        }
        return hasErrors
    }
    
    private fun maxBpmIsGreaterThanMinBpm(): Boolean {
        var hasErrors = false

        if (!minBPMField.text.isNullOrEmpty() and !maxBPMField.text.isNullOrEmpty()) {
            val minBpm = parseInt(minBPMField.text.toString())
            val maxBpm = parseInt(maxBPMField.text.toString())
            if (minBpm > maxBpm) {
                hasErrors = true
                maxBPMField.error = "Max BPM must be greater than min BPM!"
            }
        }
        return hasErrors
    }

    private fun fieldIsEmpty(field: EditText): Boolean {
        return field.text.isNullOrEmpty()
    }
}
