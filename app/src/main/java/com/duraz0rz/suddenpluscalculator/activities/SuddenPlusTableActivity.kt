package com.duraz0rz.suddenpluscalculator.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.duraz0rz.suddenpluscalculator.R

class SuddenPlusTableActivity : AppCompatActivity() {
    data class SuddenPlusTableParams(val minBPM : Int?, val maxBPM : Int?, val greenNumber : Int?)

    lateinit var tableParams : SuddenPlusTableParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudden_plus_table)

        val minBPM = intent.getIntExtra("BPM", Int.MIN_VALUE)
        val maxBPM = intent.getIntExtra("MaxBPM", Int.MIN_VALUE)
        val greenNumber = intent.getIntExtra("GreenNumber", Int.MIN_VALUE)

        tableParams = SuddenPlusTableParams(minBPM, maxBPM, greenNumber)
    }
}
