package com.example.sep4androidapp.fragments.preferencesFragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.PrefrencesViewModel;

import java.util.List;

public class PreferencesFragment extends Fragment {

    Spinner spinner;
    PrefrencesViewModel viewModel;
    Button save, yourPrefernces;
    EditText MintempEditText, MinhumEditText, Minco2EditText,
            MaxtempEditText, MaxhumEditText, Maxco2EditText;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);

        spinner = view.findViewById(R.id.prefrencesSpinner);
        ArrayAdapter< CharSequence > adapter = ArrayAdapter.createFromResource(getContext(), R.array.Rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        MintempEditText = view.findViewById(R.id.minTempEditText);
        MaxtempEditText = view.findViewById(R.id.MaxTempEditText);
        MinhumEditText = view.findViewById(R.id.minHumEditText);
        MaxhumEditText = view.findViewById(R.id.MaxHumEditText);
        Minco2EditText = view.findViewById(R.id.minCo2EditText);
        Maxco2EditText = view.findViewById(R.id.MaxCo2EditText);
        Minco2EditText = view.findViewById(R.id.minCo2EditText);

        save = view.findViewById(R.id.buttonSave);
        yourPrefernces = view.findViewById(R.id.buttonView);


        viewModel = new ViewModelProvider(this).get(PrefrencesViewModel.class);
     /*   viewModel.getAllPreferences().observe(getViewLifecycleOwner(), new Observer< List< Preferences > >() {
            @Override
            public void onChanged(List< Preferences > preferences) {
                Toast.makeText(getActivity(),"Onchanged",Toast.LENGTH_SHORT).show();
            }
        });

      */

        viewModel.getLastPreference().observe(getViewLifecycleOwner(), new Observer< Preferences >() {
            @Override
            public void onChanged(Preferences preferences) {

                MintempEditText.setText(String.format("%.1f", preferences.getTemperatureMin()));
                MaxtempEditText.setText(String.format("%.1f", preferences.getTemperatureMax()));
                MinhumEditText.setText(String.valueOf(preferences.getHumidityMin()));
                MaxhumEditText.setText(String.valueOf( preferences.getHumidityMax())) ;
                Minco2EditText.setText(String.valueOf( preferences.getCo2Min()) );
                Maxco2EditText.setText(String.valueOf(preferences.getCo2Max()) );
            }
        });





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preferences preference = new Preferences(
                        "0004A30B002181EC",
                        true,
                        Integer.parseInt(Maxco2EditText.getText().toString()),
                        Integer.parseInt(Minco2EditText.getText().toString()),
                        Integer.parseInt(MaxhumEditText.getText().toString()),
                        Integer.parseInt(MinhumEditText.getText().toString()),
                        Double.parseDouble(MintempEditText.getText().toString()),
                        Double.parseDouble(MaxtempEditText.getText().toString()));

                viewModel.updatePrefrences(preference);
            }
        });

        yourPrefernces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.showPrefrences();
            }
        });


        return view;
    }
}
