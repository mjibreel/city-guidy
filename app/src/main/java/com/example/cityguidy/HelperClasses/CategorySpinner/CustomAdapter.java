package com.example.cityguidy.HelperClasses.CategorySpinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cityguidy.R;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<CustomItem>{



    public CustomAdapter(@NonNull Context context, ArrayList<CustomItem> customList) {
        super(context, 0, customList);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView (position, convertView, parent);
    }


    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner,parent,false);

        }
        CustomItem item=getItem(position);
        ImageView spinnerImage=convertView.findViewById(R.id.spinner_image);
        TextView spinnerName=convertView.findViewById(R.id.spinner_text);

        if(item!=null){

            spinnerImage.setImageResource(item.getSpinnerImage());
            spinnerName.setText(item.getSpinnerText());
        }
        return convertView;
    }
}
