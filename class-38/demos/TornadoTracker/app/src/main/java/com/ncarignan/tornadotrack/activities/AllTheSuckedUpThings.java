package com.ncarignan.tornadotrack.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuckedUpThing;
import com.ncarignan.tornadotrack.R;
import com.ncarignan.tornadotrack.adapters.SuckedUpThingAdapter;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class AllTheSuckedUpThings extends AppCompatActivity {
    static int IMAGE_LOADED_MSG = 10;
    Handler handler;
    static String TAG = "ntornado.allsut";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_the_sucked_up_things);


        MutableLiveData<List<SuckedUpThing>> suckedUpThingList = new MutableLiveData<>(new ArrayList<>());
//        TODO: Create a recyclerview that holds the images
        RecyclerView rv = findViewById(R.id.recyclerViewSuckedUpThings);
        rv.setAdapter(new SuckedUpThingAdapter(suckedUpThingList.getValue()));
        rv.setLayoutManager(new LinearLayoutManager(this));

        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == IMAGE_LOADED_MSG){
                    Log.i(TAG, "handleMessage: " + msg.arg1);
                   rv.getAdapter().notifyItemChanged(msg.arg1);
                }
            }
        };




        suckedUpThingList.observe(this, new Observer<List<SuckedUpThing>>() {
            @Override
            public void onChanged(List<SuckedUpThing> suckedUpThings) {
                rv.getAdapter().notifyDataSetChanged();
            }
        });


//        TODO: get all the sucked up things
        Amplify.API.query(
                ModelQuery.list(SuckedUpThing.class),
                r ->{
                    List<SuckedUpThing> oldList = suckedUpThingList.getValue();
                    int counter = 0;
                    for(SuckedUpThing sUT: r.getData()){
                        oldList.add(sUT);
                        goGetTheImage(sUT, counter);
                        counter++;
                    }
                    suckedUpThingList.postValue(oldList);
                },
                r ->{}
        );


//        TODO: load all the images in a multithreaded way
//        TODO: constraining image size
    }

    void goGetTheImage(SuckedUpThing sUT, int indexOfSUT){
        Amplify.Storage.downloadFile(
              sUT.getId(),
              new File(getApplicationContext().getFilesDir(), "sUT" + sUT.getId()),
              r -> {
                  sUT.bitmap = BitmapFactory.decodeFile(r.getFile().getPath());
                  Message m = new Message();
                  m.arg1 = indexOfSUT;
                  handler.sendMessage(m);
              },
              r -> {}
        );
    }
}