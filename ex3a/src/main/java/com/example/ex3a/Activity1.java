package com.example.ex3a;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
    }

    public void Register(View v)
    {
        Intent intent = new Intent(this,Activity2.class);
        startActivityForResult(intent,123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == 123)
        {
            if(resultCode == RESULT_OK)
            {

                TextView tv = (TextView) (findViewById(R.id.st));
                tv.setText("Welcome back "+ data.getExtras().get("g") +data.getExtras().get("fn")+", "+data.getExtras().get("ln"));
                Button bt =(Button)(findViewById(R.id.button));
                bt.setText("again...");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}