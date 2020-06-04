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
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.FragmentTemperature;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        TabLayout tabLayout = v.findViewById(R.id.tablayout_id);
        ViewPager viewPager = v.findViewById(R.id.viewpager_id);
        viewPager.setOffscreenPageLimit(5);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new FragmentFirstPage(), "Main");
        adapter.AddFragment(new FragmentTemperature(), "Temp");
        adapter.AddFragment(new FragmentHumidity(), "Hum");
        adapter.AddFragment(new FragmentCo2(), "CO2");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }
}
