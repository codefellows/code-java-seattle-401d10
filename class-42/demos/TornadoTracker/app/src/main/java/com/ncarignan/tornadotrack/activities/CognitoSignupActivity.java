package com.ncarignan.tornadotrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.ncarignan.tornadotrack.R;

public class CognitoSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        String TAG = "ntornado.signup";

        ((Button) findViewById(R.id.buttonLogin)).setOnClickListener(v -> {
            String username = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
            String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();

            Amplify.Auth.signUp(
                    username, // happens to also be an email
                    password,
                    AuthSignUpOptions.builder()
                            .build(),
                    r -> {
                        Intent intent = new Intent(CognitoSignupActivity.this, CognitoSignupConfrimationActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    },
                    r -> Log.i(TAG, "signup failure: " + r.toString())
            );
        });
    }
}