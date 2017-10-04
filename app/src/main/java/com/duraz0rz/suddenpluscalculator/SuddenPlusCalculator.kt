package com.duraz0rz.suddenpluscalculator

class SuddenPlusCalculator {
    fun calculateSuddenPlus(hiSpeed: Double, bpm: Int, greenNumber: Int): Double {
        return 1000 - (hiSpeed * bpm * greenNumber / 174)
    }

    fun generateSuddenPlusTable(bpm: Int, greenNumber: Int): List<SuddenPlusValue> {
        return listOf(
                SuddenPlusValue(highSpeed = "0.00", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "0.50", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "1.00", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "1.50", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "2.00", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "2.50", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "3.00", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "3.50", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "4.00", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "4.50", greenNumber = 0, whiteNumber = 0, bpm = 0),
                SuddenPlusValue(highSpeed = "5.00", greenNumber = 0, whiteNumber = 0, bpm = 0)
        )
    }
}