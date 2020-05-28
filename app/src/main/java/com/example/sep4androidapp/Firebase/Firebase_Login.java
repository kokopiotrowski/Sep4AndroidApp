package com.example.sep4androidapp.Firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sep4androidapp.MainActivity;
import com.example.sep4androidapp.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class Firebase_Login extends AppCompatActivity {


    private FirebaseAuth _mFirebaseAuth;
    private FirebaseUser user;
    public static final int RC_SIGN_IN = 10;

    // Choose authentication providers
    List< AuthUI.IdpConfig > providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_auth);
        _mFirebaseAuth = FirebaseAuth.getInstance();
        user = _mFirebaseAuth.getCurrentUser();

        if (user != null) {
//            Toast.makeText(getApplicationContext(), "welcome, You are already logged in",
//                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));

        } else {
            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance().
                            createSignInIntentBuilder().
                            setAvailableProviders(providers).setIsSmartLockEnabled(false).
                            build(), RC_SIGN_IN);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IdpResponse response = IdpResponse.fromResultIntent(data);
        if (requestCode == RC_SIGN_IN) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(Firebase_Login.this, "You are now signed in",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, MainActivity.class));

            } else {
                if (response == null) {
                    return;
                }
                if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    return;
                }
            }
        }
    }
}






















