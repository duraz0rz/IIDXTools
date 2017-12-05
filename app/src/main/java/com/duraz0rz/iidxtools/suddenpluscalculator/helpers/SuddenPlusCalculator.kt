package com.duraz0rz.iidxtools.suddenpluscalculator.helpers

import com.duraz0rz.iidxtools.suddenpluscalculator.dataClasses.SuddenPlusValue

class SuddenPlusCalculator {

    private val highSpeedValues = listOf(0.5, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 2.25, 2.5, 2.75, 3.0, 3.25, 3.5, 3.75, 4.0)

    fun generateSuddenPlusTable(bpm: Int, maxBpm: Int? = null, greenNumber: Int, lift: Int = 0): List<SuddenPlusValue> =
        highSpeedValues.map {
            val highSpeed = "%.2f".format(it)
            val minWhiteNumber = calculateSuddenPlus(hiSpeed = it, bpm = bpm, greenNumber = greenNumber, lift = lift)
            var maxWhiteNumber: Long? = null
            if (maxBpm != null) {
               maxWhiteNumber = calculateSuddenPlus(hiSpeed = it, bpm = maxBpm, greenNumber = greenNumber, lift = lift)
            }

            SuddenPlusValue(highSpeed = highSpeed, minWhiteNumber = minWhiteNumber, maxWhiteNumber = maxWhiteNumber)
        }

    private fun calculateSuddenPlus(hiSpeed: Double, bpm: Int, greenNumber: Int, lift: Int): Long =
        Math.round(1000 - (hiSpeed * bpm * greenNumber / 174)) - lift
}