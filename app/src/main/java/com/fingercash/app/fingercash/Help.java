package com.fingercash.app.fingercash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {

    public Button button9;
    public void inithp1(){
        button9=(Button)findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vh1) {
                Intent h1 = new Intent(Help.this, Help2.class);

                startActivity(h1);

            }
        });
    }

    public Button button10;
    public void inithp2(){
        button10=(Button)findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vh2) {
                Intent h2 = new Intent(Help.this, WelcomePage.class);

                startActivity(h2);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        inithp1();
        inithp2();
    }
}
