package com.example.sep4androidapp.fragments.preferencesFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.LocalStorage.ConnectionLiveData;
import com.example.sep4androidapp.LocalStorage.ConnectionModel;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.PreferencesViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class PreferencesFragment extends Fragment {

    private Spinner spinner;
    private PreferencesViewModel viewModel;
    private Button save;
    private EditText MintempEditText, MinhumEditText,
            MaxtempEditText, MaxhumEditText, Maxco2EditText;
    private List<String> nameList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private List<NewDeviceModel> localList = new ArrayList<>();
    private List<NewDeviceModel> apiList = new ArrayList<>();
    private boolean isActiveFragment;
    private boolean isConnected;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);

        spinner = view.findViewById(R.id.prefrencesSpinner);
        MintempEditText = view.findViewById(R.id.minTempEditText);
        MaxtempEditText = view.findViewById(R.id.MaxTempEditText);
        MinhumEditText = view.findViewById(R.id.minHumEditText);
        MaxhumEditText = view.findViewById(R.id.MaxHumEditText);
        Maxco2EditText = view.findViewById(R.id.MaxCo2EditText);
        save = view.findViewById(R.id.buttonSave);

        viewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        viewModel.updateRooms();
        save.setOnClickListener(v -> {
            Preferences preference = new Preferences(
                    viewModel.getDeviceId(),
                    true,
                    Integer.parseInt(Maxco2EditText.getText().toString()),
                    0,
                    Integer.parseInt(MaxhumEditText.getText().toString()),
                    Integer.parseInt(MinhumEditText.getText().toString()),
                    Double.parseDouble(MintempEditText.getText().toString()),
                    Double.parseDouble(MaxtempEditText.getText().toString()));

            viewModel.savePreferencesToNetwork(preference);
        });

        @SuppressLint("RestrictedApi") ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(getActivity(), new Observer<ConnectionModel>() {
            @Override
            public void onChanged(@Nullable ConnectionModel connection) {
                if (isActiveFragment) {

                    if (connection.getIsConnected()) {
                        isConnected = true;
                    } else {
                        isConnected = false;
                    }
                    viewModel.updateRooms();
                    refreshSpinner();
                }
            }
        });

        viewModel.getDevicesFromApi().observe(getViewLifecycleOwner(), devices -> {
            apiList.clear();
            List<NewDeviceModel> formattedDeviceList = new ArrayList<>();
            for (int i = 0; i < devices.size(); i++) {
                formattedDeviceList.add(new NewDeviceModel(devices.get(i).getDeviceId(), devices.get(i).getName()));
            }
            apiList = formattedDeviceList;
            refreshSpinner();
        });

        viewModel.getAllLocalDevices().observe(getViewLifecycleOwner(), savedDevices -> {
            localList.clear();
            localList = savedDevices;
            refreshSpinner();
        });

        viewModel.getPreferences().observe(getViewLifecycleOwner(), preferences -> {
            if (isConnected) {
                MintempEditText.setText(String.format("%.1f", preferences.getTemperatureMin()));
                MaxtempEditText.setText(String.format("%.1f", preferences.getTemperatureMax()));
                MinhumEditText.setText(String.valueOf(preferences.getHumidityMin()));
                MaxhumEditText.setText(String.valueOf(preferences.getHumidityMax()));
                Maxco2EditText.setText(String.valueOf(preferences.getCo2Max()));
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isConnected) {
                    MintempEditText.setEnabled(true);
                    MaxtempEditText.setEnabled(true);
                    MinhumEditText.setEnabled(true);
                    MaxhumEditText.setEnabled(true);
                    Maxco2EditText.setEnabled(true);
                    viewModel.setDeviceId(idList.get(position));
                    viewModel.showPreferences(idList.get(position));
                    save.setEnabled(true);
                } else {
                    viewModel.setDeviceId(idList.get(position));
                    Preferences prefs = viewModel.getPreferencesById(idList.get(position));
                    MintempEditText.setEnabled(false);
                    MaxtempEditText.setEnabled(false);
                    MinhumEditText.setEnabled(false);
                    MaxhumEditText.setEnabled(false);
                    Maxco2EditText.setEnabled(false);
                    if (prefs == null) {
                        MintempEditText.setText("Empty");
                        MaxtempEditText.setText("Empty");
                        MinhumEditText.setText("Empty");
                        MaxhumEditText.setText("Empty");
                        Maxco2EditText.setText("Empty");
                    } else {

                        MintempEditText.setText(String.format("%.1f", prefs.getTemperatureMin()));
                        MaxtempEditText.setText(String.format("%.1f", prefs.getTemperatureMax()));
                        MinhumEditText.setText(String.valueOf(prefs.getHumidityMin()));
                        MaxhumEditText.setText(String.valueOf(prefs.getHumidityMax()));
                        Maxco2EditText.setText(String.valueOf(prefs.getCo2Max()));
                    }
                    save.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
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

    @Override
    public void onResume() {
        super.onResume();
        isActiveFragment = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isActiveFragment = false;
    }
}

