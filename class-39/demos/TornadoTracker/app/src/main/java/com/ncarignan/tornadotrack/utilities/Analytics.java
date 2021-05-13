package com.ncarignan.tornadotrack.utilities;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.analytics.AnalyticsProperties;
import com.amplifyframework.analytics.UserProfile;
import com.amplifyframework.core.Amplify;

import java.time.Duration;
import java.util.Date;

public class Analytics {
    private static Analytics analytics;

    public static Analytics getAnalytics() {
        if(analytics == null) {
            analytics = new Analytics();

            Amplify.Analytics.registerGlobalProperties(
                    AnalyticsProperties.builder()
                            .add("platform", "ntornado android app")
                            .add("priceOfAnOrange", 27)
                            .build()
            );

            // Track the user
            if(Amplify.Auth.getCurrentUser() != null){
                String id = Amplify.Auth.getCurrentUser().getUserId();
                UserProfile userProfile = UserProfile.builder()
                        .email(Amplify.Auth.getCurrentUser().getUsername())
                        .build();
                Amplify.Analytics.identifyUser(id, userProfile);
            }
        }
        return analytics;
    }

    public void trackTimeSpentOnPage(Date start, Date end, String activity){
        // start a timer, have another method in onPause that fires off the actual "finished timer"
        // Take in a start time, take in an end time

        long duration = end.getTime() - start.getTime();
        int seconds = (int) (duration / 1000);
        Amplify.Analytics.recordEvent(
                AnalyticsEvent.builder()
                        .name("durationOnActivity")
                        .addProperty("activity", activity)
                        .addProperty("duration on this activity", seconds)
                        .build()
        );
    }
}
