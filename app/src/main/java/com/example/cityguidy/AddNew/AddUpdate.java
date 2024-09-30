package com.example.cityguidy.AddNew;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguidy.Database.CheckInternet;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.HelperClasses.CategorySpinner.CustomAdapter;
import com.example.cityguidy.HelperClasses.CategorySpinner.CustomItem;
import com.example.cityguidy.HelperClasses.PhotoAdapter;
import com.example.cityguidy.LocationOuner.AddList;
import com.example.cityguidy.R;
import com.example.cityguidy.User.Profile;
import com.example.cityguidy.User.UserDashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AddUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private Spinner spn_label_Category, spn_label_Section, spinnerCity;
    ArrayList<String> arrayList_hotel, arrayList_restaurants, arrayList_bank, arrayList_hospital, arrayList_car, arrayList_house, arrayList_ChooseCategory;
    ArrayAdapter<String> customAdapter_child;
    ArrayAdapter<CharSequence> cityArrayAdapter;
    int positionOfSelectedDataFromSpinnerCategory;
    int positionOfSelectedDataFromSpinnerSection;
    int positionOfSelectedDataFromSpinnerCity;
    ImageView exitBtn;
    Button post;
    private Button selectImage;
    ImageView revPhoto, revPhoto2, revPhoto3, revPhoto4, revPhoto5, revPhoto6, revPhoto7;
    private PhotoAdapter photoAdapter;
    ProgressBar progressbar;
    TextInputEditText addName, addDetail, addPhone, addPrice,aTime;
    public static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    String imageU;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        addName = findViewById(R.id.add_name);
        addDetail = findViewById(R.id.add_details);
        addPhone = findViewById(R.id.add_phone_number);
        addPrice = findViewById(R.id.add_price);
        exitBtn = findViewById(R.id.add_exit_btn);
        post = findViewById(R.id.add_post_button);
        progressbar = findViewById(R.id.progress_pv_linear_colors);

        mStorageRef = FirebaseStorage.getInstance().getReference("New Adds");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds");

        spn_label_Category = findViewById(R.id.spinner_label);
//        spn_text_Category = findViewById(R.id.spinner_label_text);


//        spn_label_Category


        ArrayList<CustomItem> customList = new ArrayList<>();

        customList.add(new CustomItem("Choose Category", R.drawable.choose_category_icon));
        customList.add(new CustomItem("Hotel", R.drawable.hotel_icon));
        customList.add(new CustomItem("Restaurants", R.drawable.restaurant_icon));
        customList.add(new CustomItem("Hospital", R.drawable.hospital_icon));
        customList.add(new CustomItem("Bank", R.drawable.bank_icon));
        customList.add(new CustomItem("Car", R.drawable.car_icon));
        customList.add(new CustomItem("House", R.drawable.ic_baseline_house_24));


        CustomAdapter customAdapter = new CustomAdapter(this, customList);
        if (spn_label_Category != null) {
            spn_label_Category.setAdapter(customAdapter);
            spn_label_Category.setOnItemSelectedListener(this);
        }


//        spn_label_Section

        spn_label_Section = findViewById(R.id.spinner_label2);

        arrayList_ChooseCategory = new ArrayList<>();
        arrayList_ChooseCategory.add("Section");

        arrayList_hotel = new ArrayList<>();
        arrayList_hotel.add("5 Star");
        arrayList_hotel.add("4 Star");
        arrayList_hotel.add("3 Star");
        arrayList_hotel.add("2 Star");
        arrayList_hotel.add("1 Star");

        arrayList_bank = new ArrayList<>();
        arrayList_bank.add("Money Transfer ");

        arrayList_hospital = new ArrayList<>();
        arrayList_hospital.add("Hospital ");

        arrayList_restaurants = new ArrayList<>();
        arrayList_restaurants.add("Fast Food ");
        arrayList_restaurants.add("Somali Restaurants ");
        arrayList_restaurants.add("Arab Restaurants ");
        arrayList_restaurants.add("Home Made ");

        arrayList_car = new ArrayList<>();
        arrayList_car.add("Buy ");
        arrayList_car.add("Sell ");
        arrayList_car.add("Repairing ");

        arrayList_house = new ArrayList<>();
        arrayList_house.add("Buy ");
        arrayList_house.add("Sell ");
        arrayList_house.add("Rent ");

        spn_label_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                         @Override
                                                         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                             if (position == 0) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList_ChooseCategory);
                                                                 return;
                                                             }
                                                             if (position == 1) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList_hotel);

                                                             }
                                                             if (position == 2) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList_restaurants);

                                                             }
                                                             if (position == 3) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList_hospital);

                                                             }
                                                             if (position == 4) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList_bank);

                                                             }
                                                             if (position == 5) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList_car);

                                                             }
                                                             if (position == 6) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList_house);

                                                             }
                                                             spn_label_Section.setAdapter(customAdapter_child);
                                                             spn_label_Section.setOnItemSelectedListener(this);
                positionOfSelectedDataFromSpinnerSection = position;
                                                         }

                                                         @Override
                                                         public void onNothingSelected(AdapterView<?> parent) {

                                                         }


                                                     }

        );
        spn_label_Section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spn_label_Section.setAdapter(customAdapter_child);
                spn_label_Section.setOnItemSelectedListener(this);

                String sectionText = parent.getItemAtPosition(position).toString();

                Toast.makeText(parent.getContext(), sectionText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
//        spinnerCity

        spinnerCity = findViewById(R.id.spinner_city);


        cityArrayAdapter = ArrayAdapter.createFromResource(this, R.array.City, android.R.layout.simple_list_item_1);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCity.setAdapter(cityArrayAdapter);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionOfSelectedDataFromSpinnerCity = position;



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        //        selectImage
        revPhoto = findViewById(R.id.rev_photo);

        selectImage = findViewById(R.id.btn_select_image);


        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openButtonPicker();


            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddUpdate.this, "Update in progress", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(AddUpdate.this, AddList.class);
                    startActivity(intent);

                    uploadFile();
                    checkInternet();


                }
            }
        });
    }


    private void openButtonPicker() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 4);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(revPhoto);

        }
    }

    //     spinner

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomItem item = (CustomItem) parent.getSelectedItem();
        Toast.makeText(this, item.getSpinnerText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


//    upload Files to data base


    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child("Category").child(mImageUri.getLastPathSegment());
            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressbar.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(AddUpdate.this, "Update Successful", Toast.LENGTH_SHORT).show();


                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isComplete()) ;
                            Uri uriImage = uriTask.getResult();
                            imageU = uriImage.toString();
//                            uploadAdd();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddUpdate.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() /
                                    taskSnapshot.getTotalByteCount());
                            progressbar.setProgress((int) progress);
                        }
                    });


        } else {
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }

    }

/*
    private void uploadAdd() {

        Upload uploadsAdds = new Upload(
                addName.getText().toString(),
                addDetail.getText().toString(),
                addPrice.getText().toString(),
                addPhone.getText().toString(),
                spinnerCity.getSelectedItem().toString(),
                spn_label_Category.getSelectedItem().toString(),
                imageU
        );


        String currentTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        mDatabaseRef.child(currentTime).setValue(uploadsAdds);
//    check the Internet


    }
*/

    private void checkInternet() {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddUpdate.this);
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
                        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}