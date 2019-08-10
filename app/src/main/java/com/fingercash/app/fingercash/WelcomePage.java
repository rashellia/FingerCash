package com.fingercash.app.fingercash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {

    public Button btnRegister;
    public void init(){
        btnRegister=(Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(WelcomePage.this, RegisterPage.class);

                startActivity(reg);

            }
        });
    }

    public Button btnLogin;
    public void init2(){
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent reg2 = new Intent(WelcomePage.this, LoginPage.class);

                startActivity(reg2);

            }
        });
    }

    public Button button8;
    public void inith(){
        button8=(Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vh) {
                Intent regh = new Intent(WelcomePage.this, Help.class);

                startActivity(regh);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        init();
        init2();
        inith();

    }
}

