package com.duraz0rz.suddenpluscalculator

import junit.framework.Assert.assertEquals
import org.junit.Test

class SuddenPlusCalculatorTest {
    private val subject = SuddenPlusCalculator()

    @Test fun testCalculateSuddenPlusNumberReturnsCorrectWhiteNumberForInput() {
        val actualSuddenPlusNumber = subject.calculateSuddenPlus(hiSpeed = 2.0, bpm = 150, greenNumber = 310)

        assertEquals(465.51, actualSuddenPlusNumber, 0.01)
    }

    @Test fun testGenerateSuddenPlusTableReturnsAListOfSuddenPlusValuesWithHiSpeeds() {
        val suddenPlusList = subject.generateSuddenPlusTable(bpm = 150, greenNumber = 310) as Collection<SuddenPlusValue>

        val actualHighSpeedValues = suddenPlusList.map { it.highSpeed }
        val expectedHighSpeedValues = listOf("1.00","1.50","2.00","2.25","2.50","2.75","3.00","3.25","3.50","3.75","4.00")
        assertEquals(expectedHighSpeedValues, actualHighSpeedValues)
    }

    @Test fun testGenerateSuddenPlusTableReturnsAListOfSuddenPlusValuesWithSuddenPlusNumber() {
        val actualList = subject.generateSuddenPlusTable(bpm = 120, greenNumber = 400) as Collection<SuddenPlusValue>

        val expectedList = listOf(
                SuddenPlusValue(highSpeed = "1.00", whiteNumber = 724),
                SuddenPlusValue(highSpeed = "1.50", whiteNumber = 586),
                SuddenPlusValue(highSpeed = "2.00", whiteNumber = 448),
                SuddenPlusValue(highSpeed = "2.25", whiteNumber = 379),
                SuddenPlusValue(highSpeed = "2.50", whiteNumber = 310),
                SuddenPlusValue(highSpeed = "2.75", whiteNumber = 241),
                SuddenPlusValue(highSpeed = "3.00", whiteNumber = 172),
                SuddenPlusValue(highSpeed = "3.25", whiteNumber = 103),
                SuddenPlusValue(highSpeed = "3.50", whiteNumber = 34),
                SuddenPlusValue(highSpeed = "3.75", whiteNumber = -34),
                SuddenPlusValue(highSpeed = "4.00", whiteNumber = -103)
        )

        assertEquals(expectedList, actualList)
    }
}
