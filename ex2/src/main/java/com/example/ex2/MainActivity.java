
package com.example.ex2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //start state testing configurations:
    private static final boolean STATE_TEST =true; // print to logcat the states.
    private static final String TAG = "MyActivity";

    private static Integer index = 0; // the order of state

    // state test method need to call in each state method:
    private void stateTest() {
        if (STATE_TEST) {
            StackTraceElement[] ste = Thread.currentThread().getStackTrace();
            String methodName=ste[3].getMethodName();

            // on start of change orientation "Pressed"
            if (isChangingConfigurations() && methodName.equalsIgnoreCase("onPause")) index=0;


            Log.i(TAG, "order: "+index.toString() + " name: "+ methodName);
            index++;

            // end of other changes (first time , back,home)
            if (!isChangingConfigurations() && index==3) index=0;
        }

    }


    // on home pressed
    @Override
    protected void onUserLeaveHint() {
        index=0;
        super.onUserLeaveHint();
    }
    // on back pressed
    @Override
    public void onBackPressed() {
        index=0;
        super.onBackPressed();
    }

    // end state testing configurations.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateTest();
    }


    @Override
    protected void onResume() {
        super.onResume();
        stateTest();
    }

    @Override
    protected void onStart() {
        super.onStart();
        stateTest();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView tv = (TextView) (findViewById(R.id.sol));
        outState.putString("sol",tv.getText().toString());
        super.onSaveInstanceState(outState);
        stateTest();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stateTest();

    }

    @Override
    protected void onStop() {
        super.onStop();
        stateTest();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView tv = (TextView) (findViewById(R.id.sol));
        tv.setText(savedInstanceState.getString("sol"));
        stateTest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stateTest();
    }


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
            toastMsg("missing number");
        }
    }


    // calculator methods:

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
            toastMsg("missing number");
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
            toastMsg("missing number");
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
           Float res= num1 / num2;
           if (res.isInfinite() || res.isNaN()) {
               tv.setText("");
               toastMsg("Divide exception: "+res.toString());
           }
           else tv.setText(Float.toString(num1 / num2));
        } catch (Exception e) {
            toastMsg("missing number");
            tv.setText("");
        }



    }
    // for hide the keyboard (use after call onclick button methods)
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                findViewById(android.R.id.content).getWindowToken(), 0);
    }
    private void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}