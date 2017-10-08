package com.duraz0rz.suddenpluscalculator

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class SuddenPlusInputTest {
    lateinit private var subject : SuddenPlusInput

    @Before fun setup() {
        subject = Robolectric.setupActivity(SuddenPlusInput::class.java)
    }

    @Test fun testCalculateSuddenPlusNumbersStartsActivityWithCorrectClass() {
        subject.findViewById<Button>(R.id.btnCalculate).performClick()

        val expectedIntent = Intent(subject, SuddenPlusTable::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity

        assertThat(actualIntent.component, equalTo(expectedIntent.component))
    }

    @Test fun testCalculateSuddenPlusNumbersAddsTheBPMToTheIntent() {
        subject.findViewById<EditText>(R.id.textMinBPM).setText("144")
        subject.findViewById<Button>(R.id.btnCalculate).performClick()

        val expectedBPM = 144
        val actualBPM = ShadowApplication.getInstance().nextStartedActivity.extras["BPM"] as Int

        assertThat(actualBPM, equalTo(expectedBPM))
    }
}
