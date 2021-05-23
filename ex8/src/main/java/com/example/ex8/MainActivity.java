package com.example.ex8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.res.Configuration;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity implements RecyclerCountryAdapter.ViewHolder.ViewHolder_onClick {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onClick(Country country) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.port_fragment_container,Frag_country_desc.class, null,"theFrag")
                    .addToBackStack(null)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
            //.add(R.id.fragContainer, FragB.class, null,"FRAGB")
        }
        Frag_country_desc  frag_country_desc= (Frag_country_desc) getSupportFragmentManager().findFragmentByTag("theFrag");
        frag_country_desc.setTextViews(country);
    }





}