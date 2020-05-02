package com.example.sep4androidapp.fragments.mainFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.sep4androidapp.R;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.FragmentCo2;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.FragmentHumidity;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.FragmentFirstPage;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.FragmentSound;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.FragmentTemperature;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new FragmentFirstPage(), "Main");
        adapter.AddFragment(new FragmentTemperature(), "Temp");
        adapter.AddFragment(new FragmentHumidity(), "Hum");
        adapter.AddFragment(new FragmentCo2(), "CO2");
        adapter.AddFragment(new FragmentSound(), "Sound");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }
}
