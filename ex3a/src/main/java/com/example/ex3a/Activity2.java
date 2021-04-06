package com.example.ex3a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void sendBack(View v)
    {
        Intent intent = new Intent();
        EditText fn = (EditText) (findViewById(R.id.fn));
        EditText ln = (EditText) (findViewById(R.id.ln));
        RadioGroup rg = (RadioGroup) (findViewById(R.id.rg));
        RadioButton rb =(RadioButton)findViewById( rg.getCheckedRadioButtonId());
        String checked=  rb.getText().toString();

        String gender;
        if(checked.compareTo("Male")==0) gender="Mr.";
        else gender ="Ms.";

        intent.putExtra("fn",fn.getText().toString());
        intent.putExtra("ln",ln.getText().toString());
        intent.putExtra("g",gender);
        setResult(RESULT_OK,intent);
        finish();
    }
}