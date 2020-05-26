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
    Button save;
    EditText MintempEditText, MinhumEditText, Minco2EditText,
            MaxtempEditText, MaxhumEditText, Maxco2EditText;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);

        spinner = view.findViewById(R.id.prefrencesSpinner);
        ArrayAdapter< CharSequence > adapter = ArrayAdapter.createFromResource(getContext(), R.array.Rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        MintempEditText = view.findViewById(R.id.minTempEditText);
        MinhumEditText = view.findViewById(R.id.minHumEditText);
        Minco2EditText = view.findViewById(R.id.minCo2EditText);

        MaxtempEditText = view.findViewById(R.id.MaxTempEditText);
        MaxhumEditText = view.findViewById(R.id.MaxHumEditText);
        Maxco2EditText = view.findViewById(R.id.MaxCo2EditText);

        save = view.findViewById(R.id.buttonSave);


        viewModel = new ViewModelProvider(this).get(PrefrencesViewModel.class);
        viewModel.getPrefrences().observe(getViewLifecycleOwner(), new Observer< List< Preferences > >() {
            @Override
            public void onChanged(List< Preferences > preferences) {
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
                                Integer.parseInt(MinhumEditText.getText().toString()),
                                Integer.parseInt(MaxhumEditText.getText().toString()),
                                Double.parseDouble(MintempEditText.getText().toString()),
                                Double.parseDouble(MaxtempEditText.getText().toString()));                        ;
                viewModel.updatePrefrences(preference);
            }
        });
        return view;
    }
}
