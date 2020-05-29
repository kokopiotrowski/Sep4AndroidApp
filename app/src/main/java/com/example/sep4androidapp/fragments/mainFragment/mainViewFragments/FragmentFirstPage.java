package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.FragmentFirstPageViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentFirstPage extends Fragment {
    private Spinner spinner;
    private FragmentFirstPageViewModel viewModel;
    private TextView currentTemperature, currentHumidity, currentCO2, currentSound, timeStamp;
    private Switch deviceSwitch;

    private List<String> nameList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_firstpage, container, false);
        spinner = v.findViewById(R.id.spinner);
        deviceSwitch = v.findViewById(R.id.deviceSwitch);
        currentTemperature = v.findViewById(R.id.currentTemperature);
        currentHumidity = v.findViewById(R.id.currentHumidity);
        currentSound = v.findViewById(R.id.currentSound);
        currentCO2 = v.findViewById(R.id.currentCo2);
        timeStamp = v.findViewById(R.id.timeStamp);

        viewModel = new ViewModelProvider(this).get(FragmentFirstPageViewModel.class);

        viewModel.updateRooms();
        viewModel.getDevices().observe(getViewLifecycleOwner(), devices -> {
            nameList.clear();
            idList.clear();
            for (int i = 0; i < devices.size(); i++) {
                nameList.add(devices.get(i).getName());
                idList.add(devices.get(i).getDeviceId());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, nameList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setDeviceId(idList.get(position));
                viewModel.receiveStatus(viewModel.getDeviceId(), success -> {
                    Log.i("StartStopRepo",  "Result is: " + success);
                    deviceSwitch.setChecked(success);
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        viewModel.getRoomCondition().observe(getViewLifecycleOwner(), roomCondition -> {
            currentTemperature.setText(String.format("%.0f", roomCondition.getTemperature()) + " °C");
            currentCO2.setText(String.format("%.0f", roomCondition.getCo2()) + " ppm");
            currentHumidity.setText(String.format("%.0f", roomCondition.getHumidity()) + "%");
            currentSound.setText(String.format("%.0f", roomCondition.getSound()) + "dB");
            timeStamp.setText("Updated: " + roomCondition.getTimestamp());
        });

        deviceSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.switchCheck(isChecked);
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.stopTimer();
    }
}
