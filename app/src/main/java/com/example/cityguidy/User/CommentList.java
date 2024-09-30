package com.example.cityguidy.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cityguidy.HelperClasses.AddsList.AddsListAdapter;
import com.example.cityguidy.HelperClasses.CommentHelperClass;
import com.example.cityguidy.HelperClasses.CommentListAdapter;
import com.example.cityguidy.HelperClasses.Upload;
import com.example.cityguidy.LocationOuner.Restaurants;
import com.example.cityguidy.R;
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
import java.util.Collections;
import java.util.List;

public class CommentList extends AppCompatActivity {


    private ProgressBar mProgressCircular;
    private DatabaseReference mDatabaseRef;
    private List<CommentHelperClass> mData;
    RecyclerView commentListRecycler;
    CommentListAdapter adapter;
    SwipeRefreshLayout refresh;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
//    String PostKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);




        //    main screen
        commentListRecycler = findViewById(R.id.add_list_recycler);
        mProgressCircular = findViewById(R.id.progress_circular);


//        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
//        chipNavigationBar.setItemSelected(R.id.bottom_nav_menu, true);

//        db = FirebaseFirestore.getInstance();

//        mDatabaseRef=db.collection("Account").document(userId);

//        Collections.sort(mUploads);
        commentListRecycler.setHasFixedSize(true);
        commentListRecycler.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();

//        mStorageRef = FirebaseStorage.getInstance().getReference("Comment");


        commentList();


//        DatabaseReference commentReference=FirebaseDatabase.getInstance().getReference("Comment");
//        commentReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mData=new ArrayList<>();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    CommentHelperClass commentHelperClass = postSnapshot.getValue(CommentHelperClass.class);
//                    mData.add(commentHelperClass);
//
//
//                }
//                adapter = new CommentListAdapter(getApplicationContext(), mData);
////                adapter.notifyDataSetChanged();
//                commentListRecycler.setAdapter(adapter);
//                mProgressCircular.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(CommentList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                mProgressCircular.setVisibility(View.INVISIBLE);
//
//            }
//        });


//refresh
        refresh = findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                refresh.setRefreshing(false);
            }
        });


    }


//    comment


    private void commentList() {
//        commentListRecycler.setHasFixedSize(true);
//        commentListRecycler.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference commentRef = FirebaseDatabase.getInstance()
                .getReference("Comment");
mProgressCircular.setVisibility(View.INVISIBLE);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mData = new ArrayList<>();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    CommentHelperClass commentHelper = snap.getValue(CommentHelperClass.class);
                    mData.add(commentHelper);


                }
                Collections.reverse(mData);
                adapter = new CommentListAdapter(getApplicationContext(), mData);
                adapter.notifyDataSetChanged();
                commentListRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }


}
