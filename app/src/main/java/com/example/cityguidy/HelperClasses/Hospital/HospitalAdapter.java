package com.example.cityguidy.HelperClasses.Hospital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cityguidy.R;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {
    ArrayList<HospitalHelperClass> hospitalLocation;

    public HospitalAdapter(ArrayList<HospitalHelperClass> hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    @NonNull
    @Override
    public HospitalAdapter.HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_cards_desing, parent, false);
        HospitalAdapter.HospitalViewHolder HospitalViewHolder = new HospitalAdapter.HospitalViewHolder(view);
        return HospitalViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.HospitalViewHolder holder, int position) {
        HospitalHelperClass hotelHelperClass = hospitalLocation.get(position);
        holder.image.setImageResource(hotelHelperClass.getImage());
        holder.tittle.setText(hotelHelperClass.getTittle());
        holder.desc.setText(hotelHelperClass.getDescription());


    }

    @Override
    public int getItemCount() {
        return hospitalLocation.size();
    }


    public static class HospitalViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tittle, desc;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.hospital_image);
            tittle = itemView.findViewById(R.id.hospital_title);
            desc = itemView.findViewById(R.id.hospital_desc);
        }
    }
}
