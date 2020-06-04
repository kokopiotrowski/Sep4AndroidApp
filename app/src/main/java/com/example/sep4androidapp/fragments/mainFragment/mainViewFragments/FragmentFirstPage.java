package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.LocalStorage.ConnectionLiveData;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.FragmentFirstPageViewModel;
import com.example.sep4androidapp.fragments.factFragment.FactFragmentDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class FragmentFirstPage extends Fragment {
    private Spinner spinner;
    private FragmentFirstPageViewModel viewModel;
    private TextView currentTemperature, currentHumidity,
            currentCO2, timeStamp, expectedTemperature, expectedHumidity, expectedCO2;
    private Switch deviceSwitch;

    private FloatingActionButton randomFactButton;
    private ImageView temperatureStatus, humidityStatus, CO2Status;
    private FactFragmentDialog factFragmentDialog = new FactFragmentDialog();

    private List<String> nameList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();
    private List<NewDeviceModel> localList = new ArrayList<>();
    private List<NewDeviceModel> apiList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private double temp, humidity, co2;
    private boolean isConnected, isActiveFragment;
    private Fact savedFact;

    @SuppressLint({"SetTextI18n", "DefaultLocale", "RestrictedApi"})
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
        randomFactButton = v.findViewById(R.id.randomFactButton);
        expectedTemperature = v.findViewById(R.id.expectedTemperature);
        expectedHumidity = v.findViewById(R.id.expectedHumidity);
        expectedCO2 = v.findViewById(R.id.expectedCo2);
        temperatureStatus = v.findViewById(R.id.temperatureStatus);
        humidityStatus = v.findViewById(R.id.humidityStatus);
        CO2Status = v.findViewById(R.id.Co2Status);

        viewModel = new ViewModelProvider(this).get(FragmentFirstPageViewModel.class);
        viewModel.getFactRandomly();
        viewModel.getFact().observe(getViewLifecycleOwner(), fact -> {
            savedFact = new Fact(fact.getTitle(), fact.getContent(), fact.getSource(), fact.getSourceUrl());
        });

        @SuppressLint("RestrictedApi") ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(getActivity(), connection -> {
            if (isActiveFragment && connection != null) {

                if (connection.getIsConnected()) {
                    isConnected = true;
                    deviceSwitch.setEnabled(true);
                    randomFactButton.setVisibility(FloatingActionButton.VISIBLE);
                    temperatureStatus.setVisibility(ImageView.VISIBLE);
                    humidityStatus.setVisibility(ImageView.VISIBLE);
                    CO2Status.setVisibility(ImageView.VISIBLE);
                } else {
                    isConnected = false;
                    deviceSwitch.setEnabled(false);
                    randomFactButton.setVisibility(FloatingActionButton.INVISIBLE);
                    temperatureStatus.setVisibility(ImageView.INVISIBLE);
                    humidityStatus.setVisibility(ImageView.INVISIBLE);
                    CO2Status.setVisibility(ImageView.INVISIBLE);
                }
                viewModel.updateRooms();
                refreshSpinner();
            }
        });

        viewModel.getAllLocalDevices().observe(getViewLifecycleOwner(), savedDevices -> {
            localList.clear();
            localList = savedDevices;
            refreshSpinner();
        });

        randomFactButton.setOnClickListener(v1 -> {
            Bundle args = new Bundle();
            args.putString("title", savedFact.getTitle());
            args.putString("content", savedFact.getContent());
            args.putString("source", savedFact.getSource());
            args.putString("url", savedFact.getSourceUrl());
            factFragmentDialog.setArguments(args);
            factFragmentDialog.show(getParentFragmentManager(), "Random");
            viewModel.getFactRandomly();
        });


        viewModel.getDevicesFromApi().observe(getViewLifecycleOwner(), devices -> {

            apiList.clear();
            List<NewDeviceModel> formattedDeviceList = new ArrayList<>();
            for (int i = 0; i < devices.size(); i++) {
                formattedDeviceList.add(new NewDeviceModel(devices.get(i).getDeviceId(), devices.get(i).getName()));
            }
            apiList = formattedDeviceList;

            if (getArguments() != null) {
                spinner.setSelection(adapter.getPosition(getArguments().getString("deviceName")));
            }
            refreshSpinner();
        });

        viewModel.getPreferencesFromApi().observe(getViewLifecycleOwner(), preferences -> {
            if (isConnected) {
                expectedTemperature.setText(preferences.getTemperatureMin() + " - " + preferences.getTemperatureMax());
                expectedHumidity.setText(preferences.getHumidityMin() + " - " + preferences.getHumidityMax());
                expectedCO2.setText(" < " + preferences.getCo2Max());
                setIcons(preferences);
            }
        });

        viewModel.getRoomCondition().observe(getViewLifecycleOwner(), roomCondition -> {
            currentTemperature.setText(String.format("%.0f", roomCondition.getTemperature()) + " °C");
            currentCO2.setText(String.format("%.0f", roomCondition.getCo2()) + " ppm");
            currentHumidity.setText(String.format("%.0f", roomCondition.getHumidity()) + "%");
            timeStamp.setText("Updated: " + roomCondition.getTimestamp());
            temp = roomCondition.getTemperature();

            humidity = roomCondition.getHumidity();
            co2 = roomCondition.getHumidity();

            viewModel.showPreferences(viewModel.getDeviceId());
        });
        viewModel.updateRooms();
        setListeners();

        return v;
    }

    private void refreshSpinner() {
        nameList.clear();
        idList.clear();
        if (isConnected) {
            for (int i = 0; i < apiList.size(); i++) {
                nameList.add(apiList.get(i).getName());
                idList.add(apiList.get(i).getDeviceId());
            }
        } else {
            for (int i = 0; i < localList.size(); i++) {
                nameList.add(localList.get(i).getName());
                idList.add(localList.get(i).getDeviceId());
            }
        }
        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, nameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setIcons(Preferences preferences) {
        if (preferences.getTemperatureMin() > temp) {
            temperatureStatus.setImageResource(R.drawable.lower);
        } else if (preferences.getTemperatureMax() < temp) {
            temperatureStatus.setImageResource(R.drawable.higher);
        } else {
            temperatureStatus.setImageResource(R.drawable.correct);
        }

        if (preferences.getHumidityMin() > humidity) {
            humidityStatus.setImageResource(R.drawable.lower);
        } else if (preferences.getHumidityMax() < humidity) {
            humidityStatus.setImageResource(R.drawable.higher);
        } else {
            humidityStatus.setImageResource(R.drawable.correct);
        }

        if (preferences.getCo2Max() < co2) {
            CO2Status.setImageResource(R.drawable.higher);
        } else {
            CO2Status.setImageResource(R.drawable.correct);
        }
    }

    private void setListeners() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentTemperature.setText("-");
                currentCO2.setText("-");
                currentHumidity.setText("-");
                timeStamp.setText("No data");

                viewModel.updateRoomCondition(idList.get(position));
                viewModel.showPreferences(idList.get(position));
                viewModel.setChosenDeviceId(idList.get(position));

                if (!isConnected) {
                    Preferences preferences = viewModel.getPreferencesById(idList.get(position));
                    if (preferences == null) {
                        expectedTemperature.setText("-");
                        expectedHumidity.setText("-");
                        expectedCO2.setText("-");
                    } else {
                        expectedTemperature.setText(preferences.getTemperatureMin() + " - " + preferences.getTemperatureMax());
                        expectedHumidity.setText(preferences.getHumidityMin() + " - " + preferences.getHumidityMax());
                        expectedCO2.setText(" < " + preferences.getCo2Max());
                    }
                }
                viewModel.receiveStatus(viewModel.getDeviceId(), success -> deviceSwitch.setChecked(success));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        deviceSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.switchCheck(isChecked));
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.stopTimer();
        isActiveFragment = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isActiveFragment = true;
        viewModel.getFactRandomly();
    }
}
