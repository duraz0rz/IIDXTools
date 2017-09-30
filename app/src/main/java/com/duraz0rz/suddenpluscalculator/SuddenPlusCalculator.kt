package com.duraz0rz.suddenpluscalculator

class SuddenPlusCalculator {
    fun calculateSuddenPlus(hiSpeed: Double, bpm: Int, greenNumber: Int): Double {
        return 1000 - (hiSpeed * bpm * greenNumber / 174)
    }
}