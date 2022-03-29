package com.example.mark1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{

    CheckBox checkBoxShowPassword; // checkbox to show password
    EditText editTextPhoneNo;
    EditText editTextPassword;
    Button buttonLogin;
    Button buttonForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkBoxShowPassword = findViewById(R.id.checkBoxShowPassword);
        editTextPassword = findViewById(R.id.editTextPassword);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setTitle("Login");
        }

        // To show and hide the password
        int type = editTextPassword.getInputType(); // default password type;

        checkBoxShowPassword.setOnClickListener(v ->
        {
            if(checkBoxShowPassword.isChecked())
            {
                editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD); // visible password
            }
            else
            {
                editTextPassword.setInputType(type); // i.e password formant
            }
        });
    }
}