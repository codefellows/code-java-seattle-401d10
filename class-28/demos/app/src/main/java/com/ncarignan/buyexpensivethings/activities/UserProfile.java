package com.ncarignan.buyexpensivethings.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.ncarignan.buyexpensivethings.R;

public class UserProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preferencesEditor = preferences.edit();

        String username = preferences.getString("username", "Guest");
        String phoneNum = preferences.getString("phoneNumber", "");
        String address = preferences.getString("address", "");

        ((TextView)findViewById(R.id.addressTextView)).setText(address);
        ((TextView)findViewById(R.id.usernameTextView)).setText(username);
        ((TextView)findViewById(R.id.phoneNumTextView)).setText(phoneNum);

        findViewById(R.id.editProfileButton).setOnClickListener(v -> {
            String username2 = ((TextView)findViewById(R.id.editTextUserName)).getText().toString();
            String phoneNum2 = ((TextView)findViewById(R.id.editTextPhoneNum)).getText().toString();
            String address2 = ((TextView)findViewById(R.id.editTextTextAddress)).getText().toString();

            preferencesEditor.putString("username", username2);
            preferencesEditor.putString("phoneNumber", phoneNum2);
            preferencesEditor.putString("address", address2);

            preferencesEditor.apply();

            ((TextView)findViewById(R.id.addressTextView)).setText(address2);
            ((TextView)findViewById(R.id.usernameTextView)).setText(username2);
            ((TextView)findViewById(R.id.phoneNumTextView)).setText(phoneNum2);
        });


    }

}