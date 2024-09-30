package com.example.cityguidy.AddNew;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.cityguidy.Common.LoginSignUp.FaceGoogleLogin;
import com.example.cityguidy.Database.CheckInternet;
import com.example.cityguidy.HelperClasses.PhotoHelperClass;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.LocationOuner.Restaurants;
import com.example.cityguidy.Map.LocationPickerActivity;
import com.example.cityguidy.R;
import com.example.cityguidy.User.UserDashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.shivtechs.maplocationpicker.MapUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AddNewRestaurants extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spn_label_Category, spinnerCity;
    ArrayAdapter<String> categoryArrayAdapter;
    ArrayAdapter<CharSequence> cityArrayAdapter;
    int positionOfSelectedDataFromSpinnerCategory;
    int positionOfSelectedDataFromSpinnerSection;
    int positionOfSelectedDataFromSpinnerCity;
    ImageView exitBtn;
    ImageView revPhoto, userPhoto;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String name, detail, phone, aTime, category, city, timeStamp,
            user_photo, userName, userId, userEmail;
    //    PhotoAdapter photoAdapter;
    List<PhotoHelperClass> mListPhoto;
    Button location;
    TextView  locationAddress, time, post;
    //    private Button selectImage;
//    private ImageView forwardImage, backImage;
    private ImageView selectImage;
    ProgressBar progressbar;
    TextInputEditText addName, addDetail, addPhone, addPrice;
    private static final int PICK_IMAGE_REQUEST = 0;
    private static final int PICK_IMAGE_REQUEST_CODE = 3;
    private static final int PICK_CAMERA_REQUEST = 1;
    private static final int PICK_CAMERA_REQUEST_CODE = 4;
    String[] cameraPermissions;
    String[] storagePermissions;
    public static final int PICK_PLACE_REQUEST = 22;
    public static final int MY_PERMISSION_FINE_LOCATION = 33;
    private int REQUEST_STORAGE = 111;
    private int REQUEST_FILE = 222;
    Uri mImageUri;
    private String stringPath;
    String imageU;
    private Intent iData;
    private Double Latitude;
    private Double Longitude;
    String dateToSet;
    Date date;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef, userRef;
    private StorageTask mUploadTask;
    String postKey;
    Location wifiManager;
    private long countPost = 0;
    //    View view;
    private RelativeLayout relativeLayout;
    private CardView postCardView;
    private TextView textView;
    private ProgressBar progressBar;
     TextView locationName,mLocationText, mLatitudeTv, mLongitudeTv;
    private EditText mSupportedAreaEt;
    private String [] countryListIso = {"eg","sau","om","mar","usa","ind"};
    private String [] addressLanguageList = {"en","ar"};

    private Spinner mCountryListSpinner,mLanguageSpinner;
    private LinearLayout mLlResult;
    private static final int ADDRESS_PICKER_REQUEST = 1020;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        getWindow().setStatusBarColor(Color.BLACK);
        setContentView(R.layout.activity_add_new_restaurants);


        addName = findViewById(R.id.add_name);
        addDetail = findViewById(R.id.add_details);
        addPhone = findViewById(R.id.add_phone_number);
        time = findViewById(R.id.list_time);
        exitBtn = findViewById(R.id.add_exit_btn);
//        post = findViewById(R.id.add_post_button);
//        postButton = findViewById(R.id.add_post_card_button);
//        progressbar = findViewById(R.id.progress_pv_linear_colors);
        userPhoto = findViewById(R.id.user_photo);
//        view = findViewById(R.id.progress_button);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Glide.with(this).load(user.getPhotoUrl()).into(userPhoto);




        checkInternet();


//        Date date = new Date();
//        dateToSet = DateFormat.getDateInstance(DateFormat.LONG).format(date);
//        time.setText(dateToSet);
//        userRef = FirebaseDatabase.getInstance().getReference("Account");
//        Query query = userRef.orderByChild("Email").equalTo(userEmail);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    userName = "" + postSnapshot.child("UserName").getValue();
//                    user_photo = "" + postSnapshot.child("userPhoto").getValue();
//                    userEmail = "" + postSnapshot.child("Email").getValue();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        mStorageRef = FirebaseStorage.getInstance().getReference("New Adds").child("Restaurants");
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds").child("Restaurants");


