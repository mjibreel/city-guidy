package com.example.cityguidy.User;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.cityguidy.AddNew.AddUpdate;
import com.example.cityguidy.Common.LoginSignUp.FaceGoogleLogin;
import com.example.cityguidy.Database.CheckInternet;
import com.example.cityguidy.HelperClasses.Localehelper;
import com.example.cityguidy.LocationOuner.AddList;
import com.example.cityguidy.LocationOuner.Bank;
import com.example.cityguidy.LocationOuner.Car;
import com.example.cityguidy.LocationOuner.Electronics;
import com.example.cityguidy.LocationOuner.Hospital;
import com.example.cityguidy.LocationOuner.House;
import com.example.cityguidy.LocationOuner.Restaurants;
import com.example.cityguidy.LocationOuner.Shop;
import com.example.cityguidy.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private long backPressedTime;
    private Toast backToast;
    final static float END_SCALE = 0.7f;
    FirebaseAuth mAuth;
    FirebaseUser user;
    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    DrawerLayout drawerLayout;
    private GoogleSignInClient mGoogleSignInClient;
    NavigationView navigationView;
    ImageView menuIcon, imageView;
    LinearLayout contentView;
    TextView name;
    ChipNavigationBar chipNavigationBar;
    private Toolbar toolbar;
    CardView hotel, house, car, restaurants, hospital, bank, shop, electronics;
    ConstraintLayout constraintLayout;
    ImageSlider promo;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    boolean lang_selected;
    Context context;
    Resources resources;
    Dialog dialog;


    //    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        setContentView(R.layout.activity_user_dashpord);


        //    main screen
//        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        hotel = findViewById(R.id.hotel);
        hospital = findViewById(R.id.Hospital);
        house = findViewById(R.id.House);
        shop = findViewById(R.id.shop);
        electronics = findViewById(R.id.electronics);
        car = findViewById(R.id.Car);
        bank = findViewById(R.id.Bank);
        restaurants = findViewById(R.id.restaurants);
        promo = findViewById(R.id.promo);
        constraintLayout = findViewById(R.id.all_categories);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        dialog = new Dialog(UserDashboard.this);
        dialog.setContentView(R.layout.lang_custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
dialog.setCancelable(false);
//        check Internet conn

        checkInternet();

//        ImageSlider


        final List<SlideModel> remoteImage = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Slider")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren())
                            remoteImage.add(new SlideModel((data.child("url").getValue()).toString(), ScaleTypes.FIT));

                        promo.setImageList(remoteImage, ScaleTypes.FIT);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


//        rate us

        AppRate.with(this)
                .setInstallDays(1) // default 10, 0 means install day.
                .setLaunchTimes(10) // default 10
                .setRemindInterval(1) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(UserDashboard.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);

//        setSingleEvent(constraintLayout);
        //setToggleEvent(constraintLayout);


    /*    chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_menu, true);
        bottomMenu();*/

        /*     featuredRecycler();*/
//        mostViewedRecycler();
        /*   categoriesRecycler();*/

//        menu
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.design_navigation_view);
        updateNavHeader();

        navigationDrawer();


        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, AddList.class);
                intent.putExtra("Category", "Hotel");
                startActivity(intent);
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Restaurants.class);
//                intent.putExtra("Restaurants", "Category");
                startActivity(intent);
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Car.class);
                intent.putExtra("New Adds", "Car");
                startActivity(intent);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Hospital.class);
                intent.putExtra("Category", "Hospital");
                startActivity(intent);
            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Bank.class);
                intent.putExtra("Category", "Bank");
                startActivity(intent);
            }
        });
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, House.class);
                intent.putExtra("Category", "House");
                startActivity(intent);
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Shop.class);
//                intent.putExtra("Category", "House");
                startActivity(intent);
            }
        });
        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Electronics.class);
//                intent.putExtra("Category", "House");
                startActivity(intent);
            }
        });


    }


   /* private void setToggleEvent(ConstraintLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }*/



    /*    bottom nav*/
/*
    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                switch (i) {
                    case R.id.bottom_nav_home:
                        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                        break;
                    case R.id.bottom_nav_add:
                        startActivity(new Intent(getApplicationContext(), AddNew.class));
                        break;
                    case R.id.bottom_nav_profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        break;
                    case R.id.bottom_nav_search:
                        startActivity(new Intent(getApplicationContext(), AddList.class));

                        break;

                }

            }
        });

    }
*/

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
        animateNavigationDrawer();


    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.setScrimColor(Color.TRANSPARENT);
