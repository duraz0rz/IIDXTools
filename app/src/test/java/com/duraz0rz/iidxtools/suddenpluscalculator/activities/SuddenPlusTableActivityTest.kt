package com.duraz0rz.iidxtools.suddenpluscalculator.activities

import android.content.Intent
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.duraz0rz.iidxtools.R
import com.duraz0rz.iidxtools.suddenpluscalculator.dataClasses.SuddenPlusValue
import com.duraz0rz.iidxtools.suddenpluscalculator.helpers.SuddenPlusCalculator
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isEmptyString
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class SuddenPlusTableActivityTest {

    private lateinit var activityController: ActivityController<SuddenPlusTableActivity>
    private lateinit var subject: SuddenPlusTableActivity
    private lateinit var mockSuddenPlusCalculator: SuddenPlusCalculator

    private val minBPM = 144
    private val maxBPM = 160
    private val greenNumber = 310

    private val intentWithoutMaxBpm = Intent().apply {
        putExtra("BPM", minBPM)
        putExtra("GreenNumber", greenNumber)
    }

    private val intentWithMaxBpm = Intent().apply {
        putExtra("BPM", minBPM)
        putExtra("MaxBPM", maxBPM)
        putExtra("GreenNumber", greenNumber)
    }

    @Test
    fun onCreateGeneratesRowsForEachValueReturned() {
        setupActivity(intentWithoutMaxBpm)

        whenever(mockSuddenPlusCalculator.generateSuddenPlusTable(bpm = minBPM, greenNumber = greenNumber)).thenReturn(listOf(
            SuddenPlusValue("4.00", minWhiteNumber = 55),
            SuddenPlusValue("3.75", minWhiteNumber = 44)
        ))

        activityController.create()
        val table = subject.findViewById<TableLayout>(R.id.tblHiSpeed)

        validateRow(table, 1, listOf("4.00", "55", ""))
        validateRow(table, 2, listOf("3.75", "44", ""))
    }

    @Test
    fun onCreateFillsInMaxBPMValueWhenMaxWhiteNumberIsPresent() {
        setupActivity(intentWithMaxBpm)

        whenever(mockSuddenPlusCalculator.generateSuddenPlusTable(bpm = minBPM, maxBpm = maxBPM, greenNumber = greenNumber)).thenReturn(listOf(
            SuddenPlusValue("4.00", minWhiteNumber = 66, maxWhiteNumber = 132),
            SuddenPlusValue("3.75", minWhiteNumber = 55, maxWhiteNumber = 111)
        ))

        activityController.create()
        val table = subject.findViewById<TableLayout>(R.id.tblHiSpeed)

        validateValueInRow(table, 1, 2, "132")
        validateValueInRow(table, 2, 2, "111")
    }

    @Test
    fun onCreateSetsHeaderRowCorrectlyWithoutMaxBPM() {
        setupActivity(intentWithoutMaxBpm)

        whenever(mockSuddenPlusCalculator.generateSuddenPlusTable(bpm = minBPM, greenNumber = greenNumber)).thenReturn(listOf(
            SuddenPlusValue("4.00", minWhiteNumber = 55),
            SuddenPlusValue("3.75", minWhiteNumber = 44)
        ))

        activityController.create()
        val table = subject.findViewById<TableLayout>(R.id.tblHiSpeed)

        validateRow(table, 0, listOf("Hi-speeds", "At 144 BPM", ""))
    }

    @Test
    fun onCreateSetsHeaderRowCorrectlyWithMaxBPM() {
        setupActivity(intentWithMaxBpm)

        whenever(mockSuddenPlusCalculator.generateSuddenPlusTable(bpm = minBPM, greenNumber = greenNumber)).thenReturn(listOf(
            SuddenPlusValue("4.00", minWhiteNumber = 55),
            SuddenPlusValue("3.75", minWhiteNumber = 44)
        ))

        activityController.create()
        val table = subject.findViewById<TableLayout>(R.id.tblHiSpeed)

        validateRow(table, 0, listOf("Hi-speeds", "At 144 BPM", "At 160 BPM"))
    }

    private fun validateRow(table: TableLayout, tableRow: Int, expectedValues: List<String>) {
        val tableRow = table.getChildAt(tableRow) as TableRow
        assertThat(tableRow.childCount, equalTo(3))
        assertThat((tableRow.getChildAt(0) as TextView).text.toString(), equalTo(expectedValues[0]))
        assertThat((tableRow.getChildAt(1) as TextView).text.toString(), equalTo(expectedValues[1]))
        assertThat((tableRow.getChildAt(2) as TextView).text.toString(), equalTo(expectedValues[2]))
    }

    private fun validateValueInRow(table: TableLayout, tableRow: Int, tableColumn: Int, expectedValue: String) {
        val tableRow = table.getChildAt(tableRow) as TableRow
        assertThat(tableRow.childCount, equalTo(3))
        assertThat((tableRow.getChildAt(tableColumn) as TextView).text.toString(), equalTo(expectedValue))
    }

    private fun setupActivity(intent: Intent) {
        mockSuddenPlusCalculator = mock()
        activityController = Robolectric.buildActivity(SuddenPlusTableActivity::class.java, intent)
        subject = activityController.get()
        subject.suddenPlusCalculator = mockSuddenPlusCalculator
    }
}