//        spinner

        spn_label_Category = findViewById(R.id.spinner_label);

        List<String> ChooseCategory = new ArrayList<>();
        ChooseCategory.add(0, "Select Category ");
        ChooseCategory.add("Fast Food");
        ChooseCategory.add("Somali Restaurants");
        ChooseCategory.add("Arab Restaurants");
        ChooseCategory.add("Home Made");

        categoryArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ChooseCategory);
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_label_Category.setAdapter(categoryArrayAdapter);

        spn_label_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (parent.getItemAtPosition(position).equals("Select Category ")) {
//
//                } else {
//                    String item = parent.getSelectedItem().toString();
//                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        spinnerCity

        spinnerCity = findViewById(R.id.spinner_city);

        List<String> ChooseCity = new ArrayList<>();
        ChooseCity.add(0, "Select City ");
        ChooseCity.add("Bosaso");
        ChooseCity.add("Borama");
        ChooseCity.add("Beledweyne");
        ChooseCity.add("Bardera");
        ChooseCity.add("Burco");
        ChooseCity.add("Baydhabo");
        ChooseCity.add("Dhuusamareeb");
        ChooseCity.add("Erigavo");
        ChooseCity.add("Galkaio");
        ChooseCity.add("Garoowe");
        ChooseCity.add("Garbahare");
        ChooseCity.add("Hargeisa");
        ChooseCity.add("Hudur");
        ChooseCity.add("Jilib");
        ChooseCity.add("Jowhar");
        ChooseCity.add("Kismayo");
        ChooseCity.add("Las Anod");
        ChooseCity.add("Marka");
        ChooseCity.add("Mogadishu");
        cityArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ChooseCity);
//        cityArrayAdapter = ArrayAdapter.createFromResource(this, R.array.City, android.R.layout.simple_list_item_1);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCity.setAdapter(cityArrayAdapter);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                positionOfSelectedDataFromSpinnerCity = position;
/*
 saveValue(cityText);
                cityText = spinnerCity.getSelectedItem().toString();
*/
//                if (parent.getItemAtPosition(position).equals("Select City ")) {
//
//                } else {
//                    String item = parent.getSelectedItem().toString();
//                    Toast.makeText(parent.getContext(), item, Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        //        selectImage
        revPhoto = findViewById(R.id.rev_photo);
//        backImage = findViewById(R.id.btn_back);
//        forwardImage = findViewById(R.id.btn_forward);

        selectImage = findViewById(R.id.btn_select_image);
        mListPhoto = new ArrayList<>();

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);
//        revPhoto.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        revPhoto.setLayoutManager(gridLayoutManager);
//        revPhoto.setAdapter(photoAdapter);
//        revPhoto.setHasFixedSize(true);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openButtonPicker();
//                requestPermissions();
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(AddNewRestaurants.this,
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);
//                }
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT), MediaStore.Images.Media.INTERNAL_CONTENT_URI);
////                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent,REQUEST_FILE);

            }
        });
//        revPhoto.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
//                ImageView imageView = new ImageView(getApplicationContext());
//
//                return imageView;
//            }
//        });
//
//        backImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (position > 0) {
//                    position--;
//                    revPhoto.setImageURI(mListPhoto.get(position));
//                } else {
//                    Toast.makeText(AddNewRestaurants.this, "No Previous Image", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        forwardImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (position < mListPhoto.size() - 1) {
//                    position++;
//                    revPhoto.setImageURI(mListPhoto.get(position));
//                } else {
//                    Toast.makeText(AddNewRestaurants.this, "No More Image", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


//        post add

        relativeLayout = findViewById(R.id.add_post_card);
        postCardView = findViewById(R.id.add_post_card_button);
        textView = findViewById(R.id.add_post_button);
        progressBar = findViewById(R.id.progress_pv_linear_colors);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Please wait...");
                progressBar.setVisibility(View.VISIBLE);
