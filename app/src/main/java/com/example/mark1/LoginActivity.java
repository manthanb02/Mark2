package com.example.mark1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
{

    CheckBox checkBoxShowPassword; // checkbox to show password
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogin;
    Button buttonForgotPassword;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextLoginEmail);
        checkBoxShowPassword = findViewById(R.id.checkBoxLoginShowPassword);
        editTextPassword = findViewById(R.id.editTextLoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonForgotPassword = findViewById(R.id.buttonLoginForgotPassword);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setTitle("Login");
            actionBar.setDisplayHomeAsUpEnabled(true);
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

        // code for login button
        buttonLogin.setOnClickListener(v ->
        {
            String userEmail = editTextEmail.getText().toString();
            String userPassword = editTextPassword.getText().toString();
            // checks username and password of user to login
            if(userEmail.equals("") || userPassword.equals(""))
            {
                Toast.makeText(LoginActivity.this,"Enter all input fields",Toast.LENGTH_SHORT).show();
                return;
            }

            login(userEmail,userPassword);
        });

        // code for forgot password
        buttonForgotPassword.setOnClickListener(V ->
        {
            String userEmail = editTextEmail.getText().toString();

            if(userEmail.equals(""))
            {
                Toast.makeText(LoginActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!userEmail.contains("@gmail.com"))
            {
                Toast.makeText(LoginActivity.this,"Enter valid Email id",Toast.LENGTH_SHORT).show();
                return;
            }

            auth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Sent an e-mail to reset password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

    // code for login
    public void login(String email, String password)
    {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            //change the activity from loginActivity to HomeActivity
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();

                        } else
                        {
                            Toast.makeText(LoginActivity.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}