package com.example.sep4androidapp.fragments.setUpDeviceFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.SetUpDeviceViewModel;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.FragmentFirstPage;

public class SetUpDeviceFragment extends Fragment {
    private SetUpDeviceViewModel viewModel;
    private TextView availableDevices;
    private EditText deviceId, newRoomName;
    private Button setupDevice;
    private String deviceNameToSend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setupdevice, container, false);
        availableDevices = view.findViewById(R.id.availableDevices);
        deviceId = view.findViewById(R.id.deviceId);
        newRoomName = view.findViewById(R.id.newRoomName);
        setupDevice = view.findViewById(R.id.setupDevice);

        viewModel = new ViewModelProvider(this).get(SetUpDeviceViewModel.class);

        viewModel.getAvailableDevices().observe(getViewLifecycleOwner(), strings -> {
            availableDevices.setText("");
            for (int i = 0; i < strings.size(); i++) {
                availableDevices.append(strings.get(i) + "\n");
            }
        });

        viewModel.getMessage().observe(getViewLifecycleOwner(), s -> {
            if (s.equals("success")) {
                Bundle args = new Bundle();
                args.putString("deviceName", deviceNameToSend);
                FragmentFirstPage fragment = new FragmentFirstPage();
                fragment.setArguments(args);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            } else if(!s.isEmpty()) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }

        });

        setupDevice.setOnClickListener(v -> {

            if (!deviceId.getText().toString().isEmpty() && !newRoomName.getText().toString().isEmpty()) {
                NewDeviceModel model = new NewDeviceModel(deviceId.getText().toString(), newRoomName.getText().toString());
                deviceNameToSend = newRoomName.getText().toString();
                viewModel.postNewDevice(new NewDeviceModel(deviceId.getText().toString(), newRoomName.getText().toString()));
            } else {
                Toast.makeText(getActivity(), "Fill up all the required fields", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.updateAvailableDevices();
        return view;
    }
}