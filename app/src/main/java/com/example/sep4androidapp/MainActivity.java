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
import android.widget.Toast;

import com.example.sep4androidapp.Firebase.Firebase_Login;
import com.example.sep4androidapp.ViewModels.StartStopViewModel;
import com.example.sep4androidapp.fragments.factFragment.FactFragment;
import com.example.sep4androidapp.fragments.mainFragment.MainFragment;
import com.example.sep4androidapp.fragments.preferencesFragment.PreferencesFragment;
import com.example.sep4androidapp.fragments.reportFragment.ReportFragment;
import com.example.sep4androidapp.fragments.roomFragment.RoomsFragment;
import com.example.sep4androidapp.fragments.sleepFragment.SleepFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.itemMain);
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
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ).commit();
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
