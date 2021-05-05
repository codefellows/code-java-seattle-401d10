package com.ncarignan.tornadotracker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuckedUpThing;
import com.amplifyframework.datastore.generated.model.Tornado;
import com.ncarignan.tornadotracker.R;
import com.ncarignan.tornadotracker.adapters.SuckedUpThingAdapter;

import java.util.ArrayList;
import java.util.List;

public class TornadoDetailActivity extends AppCompatActivity {

    static String TAG = "ntornado.tdetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tornado_detail);

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
        Log.i(TAG, "onCreate: " + id);

        Amplify.API.query(
                ModelQuery.get(Tornado.class, id),
                r -> {
                    tornado[0] = r.getData();
                    for(SuckedUpThing thing : tornado[0].getSuckedUpThings()){
                        things.add(thing);
                    }
//                    rv.getAdapter().notifyDataSetChanged();
                    handler.sendEmptyMessage(99);
                },

                r -> {}
        );




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
//                    .
        });
    }
}