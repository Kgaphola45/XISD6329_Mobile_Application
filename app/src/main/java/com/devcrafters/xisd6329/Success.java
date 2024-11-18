package com.devcrafters.xisd6329;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Success extends AppCompatActivity {

    private EditText appointmentDate, appointmentTime;
    private Button submitAppointmentButton;
    private String appointmentURL = "http://10.0.2.2/login/submit_appointment.php"; // Replace with actual endpoint

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        appointmentDate = findViewById(R.id.appointmentDate);
        appointmentTime = findViewById(R.id.appointmentTime);
        submitAppointmentButton = findViewById(R.id.submitAppointmentButton);

        // Date picker dialog
        appointmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Time picker dialog
        appointmentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        submitAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAppointment();
            }
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Success.this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year1, monthOfYear + 1, dayOfMonth);
                    appointmentDate.setText(date);
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                Success.this,
                (view, hourOfDay, minute1) -> {
                    String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);
                    appointmentTime.setText(time);
                },
                hour, minute, true);
        timePickerDialog.show();
    }

    private void submitAppointment() {
        final String date = appointmentDate.getText().toString().trim();
        final String time = appointmentTime.getText().toString().trim();
        final String userID = "1"; // Replace with actual logged-in user ID

        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(Success.this, "Date and Time cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, appointmentURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Success.this, "Appointment saved successfully!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Success.this, "Failed to save appointment", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("ID", userID); // Assumes logged-in user ID is available
                data.put("appdate", date);
                data.put("apptime", time);
                return data;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
