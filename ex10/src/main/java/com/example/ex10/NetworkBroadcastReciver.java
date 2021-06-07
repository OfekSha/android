package com.example.ex10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkBroadcastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network connection = connMgr.getActiveNetwork();
        if (connection!=null) {
            Toast.makeText(context,"connection work", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,"connection lost", Toast.LENGTH_LONG).show();
        }

    }
}
