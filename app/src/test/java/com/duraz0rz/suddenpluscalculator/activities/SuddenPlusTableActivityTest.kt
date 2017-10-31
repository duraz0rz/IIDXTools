package com.duraz0rz.suddenpluscalculator.activities

import android.content.Intent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SuddenPlusTableActivityTest {

    @Test fun onCreateTakesInExtrasParameters() {
        val intent = Intent("").apply {
            putExtra("BPM", 144)
            putExtra("MaxBPM", 160)
            putExtra("GreenNumber", 310)
        }

        val subject = Robolectric.buildActivity(SuddenPlusTableActivity::class.java, intent)
                .create().get()

        assertThat(subject.tableParams.minBPM, equalTo(144))
        assertThat(subject.tableParams.maxBPM, equalTo(160))
        assertThat(subject.tableParams.greenNumber, equalTo(310))
    }
}
