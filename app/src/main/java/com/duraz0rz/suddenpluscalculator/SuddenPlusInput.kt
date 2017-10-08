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

    fun calculateSuddenPlusNumbers(view : View) {
        val calculateIntent = Intent(this, SuddenPlusTable::class.java)
        val minBPMText = findViewById<EditText>(R.id.textMinBPM).text.toString()
        if (minBPMText == "") {
            calculateIntent.putExtra("BPM", 0)
        } else {
            calculateIntent.putExtra("BPM", parseInt(minBPMText))
        }
        startActivity(calculateIntent)
    }
}
