package com.example.cityguidy.Common.LoginSignUp;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguidy.Database.SessionManager;
import com.example.cityguidy.R;
import com.example.cityguidy.User.Profile;
import com.example.cityguidy.User.UserDashboard;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.cityguidy.Database.SessionManager.KEY_EMAIL;
import static com.example.cityguidy.Database.SessionManager.KEY_PHONE;
import static com.example.cityguidy.Database.SessionManager.KEY_USERNAME;

public class Login extends AppCompatActivity {


    private static final String EMAIL = "email";
    private static final String TAG = "FacebookAuth";
    Button login, signin, googleSignIn, fbLogin;
    private LoginButton facebookSignIn;
    //     SignInButton googleSignIn;
    TextInputLayout email, password;
    TextView title;
    ImageView backBtn;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
     FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager mCallbackManager;
    CheckBox checkBox;
    private FirebaseUser user;
    private AccessTokenTracker accessTokenTracker;
    private int requestCode;
    private int resultCode;
    private Intent data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);

        signin = findViewById(R.id.create_account_btn);
        login = findViewById(R.id.login_btn);
//        backBtn = findViewById(R.id.login_back_icon);
//        title = findViewById(R.id.login_title);
        email = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        checkBox = findViewById(R.id.check_user);
        mAuth = FirebaseAuth.getInstance();




//        remember me
//        sessionManager=new SessionManager(getApplicationContext());
       /* SharedPreferences preferences = getSharedPreferences("checkBox", MODE_PRIVATE);
        String checkUser = preferences.getString("remember", "");

        if (checkUser.equals("true")) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Hello", "true");
            editor.apply();
            Toast.makeText(Login.this, "You already Sing in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            startActivity(intent);

        } else if (checkUser.equals("false")) {
            Toast.makeText(this, "Please Sing in", Toast.LENGTH_SHORT).show();

        }

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isChecked()) {
                SharedPreferences preferences1 = getSharedPreferences("checkBox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("remember", "true");
                editor.apply();
                Toast.makeText(Login.this, "Checked", Toast.LENGTH_SHORT).show();

            } else if (!buttonView.isChecked()) {
                SharedPreferences preferences1 = getSharedPreferences("checkBox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("remember", "false");
                editor.apply();
                Toast.makeText(Login.this, "Unchecked", Toast.LENGTH_SHORT).show();

            }
        });
*/
        login.setOnClickListener(v -> {

            final String Email = email.getEditText().getText().toString();
            final String Password = password.getEditText().getText().toString();


            if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
                Toast.makeText(Login.this, "Enter your Email and password", Toast.LENGTH_SHORT).show();
            } else {
                logIn(Email, Password);
            }
        });


    }



    private void logIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    sessionManager.createLoginSession(KEY_EMAIL,KEY_PHONE,KEY_USERNAME);
//                    sessionManager = new SessionManager(Login.this);
//                    Intent intent = new Intent(getApplicationContext(), Profile.class);
//                    startActivity(intent);
//                    sessionManager.g();
                    Toast.makeText(Login.this, "Hello  ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

/*
    public void toSignUpScreen(View view) {


        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        Pair[] pairs = new Pair[6];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_btn");
        pairs[1] = new Pair<View, String>(title, "transition_sign_up_title");
        pairs[2] = new Pair<View, String>(signin, "transition_sing_up_btn");
        pairs[3] = new Pair<View, String>(email, "username_text");
        pairs[4] = new Pair<View, String>(password, "password_text");
        pairs[5] = new Pair<View, String>(login, "transition_login_btn");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);

            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
*/


//        check internet connection


    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
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

//    Forget Password screen


    public void callForgetPasswordScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
        startActivity(intent);
        finish();
    }

//
   @Override
    protected void onStart() {
        super.onStart();

        user = mAuth.getCurrentUser();
        if (user != null) {

            Intent intent = new Intent(getApplicationContext(), Profile.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), FaceGoogleLogin.class);
        startActivity(intent);

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        user = mAuth.getCurrentUser();
//
//    }


}



