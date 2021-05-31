package com.example.ex9;


import android.content.Context;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import androidx.appcompat.app.AppCompatActivity;

import androidx.preference.PreferenceFragmentCompat;


import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static class MySettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preference_screen, rootKey);

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);
            view.setBackgroundColor(getResources().getColor(android.R.color.white));
            return view;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.main,new MySettingsFragment())
                        .addToBackStack(null)
                        .commit();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public  void saveRaw(String filename, ArrayList<Country> Countries , Context ctx) {
        FileOutputStream fos;
        try {
            fos = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Countries);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Country> getRaw(String filename , Context ctx) {
        FileInputStream fos;
        ArrayList<Country> Countries=null;
        try {
            fos = ctx.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fos);
            Countries=(ArrayList<Country>) ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Countries;
    }

    public class CaseClass{
        public ArrayList<Country> Countries;
        public CaseClass(ArrayList<Country> Countries)
        {
            this.Countries=Countries;
        }
    }
    public  void saveSP(String filename, ArrayList<Country> Countries , Context context) {
        SharedPreferences mPrefs  = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        CaseClass casing = new CaseClass(Countries);
        String json = gson.toJson(casing);
        prefsEditor.putString("casing", json);
        prefsEditor.commit();
    }

    public ArrayList<Country> getSP(String filename , Context ctx) {
        CaseClass casing=null;
        SharedPreferences mPrefs  = ctx.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString("casing", "");

        casing  = gson.fromJson(json,CaseClass.class);
        if(casing==null)
        {
            return null;
        }
        return casing.Countries;
    }
}