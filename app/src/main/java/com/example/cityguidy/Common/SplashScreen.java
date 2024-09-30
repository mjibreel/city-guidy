package com.example.cityguidy.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityguidy.Common.LoginSignUp.Login;
import com.example.cityguidy.R;
import com.example.cityguidy.User.UserDashboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    ImageView backgroundImage;
    TextView poweredByLine;
    Animation sideAnim, buttomAnim;
    SharedPreferences onBoardingScreen;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        setContentView(R.layout.spalash_screen);

        backgroundImage = findViewById(R.id.background_image);
        poweredByLine = findViewById(R.id.powered_by_line);


//        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
//        buttomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
//
//        backgroundImage.setAnimation(sideAnim);
//        poweredByLine.setAnimation(buttomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);



                if (isFirstTime) {
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    Intent intent = new Intent(SplashScreen.this, OnBoarding.class);
                    startActivity(intent);
                }
               else {
                    Intent intent = new Intent(SplashScreen.this, UserDashboard.class);
                    startActivity(intent);

                }
                finish();
            }
        }, 3000);


    }
}