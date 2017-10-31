package com.duraz0rz.suddenpluscalculator.activities

import android.content.Intent
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.duraz0rz.suddenpluscalculator.R
import com.duraz0rz.suddenpluscalculator.helpers.SuddenPlusCalculator
import com.duraz0rz.suddenpluscalculator.dataClasses.SuddenPlusValue
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
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

    @Before fun setup() {
        val intent = Intent("").apply {
            putExtra("BPM", minBPM)
            putExtra("MaxBPM", maxBPM)
            putExtra("GreenNumber", greenNumber)
        }

        mockSuddenPlusCalculator = mock()
        activityController = Robolectric.buildActivity(SuddenPlusTableActivity::class.java, intent)
        subject = activityController.get()
        subject.suddenPlusCalculator = mockSuddenPlusCalculator
    }

    @Test fun onCreateGeneratesRowsForEachValueReturned() {
        whenever(mockSuddenPlusCalculator.generateSuddenPlusTable(bpm = minBPM, greenNumber = greenNumber)).thenReturn(listOf(
                SuddenPlusValue("4.00", minWhiteNumber = 55),
                SuddenPlusValue("3.75", minWhiteNumber = 44)
        ))

        activityController.create()
        val table = subject.findViewById<TableLayout>(R.id.tblHiSpeed)

        val hiSpeed400Row = table.getChildAt(1) as TableRow
        assertThat(hiSpeed400Row.childCount, equalTo(3))
        assertThat((hiSpeed400Row.getChildAt(0) as TextView).text.toString(), equalTo("4.00"))
        assertThat((hiSpeed400Row.getChildAt(1) as TextView).text.toString(), equalTo("55"))
        assertThat((hiSpeed400Row.getChildAt(2) as TextView).text.toString(), equalTo(""))

        val hiSpeed375Row = table.getChildAt(2) as TableRow
        assertThat(hiSpeed375Row.childCount, equalTo(3))
        assertThat((hiSpeed375Row.getChildAt(0) as TextView).text.toString(), equalTo("3.75"))
        assertThat((hiSpeed375Row.getChildAt(1) as TextView).text.toString(), equalTo("44"))
        assertThat((hiSpeed375Row.getChildAt(2) as TextView).text.toString(), equalTo(""))
    }

}
