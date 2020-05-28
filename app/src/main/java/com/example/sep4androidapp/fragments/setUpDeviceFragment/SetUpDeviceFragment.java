package com.example.sep4androidapp.fragments.setUpDeviceFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.SetUpDeviceViewModel;

import java.util.List;

public class SetUpDeviceFragment extends Fragment {
    private SetUpDeviceViewModel viewModel;
    private TextView availableDevices;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setupdevice, container, false);
        availableDevices = view.findViewById(R.id.availableDevices);



        viewModel = new ViewModelProvider(this).get(SetUpDeviceViewModel.class);
        viewModel.updateAvailableDevices();
        viewModel.getAvailableDevices().observe(getViewLifecycleOwner(), strings -> {
            for (int i = 0; i < strings.size(); i++)
            {
                availableDevices.append(strings.get(i) + "\n");
            }

        });
        return view;
    }
}