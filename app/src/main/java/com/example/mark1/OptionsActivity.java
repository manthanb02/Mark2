package com.example.mark1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity
{
    Button buttonRegisterBuilding; // button to register new building
    Button buttonEnterBuilding;    // button to enter into building
    Button buttonLogin;            // button to login
    Intent intent;                 // to change activity

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        buttonEnterBuilding = findViewById(R.id.buttonEnterBuilding);
        buttonRegisterBuilding = findViewById(R.id.buttonRegisterBuilding);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v ->
        {
            intent = new Intent(OptionsActivity.this,LoginActivity.class);
            startActivity(intent);
        });
    }
}