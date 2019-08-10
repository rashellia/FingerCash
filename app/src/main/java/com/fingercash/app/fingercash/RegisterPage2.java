package com.fingercash.app.fingercash;

import android.app.ProgressDialog;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import android.widget.TextView;
import android.widget.Toast;

public class RegisterPage2 extends AppCompatActivity {
    private EditText usernm, passw, noid;
    private ProgressDialog sDialog;
    private Button butt, but;
    private SessionManager session;

    int flag=0;
    JSONParser jsonParser = new JSONParser();
    private static String url = "http://wooded-acid.000webhostapp.com/registerapp.php";
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page2);

        session = new SessionManager(getApplicationContext());
        butt = (Button) findViewById(R.id.butt);
        but = (Button) findViewById(R.id.but);
        usernm = (EditText)findViewById(R.id.editText4);
        passw = (EditText)findViewById(R.id.editText5);
        noid = (EditText)findViewById(R.id.editText9);

        //Link to go back to Login Page
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                startActivity(intent);
                finish();
            }
        });

        //Clicking signup button
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check connection
                if(!isOnline(RegisterPage2.this))
                {
                    Toast.makeText(RegisterPage2.this,"No network connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check the required fields
                else if(usernm.length() == 0)
                {
                    usernm.setError("Please fill the blank");
                }

                else if (passw.length() == 0) {
                    passw.setError("Please fill the blank");
                }

                else if (noid.length() == 0) {
                    noid.setError("Please fill the blank");
                }
                else {
                    new signupAccess().execute();
                }
            }

            //Boolean for checking connection
            private boolean isOnline(Context mContext) {
                ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting())
                {
                    return true;
                }
                return false;
            }
        });
    } //Close onCreate

    //Start the signup process
    class signupAccess extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Displaying Progress Dialog
            sDialog = new ProgressDialog(RegisterPage2.this);
            sDialog.setMessage("Registering...");
            sDialog.setIndeterminate(false);
            sDialog.setCancelable(true);
            sDialog.show();
        }

        //Checking database for user data
        @Override
        protected String doInBackground(String... arg0) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String username = usernm.getText().toString();
            String password = passw.getText().toString();
            String no_ktp = noid.getText().toString();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("no_ktp", no_ktp));
            params.add(new BasicNameValuePair("password", password));
            JSONObject json = jsonParser.makeHttpRequest(url,"POST", params);
            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1)
                {
                    sDialog.dismiss();
                    //User successfully signed up
                    session.loginSession(username); //Save the session, user keep logged in
                    flag=0;
                    //Move to main menu page


                    Intent i = new Intent(getApplicationContext(), HomePage.class);


                    i.putExtra("username",username);
                    i.putExtra("no_ktp",no_ktp);
                    i.putExtra("password",password);
                    startActivity(i);
                    finish();
                }
                else
                {
                    //User failed to signup
                    flag=1;
                }
            } catch (JSONException e) { //Failed to connect with json
                e.printStackTrace();
            }
            return null;
        }

        //If username entered already exists
        @Override
        protected void onPostExecute(String file_url) {
            sDialog.dismiss();
            if(flag==1)
                Toast.makeText(RegisterPage2.this,"Username already exists", Toast.LENGTH_SHORT).show();
        }

    } //Close class loginAccess

    //Press back button twice will exit the application
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if(exit){
            finish();
        }
        else {
            Toast.makeText(RegisterPage2.this, "Press again to exit FingerCash", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run(){
                    exit = false;
                }
            }, 3000);
        }
    }
}


