package com.devcrafters.xisd6329;

import static org.junit.Assert.*;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.junit.Before;
import org.junit.Test;

public class SuccessAPITest {

    private Success success;
    private String mockURL = "http://10.0.2.2/login/submit_appointment.php";

    @Before
    public void setup() {
        success = new Success();
    }

    @Test
    public void testAPIRequestStructure() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, mockURL, response -> {
            assertNotNull("Response should not be null", response);
        }, error -> {
            fail("Request failed");
        });

        assertEquals("Request method should be POST", Request.Method.POST, stringRequest.getMethod());
        assertEquals("URL should match", mockURL, stringRequest.getUrl());
    }
}
