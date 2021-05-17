package com.ncarignan.tornadotrack.utilities;

import android.app.Application;
import android.content.Context;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class AmplifyConfig {
    public static void configureAmplify(Application app, Context context){
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(app));
            Amplify.configure(context);

        } catch (AmplifyException e) {
            e.printStackTrace();
        }
    }
}
