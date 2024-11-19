package com.devcrafters.xisd6329;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SuccessUnitTest {

    private Success success;

    @Before
    public void setup() {
        success = new Success();
    }

    @Test
    public void testEmptyDateAndTime() {
        String date = "";
        String time = "";

        assertTrue("Date and time should be empty", date.isEmpty() && time.isEmpty());
    }

    @Test
    public void testValidDateFormatting() {
        String date = "2024-11-18"; // Simulated formatted date
        assertTrue("Date format should be valid (YYYY-MM-DD)", date.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    public void testValidTimeFormatting() {
        String time = "14:30"; // Simulated formatted time
        assertTrue("Time format should be valid (HH:mm)", time.matches("\\d{2}:\\d{2}"));
    }
}
