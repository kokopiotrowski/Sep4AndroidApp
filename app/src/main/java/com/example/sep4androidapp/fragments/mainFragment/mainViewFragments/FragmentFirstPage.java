package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.FragmentFirstPageViewModel;
import com.example.sep4androidapp.fragments.factFragment.FactFragmentDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentFirstPage extends Fragment {
    private Spinner spinner;
    private FragmentFirstPageViewModel viewModel;
    private TextView currentTemperature, currentHumidity, currentCO2, timeStamp;
    private TextView expectedTemperature, expectedHumidity, expectedCO2;
    private Switch deviceSwitch;
    private FactFragmentDialog factFragmentDialog = new FactFragmentDialog();
    private FloatingActionButton floatingButton;
    private ImageView temperatureStatus, humidityStatus, CO2Status;

    private List<String> nameList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private double temp, humidity, co2;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_firstpage, container, false);
        spinner = v.findViewById(R.id.spinner);
        deviceSwitch = v.findViewById(R.id.deviceSwitch);
        currentTemperature = v.findViewById(R.id.currentTemperature);
        currentHumidity = v.findViewById(R.id.currentHumidity);
        currentCO2 = v.findViewById(R.id.currentCo2);
        timeStamp = v.findViewById(R.id.timeStamp);
        floatingButton = v.findViewById(R.id.floatingButton);
        expectedTemperature = v.findViewById(R.id.expectedTemperature);
        expectedHumidity = v.findViewById(R.id.expectedHumidity);
        expectedCO2 = v.findViewById(R.id.expectedCo2);
        temperatureStatus = v.findViewById(R.id.temperatureStatus);
        humidityStatus = v.findViewById(R.id.humidityStatus);
        CO2Status = v.findViewById(R.id.Co2Status);

        viewModel = new ViewModelProvider(this).get(FragmentFirstPageViewModel.class);

        viewModel.getFact().observe(getViewLifecycleOwner(), fact -> {
            Bundle args = new Bundle();
            args.putString("title", fact.getTitle());
            args.putString("content", fact.getContent());
            args.putString("source", fact.getSource());
            args.putString("url", fact.getSourceUrl());

            factFragmentDialog.setArguments(args);
            factFragmentDialog.show(getChildFragmentManager(), "Chosen");
        });

        viewModel.getDevices().observe(getViewLifecycleOwner(), devices -> {

            nameList.clear();
            idList.clear();
            for (int i = 0; i < devices.size(); i++) {
                nameList.add(devices.get(i).getName());
                idList.add(devices.get(i).getDeviceId());
            }
            adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, nameList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            if (getArguments() != null) {
                spinner.setSelection(adapter.getPosition(getArguments().getString("deviceName")));
            }
        });

        viewModel.getRoomCondition().observe(getViewLifecycleOwner(), roomCondition -> {
            currentTemperature.setText(String.format("%.0f", roomCondition.getTemperature()) + " °C");
            currentCO2.setText(String.format("%.0f", roomCondition.getCo2()) + " ppm");
            currentHumidity.setText(String.format("%.0f", roomCondition.getHumidity()) + "%");
            timeStamp.setText("Updated: " + roomCondition.getTimestamp());
            temp = roomCondition.getTemperature();
            Log.i("TAG", "TEMPERATURE: " + temp);
            humidity = roomCondition.getHumidity();
            co2 = roomCondition.getHumidity();
            //Following line might not be needed
            viewModel.showPreferences(viewModel.getDeviceId());
        });

        viewModel.getPreferences().observe(getViewLifecycleOwner(), preferences -> {
            expectedTemperature.setText(preferences.getTemperatureMin() + " - " + preferences.getTemperatureMax());
            expectedHumidity.setText(preferences.getHumidityMin() + " - " + preferences.getHumidityMax());
            expectedCO2.setText(" < " + preferences.getCo2Max());
            setIcons(preferences);
        });

        viewModel.updateRooms();
        setListeners();

        return v;
    }

    private void setIcons(Preferences preferences) {
        if(preferences.getTemperatureMin() > temp)
        {
            temperatureStatus.setImageResource(R.drawable.lower);
        }else if(preferences.getTemperatureMax() < temp)
        {
            temperatureStatus.setImageResource(R.drawable.higher);
        }else{
            temperatureStatus.setImageResource(R.drawable.correct);
        }

        if(preferences.getHumidityMin() > humidity)
        {
            humidityStatus.setImageResource(R.drawable.lower);
        }else if(preferences.getHumidityMax() < humidity)
        {
            humidityStatus.setImageResource(R.drawable.higher);
        }else{
            humidityStatus.setImageResource(R.drawable.correct);
        }

        if(preferences.getCo2Max() < co2)
        {
            CO2Status.setImageResource(R.drawable.higher);
        }else{
            CO2Status.setImageResource(R.drawable.correct);
        }
    }

    private void setListeners() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentTemperature.setText("-");
                currentCO2.setText("-");
                currentHumidity.setText("-");
                timeStamp.setText("No data");

                viewModel.updateRoomCondition(idList.get(position));
                viewModel.showPreferences(idList.get(position));
                viewModel.setChosenDeviceId(idList.get(position));
                viewModel.receiveStatus(viewModel.getDeviceId(), success -> {
                    Log.i("StartStopRepo", "Result is: " + success);
                    deviceSwitch.setChecked(success);
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        deviceSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.switchCheck(isChecked);
        });

        floatingButton.setOnClickListener(v1 -> {
            viewModel.getFactRandomly();
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.stopTimer();
    }

}
