package com.pgzxc.acrasample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nullPoniter(View view) {
        String a = null;
        Log.e(TAG, "nullPoniter: " + a.length());
    }

    public void outOfBounds(View view) {
        List list = new ArrayList();
        Log.e(TAG, "outOfBounds: " + list.get(1));
    }

    public void Anr(View view) throws InterruptedException {
        Thread.sleep(100000);
    }

    public void classCast(View view) {
       String str=(String)new Object();
    }
}
