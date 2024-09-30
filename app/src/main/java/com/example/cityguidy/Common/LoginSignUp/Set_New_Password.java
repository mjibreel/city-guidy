package com.example.cityguidy.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.cityguidy.User.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Set_New_Password extends AppCompatActivity {
    ImageView backBtn, screenIcon;
    Button ChangePassword;
    TextView title, desc;
    TextInputLayout Password, ConfirmPassword;
    Animation animation;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private  FirebaseUser user;
    String userId;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__new__password);

        screenIcon = findViewById(R.id.set_password_screen_icon);
        backBtn = findViewById(R.id.set_password_back_btn);
        title = findViewById(R.id.set_password_title);
        desc = findViewById(R.id.set_password_desc);
        Password = findViewById(R.id.set_password);
        ConfirmPassword = findViewById(R.id.set_password_confirm);
        ChangePassword = findViewById(R.id.set_password_ok);
        progressbar = findViewById(R.id.progress_pv_linear_colors);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = firebaseAuth.getCurrentUser().getUid();

        animation = AnimationUtils.loadAnimation(this, R.anim.side_anim);

        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        desc.setAnimation(animation);
        Password.setAnimation(animation);
        ConfirmPassword.setAnimation(animation);
        ChangePassword.setAnimation(animation);


        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /* final String newPassword = Objects.requireNonNull(Password.getEditText()).getText().toString();
                final String conNewPassword = Objects.requireNonNull(ConfirmPassword.getEditText()).getText().toString();

                if(newPassword.isEmpty() || conNewPassword.isEmpty()){
                    Toast.makeText(Set_New_Password.this, "All Fields are repaired", Toast.LENGTH_SHORT).show();
                }
                else if(newPassword.length() <6){
                    Toast.makeText(Set_New_Password.this, "The password should be 6 characters", Toast.LENGTH_SHORT).show();

                }else if(conNewPassword.equals(newPassword)){
                    Toast.makeText(Set_New_Password.this, "The password does not match", Toast.LENGTH_SHORT).show();

                }else{
                    changePassword(newPassword);
                }
                
            }*/
                checkInternet();


/*
 CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }*/




        if (!validatePassword() | !validateConfirmPassword()) {
            return;
        }
progressbar.setVisibility(View.VISIBLE);
        String newPassword = Password.getEditText().getText().toString().trim();
         String userId = getIntent().getStringExtra("userId");

       /* FirebaseUser rUser = firebaseAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();*/
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Account");
                assert userId != null;
                reference.child(userId).child("Password").setValue(newPassword);

        Intent intent = new Intent(getApplicationContext(), ForgetSuccessMessage.class);

        startActivity(intent);
        finish();





          /* final TextInputLayout pass = new TextInputLayout(v.getContext());

                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("The password should be 6 characters");
                passwordResetDialog.setView(pass);


                String newPassword = Password.getEditText().getText().toString().trim();
                user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Set_New_Password.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newPassword = Password.getEditText().getText().toString().trim();
                                user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Set_New_Password.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Set_New_Password.this, "Password Reset Failed", Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                        });
                        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        passwordResetDialog.create().show();

                    }

                });
            }*/
        }

            });
                                          }




/*
    private void changePassword(final String newPassword) {

        AuthCredential credential= EmailAuthProvider.getCredential(firebaseUser.getEmail(),newPassword);
        firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    firebaseUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                firebaseAuth.signOut();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else {
                                Toast.makeText(Set_New_Password.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(Set_New_Password.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
*/


    private boolean validatePassword() {

        String val = Password.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            Password.setError("Field can not be empty");
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateConfirmPassword() {

        String val = ConfirmPassword.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            ConfirmPassword.setError("Field can not be empty");
            return false;
        } else {
            ConfirmPassword.setError(null);
            ConfirmPassword.setErrorEnabled(false);
            return true;
        }
    }



/*
    public void callUpdatedScreen(View view) {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }



        if (!validatePassword() | !validateConfirmPassword()) {
            return;
        }
progressbar.setVisibility(View.VISIBLE);
        String newPassword = Password.getEditText().getText().toString().trim();
         String email = getIntent().getStringExtra("Email");

        FirebaseUser rUser = firebaseAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Account").child(userId);
        reference.child("Password").setValue(newPassword);

        Intent intent = new Intent(getApplicationContext(), ForgetSuccessMessage.class);

        startActivity(intent);
        finish();
    }
*/


    /*
          check internet connection
      */

    private void checkInternet() {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }
    }
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Set_New_Password.this);
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