package com.example.ex3client;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call(View view)
    {
        EditText et = (EditText) (findViewById(R.id.call));
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+et.getText().toString()));
        startActivity(intent);
    }


    public void surf(View view)
    {
        EditText et = (EditText) (findViewById(R.id.surf));
        Uri webpage = Uri.parse( et.getText().toString());
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }
    public void email(View view)
    {
        EditText et = (EditText) (findViewById(R.id.email));
        Uri mail = Uri.parse("mailto:"+ et.getText().toString());
        Intent emailIntent = new Intent(Intent.ACTION_SEND,mail);
        emailIntent.setType("text/plain");
        startActivity(emailIntent);

    }

    public void register(View view)
    {
        Intent registerIntent = new Intent("com.action.ex3.register");
        startActivityForResult(registerIntent,123);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == 123)
        {
            if(resultCode == RESULT_OK)
            {
                TextView et = (TextView) (findViewById(R.id.textView));
                et.setText(""+ data.getExtras().get("g") +data.getExtras().get("fn")+", "+data.getExtras().get("ln"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}