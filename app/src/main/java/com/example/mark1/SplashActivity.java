package com.example.mark1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

class SplashThread extends Thread
{
    SplashActivity activity;
    Intent intent;

    SplashThread(SplashActivity activity)
    {
        this.activity = activity;
    }

    public void run()
    {
        try
        {
            Thread.sleep(3000);
            intent = new Intent(activity,LoginActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
        catch(InterruptedException e)
        {
            //
        }
    }
}


public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        SplashThread mySplash = new SplashThread(this);
        mySplash.start();
    }
}