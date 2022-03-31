package com.example.mark1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterApartment extends AppCompatActivity
{
    EditText name;
    EditText phoneNo;
    EditText email;
    EditText password;
    EditText upiId;
    EditText apartmentName;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_apartment);

        name = findViewById(R.id.editTextRegisterName);
        phoneNo = findViewById(R.id.editTextRegisterPhoneNo);
        email = findViewById(R.id.editTextRegisterEmail);
        password = findViewById(R.id.editTextRegisterPassword);
        upiId = findViewById(R.id.editTextRegisterUpiId);
        next = findViewById(R.id.buttonRegisterNext);
        apartmentName = findViewById(R.id.editTextRegisterApartmentName);


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setTitle("Register Apartment");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // A condition should be added to check whether all data fields are filled or not.

        next.setOnClickListener(v ->
        {
            // here api post request needed to be executed to send the data
        });
    }
}