package com.duraz0rz.iidxtools

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasErrorText
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import com.duraz0rz.iidxtools.suddenpluscalculator.activities.SuddenPlusInputActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SuddenPlusInputInstrumentedTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SuddenPlusInputActivity::class.java)

    private val minBpmField = onView(withId(R.id.textMinBPM))
    private val maxBpmField = onView(withId(R.id.textMaxBPM))
    private val greenNumberField = onView(withId(R.id.textGreenNumber))
    private val liftField = onView(withId(R.id.textLift))
    private val calculateButton = onView(withId(R.id.btnCalculate))

    @Test
    fun canEnterMinimumBPMAndGreenNumberAndGetResults() {
        minBpmField.perform(typeText("123"))
        greenNumberField.perform(typeText("333"))
        calculateButton.perform(closeSoftKeyboard(), click())

        checkFirstRow(0, "0.50")
        checkFirstRow(1, "882")
        checkFirstRow(2, "")
    }

    @Test
    fun canEnterAllThreeFieldsAndGetResults() {
        minBpmField.perform(typeText("123"))
        maxBpmField.perform(typeText("145"))
        greenNumberField.perform(typeText("333"))
        calculateButton.perform(closeSoftKeyboard(), click())

        checkFirstRow(0, "0.50")
        checkFirstRow(1, "882")
        checkFirstRow(2, "861")
    }

    @Test
    fun enteringMaxBPMLessThanMinimumThrowsErrorOnMaxBPMField() {
        minBpmField.perform(typeText("123"))
        maxBpmField.perform(typeText("111"))
        greenNumberField.perform(typeText("333"))
        calculateButton.perform(closeSoftKeyboard(), click())

        maxBpmField.check(matches(hasErrorText("Max BPM must be greater than min BPM!")))
    }

    @Test
    fun canScrollToCalculateButtonWhenKeyboardIsUp() {
        minBpmField.perform(typeText("123"))
        greenNumberField.perform(typeText("333"))
        calculateButton.perform(click())

        onView(withId(R.id.txtHiSpeedHeader)).check(matches(isDisplayed()))
    }

    @Test
    fun theSuddenPlusNumbersAreCalculatedWhenTheEnterKeyIsPressedOnTheLiftField() {
        minBpmField.perform(typeText("123"))
        greenNumberField.perform(typeText("123"))
        liftField.perform(pressImeActionButton())

        onView(withId(R.id.txtHiSpeedHeader)).check(matches(isDisplayed()))
    }

    @Test
    fun navigatingBackPreservesInputValues() {
        minBpmField.perform(typeText("123"))
        maxBpmField.perform(typeText("140"))
        greenNumberField.perform(typeText("333"))

        calculateButton.perform(click())
        onView(withId(R.id.txtHiSpeedHeader)).perform(pressBack())

        onView(withId(R.id.textMinBPM)).check(matches(withText("123")))
        onView(withId(R.id.textMaxBPM)).check(matches(withText("140")))
        onView(withId(R.id.textGreenNumber)).check(matches(withText("333")))
    }

    @Test
    fun navigatingUpPreservesInputValues() {
        minBpmField.perform(typeText("130"))
        maxBpmField.perform(typeText("160"))
        greenNumberField.perform(typeText("310"))

        calculateButton.perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

        onView(withId(R.id.textMinBPM)).check(matches(withText("130")))
        onView(withId(R.id.textMaxBPM)).check(matches(withText("160")))
        onView(withId(R.id.textGreenNumber)).check(matches(withText("310")))
    }

    private fun checkFirstRow(position: Int, text: String) {
        onView(allOf(
            isDescendantOfA(isAssignableFrom(TableLayout::class.java)),
            isInRowBelow(withText(R.string.hiSpeedHeader)),
            hasChildPosition(position)
        )).check(matches(withText(text)))
    }

    private fun isInRowBelow(viewInRowAbove: Matcher<View>): Matcher<View> {
        checkNotNull(viewInRowAbove)
        return object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) {
                description.appendText("is below a: ")
                viewInRowAbove.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                // Find the current row
                val viewParent = view.parent as? TableRow ?: return false
                // Find the row above
                val table = viewParent.parent as TableLayout
                val currentRowIndex = table.indexOfChild(viewParent)
                if (currentRowIndex < 1) {
                    return false
                }
                val rowAbove = table.getChildAt(currentRowIndex - 1) as TableRow
                // Does the row above contains at least one view that matches viewInRowAbove?
                return (0 until rowAbove.childCount).any { viewInRowAbove.matches(rowAbove.getChildAt(it)) }
            }
        }
    }

    private fun hasChildPosition(i: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) {
                description.appendText("is child #" + i)
            }

            override fun matchesSafely(view: View): Boolean {
                val viewParent = view.parent as? ViewGroup ?: return false
                return viewParent.indexOfChild(view) == i
            }
        }
    }
}
