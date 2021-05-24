package com.example.ex8;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    private CountryViewModel vm;
    Observer<Integer> selectedUpdateObserver ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm= ViewModelProviders.of(this).get(CountryViewModel.class);

        selectedUpdateObserver= new Observer<Integer>() {
            @Override
            public void onChanged(Integer selected) {

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && selected!=-1 )
                {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.port_fragment_container,Frag_country_desc.class, null,"theFrag")
                            .addToBackStack(null)
                            .commit();
                    getSupportFragmentManager().executePendingTransactions();
                    //.add(R.id.fragContainer, FragB.class, null,"FRAGB")
                }
            }
        };


        vm.getItemSelected().observe(this,selectedUpdateObserver);
    }


}