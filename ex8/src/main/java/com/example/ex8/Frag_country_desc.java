package com.example.ex8;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;


public class Frag_country_desc extends Fragment  {
    private CountryViewModel vm;

    Observer<ArrayList> ListUpdateObserver ;
    Observer<Integer> selectedUpdateObserver ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm= ViewModelProviders.of(requireActivity()).get(CountryViewModel.class);



        selectedUpdateObserver= new Observer<Integer>() {
            @Override
            public void onChanged(Integer selected) {
                if(selected!=-1){
               Country country= vm.getCountries().getValue().get(selected);
               setTextViews(country);
                }
            }
        };

        vm.getItemSelected().observe(getViewLifecycleOwner(),selectedUpdateObserver);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_country_desc, container,false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setTextViews(Country country) {
            TextView cname = (TextView) (getActivity().findViewById(R.id.contName));
            TextView cdesc = (TextView) (getActivity().findViewById(R.id.countDesc));
            cname.setText(country.name);
            cdesc.setText(country.details);
        }




}