//                progressButton progressButton=new progressButton(AddNewRestaurants.this,v);
//                progressButton.buttonActivated();
//                if (TextUtils.isEmpty(category)) {
//
//                    Toast.makeText(AddNewRestaurants.this, "Select Category ..", Toast.LENGTH_SHORT).show();
////                    return;
//                }
              /*  if (TextUtils.isEmpty(city)) {
                    Toast.makeText(AddNewRestaurants.this, "Select City ..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(AddNewRestaurants.this, "Enter Title ..", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddNewRestaurants.this, "Upload in progress", Toast.LENGTH_SHORT).show();

                } else {

                    uploadFile();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            progressButton.buttonFinished();
////                                    post.setText("Posted");
////                                    progressbar.setVisibility(View.INVISIBLE);
////                                    post.setTextColor(Color.parseColor("#ffffff"));
////                                    post.setVisibility(View.INVISIBLE);
////                                progressbar.setVisibility(View.VISIBLE);
////                                progressbar.setProgress(0);
//                        }
//                    }, 500);

                }


            }
        });

//        post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                post.setText("Please wait");
////                progressbar.setVisibility(View.VISIBLE);
//                if (TextUtils.isEmpty(category)) {
//
//                    Toast.makeText(AddNewRestaurants.this, "Select Category ..", Toast.LENGTH_SHORT).show();
////                    return;
//                }
//              /*  if (TextUtils.isEmpty(city)) {
//                    Toast.makeText(AddNewRestaurants.this, "Select City ..", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(name)) {
//                    Toast.makeText(AddNewRestaurants.this, "Enter Title ..", Toast.LENGTH_SHORT).show();
//                    return;
//                }*/
//                if (mUploadTask != null && mUploadTask.isInProgress()) {
//                    Toast.makeText(AddNewRestaurants.this, "Upload in progress", Toast.LENGTH_SHORT).show();
//
//                } else {
//
//                    uploadFile();
//
//
//                }
//            }
//        });


//        location
//        wifiManager = (Location) this.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        location = findViewById(R.id.add_post_location);
        mLocationText = findViewById(R.id.add_post_location_text_name);
        MapUtility.apiKey = getResources().getString(R.string.places_api_key);
//        locationName = findViewById(R.id.add_post_location_text_name);
//        locationAddress = findViewById(R.id.add_post_location_text_address);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new PlacePicker.IntentBuilder()
////                        .setLatLong(40.748672, -73.985628)
//                        .setLatLong(41.01507, 28.97643)
////                        .showLatLong(true)
////                        .setMapType(MapType.NORMAL)
////                        .build(AddNewRestaurants.this);
////                        .showLatLong(true)  // Show Coordinates in the Activity
//                        .setMapZoom(12.0f)  // Map Zoom Level. Default: 14.0
//                        .setAddressRequired(true) // Set If return only Coordinates if cannot fetch Address for the coordinates. Default: True
//                        .hideMarkerShadow(true) // Hides the shadow under the map marker. Default: False
//                        .setMarkerImageImageColor(R.color.colorPrimary)
//                        .setMapType(MapType.NORMAL)
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
//                        .build(AddNewRestaurants.this);
//                startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST);

                Intent intent = new Intent(AddNewRestaurants.this, LocationPickerActivity.class);
                startActivityForResult(intent, ADDRESS_PICKER_REQUEST);
            }

        });


    }


    //        location

    private void requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_FINE_LOCATION);
            }
        };


    }
    private void openPlacePicker() {

//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//
//
//        try {
//            startActivityForResult(builder.build(this), PICK_PLACE_REQUEST);
////            wifiManager.setWifiEnabled(false);
//
//        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
//            Log.d("Exception", e.getMessage());
//            e.printStackTrace();
//        }
//        PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
////        builder.setAndroidApiKey("YOUR_ANDROID_API_KEY")
////                .setMapsApiKey("YOUR_MAPS_API_KEY");
//        builder.setAndroidApiKey("AIzaSyDyuxHinemLbXONAK-bW4443fBWVdyFWcs\n")
//                .setMapsApiKey("AIzaSyDa-cF2-q3aOBJgewVHZKADK_SLoM0gs5E");
//
//        // If you want to set a initial location rather then the current device location.
//        // NOTE: enable_nearby_search MUST be true.
//        // builder.setLatLng(new LatLng(37.4219999, -122.0862462))
//
//        try {
//            Intent placeIntent = builder.build(this);
//            startActivityForResult(placeIntent, PICK_PLACE_REQUEST);
//        } catch (Exception ex) {
//            // Google Play services is not available...
//            Log.d("Exception", ex.getMessage());
//            ex.printStackTrace();
//        }
        // You must grant user permission for access device location first
        // please don't ignore this step >> Ignoring location permission may cause application to crash !


    }

