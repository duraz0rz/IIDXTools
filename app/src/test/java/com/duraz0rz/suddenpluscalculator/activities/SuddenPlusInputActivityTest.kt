package com.duraz0rz.suddenpluscalculator.activities

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.duraz0rz.suddenpluscalculator.R
import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isNullOrEmptyString
import com.natpryce.hamkrest.present
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class SuddenPlusInputActivityTest {
    lateinit private var subject : SuddenPlusInputActivity
    lateinit private var minBpmField : EditText
    lateinit private var maxBpmField : EditText
    lateinit private var greenNumberField : EditText
    lateinit private var calculateButton : Button

    @Before fun setup() {
        subject = Robolectric.setupActivity(SuddenPlusInputActivity::class.java)
        minBpmField = subject.findViewById<EditText>(R.id.textMinBPM)
        maxBpmField = subject.findViewById<EditText>(R.id.textMaxBPM)
        greenNumberField = subject.findViewById<EditText>(R.id.textGreenNumber)
        calculateButton = subject.findViewById<Button>(R.id.btnCalculate)
    }

    @Test fun testCalculateSuddenPlusNumbersStartsActivityWithCorrectClass() {
        setFieldsAndClick(minBPM = "144", maxBPM = "155", greenNumber = "310")

        val expectedIntent = Intent(subject, SuddenPlusTableActivity::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity

        assertThat(actualIntent.component, equalTo(expectedIntent.component))
    }

    @Test fun testCalculateSuddenPlusNumbersAddsMinBPMToIntent() {
        val expectedBPM = 144
        setFieldsAndClick(minBPM = expectedBPM.toString(), maxBPM = "144")

        minBpmField.setText(expectedBPM.toString())
        calculateButton.performClick()

        val actualBPM = ShadowApplication.getInstance().nextStartedActivity.extras["BPM"] as Int

        assertThat(actualBPM, equalTo(expectedBPM))
    }

    @Test fun testCalculateSuddenPlusNumbersSetsErrorOnMinBPMIfBlank() {
        setFieldsAndClick(minBPM = "")

        val editView = minBpmField

        val expectedError = "Must enter a BPM number!"

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(editView.error.toString(), equalTo(expectedError))
    }

    @Test fun testCalculateSuddenPlusNumberAddsGreenNumberToIntent() {
        val expectedGreenNumber = 310

        setFieldsAndClick(greenNumber = expectedGreenNumber.toString())

        val actualGreenNumber = ShadowApplication.getInstance().nextStartedActivity.extras["GreenNumber"] as Int

        assertThat(actualGreenNumber, equalTo(expectedGreenNumber))
    }

    @Test fun testCalculateSuddenPlusNumberSetsErrorOnGreenNumberIfBlank() {
        setFieldsAndClick(greenNumber = "")

        val editView = greenNumberField

        val expectedError = "Must enter a green number!"

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(editView.error.toString(), equalTo(expectedError))
    }

    @Test fun testCalculateSuddenPlusNumberSetsErrorOnBothFieldsIfBothAreBlank() {
        setFieldsAndClick(minBPM = "", greenNumber = "")

        val minBPMEditView = minBpmField
        val greenNumberEditView = greenNumberField

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(minBPMEditView.error, !isNullOrEmptyString)
        assertThat(greenNumberEditView.error, !isNullOrEmptyString)
    }

    @Test fun testCalculateSuddenPlusNumberAddsMaxBPMToIntent() {
        val expectedMaxBPM = 150
        setFieldsAndClick(maxBPM = expectedMaxBPM.toString())

        val actualMaxBPM = ShadowApplication.getInstance().nextStartedActivity.extras["MaxBPM"] as Int

        assertThat(actualMaxBPM, equalTo(expectedMaxBPM))
    }

    @Test fun testCalculateSuddenPlusNumberDoesNotSetErrorOnMaxBPMIfBlank() {
        minBpmField.setText("150")
        greenNumberField.setText("310")
        calculateButton.performClick()

        assertThat(ShadowApplication.getInstance().nextStartedActivity, present())
        assertThat(maxBpmField.error, absent())
    }

    @Test fun testCalculateSuddenPlusNumberSetsErrorOnMaxBPMIfItIsSmallerThanMinBPM() {
        setFieldsAndClick(minBPM = "150", maxBPM = "140", greenNumber = "310")

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(maxBpmField.error.toString(), equalTo("Max BPM must be greater than min BPM!"))
    }

    private fun setFieldsAndClick(minBPM: String = "0", maxBPM: String = "0", greenNumber: String = "0") {
        minBpmField.setText(minBPM)
        maxBpmField.setText(maxBPM)
        greenNumberField.setText(greenNumber)
        calculateButton.performClick()
    }
}
