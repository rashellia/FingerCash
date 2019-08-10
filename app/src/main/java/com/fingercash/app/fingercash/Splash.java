package com.fingercash.app.fingercash;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),WelcomePage.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        };
            myThread.start();
    }
}
