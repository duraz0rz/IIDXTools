package com.duraz0rz.suddenpluscalculator

import com.duraz0rz.suddenpluscalculator.dataClasses.SuddenPlusValue

class SuddenPlusCalculator {

    private val highSpeedValues = listOf(0.5, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 2.25, 2.5, 2.75, 3.0, 3.25, 3.5, 3.75, 4.0)

    fun calculateSuddenPlus(hiSpeed: Double, bpm: Int, greenNumber: Int): Double {
        return 1000 - (hiSpeed * bpm * greenNumber / 174)
    }

    fun generateSuddenPlusTable(bpm: Int, maxBpm: Int? = null, greenNumber: Int): List<SuddenPlusValue> {
        if (maxBpm != null) {
            return highSpeedValues.map {
                SuddenPlusValue(
                        highSpeed = "%.2f".format(it),
                        minWhiteNumber = Math.round(calculateSuddenPlus(hiSpeed = it, bpm = bpm, greenNumber = greenNumber)),
                        maxWhiteNumber = Math.round(calculateSuddenPlus(hiSpeed = it, bpm = maxBpm, greenNumber = greenNumber))
                )
            }
        } else {
            return highSpeedValues.map {
                SuddenPlusValue(
                        highSpeed = "%.2f".format(it),
                        minWhiteNumber = Math.round(calculateSuddenPlus(hiSpeed = it, bpm = bpm, greenNumber = greenNumber))
                )
            }
        }

    }
}