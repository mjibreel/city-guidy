package com.example.cityguidy.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cityguidy.LocationOuner.AddList;
import com.example.cityguidy.R;
import com.google.common.eventbus.EventBus;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class AllCategories extends AppCompatActivity {

    ImageView backIcon;
    Button hotel, house, car, restaurants, hospital, bank;
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        backIcon = findViewById(R.id.back_icon);
        hotel = findViewById(R.id.hotel_all_categories);
        house = findViewById(R.id.house_all_categories);
        car = findViewById(R.id.car_all_categories);
        restaurants = findViewById(R.id.restaurants_all_categories);
        hospital = findViewById(R.id.hospitals_all_categories);
        bank = findViewById(R.id.bank_all_categories);


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                startActivity(intent);
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddList.class));
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddList.class));
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddList.class));
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddList.class));
            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddList.class));
            }
        });
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddList.class));
            }
        });


    }

    /*    bottom nav*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}