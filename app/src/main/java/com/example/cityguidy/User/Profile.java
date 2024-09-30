package com.example.cityguidy.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cityguidy.Common.LoginSignUp.FaceGoogleLogin;
import com.example.cityguidy.Common.LoginSignUp.Login;
import com.example.cityguidy.Database.SessionManager;
import com.example.cityguidy.LocationOuner.Electronics;
import com.example.cityguidy.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    Button logout, update;
    FirebaseAuth mAuth;
    TextInputLayout name, phone;
    TextView username, Email;
    ImageView imageView;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = findViewById(R.id.logout);
        update = findViewById(R.id.update_your_profile);
//        name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        username = findViewById(R.id.Username);
//        phone = findViewById(R.id.username_phone);
        imageView = findViewById(R.id.user_image);
        mAuth = FirebaseAuth.getInstance();
//        SessionManager sessionManager = new SessionManager(this);
//        HashMap<String, String> userData = sessionManager.getUserDetailFromSession();

//        String user = userData.get(SessionManager.KEY_USERNAME);
//        username.setText(user);


//        showAllData();
        Intent intent = getIntent();

        String user = intent.getStringExtra("UserName");
        String email = intent.getStringExtra("Email");

        username.setText(user);
        Email.setText(email);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

//            name.getEditText().setText(personName);
            Email.setText(personEmail);
            username.setText(personGivenName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);

        }

        mGoogleSignInClient = GoogleSignIn.getClient(Profile.this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
                userSignOut();
                SharedPreferences preferences = getSharedPreferences("checkBox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                finish();


            }
        });

//                update your profile
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    private void showAllData() {
    }

    private void userSignOut() {
        mAuth.signOut();

        Intent intent = new Intent(getApplicationContext(), FaceGoogleLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getApplicationContext(), FaceGoogleLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
        startActivity(intent);

    }
}