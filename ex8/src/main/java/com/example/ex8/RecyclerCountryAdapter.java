package com.example.ex8;


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
    private int selectedCountry;
    private CountryViewModel vmInstance;

    public RecyclerCountryAdapter(CountryViewModel vmInstance){
        this.vmInstance= vmInstance;
        setCountries(vmInstance.getCountries().getValue());
    }
    public void setCountries (ArrayList<Country> countries)
    {
        this.Countries=countries;
    }

    public void setSelectedCountry(int selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView countryName;
        private TextView population;
        private ImageView flag;
        private ViewHolder_onLongClickAction onLongClickActioner =null;
        private ViewHolder_onClick viewHolder_onClick =null;
        private ViewHolder_onClick onClickActioher;
        private Country country;
        private int position=0;


        public ViewHolder(View view) {
            super(view);
            countryName = (TextView)view.findViewById(R.id.country);
            population = (TextView)view.findViewById(R.id.population);
            flag =(ImageView) view.findViewById(R.id.flag);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onLongClickActioner !=null){
                    return onLongClickActioner.onLongClickAction(v,position);
                    }
                    return false;
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder_onClick.onClick(v,position);
                    }

            });
        }

        public void  setData(Country country, int position)
        {
            this.position=position;
            this.countryName.setText(country.getName());
            population.setText(country.getShorty());


            try {
                flag.setImageResource(R.drawable.class.getField(country.getFlag()).getInt(null));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                flag.setImageResource(R.drawable.no_photo);
                e.printStackTrace();
            }
            this.country=country;
        }

        //an interface used to set stuff view holder uonLongClickAction
        public interface ViewHolder_onLongClickAction
        {
            public boolean  onLongClickAction(View v,int position);

        }
        //an interface  used to receive data from viewHolder when clicked
        public  interface ViewHolder_onClick
        {
            public void onClick(View v,int position);
        }

        public void set_ViewHolder_onLongClickAction(ViewHolder_onLongClickAction viewHolder_onLongClickAction)
        {
            this.onLongClickActioner = viewHolder_onLongClickAction;
        }
        public void set_ViewHolder_onClick(ViewHolder_onClick viewHolder_onClick)
        {
            this.viewHolder_onClick = viewHolder_onClick;
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
        holder.setData(country,position);
        ViewHolder.ViewHolder_onLongClickAction viewHolder_onLongClickAction =new ViewHolder.ViewHolder_onLongClickAction()
        {


            @Override
            public boolean  onLongClickAction(View v, int position)
            {
                Countries.remove(position);
                (vmInstance.getCountries()).setValue(Countries);
                notifyDataSetChanged();
                return false;
            }

        };
        holder.set_ViewHolder_onLongClickAction(viewHolder_onLongClickAction);


        ViewHolder.ViewHolder_onClick ViewHolder_onClick = new ViewHolder.ViewHolder_onClick() {

            @Override
            public void onClick(View v,int position) {
                (vmInstance.getItemSelected()).setValue(position);
                notifyDataSetChanged();
            }
        };
        holder.set_ViewHolder_onClick( ViewHolder_onClick);

    }
    // total number of rows
    @Override
    public int getItemCount() {
        return Countries.size();
    }
}
