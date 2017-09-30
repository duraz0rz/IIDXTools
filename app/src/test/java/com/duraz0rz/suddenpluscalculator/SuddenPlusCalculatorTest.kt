package com.duraz0rz.suddenpluscalculator

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SuddenPlusCalculatorTest {
    var subject : SuddenPlusCalculator? = null

    @Before fun setup() {
        subject = SuddenPlusCalculator()
    }

    @Test fun testCalculateSuddenPlusNumberReturnsCorrectWhiteNumberForInput() {
        val actualSuddenPlusNumber = subject.calculateSuddenPlus(hiSpeed = 2.0, bpm = 150, greenNumber = 310)

        Assert.assertEquals(465.51, actualSuddenPlusNumber, 0.01)
    }

    @Test fun testCalculateSuddenPlusNumberOnlyAcceptsValidHighSpeedNumbers() {

    }
}