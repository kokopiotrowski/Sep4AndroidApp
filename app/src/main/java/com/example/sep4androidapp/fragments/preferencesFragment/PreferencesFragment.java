package com.example.sep4androidapp.fragments.preferencesFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.ReportViewModel;

public class PreferencesFragment extends Fragment {

    Spinner spinner;
    //PreferencesViewModel viewModel;
    Button tempApply,humApply, CO2Apply;
    EditText MintempEditText, MinhumEditText, Minco2EditText,
            MaxtempEditText, MaxhumEditText, Maxco2EditText;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);

        spinner = view.findViewById(R.id.prefrencesSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        MintempEditText = view.findViewById(R.id.minTempEditText);
        MinhumEditText = view.findViewById(R.id.minHumEditText);
        Minco2EditText = view.findViewById(R.id.minCo2EditText);

        MaxtempEditText = view.findViewById(R.id.MaxTempEditText);
        MaxhumEditText = view.findViewById(R.id.MaxHumEditText);
        Maxco2EditText = view.findViewById(R.id.MaxCo2EditText);

        tempApply = view.findViewById(R.id.Tempbutton);
        humApply = view.findViewById(R.id.Humbutton);
        CO2Apply = view.findViewById(R.id.co2button);

        String newMinTemp =  MintempEditText.getText().toString();
        String newMinHum = MinhumEditText.getText().toString();
        String newMinCO2 = Minco2EditText.getText().toString();
        String newMaxTemp =  MaxtempEditText.getText().toString();
        String newMaxHum = MaxhumEditText.getText().toString();
        String newMaxCO2 = Maxco2EditText.getText().toString();

/*

        viewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        viewModel.getPrefrences().observe(getViewLifecycleOwner(), new Observer< Preferences >() {

            @Override
            public void onChanged(Preferences preferences) {

            }
        });

*/
        tempApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return view;
    }


}
