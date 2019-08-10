package com.fingercash.app.fingercash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class HomePage extends AppCompatActivity {

    private Button button7;
    public Button button6, button5;

    private SessionManager session;

    public void init7(){
        button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vh) {
                Intent hp = new Intent(HomePage.this, CheckBalance.class);

                startActivity(hp);

            }
        });
    }

    public void inita(){
        button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vh2) {
                Intent hp2 = new Intent(HomePage.this, Profile.class);

                startActivity(hp2);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        button7 = (Button) findViewById(R.id.button7);
        session = new SessionManager(getApplicationContext());



        init7();
        inita();

        //Click this icon will move to review menu page
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                Toast.makeText(HomePage.this, "Logged out.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}




