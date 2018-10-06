package com.example.simra.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

    protected static final String ACTIVITY_NAME= "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // a reference to the button in activity_login.xml
        Button button2 =(Button)findViewById(R.id.button2);
        final EditText mailText = (EditText)findViewById(R.id.editText);
        SharedPreferences sp = getSharedPreferences("SaveData",Context.MODE_PRIVATE);


        final SharedPreferences.Editor edit = sp.edit();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String storedEmail = mailText.getText().toString();
                edit.putString("Default", storedEmail);
                edit.commit();
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }

        });
        mailText.setText(sp.getString("Default", "none@none.com"));
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume()");}

    @Override
    protected void onStart(){
        super.onStart();
            Log.i(ACTIVITY_NAME,"In onStart()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause()");
    }

    @Override
    protected void  onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop()");
    }

    @Override
    protected void onDestroy(){

        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy()");
    }

}
