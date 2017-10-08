package com.duraz0rz.suddenpluscalculator

import android.content.Intent
import android.widget.Button
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class SuddenPlusInputTest {

    @Test fun testCalculateSuddenPlusNumbersStartsActivityWithCorrectClass() {
        val subject = Robolectric.setupActivity(SuddenPlusInput::class.java)

        subject.findViewById<Button>(R.id.btnCalculate).performClick()

        val expectedIntent = Intent(subject, SuddenPlusTable::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity

        assertThat(actualIntent.component, equalTo(expectedIntent.component))
    }

}