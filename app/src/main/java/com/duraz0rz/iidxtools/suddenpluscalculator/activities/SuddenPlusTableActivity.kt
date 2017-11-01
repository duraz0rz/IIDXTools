package com.duraz0rz.iidxtools.suddenpluscalculator.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.duraz0rz.iidxtools.R
import com.duraz0rz.iidxtools.suddenpluscalculator.helpers.SuddenPlusCalculator
import com.duraz0rz.iidxtools.suddenpluscalculator.dataClasses.SuddenPlusValue

class SuddenPlusTableActivity : AppCompatActivity() {

    var suddenPlusCalculator = SuddenPlusCalculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudden_plus_table)

        val minBPM = intent.getIntExtra("BPM", Int.MIN_VALUE)
        var maxBPM: Int? = null
        if (intent.hasExtra("MaxBPM")) {
            maxBPM = intent.getIntExtra("MaxBPM", Int.MIN_VALUE)
        }
        val greenNumber = intent.getIntExtra("GreenNumber", Int.MIN_VALUE)

        val suddenPlusValues = suddenPlusCalculator.generateSuddenPlusTable(bpm = minBPM, maxBpm = maxBPM, greenNumber = greenNumber)

        val table = this.findViewById<TableLayout>(R.id.tblHiSpeed)

        suddenPlusValues.map { generateTableRow(it) }.forEach { table.addView(it) }
    }

    private fun generateTableRow(suddenPlusValue: SuddenPlusValue): TableRow {
        fun generateTableCell(text: String): TextView {
            val cell = TextView(this)
            cell.text = text
            cell.gravity = Gravity.CENTER
            cell.setPadding(0, 16,0,16)

            return cell
        }

        val tableRow = TableRow(this)

        tableRow.addView(generateTableCell(suddenPlusValue.highSpeed))
        tableRow.addView(generateTableCell(suddenPlusValue.minWhiteNumber.toString()))
        if (suddenPlusValue.maxWhiteNumber != null) {
            tableRow.addView(generateTableCell(suddenPlusValue.maxWhiteNumber.toString()))
        } else {
            tableRow.addView(generateTableCell(""))
        }

        return tableRow
    }
}
