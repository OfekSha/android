package com.example.ex9;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCountryAdapter extends RecyclerView.Adapter<RecyclerCountryAdapter.ViewHolder>{
    MainActivity main;
    private ArrayList<Country> Countries;
    public ArrayList<Country> getCountries(){
        return Countries;
    }
    public RecyclerCountryAdapter(Context context){
        main= (MainActivity)context;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences.getBoolean("checkbox",false)){
            //Countries=main.getRaw("countriesRAW",context);
            Countries=main.getSP("countriesSP",context);
        }


        if (Countries== null)
        Countries=CountryXMLParser.parseCountries(context);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView country;
        public TextView population;
        public ImageView flag;

        public ViewHolder(View view) {
            super(view);
            country = (TextView)view.findViewById(R.id.country);
            population = (TextView)view.findViewById(R.id.population);
            flag =(ImageView) view.findViewById(R.id.flag);
        }
    }



    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row= layoutInflater.inflate(R.layout.row, parent, false);
        ViewHolder viewHolder = new ViewHolder(row);
        return viewHolder;
    }
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Country country = Countries.get(position);
        holder.country.setText(country.getName());
        holder.population.setText(country.getShorty());


        try {
            holder.flag.setImageResource(R.drawable.class.getField(country.getFlag()).getInt(null));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            holder.flag.setImageResource(R.drawable.no_photo);
            e.printStackTrace();
        }

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Countries.remove(position);

                    main.saveSP("countriesSP",Countries,v.getContext());
                    //main.saveRaw("countriesRAW",Countries,v.getContext());
                    notifyDataSetChanged();
                    return false;
                }
            });





    }
    // total number of rows
    @Override
    public int getItemCount() {
        return Countries.size();
    }
}
