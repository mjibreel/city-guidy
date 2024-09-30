package com.example.cityguidy.User;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cityguidy.Common.LoginSignUp.FaceGoogleLogin;
import com.example.cityguidy.HelperClasses.CommentHelperClass;
import com.example.cityguidy.HelperClasses.CommentListAdapter;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AddListProfile extends AppCompatActivity {
    ImageView viewPager, userPic, commentUser, like;
    TextView AddName, AddDetail, AddCity, AddPhone, AddPrice, AddCategory,
            AddLikes, postUser, postDate,addLocation;
    String Name, City, Category, Phone, Price, Section, Detail, time, UserLocation,Location;
    String image, userPhoto, userName, userId;
    String PostKey;
    String uploadsAdds;
    //     ProgressBar mProgressCircular;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference commentReference;
    private List<Upload> mUploads;
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    Button showComment;
    EditText commentText;
    CommentListAdapter commentAdapter;
    List<CommentHelperClass> mData;
    RecyclerView commentListRecycler;
    ImageView share;
    Button submit;
    TextView ratingText;
    RatingBar ratingBar;
    int ratValue;
    String temp;
    private RelativeLayout relativeLayout;
    private CardView postCardView;
    private TextView textView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.BLACK);
        setContentView(R.layout.activity_add_list_profile);

        AddName = findViewById(R.id.add_list_profile_name);
        AddDetail = findViewById(R.id.add_list_profile_detail_name);
        AddCity = findViewById(R.id.add_list_profile_town_name);
        AddPhone = findViewById(R.id.add_list_profile_phone_name);
//        AddPrice = findViewById(R.id.add_list_profile_price_name);
        addLocation = findViewById(R.id.add_post_location_text_name);
        AddCategory = findViewById(R.id.add_list_profile_Category_name);
        postUser = findViewById(R.id.add_list_profile_username);
//        postDate = findViewById(R.id.add_list_profile_date);
        viewPager = findViewById(R.id.image_ViewPager);
//        mProgressCircular = findViewById(R.id.progress_circular);
        share = findViewById(R.id.add_list_profile_share);
        like = findViewById(R.id.add_list_post_like);
        userPic = findViewById(R.id.add_list_profile_account);
//        showComment = findViewById(R.id.show_all_comments);
        commentUser = findViewById(R.id.comment_user_pic);
//        viewComments = findViewById(R.id.show_all_comments);
//        addComment = findViewById(R.id.add_comment_btn);
        commentText = findViewById(R.id.comment_box);
        relativeLayout = findViewById(R.id.add_comment_card);
        postCardView = findViewById(R.id.add_comment_button);
        textView = findViewById(R.id.add_comment_text);
        progressBar = findViewById(R.id.progress_comment);


//rating
        ratingBar = findViewById(R.id.post_rating);
        ratingText = findViewById(R.id.rating_text);
//        submit = findViewById(R.id.rating_submit);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratValue = (int) ratingBar.getRating();
                if (ratValue <= 1 && ratValue > 0) {
                    ratingText.setText(ratValue + "/5");
                } else if (ratValue <= 2 && ratValue > 0) {
                    ratingText.setText(ratValue + "/5");
                } else if (ratValue <= 3 && ratValue > 0) {
                    ratingText.setText(ratValue + "/5");
                } else if (ratValue <= 4 && ratValue > 0) {
                    ratingText.setText(ratValue + "/5");
                } else if (ratValue <= 5 && ratValue > 0) {
                    ratingText.setText(ratValue + "/5");
                }

            }
        });


        commentListRecycler = findViewById(R.id.comment_list_recycler);


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadComment();
                checkUser();

            }

        });
/*        ImageViewAdapter imageViewAdapter = new ImageViewAdapter(this);
        viewPager.setAdapter(imageViewAdapter);*/
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("New Adds");
        mStorageRef = FirebaseStorage.getInstance().getReference("New Adds");

    /*    getData();
        setData();*/

//        addLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(AddListProfile.this, RetriveMapsActivity.class);
////                startActivity(intent);
//            }
//        });

        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {

//             post user and detail

//            String Category = getIntent().getExtras().getString("userPhoto");
            Category = getIntent().getStringExtra("aCategory");
//            AddCategory.setText(mBundle.getString("aCategory"));
            AddCategory.setText(Category);

            City = getIntent().getStringExtra("aCity");
            AddCity.setText(City);


            userName = getIntent().getStringExtra("mImageUri");
            postUser.setText(userName);

//             image = getIntent().getStringExtra("userName");
            Glide.with(this).load(mBundle.getString("userName")).into(viewPager);
//            Picasso.get().load(mBundle.getString("mImageUri")).into(viewPager);
//            Picasso.get().load(mBundle.getString("userName")).into(viewPager);

//             userPhoto = getIntent().getStringExtra("userPhoto");
            Glide.with(this).load(mBundle.getString("userPhoto")).into(userPic);
//            Picasso.get().load(mBundle.getString("userPhoto")).into(userPic);

            Name = getIntent().getStringExtra("aName");
            AddName.setText(Name);

            Detail = getIntent().getStringExtra("aDetail");
            AddDetail.setText(Detail);

            Location = getIntent().getStringExtra("aLocation");
            addLocation.setText(Location);

            Phone = getIntent().getStringExtra("aPhone");
            AddPhone.setText(Phone);

//              UserLocation = getIntent().getStringExtra("aLocation");

            PostKey = getIntent().getStringExtra("postKey");
//             userId = getIntent().getStringExtra("aPhone");

//              time = timestampToString(getIntent().getExtras().getLong("timestamp"));
//              time = getIntent().getStringExtra("timestamp");
//            postDate.setText(time);


//             comment user photo
            Glide.with(this).load(user.getPhotoUrl()).into(commentUser);


//      postUser = getIntent().getStringExtra("Category");
//     Section = getIntent().getStringExtra("aSection");
//             Picasso.get().load(mBundle.getString("image")).
//                     into(viewPager);


            /* Glide.with(this)
                     .load(mBundle.getString("aImage")
                     .into(viewPager),*/
//viewPager.setImageResource(mBundle.getInt("image"));
//             addLocation.getMapAsync(mBundle.getString("Location"));
//             postDate.setText(mBundle.getString("Time"));


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


     /*   ImageSlider = findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel());
        imageSlider.setImageList(slideModels,true);*/
//        Intent intent=getIntent();

//like
      like.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              postLike();

          }
      });


        commentList();

        showComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),CommentList.class);