//    private void startMapActivity(String apiKey, String[] supportedAreas){
//        Intent intent = new Intent(this, PickPlace.class);
//        Bundle bundle = new Bundle();
//
//        bundle.putString(SimplePlacePicker.API_KEY,apiKey);
////        bundle.putString(SimplePlacePicker.COUNTRY,country);
////        bundle.putString(SimplePlacePicker.LANGUAGE,language);
//        bundle.putStringArray(SimplePlacePicker.SUPPORTED_AREAS,supportedAreas);
//
//        intent.putExtras(bundle);
//        startActivityForResult(intent, SimplePlacePicker.SELECT_LOCATION_REQUEST_CODE);
//    }

   /* private void startMapActivity(String apiKey){
        Intent intent = new Intent(this, MapActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(SimplePlacePicker.API_KEY,apiKey);
//        bundle.putString(SimplePlacePicker.COUNTRY,country);
//        bundle.putString(SimplePlacePicker.LANGUAGE,language);
//        bundle.putStringArray(SimplePlacePicker.SUPPORTED_AREAS,supportedAreas);

        intent.putExtras(bundle);
        startActivityForResult(intent, SimplePlacePicker.SELECT_LOCATION_REQUEST_CODE);
    }

    private void selectLocationOnMap() {
        String apiKey = getString(R.string.places_api_key);
//        String mCountry = countryListIso[mCountryListSpinner.getSelectedItemPosition()];
//        String mLanguage = addressLanguageList[mLanguageSpinner.getSelectedItemPosition()];
//        String [] mSupportedAreas = mSupportedAreaEt.getText().toString().split(",");
        startMapActivity(apiKey);
    }

    private void updateUi(Intent data){
        mLocationText.setText(data.getStringExtra(SimplePlacePicker.SELECTED_ADDRESS));
        mLatitudeTv.setText(String.valueOf(data.getDoubleExtra(SimplePlacePicker.LOCATION_LAT_EXTRA,-1)));
        mLongitudeTv.setText(String.valueOf(data.getDoubleExtra(SimplePlacePicker.LOCATION_LNG_EXTRA,-1)));
//        mLlResult.setVisibility(View.VISIBLE);
    }

    //check for location permission
    public static boolean hasPermissionInManifest(Activity activity, int requestCode, String permissionName) {
        if (ContextCompat.checkSelfPermission(activity,
                permissionName)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(activity,
                    new String[]{permissionName},
                    requestCode);
        } else {
            return true;
        }
        return false;
    }
*/

