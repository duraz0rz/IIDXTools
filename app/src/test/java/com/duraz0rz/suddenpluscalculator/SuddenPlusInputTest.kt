package com.duraz0rz.suddenpluscalculator

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.natpryce.hamkrest.absent
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
        subject.findViewById<EditText>(R.id.textMinBPM).setText("300")
        subject.findViewById<EditText>(R.id.textGreenNumber).setText("573")
    }

    @Test fun testCalculateSuddenPlusNumbersStartsActivityWithCorrectClass() {
        subject.findViewById<Button>(R.id.btnCalculate).performClick()

        val expectedIntent = Intent(subject, SuddenPlusTable::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity

        assertThat(actualIntent.component, equalTo(expectedIntent.component))
    }

    @Test fun testCalculateSuddenPlusNumbersAddsMinBPMToIntent() {
        val expectedBPM = 144

        subject.findViewById<EditText>(R.id.textMinBPM).setText(expectedBPM.toString())
        subject.findViewById<Button>(R.id.btnCalculate).performClick()

        val actualBPM = ShadowApplication.getInstance().nextStartedActivity.extras["BPM"] as Int

        assertThat(actualBPM, equalTo(expectedBPM))
    }

    @Test fun testCalculateSuddenPlusNumbersSetsErrorOnMinBPMIfNonexistent() {
        subject.findViewById<EditText>(R.id.textMinBPM).setText("")
        subject.findViewById<Button>(R.id.btnCalculate).performClick()

        val editView = subject.findViewById<EditText>(R.id.textMinBPM)

        val expectedError = "Must enter a BPM number!"

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(editView.error.toString(), equalTo(expectedError))
    }

    @Test fun testCalculateSuddenPlusNumberAddsGreenNumberToIntent() {
        val expectedGreenNumber = 310

        subject.findViewById<EditText>(R.id.textGreenNumber).setText(expectedGreenNumber.toString())
        subject.findViewById<Button>(R.id.btnCalculate).performClick()

        val actualGreenNumber = ShadowApplication.getInstance().nextStartedActivity.extras["GreenNumber"] as Int

        assertThat(actualGreenNumber, equalTo(expectedGreenNumber))
    }

}
