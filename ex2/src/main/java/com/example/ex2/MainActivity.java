
package com.example.ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private static Integer index = 0;

    public void plusClicked(View v) {
        hideSoftKeyboard();
        EditText et = (EditText) (findViewById(R.id.op1));
        TextView tv = (TextView) (findViewById(R.id.sol));
        try {
            float num1 = Float.valueOf(et.getText().toString());
            et = (EditText) (findViewById(R.id.op2));
            float num2 = Float.valueOf(et.getText().toString());

            tv.setText(Float.toString(num1 + num2));
        } catch (Exception e) {
            tv.setText("missing number");
        }
    }

    public void minusClicked(View v) {
        hideSoftKeyboard();
        EditText et = (EditText) (findViewById(R.id.op1));
        TextView tv = (TextView) (findViewById(R.id.sol));
        try {
            float num1 = Float.valueOf(et.getText().toString());
            et = (EditText) (findViewById(R.id.op2));
            float num2 = Float.valueOf(et.getText().toString());

            tv.setText(Float.toString(num1 - num2));
        } catch (Exception e) {
            tv.setText("missing number");
        }
    }

    public void multiClicked(View v) {
        hideSoftKeyboard();
        EditText et = (EditText) (findViewById(R.id.op1));
        TextView tv = (TextView) (findViewById(R.id.sol));
        try {
            float num1 = Float.valueOf(et.getText().toString());
            et = (EditText) (findViewById(R.id.op2));
            float num2 = Float.valueOf(et.getText().toString());

            tv.setText(Float.toString(num1 * num2));
        } catch (Exception e) {
            tv.setText("missing number");
        }
    }

    public void divClicked(View v) {
        hideSoftKeyboard();
        EditText et = (EditText) (findViewById(R.id.op1));
        TextView tv = (TextView) (findViewById(R.id.sol));
        try {
            float num1 = Float.valueOf(et.getText().toString());
            et = (EditText) (findViewById(R.id.op2));
            float num2 = Float.valueOf(et.getText().toString());
            tv.setText(Float.toString(num1 / num2));
        } catch (Exception e) {
            tv.setText("missing number");
        }



    }
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), 0);
    }
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
    public void onSaveInstanceState( Bundle outState) {
        TextView tv = (TextView) (findViewById(R.id.sol));
        outState.putString("sol",tv.getText().toString());
        super.onSaveInstanceState(outState);
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
        TextView tv = (TextView) (findViewById(R.id.sol));
        tv.setText(savedInstanceState.getString("sol"));
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