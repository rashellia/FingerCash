package com.fingercash.app.fingercash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    public Button buttonp;
    private TextView textView15;
    private SessionManager session;
    public void initpr(){
        buttonp=(Button)findViewById(R.id.buttonp);
        buttonp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vp) {
                Intent p = new Intent(Profile.this, HomePage.class);

                startActivity(p);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView15 = (TextView) findViewById(R.id.textView15);
        session = new SessionManager(getApplicationContext());

        //Get user data from SessionManager
        HashMap<String, String> user = session.getUserDetails();

        //Displaying username
        String name = user.get(SessionManager.KEY_NAME);
        textView15.setText(Html.fromHtml("</b>" + name + "</b>"));

        initpr();
    }
}
