package com.duraz0rz.suddenpluscalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SuddenPlusInput : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudden_plus_input)
    }

    fun calculateSuddenPlusNumbers(view : View) {
        val calculateIntent = Intent(this, SuddenPlusTable::class.java)
        startActivity(calculateIntent)
    }
}
