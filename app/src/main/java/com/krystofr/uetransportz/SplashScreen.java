package com.krystofr.uetransportz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    public int Time_DelayForRequest = 600;
    public int splashScreentime = 10 * Time_DelayForRequest;
    Thread background;
    //AppSharedPreferences appSharedPreferences;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(splashScreentime);

                    Intent splashIntent = new Intent(SplashScreen.this, WelcomeActivity.class);
                    startActivity(splashIntent);
                    finish();
                    // After 5 seconds redirect to another intent
                    // overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    //Remove activity_selection_list

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        background.start();
    }
}
