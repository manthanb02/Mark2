package com.example.mark1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinApartmentActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText phoneNo;
    EditText password;
    EditText upiId;
    EditText apartmentCode;
    Button buttonEnterJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_apartment);


//----------------------- add findViewById() for all components------------------------------------------
        name = findViewById(R.id.editTextEnterName);
        email = findViewById(R.id.editTextEnterEmail);
        phoneNo = findViewById(R.id.editTextEnterPhoneNo);
        password = findViewById(R.id.editTextEnterPassword);
        upiId = findViewById(R.id.editTextEnterUpiId);
        apartmentCode = findViewById(R.id.editTextEnterApartmentCode);
        buttonEnterJoin = findViewById(R.id.buttonEnterJoin);

        //sets title to actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("Join Apartment");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // button to join the apartment
        buttonEnterJoin.setOnClickListener(v ->
        {
            Toast.makeText(this, "joining to apartment...", Toast.LENGTH_SHORT).show();
            // here code for api post request will be written
        });
    }
}