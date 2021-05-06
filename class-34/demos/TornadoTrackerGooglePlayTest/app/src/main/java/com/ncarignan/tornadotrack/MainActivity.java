package com.ncarignan.tornadotrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.aws.GsonVariablesSerializer;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.SimpleGraphQLRequest;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Tornado;
import com.ncarignan.tornadotrack.activities.AddTornado;
import com.ncarignan.tornadotrack.activities.TornadoDetailActivity;
import com.ncarignan.tornadotrack.adapters.TornadoAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "ntornado.main";

//    declare the string joiner here and use that
//    Declare a list of tornados
    public List<Tornado> tornados = new ArrayList<>();

    Handler mainThreadHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Load and show Tornados

        RecyclerView recyclerView = findViewById(R.id.tornados);
        recyclerView.setAdapter(new TornadoAdapter(
                tornados,
                vh -> {
                    Intent intent = new Intent(MainActivity.this, TornadoDetailActivity.class);
                    intent.putExtra("tornadoId", vh.tornado.getId());
                    startActivity(intent);
                }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mainThreadHandler = new Handler(this.getMainLooper()){ //this.getMainLooper gets the mainui thread
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
//                Log.i(TAG, "handleMessage: hit second handler");
                if(msg.what == 1){
                    StringJoiner sj = new StringJoiner(", ");
                    for(Tornado t : tornados){
                        sj.add(t.getName());
                    }

                    ((TextView) findViewById(R.id.allTheTornados)).setText(sj.toString());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        };

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
        } catch (AmplifyException e) {
            e.printStackTrace();
        }

        Amplify.API.query(
                ModelQuery.list(Tornado.class),
                response -> {
//                    StringJoiner sj = new StringJoiner(", ");
                    for(Tornado t : response.getData()){
//                        Log.i(TAG, "Tornado: " + t.getName());
//                        sj.add(t.getName());
                        tornados.add(t);
                    }
                    mainThreadHandler.sendEmptyMessage(1); // all the tornados are loaded
                },
                response -> {
//                    Log.i(TAG, "retrievingTornados: " + response.toString())
                }
        );

        ((Button) findViewById(R.id.addTornadoButton)).setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this, AddTornado.class);
            startActivity(intent);
        });


//        Code Review
//        Amplify.API.query(
//                getTornadoRequest("Clarissa"),
//                r -> Log.i(TAG, r.toString()),
//                r -> Log.i(TAG, "onCreate: " + r.toString())
//        );

        Amplify.API.query(
                ModelQuery.list(Tornado.class, Tornado.NAME.contains("Cla")),
                r -> {
//                    Log.i(TAG, r.toString())
                },
                r -> {
//                    Log.i(TAG, "onCreate: " + r.toString())
                }
        );



    }

    @Override
    protected void onResume() {
        super.onResume();

//        mainThreadHandler.sendEmptyMessage(1);

    }

    private GraphQLRequest<Tornado> getTornadoRequest(String name) {
        String document = "query getTornadoByName($name: String!) { "
                + "getTornadoByName(name: $name) { "
                + "id "
                + "name "
                + "latitude "
                + "longitude "
                + "category "
                + "}"
                + "}";
        return new SimpleGraphQLRequest<>(
                document,
                Collections.singletonMap("id", name),
                Tornado.class,
                new GsonVariablesSerializer());
    }
}