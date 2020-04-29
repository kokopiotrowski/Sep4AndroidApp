package com.example.sep4androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sep4androidapp.fragments.FragmentCO2;
import com.example.sep4androidapp.fragments.FragmentHumidity;
import com.example.sep4androidapp.fragments.FragmentMain;
import com.example.sep4androidapp.fragments.FragmentSound;
import com.example.sep4androidapp.fragments.FragmentTemperature;
import com.example.sep4androidapp.fragments.ViewPagerAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentMain(), "Main");
        adapter.AddFragment(new FragmentTemperature(), "Temp");
        adapter.AddFragment(new FragmentHumidity(), "Hum");
        adapter.AddFragment(new FragmentCO2(), "CO2");
        adapter.AddFragment(new FragmentSound(), "Sound");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
//      something = headerView.findViewById()

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.itemRooms) {
            Toast.makeText(this, "Open Rooms", Toast.LENGTH_SHORT).show();
            Log.i("TAG", "xxxOpen Rooms");
        }
        if(menuItem.getItemId() == R.id.itemPreferences) {
            Toast.makeText(this, "Open Prefs", Toast.LENGTH_SHORT).show();
            Log.i("TAG", "xxxOpen Prefs");
        }
        if(menuItem.getItemId() == R.id.itemSetUp) {
            Toast.makeText(this, "Set up device", Toast.LENGTH_SHORT).show();
            Log.i("TAG", "xxxSet up device");
        }
        if(menuItem.getItemId() == R.id.itemTipsFacts) {
            Toast.makeText(this, "Open Tips & Facts", Toast.LENGTH_SHORT).show();
            Log.i("TAG", "xxxOpen Tips & Facts");
        }
        if(menuItem.getItemId() == R.id.itemAccount) {
            Toast.makeText(this, "Open Account", Toast.LENGTH_SHORT).show();
            Log.i("TAG", "xxxOpen Account");
        }
        if(menuItem.getItemId() == R.id.itemLogOut) {
            Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
            Log.i("TAG", "xxxLog out");
        }

        return false;
    }


}
