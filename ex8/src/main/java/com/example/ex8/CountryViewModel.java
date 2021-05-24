package com.example.ex8;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class CountryViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Country>> countries;
    private MutableLiveData<Integer> itemSelected;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countries= new MutableLiveData<>();
        itemSelected= new MutableLiveData<>();
        countries.setValue(CountryXMLParser.parseCountries(application));
        itemSelected.setValue(-1);
    }

    public MutableLiveData<ArrayList<Country>> getCountries() {
        return countries;
    }

    public MutableLiveData<Integer> getItemSelected() {
        return itemSelected;
    }


}