//            select Multi Image


    private void openButtonPicker() {

        String[] option = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image from");

        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    } else {
                        PickFromCamera();
                    }
                }
                if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        PickFromGallery();
                    }
                }
            }
        });
        builder.create().show();

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    private void PickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }

    private void PickFromCamera() {
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.TITLE, "Temp Pick");
        cv.put(MediaStore.Images.Media.DESCRIPTION, "Temp Descr");
        mImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(intent, PICK_CAMERA_REQUEST_CODE);


    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission
                .CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, PICK_CAMERA_REQUEST);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermissions, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case PICK_CAMERA_REQUEST: {
                if (grantResults.length < 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted) {
                        PickFromCamera();
                    } else {
                        Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }
            break;
            case PICK_IMAGE_REQUEST: {
                if (grantResults.length < 0) {
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        PickFromGallery();
                    } else {
                        Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }
            break;
//            case MY_PERMISSION_FINE_LOCATION: {
//                if (grantResults[0]) {
//                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                    if (storageAccepted) {
//                        PickFromGallery();
//                    } else {
//                        Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//
//                }
//            }
//            break;


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_CAMERA_REQUEST_CODE) {
                revPhoto.setImageURI(mImageUri);

            } else if (requestCode == PICK_IMAGE_REQUEST_CODE) {
                mImageUri = data.getData();
                revPhoto.setImageURI(mImageUri);
            }
        }

        if (requestCode == ADDRESS_PICKER_REQUEST) {
            try {
                if (data != null && data.getStringExtra(MapUtility.ADDRESS) != null) {
                    // String address = data.getStringExtra(MapUtility.ADDRESS);
                    double currentLatitude = data.getDoubleExtra(MapUtility.LATITUDE, 0.0);
                    double currentLongitude = data.getDoubleExtra(MapUtility.LONGITUDE, 0.0);
                    Bundle completeAddress =data.getBundleExtra("fullAddress");
                    /* data in completeAddress bundle
                    "fulladdress"
                    "city"
                    "state"
                    "postalcode"
                    "country"
                    "addressline1"
                    "addressline2"
                     */

                    mLocationText.setText(new StringBuilder()
                            .append("address: ").append
                            (completeAddress.getString("address"))
                            .append("\ncity: ").append
                            (completeAddress.getString("city"))
                                    .append("\npostalcode: ").append
                            (completeAddress.getString("postalcode"))
                            .append("\nstate: ").append
                            (completeAddress.getString("state")).toString());

//                    txtLatLong.setText(new StringBuilder().append("Lat:").append(currentLatitude).append
//                            ("  Long:").append(currentLongitude).toString());

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
//        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
//            if (resultCode == Activity.RESULT_OK && data != null) {
//
//                AddressData addressData= data.getParcelableExtra(Constants.ADDRESS_INTENT);
//                ((TextView) findViewById(R.id.add_post_location_text_name)).setText(addressData.toString());
//
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }
//        if (resultCode == RESULT_OK) {
//            mImageUri = data.getData();
//            Picasso.get().load(mImageUri).into(revPhoto);
//            revPhoto.setImageURI(mImageUri);
////            Picasso.with(this).load(mImageUri).into(revPhoto);
//        }
//

//        if (requestCode == REQUEST_FILE && resultCode == RESULT_OK) {
//            ClipData clipData = data.getClipData();
//            if (clipData != null) {
////                revPhoto = data.getData();
////int totalItem=clipData.getItemCount();
//                for (int i = 0; i < clipData.getItemCount(); i++) {
//                    ClipData.Item item = clipData.getItemAt(i);
//                    mImageUri = item.getUri();
////                    Log.e("MAS JMGS: ", mImageUri.toString());
////                    getStringPath(mImageUri);
//                    PhotoHelperClass photoHelperClass = new PhotoHelperClass(mImageUri);
//                    mListPhoto.add(photoHelperClass);
//                    photoAdapter = new PhotoAdapter(AddNewRestaurants.this, mListPhoto);
//                    revPhoto.setAdapter(photoAdapter);
//
//
//                }
//            }
//        }
       /* if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getClipData() != null) {
                    int cont = data.getClipData().getItemCount();
                    for (int i = 0; i < cont; i++) {
                        mImageUri = data.getClipData().getItemAt(i).getUri();
                        mListPhoto.add(mImageUri);

                    }
//                    revPhoto.setImageURI(mListPhoto.get(0));
//                    position = 0;
                } else {
                    mImageUri = data.getData();
//                    mListPhoto.add(mImagelUri);
//                    revPhoto.setImageURI(mListPhoto.get(0));
//                    position = 0;

                }
            }*/


//        if (resultCode == RESULT_OK) {
//
//            switch (requestCode) {
//
//                case PICK_PLACE_REQUEST:
//                    Place place = PlacePicker.getPlace(AddNewRestaurants.this, data);
//                    double latitudo = place.getLatLng().latitude;
//                    double longtudo = place.getLatLng().longitude;
//
//                    String placeLatlng = String.valueOf(latitudo) + " , " + String.valueOf(longtudo);
//                    userLocation.setText(placeLatlng);
//
//            }
//        }

//        if (resultCode == PICK_PLACE_REQUEST) {
//
//            if (requestCode==RESULT_OK) {
//
//                    Place place = PlacePicker.getPlace(data,this);
//                    StringBuilder stringBuilder=new StringBuilder();
//                    String latitudo = String.valueOf(place.getLatLng().latitude);
//                    String longtudo = String.valueOf(place.getLatLng().longitude);
//                    stringBuilder.append("Latitude");
//                    stringBuilder.append(latitudo);
//                    stringBuilder.append("\n");
//                stringBuilder.append("Longitude");
//                stringBuilder.append(longtudo);
////                getAddress(latLng);
////                    String placeLatlng = String.valueOf(latitudo) + " , " + String.valueOf(longtudo);
//                    locationAddress.setText(stringBuilder);
//
//            }
//        }

//        if ((requestCode == PICK_PLACE_REQUEST) && (resultCode == RESULT_OK)) {
//            Place place = (Place) PingPlacePicker.getPlace(data);
//            if (place != null) {
//                Toast.makeText(this, "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();
//            }
//        }


/*
    private String getAddress(LatLng latLng){

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        locationAddress.setText(addresses);

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {

                ft.remove(prev);
            }
            ft.addToBackStack(null);
            DialogFragment dialogFragment = new ConfirmAddress();

            Bundle args = new Bundle();
            args.putDouble("lat", latLng.latitude);
            args.putDouble("long", latLng.longitude);
            args.putString("address", address);
            dialogFragment.setArguments(args);
            dialogFragment.show(ft, "dialog");
            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return "No Address Found";

        }


    }
*/
/*
    private String getStringPath(Uri myUri) {

        String[] filePathColuon = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(myUri, filePathColuon,
                null, null, null);
        if (cursor == null) {
            stringPath = myUri.getPath();

        } else {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColuon[0]);
            stringPath = cursor.getString(columnIndex);
            cursor.close();
        }
        return stringPath;
    }
*/


    //     spinner

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////        CustomItem item = (CustomItem) parent.getSelectedItem();
////        Toast.makeText(this, item.getSpinnerText(), Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }


//    upload Files to data base

   /* private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }*/

    private void uploadFile() {
//       mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    countPost = snapshot.getChildrenCount();
//
//                } else {
//                    countPost = 0;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        if (mImageUri != null && !addName.getText().toString().isEmpty() && !spn_label_Category.getSelectedItem().toString().isEmpty()) {

            StorageReference fileReference = mStorageRef.child(mImageUri.getLastPathSegment());
            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    relativeLayout.setBackgroundColor(postCardView.getResources().getColor(R.color.quantum_googgreen));
                                    textView.setText("Posted");
                                    progressBar.setVisibility(View.GONE);
//                                    progressButton.buttonFinished();
//                                    post.setText("Posted");
//                                    progressbar.setVisibility(View.INVISIBLE);
//                                    post.setTextColor(Color.parseColor("#ffffff"));
//                                    post.setVisibility(View.INVISIBLE);
//                                progressbar.setVisibility(View.VISIBLE);
//                                progressbar.setProgress(0);
                                }
                            }, 500);

