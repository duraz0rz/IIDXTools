package com.duraz0rz.suddenpluscalculator

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isNullOrEmptyString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class SuddenPlusInputTest {
    lateinit private var subject : SuddenPlusInput
    lateinit private var minBpmField : EditText
    lateinit private var greenNumberField : EditText
    lateinit private var calculateButton : Button

    @Before fun setup() {
        subject = Robolectric.setupActivity(SuddenPlusInput::class.java)
        minBpmField = subject.findViewById<EditText>(R.id.textMinBPM)
        greenNumberField = subject.findViewById<EditText>(R.id.textGreenNumber)
        calculateButton = subject.findViewById<Button>(R.id.btnCalculate)
    }

    @Test fun testCalculateSuddenPlusNumbersStartsActivityWithCorrectClass() {
        setFieldsAndClick("144","310")

        val expectedIntent = Intent(subject, SuddenPlusTable::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity

        assertThat(actualIntent.component, equalTo(expectedIntent.component))
    }

    @Test fun testCalculateSuddenPlusNumbersAddsMinBPMToIntent() {
        val expectedBPM = 144
        setFieldsAndClick(expectedBPM.toString(),"311")

        minBpmField.setText(expectedBPM.toString())
        calculateButton.performClick()

        val actualBPM = ShadowApplication.getInstance().nextStartedActivity.extras["BPM"] as Int

        assertThat(actualBPM, equalTo(expectedBPM))
    }

    @Test fun testCalculateSuddenPlusNumbersSetsErrorOnMinBPMIfBlank() {
        setFieldsAndClick("","")

        val editView = minBpmField

        val expectedError = "Must enter a BPM number!"

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(editView.error.toString(), equalTo(expectedError))
    }

    @Test fun testCalculateSuddenPlusNumberAddsGreenNumberToIntent() {
        val expectedGreenNumber = 310

        setFieldsAndClick("140", expectedGreenNumber.toString())

        val actualGreenNumber = ShadowApplication.getInstance().nextStartedActivity.extras["GreenNumber"] as Int

        assertThat(actualGreenNumber, equalTo(expectedGreenNumber))
    }

    @Test fun testCalculateSuddenPlusNumberSetsErrorOnGreenNumberIfBlank() {
        setFieldsAndClick("", "")

        val editView = greenNumberField

        val expectedError = "Must enter a green number!"

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(editView.error.toString(), equalTo(expectedError))
    }

    @Test fun testCalculateSuddenPlusNumberSetsErrorOnBothFieldsIfBothAreBlank() {
        setFieldsAndClick("", "")

        val minBPMEditView = minBpmField
        val greenNumberEditView = greenNumberField

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(minBPMEditView.error, !isNullOrEmptyString)
        assertThat(greenNumberEditView.error, !isNullOrEmptyString)
    }

    private fun setFieldsAndClick(minBPM : String, greenNumber : String) {
        minBpmField.setText(minBPM)
        greenNumberField.setText(greenNumber)
        calculateButton.performClick()
    }
}
