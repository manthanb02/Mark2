package com.example.mark1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity
{
    Button logout;
    TextView text;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        logout = findViewById(R.id.logoutButton);
        text = findViewById(R.id.text);

        text.setText(email);

        logout.setOnClickListener(v ->
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeActivity.this,OptionsActivity.class));
            finish();
        });
    }


    // code for back button pressed which pops up a dialog box
    @Override
    public void onBackPressed() // function for back press
    {

        // below code will show the dialog box when user back presses
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(HomeActivity.this);
        exitDialog.setTitle("Exit")
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                .setMessage("Are you sure you want to exit app ? ")
                .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }
}