//                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                            while (!uriTask.isComplete()) ;
//                            Uri uriImage = uriTask.getResult();
                            imageU = mImageUri.toString();


                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imageU = mImageUri.toString();
                                    String postName = addName.getText().toString();
                                    String postDetail = addDetail.getText().toString();
                                    String postPhone = addPhone.getText().toString();
                                    String postLocation = mLocationText.getText().toString();
//                                    String postUser = user.getUid();
                                    String postUserName = user.getDisplayName();
                                    String postUserPhoto = user.getPhotoUrl().toString();
                                    String postCity = spinnerCity.getSelectedItem().toString();
                                    String postCategory = spn_label_Category.getSelectedItem().toString();
                                    timeStamp = String.valueOf(System.currentTimeMillis());

                                    Upload uploadsAdds = new Upload(
                                            postName, postDetail, postPhone, postCity, postCategory, postUserName
                                            , postUserPhoto, imageU,postLocation
                                    );
//                                    uploadsAdds.setTimestamp(new Timestamp(new Date()));
//                                    String key = mDatabaseRef.getKey();
//                                    uploadsAdds.setPostKey(key);
//
//                                    mDatabaseRef.setValue(uploadsAdds).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void aVoid) {
////                Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//                                            Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//                                        post.setText("Posted");
//                                        progressbar.setVisibility(View.INVISIBLE);
//                                        post.setTextColor(Color.parseColor("#ffffff"));
//                                        Intent intent = new Intent(AddNewRestaurants.this, Restaurants.class);
//                                        startActivity(intent);
//
//                                        }
//                                    });
                                    add(uploadsAdds);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(AddNewRestaurants.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });


        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    relativeLayout.setBackgroundColor(postCardView.getResources().getColor(R.color.quantum_googred));
                    textView.setText("Error");
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.refreshDrawableState();
                    relativeLayout.clearAnimation();

                }

            }, 500);
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void add(Upload uploadsAdds) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds")
                .child("Restaurants").push();

        String key = mDatabaseRef.getKey();
        uploadsAdds.setPostKey(key);

        mDatabaseRef.setValue(uploadsAdds).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddNewRestaurants.this, Restaurants.class);
                startActivity(intent);

            }
        });
    }


