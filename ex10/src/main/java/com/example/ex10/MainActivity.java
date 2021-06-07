package com.example.ex10;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
    private ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissionsContract;
    private  NetworkBroadcastReciver networkBroadcastReciver;
    private ActivityResultLauncher<String[]> multiplePermissionActivityResultLauncher;
    final String[] PERMISSIONS = {
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS

    };
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dynamic register
        intentFilter=new IntentFilter();
        intentFilter.addAction ( "android.net.conn.CONNECTIVITY_CHANGE"); // define the filter
        networkBroadcastReciver = new NetworkBroadcastReciver (); // object definition processing logic
        registerReceiver (networkBroadcastReciver, intentFilter); // Register


        //static prem
        requestMultiplePermissionsContract = new ActivityResultContracts.RequestMultiplePermissions();
        multiplePermissionActivityResultLauncher = registerForActivityResult(requestMultiplePermissionsContract, isGranted -> {
            if (isGranted.containsValue(false)) {
                multiplePermissionActivityResultLauncher.launch(PERMISSIONS);
            }
        });

        askPermissions();

    }

    private void askPermissions() {
        if (!hasPermissions(PERMISSIONS)) {

            multiplePermissionActivityResultLauncher.launch(PERMISSIONS);
        }
    }

    private boolean hasPermissions(String[] permissions) {
        if (permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }





}