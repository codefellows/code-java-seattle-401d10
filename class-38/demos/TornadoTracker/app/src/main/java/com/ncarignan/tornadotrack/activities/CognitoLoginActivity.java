package com.ncarignan.tornadotrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.ncarignan.tornadotrack.R;

public class CognitoLoginActivity extends AppCompatActivity {

    String TAG = "ntornado.signup";

    Handler signinHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cognito_login);

        signinHandler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    Log.i(TAG, "handleMessage: success signin");
                    Toast.makeText(CognitoLoginActivity.this, "signed in", Toast.LENGTH_LONG).show();
                } else if(msg.what == 2){
                    Log.i(TAG, "handleMessage: fail signin");

                    Toast.makeText(CognitoLoginActivity.this, "sign in failed", Toast.LENGTH_LONG).show();
                }
            }
        };

        ((Button) findViewById(R.id.buttonLogin)).setOnClickListener(v -> {
            String username = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
            String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
            Log.i(TAG, "onCreate: starting signin");
            Amplify.Auth.signIn(
                    username,
                    password,
                    r -> signinHandler.sendEmptyMessage(1),
                    r -> signinHandler.sendEmptyMessage(2)
            );
        });
    }
}