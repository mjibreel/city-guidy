package com.example.cityguidy.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityguidy.Database.CheckInternet;
import com.example.cityguidy.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgetPassword extends AppCompatActivity {

    ImageView backBtn, screenIcon;
    Button next;
    TextView title, desc;
    TextInputLayout Email;
    Animation animation;
    ProgressBar progressbar;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password2);

        screenIcon = findViewById(R.id.forget_password_screen_icon);
        backBtn = findViewById(R.id.forget_password_back_btn);
        next = findViewById(R.id.forget_password_next_btn);
        title = findViewById(R.id.forget_password_text);
        desc = findViewById(R.id.forget_password_desc);
        Email = findViewById(R.id.forget_password_email);
        progressbar = findViewById(R.id.progress_pv_linear_colors);

        animation = AnimationUtils.loadAnimation(this,R.anim.side_anim);

        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        desc.setAnimation(animation);
        Email.setAnimation(animation);
        next.setAnimation(animation);



        firebaseAuth= FirebaseAuth.getInstance();

    }

    private boolean validateEmail() {

        String val = Email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            Email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            Email.setError("Email is not Valid");
            return false;
        } else {

            Email.setError(null);
            Email.setErrorEnabled(false);
            return true;
        }

    }

    public void callPasswordUpdateScreen(View view) {

        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }


        if (! validateEmail()) {
            return;
        }
        progressbar.setVisibility(View.VISIBLE);


         final  String email = Email.getEditText().getText().toString().trim();
        final String userId = getIntent().getStringExtra("userId");

/*        FirebaseUser rUser = firebaseAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();*/
        assert userId != null;
        Query checkUser = FirebaseDatabase.getInstance().getReference("Account").child(userId);

        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Email.setError(null);
                    Email.setErrorEnabled(false);

                    Intent intent = new Intent(getApplicationContext(), Set_New_Password.class);

                    intent.putExtra("email", email);
                    intent.putExtra("userId", userId);
                    intent.putExtra("whatToDo", "updateData");

                    startActivity(intent);
                    finish();

                    progressbar.setVisibility(View.GONE);


                } else {
                    progressbar.setVisibility(View.GONE);
                    Email.setError(" No such User exist");
                    Email.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ForgetPassword.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    /*
          check internet connection
      */


    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPassword.this);
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
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}