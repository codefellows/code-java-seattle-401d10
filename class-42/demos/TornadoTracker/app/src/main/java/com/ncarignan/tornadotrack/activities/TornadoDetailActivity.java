package com.ncarignan.tornadotrack.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.ncarignan.tornadotrack.utilities.AmplifyConfig;

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

    Spinner spinner;
    List<Tornado> tornados;
    Handler h;
    Tornado[] tornado = new Tornado[1];
    List<SuckedUpThing> things = new ArrayList<>();
    RecyclerView rv;
    Handler handler;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tornado_detail);

        AmplifyConfig.configureAmplify(getApplication(), getApplicationContext());

        setupSpinner();
        setupHandler();
        queryTornados();
        setupRecyclerView();
        setupTheRest();

        checkDataFromIntentFilter();
    }

    void checkDataFromIntentFilter(){
        // To share text: setup the intent-filter in your androidManifest.XML on the activity of your choice
        // Read the intent, check for type "text/plain" when that activity loads, get the text

        // To share image: setup the intent-filter in your androidManifest.XML on the activity of your choice
        // Read the intent, check for type "image/*" when that activity loads, get the data

        Intent intent = getIntent();
        if(intent.getType().equals("text/plain")){
            //preload the sent text into the sucked up thing form

            String text = intent.getStringExtra(Intent.EXTRA_TEXT);

            EditText et = findViewById(R.id.editTextSuckedUpThingName);
            et.setText(text);


        } else if(intent.getType().startsWith("image/")){ // image/jpg
            //preload the image into the instance variable File and the instance variabl e Bitmap
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Uri uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                loadImageFromIntentUsingUri(uri);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    void loadImageFromIntentUsingUri(Uri uri){
        fileToUpload = new File(getApplicationContext().getFilesDir(), "tempFile");
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            FileUtils.copy(inputStream, new FileOutputStream(fileToUpload));

            ImageView i = findViewById(R.id.imageViewSuckedUpThingPreview);
            i.setImageBitmap(BitmapFactory.decodeFile(fileToUpload.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_IMAGE_CODE) {
            //retrieve the file and upload it
            loadImageFromIntentUsingUri(data.getData());
        }
    }

    void setupSpinner() {
        spinner = findViewById(R.id.spinnerTornadoNames);
        tornados = new ArrayList<>();
    }
    void setupHandler(){
        h = new Handler(getMainLooper()){
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
    }
    void queryTornados(){
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
    }

    void setupRecyclerView(){
        rv = findViewById(R.id.suckedUpThingsRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new SuckedUpThingAdapter(things));
    }

    void setupTheRest(){
        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what == 99){
                    rv.getAdapter().notifyDataSetChanged();
                }
            }
        };

        id = getIntent().getStringExtra("tornadoId");
//        Log.i(TAG, "onCreate: " + id);

        Amplify.API.query(
                ModelQuery.get(Tornado.class, id),
                r -> {
                    tornado[0] = r.getData();
                    if(tornado[0] != null){
                        for(SuckedUpThing thing : tornado[0].getSuckedUpThings()){
                            things.add(thing);
                        }
//                    rv.getAdapter().notifyDataSetChanged();
                        handler.sendEmptyMessage(99);
                    }


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
}