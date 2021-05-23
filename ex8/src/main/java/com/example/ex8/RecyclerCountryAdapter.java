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
    public RecyclerCountryAdapter(ArrayList arrayList,CountryViewModel vmInstance){
        Countries=arrayList;
        this.vmInstance=vmInstance;
    }

    public void setSelectedCountry(int selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView countryName;
        private TextView population;
        private ImageView flag;
        private ViewHolder_onLongClickAction onLongClickActioner =null;
        private ViewHolder_onClick onClickActioher;
        private Country country;


        public ViewHolder(View view,ViewHolder_onClick onClickActioher) {
            super(view);
            countryName = (TextView)view.findViewById(R.id.country);
            population = (TextView)view.findViewById(R.id.population);
            flag =(ImageView) view.findViewById(R.id.flag);
            this.onClickActioher=onClickActioher;
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onLongClickActioner !=null){
                    return onLongClickActioner.onLongClickAction(v);
                    }
                    return false;
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickActioher !=null){
                        onClickActioher.onClick(country);
                    }
                }
            });
        }

        public void  setData(Country country)
        {
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
            public boolean  onLongClickAction(View v);
            public void setPosition(int position);

        }
        //an interface  used to receive data from viewHolder when clicked
        public  interface ViewHolder_onClick
        {
            public void  onClick (Country country);
        }

        public void set_ViewHolder_set(ViewHolder_onLongClickAction viewHolder_onLongClickAction)
        {
            onLongClickActioner = viewHolder_onLongClickAction;
        }
    }



    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public RecyclerCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row= layoutInflater.inflate(R.layout.row, parent, false);
        ViewHolder viewHolder = new ViewHolder(row,(ViewHolder.ViewHolder_onClick)parent.getContext());
        return viewHolder;
    }
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull RecyclerCountryAdapter.ViewHolder holder, int position) {
        final Country country = Countries.get(position);
        holder.setData(country);
        ViewHolder.ViewHolder_onLongClickAction viewHolder_onLongClickAction =new ViewHolder.ViewHolder_onLongClickAction()
        {
            private  int position;

            @Override
            public boolean  onLongClickAction(View v)
            {
                Countries.remove(this.position);
                notifyDataSetChanged();
                return false;
            }
            @Override
            public void setPosition(int position)
            {
                this.position=position;
            }
        };
        viewHolder_onLongClickAction.setPosition(position);
        holder.set_ViewHolder_set(viewHolder_onLongClickAction);






    }
    // total number of rows
    @Override
    public int getItemCount() {
        return Countries.size();
    }
}
