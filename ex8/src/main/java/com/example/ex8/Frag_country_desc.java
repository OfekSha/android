package com.example.ex8;

import android.content.Context;
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


public class Frag_country_desc extends Fragment  {
    AndroidViewModel vm;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void attachViewModel(AndroidViewModel vm){
        this.vm=vm;
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
