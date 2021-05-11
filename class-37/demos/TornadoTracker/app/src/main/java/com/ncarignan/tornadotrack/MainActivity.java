package com.ncarignan.tornadotrack;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.aws.GsonVariablesSerializer;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.SimpleGraphQLRequest;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Tornado;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.ncarignan.tornadotrack.activities.AddTornado;
import com.ncarignan.tornadotrack.activities.CognitoLoginActivity;
import com.ncarignan.tornadotrack.activities.CognitoSignupActivity;
import com.ncarignan.tornadotrack.activities.TornadoDetailActivity;
import com.ncarignan.tornadotrack.adapters.TornadoAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    static int FILE_UPLOAD_REQUEST_CODE = 321;

    public static String TAG = "ntornado.main";

//    declare the string joiner here and use that
//    Declare a list of tornados
    public List<Tornado> tornados = new ArrayList<>();

    Handler mainThreadHandler;


    void signupCognito(){
        Amplify.Auth.signUp(
                "nwaterpolo@gmail.com",
                "password",
                AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), "nick.carignan@codefellows.com")
                        .userAttribute(AuthUserAttributeKey.nickname(), "nich")
                        .build(),
                r ->Log.i(TAG, "signup success: " + r.toString()),
                r -> Log.i(TAG, "signup failure: " + r.toString())
        );
    }

    void signupConfirmationCognito(String username, String confirmationNum){
        Amplify.Auth.confirmSignUp(
                username,
                confirmationNum,
                r -> Log.i(TAG, "signupConfirmationCognito: " + r.toString()),
                r -> Log.i(TAG, "signupConfirmationCognito: " + r.toString())
        );
    }

    void loginCongnito(String username, String password){
        Amplify.Auth.signIn(
                username,
                password,
                r -> Log.i(TAG, "loginCongnito success: " + r.toString()),
                r -> Log.i(TAG, "loginCongnito failure: " + r.toString())
        );
    }

//    789518

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAmplify();

        // =================== s3 stuff =======================

//        saveMockFileToS3();
//        getFileFromPhone();
        downloadFileFromS3("testScoobyPic");
//        loadMockFileFroms3()
//      saveRealFileToS3();
//      loadAndDisplayRealFileFromS3();




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

//                    ((TextView) findViewById(R.id.textUsername)).setText(sj.toString());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        };



        Amplify.API.query(
                ModelQuery.list(Tornado.class),
                response -> {
//                    StringJoiner sj = new StringJoiner(", ");
                    for(Tornado t : response.getData()){
//                        Log.i(TAG, "Tornado: " + t.getName());
//                        sj.add(t.getName());
                        tornados.add(t);
                    }
                    Collections.sort(
                            this.tornados,
                            (a,b) -> {
                                double first = a.getLatitude() + a.getLongitude();
                                double second = b.getLatitude() + b.getLongitude();
                                if(first > second){
                                    return 1;
                                } else if(first < second){
                                    return -1;
                                } else {
                                    return 0;
                                }
                            });
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

        ((Button) findViewById(R.id.loginPageButton)).setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this, CognitoLoginActivity.class);
            startActivity(intent);
        });

        ((Button) findViewById(R.id.signupPageButton)).setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this, CognitoSignupActivity.class);
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

    void saveMockFileToS3(){
        File file = new File(getApplicationContext().getFilesDir(), "Scooby");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.append("zoinks");
            bw.close();

            Amplify.Storage.uploadFile(
                    "Scooby",
                    file,
                    r -> {
//                        r.getKey();
                    },
                    r -> {}
            );
        } catch(Exception e){
            Log.e(TAG, "saveMockFileToS3: " + e.toString());
        }
    }





    void configureAmplify(){
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
        } catch (AmplifyException e) {
            e.printStackTrace();
        }
    }

    void getFileFromPhone(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // limit this to pictures // this can be used for just .jpg
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{".jpg", ".png"}); // this is for multiple types
        startActivityForResult(intent, FILE_UPLOAD_REQUEST_CODE);//will not be perfect
        //
//        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q) // this says we can only do this method in api version 29
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){ // may be deprecated
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FILE_UPLOAD_REQUEST_CODE){
            //retrieve the file and upload it
            File f = new File(getApplicationContext().getFilesDir(), "uploadingfile");
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                FileUtils.copy(inputStream, new FileOutputStream(f));
                saveFileToS3(f, "testScoobyPic");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                URI uri = new URI(
//                        data.getData().getScheme(),
//                        data.getData().getPath(),
//                        data.getData().getHost(),
//                        data.getData().getFragment());
//
//                File file = new File(uri);
//
//                saveFileToS3(file, "firstFileScoobyPic");
//
//
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
        }
    }

    void saveFileToS3(File file, String filename){
        Amplify.Storage.uploadFile(
                filename,
                file,
                r -> {
                },
                r -> {}
        );
    }

    void downloadFileFromS3(String key){
        Amplify.Storage.downloadFile(
                key,
                new File(getApplicationContext().getFilesDir(), key),
                r ->{
                    ImageView i = findViewById(R.id.scoobyImage);
                    i.setImageBitmap(BitmapFactory.decodeFile(r.getFile().getPath()));
                },
                r -> {});
    }

    @Override
    protected void onResume() {
        super.onResume();

//        mainThreadHandler.sendEmptyMessage(1);

//        Display username
        AuthUser authuser = Amplify.Auth.getCurrentUser();
        if(authuser != null) {
            String username = authuser.getUsername();
            ((TextView) findViewById(R.id.textUsername)).setText(username);

        }

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