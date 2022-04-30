package com.example.mark1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterApartment extends AppCompatActivity
{
    EditText name;
    EditText phoneNo;
    EditText email;
    EditText password;
    EditText upiId;
    EditText apartmentName;
    Button next;

    Integer aptCode;
    Integer value; // variable to store fetched currentAptCode

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_apartment);

        name = findViewById(R.id.editTextRegisterName);
        phoneNo = findViewById(R.id.editTextRegisterPhoneNo);
        email = findViewById(R.id.editTextRegisterEmail);
        password = findViewById(R.id.editTextRegisterPassword);
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
            String userName = name.getText().toString();
            String userPhoneNo = phoneNo.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            String userApartmentName = apartmentName.getText().toString();

            if(userName.equals("") || userPhoneNo.equals("") || userEmail.equals("") || userPassword.equals("") || userApartmentName.equals(""))
            {
                Toast.makeText(RegisterApartment.this,"Enter All Input Fields",Toast.LENGTH_SHORT).show();
                return;
            }

            if(!userEmail.contains("@gmail.com"))
            {
                Toast.makeText(RegisterApartment.this,"Enter valid gmail account",Toast.LENGTH_SHORT).show();
                email.setText("");
                return;
            }

            if(password.length() < 8)
            {
                Toast.makeText(RegisterApartment.this,"Password should be atleast 8 characters long ",Toast.LENGTH_SHORT).show();
                return;
            }

            registerAdmin(userName,userPhoneNo,userEmail,userPassword,userApartmentName);
        });


    }

    void registerAdmin(String name, String phoneNo, String email, String password,String apartmentName)
    {
        getCurrentAptCode();

//        if(value == -1) // this code stats that it was unable to fetch the currentAptCode from database
//        {
//            Toast.makeText(this, "Apartment Code Error code  :" + aptCode, Toast.LENGTH_SHORT).show();
//            return;
//        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            aptCode = value;
                            aptCode++;
                            Toast.makeText(RegisterApartment.this, "Apartment code : " + aptCode, Toast.LENGTH_SHORT).show();

                            HashMap<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("phoneNo",phoneNo);
                            user.put("aptCode",aptCode);
                            user.put("status","admin");

                            HashMap<String,Object> apartment = new HashMap<>();
                            apartment.put("name",apartmentName);
                            apartment.put("balance",0);
                            apartment.put("maintenance",0);

                            FirebaseDatabase.getInstance().getReference().child("users").child(email.substring(0,email.length() - 4)).setValue(user);
                            FirebaseDatabase.getInstance().getReference().child("apartments").child(String.valueOf(aptCode)).setValue(apartment);
                            FirebaseDatabase.getInstance().getReference().child("code").setValue(aptCode);

                            Toast.makeText(RegisterApartment.this,"Data Saved Successfully",Toast.LENGTH_SHORT).show();

                            //changes screen from RegisterApartment to HomeActivity
                            Intent intent = new Intent(RegisterApartment.this,HomeActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        else
                        {
                            Toast.makeText(RegisterApartment.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void getCurrentAptCode()
    {
        this.value = -1;
        FirebaseDatabase.getInstance().getReference().child("code").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    String code = String.valueOf(snapshot.getValue(Integer.class));
                    value = Integer.parseInt(code);
                    Log.d("message","Code : " + value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                value = -1;
            }

        });
    }
}