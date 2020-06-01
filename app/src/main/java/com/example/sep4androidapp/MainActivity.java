package com.example.sep4androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sep4androidapp.Firebase.Firebase_Login;
import com.example.sep4androidapp.ViewModels.StartStopViewModel;
import com.example.sep4androidapp.fragments.factFragment.FactFragment;
import com.example.sep4androidapp.fragments.mainFragment.MainFragment;
import com.example.sep4androidapp.fragments.preferencesFragment.PreferencesFragment;
import com.example.sep4androidapp.fragments.reportFragment.ReportFragment;
import com.example.sep4androidapp.fragments.roomFragment.RoomsFragment;
import com.example.sep4androidapp.fragments.setUpDeviceFragment.SetUpDeviceFragment;
import com.example.sep4androidapp.fragments.sleepFragment.SleepFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView email;
    TextView name;
    ImageView profilePic;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        email = headerView.findViewById(R.id.email);
        name = headerView.findViewById(R.id.name);
        profilePic = headerView.findViewById(R.id.profilePic);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.itemMain);
        }
        mAuth = FirebaseAuth.getInstance();
        userInfoUpdate(mAuth.getCurrentUser());
    }

    public void userInfoUpdate(FirebaseUser user)
    {
        mAuth = FirebaseAuth.getInstance();
        if(user != null)
        {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photo = String.valueOf(user.getPhotoUrl());

            this.name.setText(name);
            this.email.setText(email);

            if(user.getPhotoUrl() == null)
            {
                Picasso.get().load(R.drawable.sleep).into(this.profilePic);
            }else {
                Picasso.get().load(photo).into(this.profilePic);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.itemMain:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
                break;
            case R.id.itemReports:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ReportFragment.newInstance()).commit();
                break;
            case R.id.itemRooms:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RoomsFragment()).commit();
                break;
            case R.id.itemPreferences:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PreferencesFragment()).commit();
                break;
            case R.id.itemSetUp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SetUpDeviceFragment()).commit();
                break;
            case R.id.itemTipsFacts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FactFragment()).commit();
                break;
            case R.id.itemAccount:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ).commit();
                break;
            case R.id.itemSleep:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SleepFragment()).commit();
                break;
            case R.id.itemLogOut:
                AuthUI.getInstance().signOut(this).addOnCompleteListener(
                        new OnCompleteListener< Void >() {
                            @Override
                            public void onComplete(@NonNull Task< Void > task) {
                                Toast.makeText(getApplicationContext(),"You are now signed out",
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Firebase_Login.class));
                                finish();
                            }
                        }
                );
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
