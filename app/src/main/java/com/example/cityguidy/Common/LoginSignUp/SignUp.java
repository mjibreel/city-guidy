package com.example.cityguidy.Common.LoginSignUp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguidy.Database.UserHelperClass;
import com.example.cityguidy.R;
import com.example.cityguidy.User.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Objects;

public class SignUp extends AppCompatActivity {

    ImageView backBtn;
    TextView title;
    Button signUp, login;
    CountryCodePicker countryCodePicker;
    TextInputLayout fullName, userName, mail, pasword, phone;
    FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_sign_up);

        backBtn = findViewById(R.id.signup_back_btn);
        title = findViewById(R.id.signup_title);
        signUp = findViewById(R.id.create_account_btn);
        login = findViewById(R.id.login_btn);
//        fullName = findViewById(R.id.user_full_name);
        userName = findViewById(R.id.username_sign_up);
        mail = findViewById(R.id.email);
        pasword = findViewById(R.id.password_sign_up);
        phone = findViewById(R.id.phone_number);
        countryCodePicker = findViewById(R.id.country_picker);



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String PhoneNo = phone.getEditText().getText().toString();
                final String UserName = userName.getEditText().getText().toString();
                final String email = mail.getEditText().getText().toString();
                final String password = pasword.getEditText().getText().toString();


                register(PhoneNo, UserName, email, password);

            }
        });

        firebaseAuth= FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });

    }


    private void register(final String PhoneNo, final String UserName, final String email, final String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser rUser = firebaseAuth.getCurrentUser();
                    assert rUser != null;
                    String userId = rUser.getUid();
                    reference = FirebaseDatabase.getInstance().getReference("Account");
                    HashMap<String, String> hashMap=new HashMap<>();
                    hashMap.put("userId",userId);
                    hashMap.put("PhoneNo",PhoneNo);
                    hashMap.put("UserName",UserName);
                    hashMap.put("Email",email);
                    hashMap.put("Password",password);
//                    UserHelperClass helperClass = new UserHelperClass(_fullName, _userName, _email, _password);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Hello" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }else {
                                Toast.makeText(SignUp.this, (Objects.requireNonNull(task.getException())).getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
                else {
                    Toast.makeText(SignUp.this, (Objects.requireNonNull(task.getException())).getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void call2ndSingUpScreen() {
        /*rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");
        final String FullName = fullName.getEditText().getText().toString();
        final String UserName = userName.getEditText().getText().toString();
        final String Email = email.getEditText().getText().toString();
        final String Password = password.getEditText().getText().toString();

        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser rUser = mAuth.getCurrentUser();
                    assert rUser != null;
                    String userId = rUser.getUid();
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Users").child(userId);
                  HashMap<String, String> hashMap=new HashMap<>();
                    hashMap.put("userId",userId);
                    hashMap.put("FullName",FullName);
                    hashMap.put("UserName",UserName);
                    hashMap.put("Email",Email);
                    hashMap.put("Password",Password);


                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Hello" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }else {
                                Toast.makeText(SignUp.this, (Objects.requireNonNull(task.getException())).getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
                else {
                    Toast.makeText(SignUp.this, (Objects.requireNonNull(task.getException())).getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });*/
        /* String _fullName = fullName.getEditText().getText().toString();
         String _userName = userName.getEditText().getText().toString();
         String _email = email.getEditText().getText().toString();
         String _password = Password.getEditText().getText().toString();


       *//* if (_fullName.isEmpty() || _fullName.length() < 6) {
            fullName.setError("your name is not Valid");
            fullName.requestFocus();
            return;
        }
        if (_userName.isEmpty() || _userName.length() < 6) {
            userName.setError("your userName is not Valid");
            userName.requestFocus();
            return;
        }
        if (_email.isEmpty() ) {
            Email.setError("Email is not Valid");
            Email.requestFocus();
            return;
        }

        if (_password.isEmpty() || _password.length() < 6) {
            Password.setError("Password must be 7 character");
            Password.requestFocus();
            return;
        }
        if (_phone.isEmpty()) {
            phone.setError("Enter valid phone number");
            phone.requestFocus();
            return;
        }*//*
        if (!_email.isEmpty() && _email.contains("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") ) {
            email.setError(null);

           *//* email.setError("Email is not Valid");
            email.requestFocus();*//*
            return ;
        }


        UserHelperClass helperClass = new UserHelperClass(_fullName, _userName, _email, _password);
        reference.child(_email).setValue(helperClass);

        Toast.makeText(SignUp.this, "Hello" , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

     *//*   intent.putExtra("_fullName" ,_fullName);
        intent.putExtra("_userName", _userName);*//*
        intent.putExtra("_email", _email);
//        intent.putExtra("_password", _password);
        startActivity(intent);
*/


    }


    public void call2ndSingUpScreen(View view) {


    }

/*

    private boolean validateFullName() {

        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {

            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateUsername() {

        String val = userName.getEditText().getText().toString().trim();
        String checkSpaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            userName.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            userName.setError("Username is too large!");
            return false;
        } else if (val.matches(checkSpaces)) {
            userName.setError("No White spaces are allowed!");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
         */
/*     String val = email.getEditText().getText().toString().trim();

        return (!TextUtils.isEmpty(val)&&Patterns.EMAIL_ADDRESS.matcher(val).matches());*//*

        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Email is not Valid");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }

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

    public boolean validatePhoneNumber() {

        String val = phone.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            phone.setError("Enter valid phone number");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }
*/


}