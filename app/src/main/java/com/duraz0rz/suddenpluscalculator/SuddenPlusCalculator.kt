package com.duraz0rz.suddenpluscalculator

class SuddenPlusCalculator {
    fun calculateSuddenPlus(hiSpeed: Double, bpm: Int, greenNumber: Int): Double {
        return 1000 - (hiSpeed * bpm * greenNumber / 174)
    }

    fun generateSuddenPlusTable(bpm: Int, greenNumber: Int): List<SuddenPlusValue> {
        return listOf(
                SuddenPlusValue(highSpeed = "1.00", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 1.0, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "1.50", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 1.5, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "2.00", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 2.0, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "2.25", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 2.25, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "2.50", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 2.50, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "2.75", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 2.75, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "3.00", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 3.0, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "3.25", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 3.25, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "3.50", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 3.5, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "3.75", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 3.75, bpm = bpm, greenNumber = greenNumber))),
                SuddenPlusValue(highSpeed = "4.00", whiteNumber = Math.round(calculateSuddenPlus(hiSpeed = 4.0, bpm = bpm, greenNumber = greenNumber)))
        )
    }
}