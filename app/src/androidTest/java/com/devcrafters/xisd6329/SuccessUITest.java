package com.devcrafters.xisd6329;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SuccessUITest {

    @Rule
    public ActivityScenarioRule<Success> activityScenarioRule = new ActivityScenarioRule<>(Success.class);

    @Test
    public void testAppointmentDateSelection() {
        Espresso.onView(withId(R.id.appointmentDate))
                .perform(ViewActions.click());
        // Check if date picker dialog is displayed
        Espresso.onView(ViewMatchers.withClassName(org.hamcrest.Matchers.containsString("DatePicker")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testAppointmentTimeSelection() {
        Espresso.onView(withId(R.id.appointmentTime))
                .perform(ViewActions.click());
        // Check if time picker dialog is displayed
        Espresso.onView(ViewMatchers.withClassName(org.hamcrest.Matchers.containsString("TimePicker")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSubmitAppointmentButtonEnabled() {
        Espresso.onView(withId(R.id.appointmentDate))
                .perform(ViewActions.typeText("2024-11-18"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.appointmentTime))
                .perform(ViewActions.typeText("14:30"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.submitAppointmentButton))
                .check(matches(ViewMatchers.isEnabled()));
    }
}
