package com.example.cityguidy.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cityguidy.R;
import com.example.cityguidy.User.Profile;
import com.example.cityguidy.User.UserDashboard;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class FaceGoogleLogin extends AppCompatActivity {

    private static final String EMAIL = "email";
    private static final String TAG = "FacebookAuth";
    Button login, signin,googleSignIn;
    private LoginButton facebookSignIn;
    //     SignInButton googleSignIn;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager mCallbackManager;
//    private FirebaseUser user;
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_google_login);


        mAuth=FirebaseAuth.getInstance();
/*

//        facebook
        facebookSignIn = findViewById(R.id.facebook_sign_in_btn);
//        fbLogin = findViewById(R.id.facebook_sign_in_btn);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        facebookSignIn.setReadPermissions("email","public_profile");

        mCallbackManager = CallbackManager.Factory.create();
        facebookSignIn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess" + loginResult);
                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                startActivity(intent);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d(TAG, "onError" + exception);
            }
        });

//        setContentView(R.layout.com_facebook_activity_layout);
//        fbLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                LoginManager.getInstance().logInWithReadPermissions(Login,this, Arrays.asList("email","public_profile"));
//                if (v.getId() == R.id.facebook_sign_in) {
//
//                    facebookSignIn.performClick();
//                }
//            }
//        });


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mAuth = FirebaseAuth.getInstance();
                if (user != null) {
                    updateUI(user);

                } else {
                    updateUI(null);

                }
            }
        };

        accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null){
                    mAuth.signOut();
                }
            }
        };
*/


//google log in


        googleSignIn  = findViewById(R.id.google_sign_in);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });



//Login
        login = findViewById(R.id.email_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });

//        SignUp
        signin = findViewById(R.id.sign_in);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);

            }
        });

    }

/*    //    facebookLogIn

    private void handleFacebookToken(AccessToken accessToken) {
        Log.d(TAG, "handleFacebookToken" + accessToken);
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "sign In With Credential:Successful ");
                    user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.d(TAG, "sign In With Credential:Failed ", task.getException());
                    Toast.makeText(FaceGoogleLogin.this, "sign In Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);

                }
            }
        });

    }

   */
//        check internet connection




    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FaceGoogleLogin.this);
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



//    Google sign in


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            updateUI();

                        } else {
                            Toast.makeText(FaceGoogleLogin.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void updateUI() {
    Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivity(intent);

    }
   @Override
    protected void onStart() {
        super.onStart();

       FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            updateUI();
        }
    }


}