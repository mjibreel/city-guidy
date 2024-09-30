package com.example.cityguidy.LocationOuner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.example.cityguidy.AddNew.AddNewHospital;
import com.example.cityguidy.HelperClasses.AddsList.AddsListAdapter;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.R;
import com.example.cityguidy.User.Profile;
import com.example.cityguidy.User.UserDashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Hospital extends AppCompatActivity {

    LinearLayout contentView;
    ChipNavigationBar chipNavigationBar;
    private ProgressBar mProgressCircular;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    RecyclerView AddListRecycler;
    RecyclerView.Adapter adapter;

    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        //    main screen
        AddListRecycler = findViewById(R.id.add_list_recycler);

        contentView = findViewById(R.id.content);
        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_menu, true);


        AddListRecycler.setHasFixedSize(true);
        AddListRecycler.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds").child("Hospital");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);


                }
                adapter = new AddsListAdapter(Hospital.this, mUploads);
                AddListRecycler.setAdapter(adapter);
                mProgressCircular.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Hospital.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircular.setVisibility(View.INVISIBLE);

            }
        });

//      share button
        share = findViewById(R.id.add_list_share);


        contentView = findViewById(R.id.content);
        mProgressCircular = findViewById(R.id.progress_circular);
        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_menu, true);
//        bottomMenu();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                switch (i) {
                    case R.id.bottom_nav_home:
                        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                        break;
                    case R.id.bottom_nav_add:
//                        startActivity(new Intent(getApplicationContext(), AddNewHospital.class));
                        break;
                    case R.id.bottom_nav_profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        break;
                    case R.id.bottom_nav_search:

                        break;

                }

            }

        });


    }



//    bottom nav

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }



//  share

    public void share(View view){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"Share via"));
    }
}