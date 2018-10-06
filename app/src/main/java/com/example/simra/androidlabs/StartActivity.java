package com.example.simra.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

    protected static final String ACTIVITY_NAME= "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button Button = (Button)findViewById(R.id.button);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,ListItemsActivity.class);
                startActivityForResult(intent,50);
            }
        });



    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
       Context context = getApplicationContext();
      if((requestCode==50) &&(resultCode==Activity.RESULT_OK)){
            Log.i(ACTIVITY_NAME,"Returned to the StartActivity.onActivityResult");
            String messagePassed = data.getStringExtra("response");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, messagePassed, duration);
            toast.show();





        }
    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume()");

        }

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
        Log.i(ACTIVITY_NAME,"In onDestroy)");
    }













}
