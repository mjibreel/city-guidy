package com.example.cityguidy.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cityguidy.AddNew.AddUpdate;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddListProfileUpdate extends AppCompatActivity {
    ImageView viewPager;
    TextView AddName, AddDetail, AddCity, AddPhone, AddPrice, AddCategory, AddLikes;
    String Name, City, Category, Phone, Price, Section, Detail;
    String image;
    Button update,delete;
    private ProgressBar mProgressCircular;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private Context mContext;

    ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_profile_update);
        AddName = findViewById(R.id.add_list_profile_name);
        AddDetail = findViewById(R.id.add_list_profile_detail_name);
        AddCity = findViewById(R.id.add_list_profile_town_name);
        AddPhone = findViewById(R.id.add_list_profile_phone_name);
        AddPrice = findViewById(R.id.add_list_profile_price_name);
        AddCategory = findViewById(R.id.add_list_profile_Category_name);
        viewPager = findViewById(R.id.image_ViewPager);
        mProgressCircular = findViewById(R.id.progress_circular);
        share = findViewById(R.id.add_list_profile_share);
        update = findViewById(R.id.update_your_profile);
        delete = findViewById(R.id.delete_your_profile);


/*        ImageViewAdapter imageViewAdapter = new ImageViewAdapter(this);
        viewPager.setAdapter(imageViewAdapter);*/


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds");
        mStorageRef = FirebaseStorage.getInstance().getReference("New Adds");


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {

            Category = getIntent().getStringExtra("Category");
//     Section = getIntent().getStringExtra("aSection");
            City = getIntent().getStringExtra("City");
//            image = getIntent().getIntExtra("aImage,1");
            Name = getIntent().getStringExtra("Name");
            Detail = getIntent().getStringExtra("Detail");
            Price = getIntent().getStringExtra("Price");
            Phone = getIntent().getStringExtra("Phone");

            Picasso.get().load(mBundle.getString("image")).
                    into(viewPager);
            /* Glide.with(this)
                     .load(mBundle.getString("aImage")
                     .into(viewPager),*/
//viewPager.setImageResource(mBundle.getInt("image"));
            AddName.setText(mBundle.getString("Name"));
            AddCity.setText(mBundle.getString("City"));
            AddPrice.setText(mBundle.getString("Price"));
            AddDetail.setText(mBundle.getString("Detail"));
            AddPhone.setText(mBundle.getString("Phone"));
            AddCategory.setText(mBundle.getString("Category"));


        }

//        share

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Share via"));
            }
        });


//        update add
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddUpdate.class));
            }
        });


//        delete add

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}