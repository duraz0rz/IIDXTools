package com.duraz0rz.iidxtools.suddenpluscalculator.activities

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.duraz0rz.iidxtools.R
import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasElement
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
    lateinit private var subject: SuddenPlusInputActivity
    lateinit private var minBpmField: EditText
    lateinit private var maxBpmField: EditText
    lateinit private var greenNumberField: EditText
    lateinit private var liftField: EditText
    lateinit private var calculateButton: Button

    @Before
    fun setup() {
        subject = Robolectric.setupActivity(SuddenPlusInputActivity::class.java)
        minBpmField = subject.findViewById(R.id.textMinBPM)
        maxBpmField = subject.findViewById(R.id.textMaxBPM)
        greenNumberField = subject.findViewById(R.id.textGreenNumber)
        liftField = subject.findViewById(R.id.textLift)
        calculateButton = subject.findViewById(R.id.btnCalculate)
    }

    @Test
    fun testCalculateSuddenPlusNumbersStartsActivityWithCorrectClass() {
        setFieldsAndClick(minBPM = "144", maxBPM = "155", greenNumber = "310")

        val expectedIntent = Intent(subject, SuddenPlusTableActivity::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity

        assertThat(actualIntent.component, equalTo(expectedIntent.component))
    }

    @Test
    fun testCalculateSuddenPlusNumbersAddsMinBPMToIntent() {
        val expectedBPM = 144
        setFieldsAndClick(minBPM = expectedBPM.toString(), maxBPM = "144")

        minBpmField.setText(expectedBPM.toString())
        calculateButton.performClick()

        val actualBPM = ShadowApplication.getInstance().nextStartedActivity.extras["BPM"] as Int

        assertThat(actualBPM, equalTo(expectedBPM))
    }

    @Test
    fun testCalculateSuddenPlusNumbersSetsErrorOnMinBPMIfBlank() {
        setFieldsAndClick(minBPM = "")

        val editView = minBpmField

        val expectedError = "Must enter a BPM number!"

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(editView.error.toString(), equalTo(expectedError))
    }

    @Test
    fun testCalculateSuddenPlusNumberAddsGreenNumberToIntent() {
        val expectedGreenNumber = 310

        setFieldsAndClick(greenNumber = expectedGreenNumber.toString())

        val actualGreenNumber = ShadowApplication.getInstance().nextStartedActivity.extras["GreenNumber"] as Int

        assertThat(actualGreenNumber, equalTo(expectedGreenNumber))
    }

    @Test
    fun testCalculateSuddenPlusNumberSetsErrorOnGreenNumberIfBlank() {
        setFieldsAndClick(greenNumber = "")

        val editView = greenNumberField

        val expectedError = "Must enter a green number!"

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(editView.error.toString(), equalTo(expectedError))
    }

    @Test
    fun testCalculateSuddenPlusNumberSetsErrorOnBothFieldsIfBothAreBlank() {
        setFieldsAndClick(minBPM = "", greenNumber = "")

        val minBPMEditView = minBpmField
        val greenNumberEditView = greenNumberField

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(minBPMEditView.error, !isNullOrEmptyString)
        assertThat(greenNumberEditView.error, !isNullOrEmptyString)
    }

    @Test
    fun testCalculateSuddenPlusNumberAddsMaxBPMToIntent() {
        val expectedMaxBPM = 150
        setFieldsAndClick(maxBPM = expectedMaxBPM.toString())

        val actualMaxBPM = ShadowApplication.getInstance().nextStartedActivity.extras["MaxBPM"] as Int

        assertThat(actualMaxBPM, equalTo(expectedMaxBPM))
    }

    @Test
    fun testCalculateSuddenPlusNumberDoesNotSetErrorOnMaxBPMIfBlank() {
        minBpmField.setText("150")
        greenNumberField.setText("310")
        calculateButton.performClick()

        assertThat(ShadowApplication.getInstance().nextStartedActivity, present())
        assertThat(maxBpmField.error, absent())
    }

    @Test
    fun testCalculateSuddenPlusNumberSetsErrorOnMaxBPMIfItIsSmallerThanMinBPM() {
        setFieldsAndClick(minBPM = "150", maxBPM = "140", greenNumber = "310")

        assertThat(ShadowApplication.getInstance().nextStartedActivity, absent())
        assertThat(maxBpmField.error.toString(), equalTo("Max BPM must be greater than min BPM!"))
    }

    @Test
    fun testCalculateSuddenPlusNumbersDoesNotSetMaxBPMIfNotPresent() {
        setFieldsAndClick(minBPM = "150", greenNumber = "300")

        assertThat(ShadowApplication.getInstance().nextStartedActivity.extras.keySet(), !hasElement("MaxBPM"))
    }

    @Test
    fun calculateSuddenPlusNumbersSetsLiftIfPresent() {
        setFieldsAndClick(minBPM = "160", greenNumber = "310", lift = "155")

        val intent = ShadowApplication.getInstance().nextStartedActivity.extras

        assertThat(intent["Lift"], present())
        assertThat(intent["Lift"].toString(), equalTo("155"))
    }

    @Test
    fun calculateSuddenPlusNumbersDoesNotSetLiftIfNotPresent() {
        setFieldsAndClick(minBPM = "140", greenNumber = "330")

        val intent = ShadowApplication.getInstance().nextStartedActivity.extras

        assertThat(intent.keySet(), !hasElement("Lift"))
    }

    private fun setFieldsAndClick(minBPM: String = "0", maxBPM: String? = null, greenNumber: String = "0", lift: String? = null) {
        minBpmField.setText(minBPM)
        if (maxBPM != null) {
            maxBpmField.setText(maxBPM)
        }
        greenNumberField.setText(greenNumber)
        if (lift != null) {
            liftField.setText(lift)
        }
        calculateButton.performClick()
    }
}
