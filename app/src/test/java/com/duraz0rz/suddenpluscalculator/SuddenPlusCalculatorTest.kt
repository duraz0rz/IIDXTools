package com.duraz0rz.suddenpluscalculator

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class SuddenPlusCalculatorTest {
    private val subject = SuddenPlusCalculator()

    @Test fun testCalculateSuddenPlusNumberReturnsCorrectWhiteNumberForInput() {
        val actualSuddenPlusNumber = subject.calculateSuddenPlus(hiSpeed = 2.0, bpm = 150, greenNumber = 310)

        assertEquals(465.51, actualSuddenPlusNumber, 0.01)
    }

    @Test fun testGenerateSuddenPlusTableReturnsAListOfSuddenPlusNumbersForTheGivenBPMAndGreenNumber() {
        val suddenPlusList = subject.generateSuddenPlusTable(bpm = 150, greenNumber = 310) as Collection<SuddenPlusValue>

        val highSpeedValues = suddenPlusList.map { it.highSpeed }
        val expectedHighSpeedValues = listOf("0.00","0.50","1.00","1.50","2.00","2.50","3.00","3.50","4.00","4.50","5.00")
        assertEquals(highSpeedValues, expectedHighSpeedValues)
    }
}
