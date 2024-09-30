/*
package com.example.cityguidy.AddNew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.cityguidy.Database.CheckInternet;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.HelperClasses.CategorySpinner.CustomAdapter;
import com.example.cityguidy.HelperClasses.CategorySpinner.CustomItem;
import com.example.cityguidy.HelperClasses.PhotoAdapter;
import com.example.cityguidy.LocationOuner.House;
import com.example.cityguidy.R;
import com.example.cityguidy.User.UserDashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AddNewHouse extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
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
    private StorageReference mStorageRef ;
    private DatabaseReference mDatabaseRef ;
    private StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_house);
        addName = findViewById(R.id.add_name);
        addDetail = findViewById(R.id.add_details);
        addPhone = findViewById(R.id.add_phone_number);
        addPrice = findViewById(R.id.add_price);
        exitBtn = findViewById(R.id.add_exit_btn);
        post = findViewById(R.id.add_post_button);
        progressbar = findViewById(R.id.progress_pv_linear_colors);

        mStorageRef = FirebaseStorage.getInstance().getReference("New Adds").child("House");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds").child("House");

        spn_label_Category = findViewById(R.id.spinner_label);
//        spn_text_Category = findViewById(R.id.spinner_label_text);


//        spn_label_Category


        ArrayList<CustomItem> customList = new ArrayList<>();

        customList.add(new CustomItem("Buy", R.drawable.choose_category_icon));
        customList.add(new CustomItem("Sell", R.drawable.hotel_icon));
        customList.add(new CustomItem("Rent", R.drawable.restaurant_icon));


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
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList_ChooseCategory);
                                                                 return;
                                                             }
                                                             if (position == 1) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList_hotel);

                                                             }
                                                             if (position == 2) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList_restaurants);

                                                             }
                                                             if (position == 3) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList_hospital);

                                                             }
                                                             if (position == 4) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList_bank);

                                                             }
                                                             if (position == 5) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList_car);

                                                             }
                                                             if (position == 6) {
                                                                 customAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList_house);

                                                             }
                                                             spn_label_Section.setAdapter(customAdapter_child);
                                                             spn_label_Section.setOnItemSelectedListener(this);
//                positionOfSelectedDataFromSpinnerSection = position;
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

                String sectionText=parent.getItemAtPosition(position).toString();

                Toast.makeText(parent.getContext(),sectionText, Toast.LENGTH_SHORT).show();
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
//                positionOfSelectedDataFromSpinnerCity = position;
 saveValue(cityText);
                cityText = spinnerCity.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        //        selectImage
        revPhoto = findViewById(R.id.rev_photo);
 revPhoto2 = findViewById(R.id.rev_photo2);
        revPhoto3 = findViewById(R.id.rev_photo3);
        revPhoto4 = findViewById(R.id.rev_photo4);
        revPhoto5 = findViewById(R.id.rev_photo5);
        revPhoto6 = findViewById(R.id.rev_photo6);
        revPhoto7 = findViewById(R.id.rev_photo7);

        selectImage = findViewById(R.id.btn_select_image);

        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);
        revPhoto.setLayoutManager(gridLayoutManager);
        revPhoto.setAdapter(photoAdapter);


        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openButtonPicker();

//                requestPermissions();

            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddNewHouse.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {

                    uploadFile();
                    checkInternet();
                    Intent intent = new Intent( getApplicationContext(), House.class);
                    startActivity(intent);
 String aName = addName.getText().toString();
                    String aDetail = addDetail.getText().toString();
                    String aPrice = addPrice.getText().toString();
                    String aCity = spinnerCity.getSelectedView().toString();
                    String aCategory = spn_label_Category.getSelectedView().toString();
                    String aSection = spn_label_Section.getSelectedView().toString();
                    String aPhone = addPhone.getText().toString();
                    String mImageUri = revPhoto.setImageURI().toString();


                    Upload uploadsAdds = new Upload(aName,aDetail,aPrice,aPhone,aCity,aCategory,aSection,mImageUri);
                    mDatabaseRef.child(aCategory).setValue(uploadsAdds);

                    Intent intent = new Intent(getApplicationContext(),AddListProfile.class);
                    intent.putExtra("image",mImageUri);
                    intent.putExtra("Name",aName);
                    intent.putExtra("City",aCity);
                    intent.putExtra("Price",aCity);
                    intent.putExtra("Category",aCity);
                    intent.putExtra("Section",aCity);
                    intent.putExtra("Phone",aCity);
                    intent.putExtra("Detail",aCity);


                    startActivity(intent);

                }
            }
        });
    }


    //        select Multi Image

//    private void requestPermissions() {
//
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                Toast.makeText(AddNew.this, "Permission Granted", Toast.LENGTH_SHORT).show();
//                openButtonPicker();
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                Toast.makeText(AddNew.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//
//        };
//
//        TedPermission.with(this)
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
//                .check();
//
//    }

    private void openButtonPicker() {
 TedBottomPicker.with(AddNew.this)
                .setPeekHeight(1600)
                .showTitle(true).setPreviewMaxCount(20)
                .setCompleteButtonText("Done")
                .setEmptySelectionText("No Select")
                .showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(List<Uri> uriList) {
                        if (uriList != null && !uriList.isEmpty()) {
                            photoAdapter.setData(uriList);
                        }
                    }
                });

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST || resultCode == RESULT_OK
                ||  data != null ||  data.getData() != null) {
            mImageUri = data.getData();
//            Picasso.get().load(mImageUri).into(revPhoto);
            revPhoto.setImageURI(mImageUri);
//            Picasso.with(this).load(mImageUri).into(revPhoto);
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


 public void callAddListProfile(View view) {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }


        String mImageUri = revPhoto.
        String aName = addName.getEditText().getText().toString().trim();
        String aCity  = spinnerCity.toString();




        CollectionReference addListRef= FirebaseFirestore.getInstance().collection("AddList");
        addListRef.add(new AddsListHelperClass(aName,aCity,mImageUri));
        Toast.makeText(this, "Add Added", Toast.LENGTH_SHORT).show();
        finish();
        rootNode = FirebaseDatabase.getInstance();t
        reference = rootNode.getReference("Adds");

        reference.child(aPhone);

        Intent intent = new Intent(getApplicationContext(), AddList.class);

        intent.putExtra("addName", aName);
        intent.putExtra("addDetail", aDetail);
        intent.putExtra("addPrice", aPrice);
        intent.putExtra("addPhone", aPhone);
        intent.putExtra("position", positionOfSelectedDataFromSpinnerCategory);
        intent.putExtra("position", positionOfSelectedDataFromSpinnerSection);
        intent.putExtra("position", positionOfSelectedDataFromSpinnerCity);
 Intent intent = new Intent(getApplicationContext(), AddList.class);

         Intent intent = new Intent(mContext, AddListProfile.class);
                intent.putExtra("image".getImageUri());
                intent.putExtra("Name".getName());
                intent.putExtra("City".getCity());
                intent.putExtra("Price".getPrice());
                intent.putExtra("Category".getCategory());
                intent.putExtra("Section".getSection());
                intent.putExtra("Phone".getPhone());
                intent.putExtra("Detail".getDetail());

        startActivity(intent);
        startActivity(intent);


    }

//    upload Files to data base

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child("House").child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
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
                            Toast.makeText(AddNewHouse.this, "Upload Successful", Toast.LENGTH_SHORT).show();


                            Upload uploadsAdds = new Upload(
                                    addName.getText().toString().trim(),
                                    addDetail.getText().toString().trim(),
                                    addPrice.getText().toString().trim(),
                                    addPhone.getText().toString().trim(),
                                    spinnerCity.getSelectedItem().toString().trim(),
                                    spn_label_Category.getSelectedItem().toString().trim(),
                                    taskSnapshot.getUploadSessionUri().toString().trim());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(uploadsAdds);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddNewHouse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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


//    check the Internet

    private void checkInternet() {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewHouse.this);
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



}
*/
