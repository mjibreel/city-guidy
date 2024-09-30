package com.example.cityguidy.HelperClasses.House;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cityguidy.R;

import java.util.ArrayList;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder>{
    ArrayList<HouseHelperClass> houseLocation;

    public HouseAdapter(ArrayList<HouseHelperClass> houseLocation) {
        this.houseLocation = houseLocation;
    }

    @NonNull
    @Override
    public HouseAdapter.HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_cards_desing, parent, false);
        HouseAdapter.HouseViewHolder HouseViewHolder = new HouseAdapter.HouseViewHolder(view);
        return HouseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HouseAdapter.HouseViewHolder holder, int position) {
        HouseHelperClass houseHelperClass = houseLocation.get(position);
        holder.image.setImageResource(houseHelperClass.getImage());
        holder.tittle.setText(houseHelperClass.getTittle());
        holder.desc.setText(houseHelperClass.getDescription());


    }

    @Override
    public int getItemCount() {
        return houseLocation.size();
    }


    public static class HouseViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tittle, desc;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.house_image);
            tittle = itemView.findViewById(R.id.house_title);
            desc = itemView.findViewById(R.id.house_desc);
        }
    }

}
