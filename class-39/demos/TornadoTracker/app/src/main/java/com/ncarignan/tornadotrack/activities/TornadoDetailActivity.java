package com.ncarignan.tornadotrack.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuckedUpThing;
import com.amplifyframework.datastore.generated.model.Tornado;
import com.ncarignan.tornadotrack.R;
import com.ncarignan.tornadotrack.adapters.SuckedUpThingAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TornadoDetailActivity extends AppCompatActivity {

    static String TAG = "ntornado.tdetail";
    static int GET_IMAGE_CODE = 97;
    File fileToUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tornado_detail);

        Spinner spinner = findViewById(R.id.spinnerTornadoNames);
        List<Tornado> tornados = new ArrayList<>();

        Handler h = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 32) {
                    ArrayAdapter<Tornado> aAT = new ArrayAdapter<>(
                            TornadoDetailActivity.this,
                            R.layout.support_simple_spinner_dropdown_item,
                            tornados);

//                    aAT.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                    spinner.setAdapter(aAT);
                    Log.i(TAG, "handleMessage:"  + ((Tornado)spinner.getSelectedItem()).toString());
                }

            }
        };

        Amplify.API.query(
                ModelQuery.list(Tornado.class),
                r -> {
                    for(Tornado t : r.getData()){
                        tornados.add(t);
                    }
                    h.sendEmptyMessage(32);
                },
                r -> {}
        );



        Tornado[] tornado = new Tornado[1]; // [Tornado] or [null] // create an array with one index
        List<SuckedUpThing> things = new ArrayList<>();

        RecyclerView rv = findViewById(R.id.suckedUpThingsRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new SuckedUpThingAdapter(things));

        Handler handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what == 99){
                    rv.getAdapter().notifyDataSetChanged();
                }
            }
        };





        String id = getIntent().getStringExtra("tornadoId");
//        Log.i(TAG, "onCreate: " + id);

        Amplify.API.query(
                ModelQuery.get(Tornado.class, id),
                r -> {
                    tornado[0] = r.getData();
                    for(SuckedUpThing thing : tornado[0].getSuckedUpThings()){
                        things.add(thing);
                    }
//                    rv.getAdapter().notifyDataSetChanged();
                    handler.sendEmptyMessage(99);

                    // TODO: When we first get our tornado (and its sucked up things) we should load the images
                    // Build a relationship between a SuckedUpThing and a File
//                    Could be a hashMap : idOfSuckedUpThing -> File
//                    should NOT be a List: index -> File  (when we start deleting, the indexes shift)
//                    RecyclerView should reference that hashtable when they load
                },

                r -> {}
        );




        ((Button) findViewById(R.id.buttonPickSuckedUpThingImage)).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, GET_IMAGE_CODE);
        });

        ((Button) findViewById(R.id.buttonAddSuckedUpThing)).setOnClickListener(v -> {
            String name = ((EditText) findViewById(R.id.editTextSuckedUpThingName)).getText().toString();
            SuckedUpThing sut = SuckedUpThing.builder()
                    .tornado(tornado[0])
                    .name(name)
                    .build();
            things.add(sut);
            rv.getAdapter().notifyItemInserted(things.size() - 1);
            Amplify.API.mutate(
                    ModelMutation.create(sut),
                    r -> {},
                    r -> {}
            );

            Amplify.Storage.uploadFile(
                    sut.getId(), // if we wanted more than one image getId + "/" + 1
                    fileToUpload,
                    r -> {
                        Log.i(TAG, "onCreate: " + r.toString());
                    },
                    r -> {
                        Log.i(TAG, "onCreate: " + r.toString());
                    }
            );
//                    .
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GET_IMAGE_CODE) {
            //retrieve the file and upload it
            fileToUpload = new File(getApplicationContext().getFilesDir(), "tempFile");
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                FileUtils.copy(inputStream, new FileOutputStream(fileToUpload));

                ImageView i = findViewById(R.id.imageViewSuckedUpThingPreview);
                i.setImageBitmap(BitmapFactory.decodeFile(fileToUpload.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ;
}