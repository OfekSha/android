package com.example.ex8;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class TheFrag extends Fragment {
    private  RecyclerCountryAdapter adapter;
    private  RecyclerView recyclerView;
    private CountryViewModel vm;

    Observer<ArrayList> ListUpdateObserver ;
    Observer<Integer> selectedUpdateObserver ;
    public TheFrag(){
        super(R.layout.main_frag);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context context = getContext();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler);

        vm=ViewModelProviders.of(requireActivity()).get(CountryViewModel.class);

        ListUpdateObserver= new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList ArrayList) {
                Context context = getContext();
                adapter.setCountries(ArrayList);
            }
        };

        selectedUpdateObserver= new Observer<Integer>() {
            @Override
            public void onChanged(Integer selected) {

                adapter.setSelectedCountry(selected);

            }
        };


        vm.getCountries().observe(getViewLifecycleOwner(),ListUpdateObserver );
        vm.getItemSelected().observe(getViewLifecycleOwner(),selectedUpdateObserver);

        adapter = new RecyclerCountryAdapter(vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.main_frag, container, false);
//    }



}
