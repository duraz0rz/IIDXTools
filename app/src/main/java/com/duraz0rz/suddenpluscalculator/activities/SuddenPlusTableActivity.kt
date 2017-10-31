package com.duraz0rz.suddenpluscalculator.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.duraz0rz.suddenpluscalculator.R
import com.duraz0rz.suddenpluscalculator.SuddenPlusCalculator
import com.duraz0rz.suddenpluscalculator.SuddenPlusValue

class SuddenPlusTableActivity : AppCompatActivity() {

    var suddenPlusCalculator = SuddenPlusCalculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudden_plus_table)

        val minBPM = intent.getIntExtra("BPM", Int.MIN_VALUE)
        val greenNumber = intent.getIntExtra("GreenNumber", Int.MIN_VALUE)

        val minBPMValues = suddenPlusCalculator.generateSuddenPlusTable(minBPM, greenNumber)

        val table = this.findViewById<TableLayout>(R.id.tblHiSpeed)

        minBPMValues.map { generateTableRow(it) }.forEach { table.addView(it) }
    }

    private fun generateTableRow(minBPMValue: SuddenPlusValue): TableRow {
        val tableRow = TableRow(this)

        val hiSpeedCell = TextView(this)
        hiSpeedCell.text = minBPMValue.highSpeed
        val minBPMCell = TextView(this)
        minBPMCell.text = minBPMValue.whiteNumber.toString()
        val maxBPMCell = TextView(this)
        maxBPMCell.text = ""

        tableRow.addView(hiSpeedCell)
        tableRow.addView(minBPMCell)
        tableRow.addView(maxBPMCell)

        return tableRow
    }
}
