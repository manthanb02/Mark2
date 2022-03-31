package com.example.mark1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class OptionsActivity extends AppCompatActivity
{
    Button buttonRegisterApartment; // button to register new building
    Button buttonEnterApartment;    // button to enter into building
    Button buttonLogin;            // button to login
    Intent intent;                 // to change activity

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        buttonEnterApartment = findViewById(R.id.buttonEnterApartment);
        buttonRegisterApartment = findViewById(R.id.buttonRegisterApartment);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonRegisterApartment.setOnClickListener(v ->
        {

        });

        buttonEnterApartment.setOnClickListener(v ->
        {
            intent = new Intent(OptionsActivity.this,JoinApartmentActivity.class);
            startActivity(intent);
        });

        buttonLogin.setOnClickListener(v ->
        {
            intent = new Intent(OptionsActivity.this,LoginActivity.class);
            startActivity(intent);
        });
    }
}