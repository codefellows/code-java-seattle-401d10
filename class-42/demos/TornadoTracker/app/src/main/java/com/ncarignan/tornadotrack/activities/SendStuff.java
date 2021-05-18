package com.ncarignan.tornadotrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ncarignan.tornadotrack.R;

import java.util.ArrayList;

public class SendStuff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_stuff);

        Intent intent = new Intent();
//         sending text
//        intent.setType("text/plain");
//        intent.setAction(Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_TEXT, "I love Ginger");
//
//        startActivity(intent);


//        sending a picture
        intent.setType("image/png");
        intent.setAction(Intent.ACTION_SEND);
        ArrayList<Uri> uris = new ArrayList<>();
//        uris.add(uri)
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        startActivity(intent);
    }
}