//    private void add() {
//        Upload uploadsAdds = new Upload(
//                addName.getText().toString().trim(),
//                addDetail.getText().toString().trim(),
//                addPhone.getText().toString().trim(),
//                user.getUid(),
//                user.getDisplayName(),
//                user.getPhotoUrl().toString(),
//                spinnerCity.getSelectedItem().toString().trim(),
//                spn_label_Category.getSelectedItem().toString().trim(),
//                imageU
//        );
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds")
//                .child("Restaurants").push();
//
//        String key =mDatabaseRef.getKey();
//        uploadsAdds.setPostKey(key);
//
//        mDatabaseRef.setValue(uploadsAdds).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
////                Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//                Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//                                        post.setText("Posted");
//                                        progressbar.setVisibility(View.INVISIBLE);
//                                        post.setTextColor(Color.parseColor("#ffffff"));
//                                        Intent intent = new Intent(AddNewRestaurants.this, Restaurants.class);
//                                        startActivity(intent);
//
//            }
//        });
//    }

//    private void uploadFile() {
//        checkInternet();
//
//   /*    mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    countPost = snapshot.getChildrenCount();
//
//                } else {
//                    countPost = 0;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });*/
//        name = addName.getText().toString().trim();
//        detail = addDetail.getText().toString().trim();
//        phone = addPhone.getText().toString().trim();
//        category = spn_label_Category.getSelectedItem().toString().trim();
//        city = spinnerCity.getSelectedItem().toString().trim();
//        userId = user.getUid();
//        userName = user.getDisplayName();
//        user_photo = user.getPhotoUrl().toString();
//        timeStamp = String.valueOf(System.currentTimeMillis());
//        postKey = mDatabaseRef.getKey();
//
//        if (mImageUri != null) {
//            StorageReference fileReference = mStorageRef.child(mImageUri.getLastPathSegment());
//            mUploadTask = fileReference.putFile(mImageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//
////                                    post.setTextColor(Color.parseColor("#3DDC84"));
//                                }
//                            }, 500);
//                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
////                            while (!uriTask.isComplete()) ;
//                            while (!uriTask.isSuccessful()) ;
////                            Uri uriImage = uriTask.getResult();
////                            imageU=uriImage.toString();
//                            String downloadUrl = uriTask.getResult().toString();
//
//                            if (uriTask.isSuccessful()) {
//                                HashMap<Object, String> hashMap = new HashMap<>();
//                                hashMap.put("postKey", postKey);
//                                hashMap.put("aName", name);
//                                hashMap.put("aDetail", detail);
//                                hashMap.put("timestamp", timeStamp);
//                                hashMap.put("aPhone", phone);
//                                hashMap.put("aCategory", category);
//                                hashMap.put("aCity", city);
//                                hashMap.put("userId", userId);
//                                hashMap.put("userName", userName);
//                                hashMap.put("user_photo", userPhoto.toString());
//                                hashMap.put("mImageUri", downloadUrl);
////                                hashMap.put("counter", countPost);
//
//                                mDatabaseRef.child(timeStamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
////                Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//                                        Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//                                        post.setText("Posted");
//                                        progressbar.setVisibility(View.INVISIBLE);
//                                        post.setTextColor(Color.parseColor("#ffffff"));
//                                        Intent intent = new Intent(AddNewRestaurants.this, Restaurants.class);
//                                        startActivity(intent);
//
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(AddNewRestaurants.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                                    }
//                                });
//
//                            }
//
////                            add(uploadsAdds);
////                            add();
//
//
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(AddNewRestaurants.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() /
//                                    taskSnapshot.getTotalByteCount());
//                            progressbar.setProgress((int) progress);
//                        }
//                    });
//
//        } else {
//            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void add( ) {
//
//        Upload uploadsAdds = new Upload(
//                addName.getText().toString(),
//                addDetail.getText().toString(),
//                locationName.getText().toString(),
//                addPhone.getText().toString(),
//                user.getUid(),
//                user.getDisplayName(),
//                user.getPhotoUrl().toString(),
//                spinnerCity.getSelectedItem().toString(),
//                spn_label_Category.getSelectedItem().toString(),
//                imageU
//        );
//
//
//        String key =mDatabaseRef.getKey();
//        uploadsAdds.setPostKey(key);
//
//        mDatabaseRef.setValue(uploadsAdds).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
////                Toast.makeText(AddNewRestaurants.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//}


//    check the Internet

    private void checkInternet() {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewRestaurants.this);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    protected void onStart() {
        super.onStart();

        user = mAuth.getCurrentUser();
        if (user != null) {
//            Toast.makeText(this, "Post your Add", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), FaceGoogleLogin.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = mAuth.getCurrentUser();
        if (user != null) {
//            Toast.makeText(this, "Post your Add", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), FaceGoogleLogin.class);
            startActivity(intent);
        }
    }
}
