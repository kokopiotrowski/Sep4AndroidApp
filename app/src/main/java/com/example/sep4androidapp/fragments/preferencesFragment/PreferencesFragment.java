package com.example.sep4androidapp.fragments.preferencesFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Network.CheckNetwork;
import com.example.sep4androidapp.Network.Variables;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.PreferencesViewModel;

import java.util.ArrayList;
import java.util.List;

public class PreferencesFragment extends Fragment {

    private Spinner spinner;
    private PreferencesViewModel viewModel;
    private Button save;
    private EditText MintempEditText, MinhumEditText, Minco2EditText,
            MaxtempEditText, MaxhumEditText, Maxco2EditText;
    private List<String> nameList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);

        spinner = view.findViewById(R.id.prefrencesSpinner);
        MintempEditText = view.findViewById(R.id.minTempEditText);
        MaxtempEditText = view.findViewById(R.id.MaxTempEditText);
        MinhumEditText = view.findViewById(R.id.minHumEditText);
        MaxhumEditText = view.findViewById(R.id.MaxHumEditText);
        Minco2EditText = view.findViewById(R.id.minCo2EditText);
        Maxco2EditText = view.findViewById(R.id.MaxCo2EditText);
        save = view.findViewById(R.id.buttonSave);

        CheckNetwork network = new CheckNetwork(getActivity().getApplicationContext());
        network.registerNetworkCallback();
        viewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);

        save.setOnClickListener(v -> {
            Preferences preference = new Preferences(
                    viewModel.getDeviceId(),
                    true,
                    Integer.parseInt(Maxco2EditText.getText().toString()),
                    Integer.parseInt(Minco2EditText.getText().toString()),
                    Integer.parseInt(MaxhumEditText.getText().toString()),
                    Integer.parseInt(MinhumEditText.getText().toString()),
                    Double.parseDouble(MintempEditText.getText().toString()),
                    Double.parseDouble(MaxtempEditText.getText().toString()));

            viewModel.update(preference);
            viewModel.updatePreferences(preference);
        });

        //spinner
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
                viewModel.showPrefrences(idList.get(position));

                if (Variables.isNetworkConnected) {
                    Toast.makeText(getActivity(), "Connected to the network" + "", Toast.LENGTH_LONG).show();

                    viewModel.getLastPreference().observe(getViewLifecycleOwner(), preferences -> {

                        MintempEditText.setText(String.format("%.1f", preferences.getTemperatureMin()));
                        MaxtempEditText.setText(String.format("%.1f", preferences.getTemperatureMax()));
                        MinhumEditText.setText(String.valueOf(preferences.getHumidityMin()));
                        MaxhumEditText.setText(String.valueOf(preferences.getHumidityMax()));
                        Minco2EditText.setText(String.valueOf(preferences.getCo2Min()));
                        Maxco2EditText.setText(String.valueOf(preferences.getCo2Max()));
                    });

                } else {
                    Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_LONG).show();
                    viewModel.getAllPreferences().observe(getViewLifecycleOwner(), preferences -> {

                        if (!preferences.isEmpty()) {
                            MintempEditText.setText("");
                            MaxtempEditText.setText("");
                            MinhumEditText.setText("");
                            MaxhumEditText.setText("");
                            Minco2EditText.setText("");
                            Maxco2EditText.setText("");
                            for (Preferences p : preferences) {
                                MintempEditText.append(p.getTemperatureMin() + "\n");
                                MaxtempEditText.append(p.getTemperatureMax() + "\n");
                                MinhumEditText.append(p.getHumidityMin() + "\n");
                                MaxhumEditText.append(p.getHumidityMax() + "\n");
                                Minco2EditText.append(p.getCo2Min() + "\n");
                                Maxco2EditText.append(p.getCo2Max() + "\n");
                            }
                        } else {

                            MintempEditText.setText("Empty");
                            MaxtempEditText.setText("Empty");
                            MinhumEditText.setText("Empty");
                            MaxhumEditText.setText("Empty");
                            Minco2EditText.setText("Empty");
                            Maxco2EditText.setText("Empty");
                        }
                    });
                    save.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        return view;
    }
}

