package com.fingercash.app.fingercash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckBalance extends AppCompatActivity {

    public Button button;
    public void init8(){
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vcb) {
                Intent cb = new Intent(CheckBalance.this, HomePage.class);

                startActivity(cb);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);
        init8();
    }
}
