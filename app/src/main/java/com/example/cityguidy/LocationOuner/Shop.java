package com.example.cityguidy.LocationOuner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cityguidy.AddNew.AddNewShop;
import com.example.cityguidy.AddNew.AddUpdate;
import com.example.cityguidy.Common.LoginSignUp.Login;
import com.example.cityguidy.HelperClasses.AddsList.AddsListAdapter;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.R;
import com.example.cityguidy.User.AllCategories;
import com.example.cityguidy.User.Profile;
import com.example.cityguidy.User.UserDashboard;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    LinearLayout contentView;
    ChipNavigationBar chipNavigationBar;
    private ProgressBar mProgressCircular;
    private DatabaseReference mDatabaseRef, favRev, fav_listRef;
    private List<Upload> mUploads;
    RecyclerView AddListRecycler;
    AddsListAdapter adapter;
    SwipeRefreshLayout refresh;
    EditText searchView;
    CharSequence search="";
    ImageView add_post, menuIcon;
    Button share, fav_btn;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    final static float END_SCALE = 0.7f;
    ConstraintLayout constraintLayout;
    String currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
//        TextView aTime = findViewById(R.id.add_list_time);
        searchView = findViewById(R.id.search);
        add_post = findViewById(R.id.add_post_button);
        contentView = findViewById(R.id.content);
        menuIcon = findViewById(R.id.menu_icon);

        mStorageRef = FirebaseStorage.getInstance().getReference("New Adds").child("Shop");

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds")
                .child("Shop");

//         search
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                adapter = new AddsListAdapter(Shop.this, mUploads);
//                adapter.getFilter().filter(s);
//                search = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        menu
        drawerLayout = findViewById(R.id.res_drawer_Layout);
        navigationView = findViewById(R.id.res_design_navigation_view);

//
        navigationDrawer();

/*
//        Fav
        user = mAuth.getCurrentUser();
        final String userId = user.getUid();
        favRev=database.getReference("favourites");
        fav_listRef=database.getReference("favouriteList").child(userId);*/

     /*   fvrt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favChecker=true;
                favRev.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Upload upload = snapshot.getValue(Upload.class);
                        mUploads.add(upload);
                        if(favChecker.equals(true)){
                            if (snapshot.hasChild(userId)){

                                favRev.child(userId).removeValue();
                                delete(time);
                                favChecker=false;
                            }else {
                                favRev.child(userId).setValue(true);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });*/


        //    main screen
        AddListRecycler = findViewById(R.id.add_list_recycler);


//        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
//        chipNavigationBar.setItemSelected(R.id.bottom_nav_menu, true);

//        db = FirebaseFirestore.getInstance();

//        mDatabaseRef=db.collection("Account").document(userId);

//        Collections.sort(mUploads);
        AddListRecycler.setHasFixedSize(true);
        AddListRecycler.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();



        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);


                }
                adapter = new AddsListAdapter(Shop.this, mUploads);
                adapter.notifyDataSetChanged();
                AddListRecycler.setAdapter(adapter);
                mProgressCircular.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Shop.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircular.setVisibility(View.INVISIBLE);

            }
        });


//        add post
        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddNewShop.class));

            }
        });

//      share button
        share = findViewById(R.id.add_list_share);


        contentView = findViewById(R.id.content);
        mProgressCircular = findViewById(R.id.progress_circular);
       /*
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
                        startActivity(new Intent(getApplicationContext(), AddNewRestaurants.class));
                        break;
                    case R.id.bottom_nav_profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        break;
                    case R.id.bottom_nav_search:

                        break;

                    default:

                }

            }

        });
*/

        refresh = findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUploads = new ArrayList<>();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                        adapter.notifyDataSetChanged();

                    }
                },4*1000);
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


    //    Nav drawer function
    private void navigationDrawer() {

//        nav drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
//        animateNavigationDrawer();


    }

/*
    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setScrimColor(getResources().getColor(R.color.card1));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }
*/


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);
        int menu_id = item.getItemId();
        switch (menu_id) {

            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                break;

            case R.id.nav_add:
                startActivity(new Intent(getApplicationContext(), AddUpdate.class));
                break;

            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
                break;
//            case R.id.nav_Language:

//                break;
            case R.id.nav_Settings:


            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;
            case R.id.nav_login:
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
           /* case R.id.nav_logout:

                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    mAuth.signOut();
                                    finish();
                                }
                            }
                        });
                SharedPreferences preferences = getSharedPreferences("checkBox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();


                break;


        }*/
        }
        drawerLayout.closeDrawers();
        return true;
    }


//  share

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Share via"));
    }


}