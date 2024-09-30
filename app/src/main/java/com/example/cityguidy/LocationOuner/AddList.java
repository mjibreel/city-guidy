package com.example.cityguidy.LocationOuner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguidy.HelperClasses.AddsList.AddsListAdapter;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.R;
import com.example.cityguidy.AddNew.AddUpdate;
import com.example.cityguidy.User.Profile;
import com.example.cityguidy.User.UserDashboard;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class AddList extends AppCompatActivity {


    private DatabaseReference mDatabaseRef, likesRef;
    private List<Upload> mUploads;
    private AddsListAdapter adapter;
    TextView listName, listCity, listCategory;
    ImageView listImage;
    Button share;
    ChipNavigationBar chipNavigationBar;
    RecyclerView AddListRecycler;
    LinearLayout contentView;
    private ProgressBar mProgressCircular;
    Boolean likeChecker = false;
    String Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        //    main screen
      listName = findViewById(R.id.add_list_title);
//        listCategory = findViewById(R.id.add_list_Category);
        listCity = findViewById(R.id.add_list_location);
        listImage=findViewById(R.id.add_list_image);

//      share button
        share = findViewById(R.id.add_list_share);


        likesRef = FirebaseDatabase.getInstance().getReference("Like");
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
                        startActivity(new Intent(getApplicationContext(), AddUpdate.class));
                        break;
                    case R.id.bottom_nav_profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        break;
                    case R.id.bottom_nav_search:

                        break;

                }

            }

        });


        AddListRecycler = findViewById(R.id.add_list_recycler);
        AddListRecycler.setHasFixedSize(true);
        AddListRecycler.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);

                    mUploads.add(upload);


                }
                adapter = new AddsListAdapter(AddList.this, mUploads);
                adapter.notifyDataSetChanged();
                AddListRecycler.setAdapter(adapter);
                mProgressCircular.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircular.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();

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

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}