package com.ncarignan.tornadotrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.ncarignan.tornadotrack.MainActivity;
import com.ncarignan.tornadotrack.R;

public class CognitoSignupConfrimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cognito_signup_confrimation);

//        Intent i = getIntent();
        String username = getIntent().getStringExtra("username");

        ((Button) findViewById(R.id.buttonLogin)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String confirmationCode =
                                ((EditText) findViewById(R.id.editTextTextPassword))
                                    .getText().toString();
                        Amplify.Auth.confirmSignUp(
                                username,
                                confirmationCode,
                                r -> {
                                    startActivity(new Intent(CognitoSignupConfrimationActivity.this, MainActivity.class));
                                },
                                r -> {
                                    Toast.makeText(CognitoSignupConfrimationActivity.this, "confirmation code failed", Toast.LENGTH_LONG);
                                }
                        );
                    }
                }
        );
    }
}