
package com.example.ex4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

        public int numberOfZeros=0;
        public float masterResult =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText tvU = (EditText) findViewById(R.id.op1);
        EditText tvL = (EditText) findViewById(R.id.op2);
        HandleNumber handlerNumber=new HandleNumber();
        handlerNumber.onTextChanged("ss",1,2,3);
        tvU.addTextChangedListener(handlerNumber);
        tvL.addTextChangedListener(handlerNumber);
        //-------------
        ((SeekBar)findViewById(R.id.sb)).setOnSeekBarChangeListener(this);
        //-------------
        findViewById(R.id.clr).setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                Button btn = (Button)arg0;
                EditText ed1 = (EditText) findViewById(R.id.op1);
                EditText ed2 = (EditText) findViewById(R.id.op2);
                TextView ed3 = (TextView) findViewById(R.id.sol);

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");

            }
        });





    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView tv = (TextView) (findViewById(R.id.sol));
        outState.putString("sol",tv.getText().toString());
        super.onSaveInstanceState(outState);
    }




    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView tv = (TextView) (findViewById(R.id.sol));
        tv.setText(savedInstanceState.getString("sol"));
    }



    public void plusClicked(View v) {
        hideSoftKeyboard();
        EditText et = (EditText) (findViewById(R.id.op1));
        TextView tv = (TextView) (findViewById(R.id.sol));
        try {
            float num1 = Float.valueOf(et.getText().toString());
            et = (EditText) (findViewById(R.id.op2));
            float num2 = Float.valueOf(et.getText().toString());

            //tv.setText(Float.toString(num1 + num2));
            masterResult=num1 + num2;
            solutionFormatter(masterResult);
        } catch (Exception e) {
            toastMsg("missing number");
        }
    }


    // calculator methods:

    public void minusClicked(View v) {
        hideSoftKeyboard();
        EditText et = (EditText) (findViewById(R.id.op1));
        try {
            float num1 = Float.valueOf(et.getText().toString());
            et = (EditText) (findViewById(R.id.op2));
            float num2 = Float.valueOf(et.getText().toString());
            masterResult=num1-num2;
            solutionFormatter(masterResult);

        } catch (Exception e) {
            toastMsg("missing number");
        }
    }

    public void multiClicked(View v) {
        hideSoftKeyboard();
        EditText et = (EditText) (findViewById(R.id.op1));
        try {
            float num1 = Float.valueOf(et.getText().toString());
            et = (EditText) (findViewById(R.id.op2));
            float num2 = Float.valueOf(et.getText().toString());
            masterResult=num1 * num2;
            solutionFormatter(num1 * num2);
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
           else {
                masterResult= num1 / num2;
               solutionFormatter(num1/num2);
           }
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser)
        {
        numberOfZeros= progress/20;
        TextView  tv = (TextView) (findViewById(R.id.sol));

        if(!tv.getText().toString().equals(""))
        solutionFormatter(masterResult);

        TextView  Etv = (TextView) (findViewById(R.id.eTv));
        Etv.setText(String.format("Example %."+numberOfZeros+"f",123.0));

        }
    }

    private  void solutionFormatter(float f)
    {
        TextView tv = (TextView) (findViewById(R.id.sol));
        tv.setText(String.format("%."+numberOfZeros+"f",f));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    private class HandleNumber implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EditText tvU = (EditText) findViewById(R.id.op1);
            EditText tvL = (EditText) findViewById(R.id.op2);

           String s1= tvU.getText().toString();
           String s2=tvL.getText().toString();

            Button bDiv =(Button) findViewById(R.id.button);
            Button bMult =(Button) findViewById(R.id.button2);
            Button bSub =(Button) findViewById(R.id.button3);
            Button bAdd =(Button) findViewById(R.id.button4);

           if(s1.equals("") || s2.equals(""))
           {
               bDiv.setEnabled(false);
               bMult.setEnabled(false);
               bSub.setEnabled(false);
               bAdd.setEnabled(false);
           }

           else
               {
                   bMult.setEnabled(true);
                   bSub.setEnabled(true);
                   bAdd.setEnabled(true);

                   if((Float.valueOf(s2)).equals(new Float(0)))
                   {
                       bDiv.setEnabled(false);
                   }
                   else
                       {
                           bDiv.setEnabled(true);
                       }
               }




        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    }

}