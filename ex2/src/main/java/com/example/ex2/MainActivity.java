
package com.example.ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private static Integer index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Log.i(TAG,index.toString()+ "onCreate");
        // index++;
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Log.i(TAG,index.toString()+"onResume");
        // index++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Log.i(TAG,index.toString()+"onStart");
        //index++;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //Log.i(TAG,index.toString()+"onSaveInstanceState");
        // index++;
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.i(TAG,index.toString()+"onPause");
        //index++;

    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.i(TAG,index.toString()+"onStop");
        //index++;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Log.i(TAG,index.toString()+"onRestoreInstanceState");
        //index++;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.i(TAG,index.toString()+"onDestroy");
        //index++;
    }
}