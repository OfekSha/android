package com.example.ex7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCountryAdapter extends RecyclerView.Adapter<RecyclerCountryAdapter.ViewHolder>{

    private ArrayList<Country> Countries;
    public RecyclerCountryAdapter(Context context){
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
            // Define click listener for the ViewHolder's View

        }
    }



    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public RecyclerCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row= layoutInflater.inflate(R.layout.row, parent, false);
        ViewHolder viewHolder = new ViewHolder(row);
        return viewHolder;
    }
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull RecyclerCountryAdapter.ViewHolder holder, int position) {
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
