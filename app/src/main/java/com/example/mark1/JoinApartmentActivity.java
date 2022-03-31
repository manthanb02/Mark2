package com.example.mark1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinApartmentActivity extends AppCompatActivity {

    EditText editTextEnterName;
    EditText editTextEnterEmail;
    EditText editTextEnterPhoneNo;
    EditText editTextEnterPassword;
    EditText editTextEnterUpiId;
    EditText editTextEnterApartmentCode;
    Button buttonEnterJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_apartment);


//----------------------- add findViewById() for all components------------------------------------------
        buttonEnterJoin = findViewById(R.id.buttonEnterJoin);

        //sets title to actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("Join Apartment");
        }

        // button to join the apartment
        buttonEnterJoin.setOnClickListener(v ->
        {
            Toast.makeText(this, "joining to apartment...", Toast.LENGTH_SHORT).show();
        });
    }
}