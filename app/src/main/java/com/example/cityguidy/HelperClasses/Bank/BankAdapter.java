package com.example.cityguidy.HelperClasses.Bank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguidy.R;

import java.util.ArrayList;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder>{

    ArrayList<BankHelperClass> bankLocation;

    public BankAdapter(ArrayList<BankHelperClass> bankLocation) {
        this.bankLocation = bankLocation;
    }

    @NonNull
    @Override
    public BankAdapter.BankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_cards_desing, parent, false);
        BankAdapter.BankViewHolder BankViewHolder = new BankAdapter.BankViewHolder(view);
        return BankViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BankAdapter.BankViewHolder holder, int position) {
        BankHelperClass bankHelperClass = bankLocation.get(position);
        holder.image.setImageResource(bankHelperClass.getImage());
        holder.tittle.setText(bankHelperClass.getTittle());
        holder.desc.setText(bankHelperClass.getDescription());


    }

    @Override
    public int getItemCount() {
        return bankLocation.size();
    }


    public static class BankViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tittle, desc;

        public BankViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.bank_image);
            tittle = itemView.findViewById(R.id.bank_title);
            desc = itemView.findViewById(R.id.bank_desc);
        }
    }
}
