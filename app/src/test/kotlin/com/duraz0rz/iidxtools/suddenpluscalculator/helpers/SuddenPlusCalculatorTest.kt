package com.duraz0rz.iidxtools.suddenpluscalculator.helpers

import com.duraz0rz.iidxtools.suddenpluscalculator.dataClasses.SuddenPlusValue
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.closeTo
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class SuddenPlusCalculatorTest {
    private val subject = SuddenPlusCalculator()

    @Test
    fun testCalculateSuddenPlusNumberReturnsCorrectWhiteNumberForInput() {
        val actualSuddenPlusNumber = subject.calculateSuddenPlus(hiSpeed = 2.0, bpm = 150, greenNumber = 310)

        assertThat(actualSuddenPlusNumber, closeTo(465.51, error = 0.01))
    }

    @Test
    fun testGenerateSuddenPlusTableReturnsAListOfSuddenPlusValuesWithSuddenPlusNumber() {
        val actualList = subject.generateSuddenPlusTable(bpm = 120, greenNumber = 400)

        val expectedList = listOf(
            SuddenPlusValue(highSpeed = "0.50", minWhiteNumber = 862),
            SuddenPlusValue(highSpeed = "0.75", minWhiteNumber = 793),
            SuddenPlusValue(highSpeed = "1.00", minWhiteNumber = 724),
            SuddenPlusValue(highSpeed = "1.25", minWhiteNumber = 655),
            SuddenPlusValue(highSpeed = "1.50", minWhiteNumber = 586),
            SuddenPlusValue(highSpeed = "1.75", minWhiteNumber = 517),
            SuddenPlusValue(highSpeed = "2.00", minWhiteNumber = 448),
            SuddenPlusValue(highSpeed = "2.25", minWhiteNumber = 379),
            SuddenPlusValue(highSpeed = "2.50", minWhiteNumber = 310),
            SuddenPlusValue(highSpeed = "2.75", minWhiteNumber = 241),
            SuddenPlusValue(highSpeed = "3.00", minWhiteNumber = 172),
            SuddenPlusValue(highSpeed = "3.25", minWhiteNumber = 103),
            SuddenPlusValue(highSpeed = "3.50", minWhiteNumber = 34),
            SuddenPlusValue(highSpeed = "3.75", minWhiteNumber = -34),
            SuddenPlusValue(highSpeed = "4.00", minWhiteNumber = -103)
        )

        assertThat(actualList, equalTo(expectedList))
    }

    @Test
    fun testGenerateSuddenPlusTableReturnsMaxWhiteNumberIfMaxBPMIsPassedIn() {
        val actualList = subject.generateSuddenPlusTable(bpm = 120, maxBpm = 160, greenNumber = 400)

        val expectedList = listOf(
            SuddenPlusValue(highSpeed = "0.50", minWhiteNumber = 862, maxWhiteNumber = 816),
            SuddenPlusValue(highSpeed = "0.75", minWhiteNumber = 793, maxWhiteNumber = 724),
            SuddenPlusValue(highSpeed = "1.00", minWhiteNumber = 724, maxWhiteNumber = 632),
            SuddenPlusValue(highSpeed = "1.25", minWhiteNumber = 655, maxWhiteNumber = 540),
            SuddenPlusValue(highSpeed = "1.50", minWhiteNumber = 586, maxWhiteNumber = 448),
            SuddenPlusValue(highSpeed = "1.75", minWhiteNumber = 517, maxWhiteNumber = 356),
            SuddenPlusValue(highSpeed = "2.00", minWhiteNumber = 448, maxWhiteNumber = 264),
            SuddenPlusValue(highSpeed = "2.25", minWhiteNumber = 379, maxWhiteNumber = 172),
            SuddenPlusValue(highSpeed = "2.50", minWhiteNumber = 310, maxWhiteNumber = 80),
            SuddenPlusValue(highSpeed = "2.75", minWhiteNumber = 241, maxWhiteNumber = -11),
            SuddenPlusValue(highSpeed = "3.00", minWhiteNumber = 172, maxWhiteNumber = -103),
            SuddenPlusValue(highSpeed = "3.25", minWhiteNumber = 103, maxWhiteNumber = -195),
            SuddenPlusValue(highSpeed = "3.50", minWhiteNumber = 34, maxWhiteNumber = -287),
            SuddenPlusValue(highSpeed = "3.75", minWhiteNumber = -34, maxWhiteNumber = -379),
            SuddenPlusValue(highSpeed = "4.00", minWhiteNumber = -103, maxWhiteNumber = -471)
        )

        assertThat(actualList, equalTo(expectedList))
    }
}