//        drawerLayout.setScrimColor(getResources().getColor(R.color.card1));
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            super.onBackPressed();

        } else if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
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
            case R.id.nav_language:
             /*   language_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {*/
                final String[] Language = {"ENGLISH", "Somali", "Arabic"};
                final int checkedItem;
                if (lang_selected) {
                    checkedItem = 0;
                } else {
                    checkedItem = 1;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(UserDashboard.this);
//                dialog = new Dialog(UserDashboard.this);
//                builder.setView(R.layout.lang_custom_dialog);
                builder.setTitle("Select a Language...")
                        .setSingleChoiceItems(Language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                        Toast.makeText(UserDashboard.this,""+which,Toast.LENGTH_SHORT).show();
//                                        language_dialog.setText(Language[which]);
//                                        lang_selected= Language[which].equals("ENGLISH");
                                //if user select prefered language as English then
                                if (Language[which].equals("ENGLISH")) {
                                    context = Localehelper.setLocale(UserDashboard.this, "en");
                                    resources = context.getResources();
                                    Toast.makeText(UserDashboard.this, "ENGLISH", Toast.LENGTH_SHORT).show();

//                                            text1.setText(resources.getString(R.string.language));
                                }
                                //if user select prefered language as Hindi then
                                if (Language[which].equals("Somali")) {
                                    context = Localehelper.setLocale(UserDashboard.this, "so");
                                    resources = context.getResources();
                                    Toast.makeText(UserDashboard.this, "Somali", Toast.LENGTH_SHORT).show();

//                                            text1.setText(resources.getString(R.string.language));
                                }
                                if (Language[which].equals("Arabic")) {
                                    context = Localehelper.setLocale(UserDashboard.this, "ar");
                                    resources = context.getResources();
                                    Toast.makeText(UserDashboard.this, "Arabic", Toast.LENGTH_SHORT).show();

//                                            text1.setText(resources.getString(R.string.language));
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();


                break;
            case R.id.nav_Settings:
                AppRate.with(this)
                        .setInstallDays(5) // default 10, 0 means install day.
                        .setLaunchTimes(1) // default 10
                        .setRemindInterval(1) // default 1
                        .setShowLaterButton(true) // default true
                        .setDebug(false) // default false
                        .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                            @Override
                            public void onClickButton(int which) {
                                Log.d(UserDashboard.class.getName(), Integer.toString(which));
                            }
                        })
                        .monitor();

                // Show a dialog if meets conditions
                AppRate.showRateDialogIfMeetsConditions(this);
                AppRate.with(this).showRateDialog(this);

                break;
            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;
            case R.id.nav_login:
                startActivity(new Intent(getApplicationContext(), FaceGoogleLogin.class));
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

    public void updateNavHeader() {

        View headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.Name);
        imageView = headerView.findViewById(R.id.user_image);
        if (user != null) {
            name.setText(user.getDisplayName());
            Glide.with(this).load(user.getPhotoUrl()).into(imageView);
        } else {
            name.setText(null);
            Glide.with(this).load((Bitmap) null).into(imageView);

        }

    }

    //    Recycle function
 /*   private void categoriesRecycler() {


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.restaurant_icon, "Restaurant"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.restaurant_icon, "Restaurant"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.restaurant_icon, "Restaurant"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.restaurant_icon, "Restaurant"));


        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);
    }

//    private void mostViewedRecycler() {
//        mostViewedRecycler.setHasFixedSize(true);
//        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        ArrayList<MostViewedHelperClass> mostViewedLocation = new ArrayList<>();
//
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        mostViewedLocation.add(new MostViewedHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
//        adapter = new MostViewedAdapter(mostViewedLocation);
//        mostViewedRecycler.setAdapter(adapter);
//    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeatureHelperClass> featureLocation = new ArrayList<>();

        featureLocation.add(new FeatureHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
        featureLocation.add(new FeatureHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
        featureLocation.add(new FeatureHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));
        featureLocation.add(new FeatureHelperClass(R.drawable.my_pic, "My Pic", "You can search any place nearby or with-in the specified city"));

        adapter = new FeaturedAdapter(featureLocation);
        featuredRecycler.setAdapter(adapter);
    }*/

    private void checkInternet() {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }
    }

    private void showCustomDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(UserDashboard.this);
        builder.setMessage("Please connect to the internet")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
//                        finish();
                    }
                });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        user = mAuth.getCurrentUser();
        if (user != null) {

//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }
    }

}
