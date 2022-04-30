package com.example.mark1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class JoinApartmentActivity extends AppCompatActivity
{

    EditText name;
    EditText email;
    EditText phoneNo;
    EditText password;
    EditText upiId;
    EditText apartmentCode;
    Button buttonEnterJoin;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_apartment);


//----------------------- add findViewById() for all components------------------------------------------
        name = findViewById(R.id.editTextEnterName);
        email = findViewById(R.id.editTextEnterEmail);
        phoneNo = findViewById(R.id.editTextEnterPhoneNo);
        password = findViewById(R.id.editTextEnterPassword);
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
            String userName = name.getText().toString();
            String userPhoneNo = phoneNo.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            String userApartmentCode = apartmentCode.getText().toString();

            if(userName.equals("") || userPhoneNo.equals("") || userEmail.equals("") || userPassword.equals("") || userApartmentCode.equals(""))
            {
                Toast.makeText(JoinApartmentActivity.this,"Enter All Input Fields",Toast.LENGTH_SHORT).show();
                return;
            }

            if(!userEmail.contains("@gmail.com"))
            {
                Toast.makeText(JoinApartmentActivity.this,"Enter valid gmail account",Toast.LENGTH_SHORT).show();
                email.setText("");
                return;
            }

            if(password.length() < 8)
            {
                Toast.makeText(JoinApartmentActivity.this,"Password should be atleast 8 characters long ",Toast.LENGTH_SHORT).show();
                return;
            }


            FirebaseDatabase.getInstance().getReference().child("apartments").child(userApartmentCode).addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    if(snapshot.exists())
                    {
                        // As apt code is valid user will be added to to particular apartment
                        int aptCode = Integer.parseInt(userApartmentCode);
                        joinApartment(userName,userPhoneNo,userEmail,userPassword,aptCode);
                    }
                    else
                    {
                        Toast.makeText(JoinApartmentActivity.this, "Invalid Apartment Code", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {

                }
            });

        });
    }

    public void joinApartment(String name, String phoneNo, String email,String password, int aptCode)
    {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(JoinApartmentActivity.this, "Apartment code : " + aptCode, Toast.LENGTH_SHORT).show();

                            HashMap<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("phoneNo",phoneNo);
                            user.put("aptCode",aptCode);
                            user.put("status","member");


                            FirebaseDatabase.getInstance().getReference().child("users").child(email.substring(0,email.length() - 4)).setValue(user);
                            Toast.makeText(JoinApartmentActivity.this,"Data Saved Successfully",Toast.LENGTH_SHORT).show();

                            // changes Activity screen from JoinApartment to HomeActivity
                            Intent intent = new Intent(JoinApartmentActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        else
                        {
                            Toast.makeText(JoinApartmentActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}