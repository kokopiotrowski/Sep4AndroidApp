package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.ReportViewModel;
import com.example.sep4androidapp.ViewModels.StartStopViewModel;
import com.example.sep4androidapp.connection.ApiCallBack;

public class FragmentFirstPage extends Fragment {
    private Spinner spinner;
    private ReportViewModel viewModel;
    private TextView currentTemperature, currentHumidity, currentCO2, currentSound, timeStamp;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_firstpage, container, false);
        spinner = v.findViewById(R.id.spinner);

        String[] arraySpinner = new String[] {
                "Living room", "Bedroom", "Bedroom 2"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        currentTemperature = v.findViewById(R.id.currentTemperature);
        currentHumidity = v.findViewById(R.id.currentHumidity);
        currentSound = v.findViewById(R.id.currentSound);
        currentCO2 = v.findViewById(R.id.currentCo2);
        timeStamp = v.findViewById(R.id.timeStamp);

        viewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        viewModel.getRoomCondition().observe(getViewLifecycleOwner(), roomCondition -> {
              currentTemperature.setText(String.format("%.0f", roomCondition.getTemperature()) + " °C");
              currentCO2.setText(String.format("%.0f", roomCondition.getCo2()) + " ppm");
              currentHumidity.setText(String.format("%.0f", roomCondition.getHumidity()) + "%");
              currentSound.setText(String.format("%.0f", roomCondition.getSound()) + "dB");
                timeStamp.setText("Updated: " + roomCondition.getTimestamp());
        });

        return v;
    }



}
