package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.ReportViewModel;
import com.example.sep4androidapp.ViewModels.StartStopViewModel;
import com.example.sep4androidapp.connection.ApiCallBack;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FragmentFirstPage extends Fragment {
    private Spinner spinner;
    private ReportViewModel viewModel;
    private TextView currentTemperature, currentHumidity, currentCO2, currentSound, timeStamp;
    FloatingActionButton randomFactButton;
    private TextView title, text;
    CardView cardView;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_firstpage, container, false);
        spinner = v.findViewById(R.id.spinner);

        String[] arraySpinner = new String[]{
                "Living room", "Bedroom", "Bedroom 2"
        };
        ArrayAdapter< String > adapter = new ArrayAdapter< String >(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        currentTemperature = v.findViewById(R.id.currentTemperature);
        currentHumidity = v.findViewById(R.id.currentHumidity);
        currentSound = v.findViewById(R.id.currentSound);
        currentCO2 = v.findViewById(R.id.currentCo2);
        timeStamp = v.findViewById(R.id.timeStamp);
        randomFactButton = v.findViewById(R.id.floatingButton);
        title = v.findViewById(R.id.randomTitle);
        text = v.findViewById(R.id.randomText);
        cardView = v.findViewById(R.id.randomFactLayout);

        viewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        viewModel.getRoomCondition().observe(getViewLifecycleOwner(), roomCondition -> {
            currentTemperature.setText(String.format("%.0f", roomCondition.getTemperature()) + " °C");
            currentCO2.setText(String.format("%.0f", roomCondition.getCo2()) + " ppm");
            currentHumidity.setText(String.format("%.0f", roomCondition.getHumidity()) + "%");
            currentSound.setText(String.format("%.0f", roomCondition.getSound()) + "dB");
            timeStamp.setText("Updated: " + roomCondition.getTimestamp());
        });

        viewModel.getFact().observe(getViewLifecycleOwner(), new Observer< Fact >() {
            @Override
            public void onChanged(Fact fact) {
                title.setText(String.valueOf(fact.getTitle()));
                text.setText(String.valueOf(fact.getContent()));

            }
        });

        randomFactButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cardView.setVisibility(v.VISIBLE);
                viewModel.getRandomFact();
            }
        });

        return v;
    }


}
