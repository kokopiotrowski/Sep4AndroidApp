package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.StatStopViewModel;

public class FragmentCo2 extends Fragment {
    StatStopViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_co2, container, false);

        Button button= v.findViewById(R.id.start);
        Button buttonStop= v.findViewById(R.id.stop);
        viewModel = new ViewModelProvider(this).get(StatStopViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.start();

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.stop();

            }
        });

        return v;
    }
}