//                startActivity(intent);
            }
        });

    }




    private void uploadComment() {
        textView.setText("Please wait...");
        progressBar.setVisibility(View.VISIBLE);
//                uploadComment();
//                addComment.setVisibility(View.INVISIBLE);


        commentReference = FirebaseDatabase.getInstance().getReference("Comment").child(PostKey)
                .push();

        String commentContact = commentText.getText().toString();
        String rateText = ratingText.getText().toString();
//        String uid=user.getUid();
        String uName = user.getDisplayName();
        String uImg = user.getPhotoUrl().toString();
        CommentHelperClass commentHelperClass = new CommentHelperClass(
//                        commentText.getText().toString().trim(),
//                        ratingText.getText().toString().trim(),
//                        user.getUid(),
//                        user.getDisplayName(),
//                        user.getPhotoUrl().toString()
                commentContact, rateText, uName, uImg
        );

                String key = commentReference.getKey();
                commentHelperClass.setPostKey(key);

        commentReference.setValue(commentHelperClass).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

//                        addComment.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setBackgroundColor(postCardView.getResources().getColor(R.color.quantum_googgreen));
                        textView.setText("Thank you");
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(AddListProfile.this, "Comment Added", Toast.LENGTH_SHORT).show();

                        commentContact.isEmpty();
                        rateText.isEmpty();
relativeLayout.refreshDrawableState();
//                        Toast.makeText(mContext, "Comment Added", Toast.LENGTH_SHORT).show();
//                        commentText.setText("");
                    }
                }, 500);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext, "failed to add Comment :" + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void checkUser() {
        user = mAuth.getCurrentUser();
        if (user != null) {
//            Toast.makeText(this, "Post your Add", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), FaceGoogleLogin.class);
            startActivity(intent);
        }
    }

//    private void uploadComment() {
//        String comment_content = commentText.getText().toString();
//        String uid=user.getUid();
//        String uName=user.getDisplayName();
//        String uImg=user.getPhotoUrl().toString();
//        String timeStamp = String.valueOf(System.currentTimeMillis());
//
//        HashMap<Object, String> hashMap = new HashMap<>();
//        hashMap.put("commentContact", comment_content);
//        hashMap.put("uid", uid);
//        hashMap.put("uName", uName);
//        hashMap.put("uImg", uImg);
//        hashMap.put("timestamp", timeStamp);
//
//        commentReference.child(timeStamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(mContext, "Comment Added", Toast.LENGTH_SHORT).show();
//                commentText.setText("");
//                addComment.setVisibility(View.VISIBLE);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(mContext, "failed to add Comment :" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }

    //    like
    private void postLike() {
        user = mAuth.getCurrentUser();

        DatabaseReference likeRef = FirebaseDatabase.getInstance().
                getReference().child("like").child(PostKey);

        likeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(user.getUid()).exists()) {
                    like.setImageResource(R.drawable.liked);
                    like.setTag("Liked");
                } else {
                    like.setImageResource(R.drawable.like);
                    like.setTag(" un Liked");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//    private void postLike() {
//
//    }

//post time

    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
//        String date = DateFormat.format("hh:mm", calendar).toString();
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;
    }


    //        comment

    private void commentList() {
//        commentListRecycler.setHasFixedSize(true);
        commentListRecycler.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = FirebaseDatabase.getInstance()
                .getReference("Comment").child(PostKey);

        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mData = new ArrayList<>();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    CommentHelperClass commentHelper = snap.getValue(CommentHelperClass.class);
                    mData.add(commentHelper);


                }

                Collections.reverse(mData);
                commentAdapter = new CommentListAdapter(getApplicationContext(), mData);
                commentAdapter.notifyDataSetChanged();
                commentListRecycler.setAdapter(commentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

   /* private void getData() {
        if (getIntent().hasExtra("image") && getIntent().hasExtra("Name")
                && getIntent().hasExtra("City") && getIntent().hasExtra("Price")
        ) {

//      Category = getIntent().getStringExtra("aCategory");
//     Section = getIntent().getStringExtra("aSection");
            City = getIntent().getStringExtra("aCity");
            image = getIntent().getIntExtra("viewPager", 10);
            Name = getIntent().getStringExtra("aName");
//     Detail= getIntent().getStringExtra("aDetail");
//     Price = getIntent().getStringExtra("aPhone");
            Phone = getIntent().getStringExtra("aPrice");


        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        viewPager.setCurrentItem(image);
        AddName.setText(Name);
//        AddDetail.setText(Detail);
        AddPrice.setText(Price);
//        AddPhone.setText(Phone);
//        AddCategory.setText(Category);
        AddCity.setText(City);
//        AddSection.setText(  Section);

    }
*/

}
