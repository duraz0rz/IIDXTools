package com.duraz0rz.suddenpluscalculator.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
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

        val suddenPlusValues = suddenPlusCalculator.generateSuddenPlusTable(bpm = minBPM, greenNumber = greenNumber)

        val table = this.findViewById<TableLayout>(R.id.tblHiSpeed)

        suddenPlusValues.map { generateTableRow(it) }.forEach { table.addView(it) }
    }

    private fun generateTableRow(minBPMValue: SuddenPlusValue): TableRow {
        fun generateTableCell(text: String): TextView {
            val cell = TextView(this)
            cell.text = text
            cell.gravity = Gravity.CENTER
            cell.setPadding(0, 16,0,16)

            return cell
        }

        val tableRow = TableRow(this)

        tableRow.addView(generateTableCell(minBPMValue.highSpeed))
        tableRow.addView(generateTableCell(minBPMValue.minWhiteNumber.toString()))
        tableRow.addView(generateTableCell(""))

        return tableRow
    }
}
