package com.example.cityguidy.HelperClasses.Hotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguidy.R;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    ArrayList<HotelHelperClass> hotelLocation;

    public HotelAdapter(ArrayList<HotelHelperClass> hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    @NonNull
    @Override
    public HotelAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_cards_desing, parent, false);
        HotelAdapter.HotelViewHolder HotelViewHolder = new HotelAdapter.HotelViewHolder(view);
        return HotelViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.HotelViewHolder holder, int position) {
        HotelHelperClass hotelHelperClass = hotelLocation.get(position);
        holder.image.setImageResource(hotelHelperClass.getImage());
        holder.tittle.setText(hotelHelperClass.getTittle());
        holder.desc.setText(hotelHelperClass.getDescription());


    }

    @Override
    public int getItemCount() {
        return hotelLocation.size();
    }


    public static class HotelViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tittle, desc;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.hotel_image);
            tittle = itemView.findViewById(R.id.hotel_title);
            desc = itemView.findViewById(R.id.hotel_desc);
        }
    }
}
