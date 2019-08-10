package com.fingercash.app.fingercash;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    private EditText username, password;
    private Button login, cancel;
    private ProgressDialog lDialog;
    private SessionManager session;
    int flag=0;
    JSONParser jsonParser = new JSONParser();
    private static String url = "http://wooded-acid.000webhostapp.com/loginapp.php";
    private static final String TAG_SUCCESS = "success";
    protected static final int REQUEST_SIGNUP = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());

        login = (Button) findViewById(R.id.btnLogin2);
        cancel = (Button) findViewById(R.id.button4);
        username = (EditText)findViewById(R.id.etName);
        password = (EditText)findViewById(R.id.etPassword);

        //Link to go to SignupPage.java
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });
        //Close link

        //Check if user is already logged in
        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()) {
            //If yes, user go directly to MainMenu Page
            Intent intent = new Intent(LoginPage.this, HomePage.class);
            startActivity(intent);
            finish();
        }
        //Checking finished

        //Checking when user click login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check the required fields
                if(username.length() == 0 || password.length() == 0)
                {
                    Toast.makeText(LoginPage.this, "Please fill the blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check connection
                if(!isOnline(LoginPage.this))
                {
                    Toast.makeText(LoginPage.this,"No network connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                //If everything is fine, start logging in
                new loginAccess().execute();
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
        }); //Close login button
    } //Close void OnCreate

    class loginAccess extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Displaying Progress Dialog
            lDialog = new ProgressDialog(LoginPage.this);
            lDialog.setMessage("Logging in...");
            lDialog.setIndeterminate(false);
            lDialog.setCancelable(true);
            lDialog.show();
        }

        //Checking database for user data
        @Override
        protected String doInBackground(String... arg0) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String usernm = username.getText().toString();
            String passw = password.getText().toString();
            params.add(new BasicNameValuePair("username", usernm));
            params.add(new BasicNameValuePair("password", passw));
            JSONObject json = jsonParser.makeHttpRequest(url,"POST", params);
            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1)
                {
                    lDialog.dismiss();
                    //User successfully logged in
                    session.loginSession(usernm); //Save the session, user keep logged in
                    flag=0;
                    //Move to main menu page



                    Intent i = new Intent(getApplicationContext(), HomePage.class);
                    i.putExtra("username",usernm);
                    i.putExtra("password",passw);
                    startActivity(i);
                    finish();
                }
                else if (success == 2)
                {
                    //User input wrong password
                    flag=2;
                }

                else
                {
                    //User input wrong username
                    flag=1;
                }
            } catch (JSONException e) { //Failed to connect with json
                e.printStackTrace();
            }
            return null;
        }

        //If user can login but no data match in database
        @Override
        protected void onPostExecute(String file_url) {
            lDialog.dismiss();
            if(flag==1){
                Toast.makeText(LoginPage.this,"Wrong username", Toast.LENGTH_SHORT).show(); }
            else if(flag==2){
                Toast.makeText(LoginPage.this,"Wrong password", Toast.LENGTH_SHORT).show(); }
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
            Toast.makeText(LoginPage.this, "Press again to exit FingerCash", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run(){
                    exit = false;
                }
            }, 3000);
        }
    } //Exit onBackPressed
}

