package com.example.cityguidy.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguidy.R;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder> {

    ArrayList<MostViewedHelperClass> mostViewedLocation;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewedLocation) {
        this.mostViewedLocation = mostViewedLocation;
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_cards_desing, parent, false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {
        MostViewedHelperClass mostViewedHelperClass = mostViewedLocation.get(position);
        holder.image.setImageResource(mostViewedHelperClass.getImage());
        holder.tittle.setText(mostViewedHelperClass.getTittle());
        holder.desc.setText(mostViewedHelperClass.getDescription());


    }

    @Override
    public int getItemCount() {
        return mostViewedLocation.size();
    }


    public static class MostViewedViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tittle, desc;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.mv_image);
            tittle = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);
        }
    }
}
