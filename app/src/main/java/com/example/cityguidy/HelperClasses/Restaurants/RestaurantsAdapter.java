package com.example.cityguidy.HelperClasses.Restaurants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguidy.R;

import java.util.ArrayList;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder> {

    ArrayList<RestaurantsHelperClass> restaurantsLocation;

    public RestaurantsAdapter(ArrayList<RestaurantsHelperClass> restaurantsLocation) {
        this.restaurantsLocation = restaurantsLocation;
    }

    @NonNull
    @Override
    public RestaurantsAdapter.RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_cards_desing, parent, false);
        RestaurantsAdapter.RestaurantsViewHolder RestaurantsViewHolder = new RestaurantsAdapter.RestaurantsViewHolder(view);
        return RestaurantsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsAdapter.RestaurantsViewHolder holder, int position) {
        RestaurantsHelperClass restaurantsHelperClass = restaurantsLocation.get(position);
        holder.image.setImageResource(restaurantsHelperClass.getImage());
        holder.tittle.setText(restaurantsHelperClass.getTittle());
        holder.desc.setText(restaurantsHelperClass.getDescription());


    }

    @Override
    public int getItemCount() {
        return restaurantsLocation.size();
    }


    public static class RestaurantsViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tittle, desc;

        public RestaurantsViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.restaurants_image);
            tittle = itemView.findViewById(R.id.restaurants_title);
            desc = itemView.findViewById(R.id.restaurants_desc);
        }
    }
}
