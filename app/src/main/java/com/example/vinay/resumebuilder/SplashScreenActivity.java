package com.example.vinay.resumebuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by vinay thoutam on 17-02-2017.
 */

public class SplashScreenActivity extends Activity {
    Thread timer;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
