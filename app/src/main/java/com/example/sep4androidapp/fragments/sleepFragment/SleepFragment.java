package com.example.sep4androidapp.fragments.sleepFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.SleepData;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.ReportViewModel;

public class SleepFragment extends Fragment {
    ReportViewModel viewModel;
    Button button;
    TextView textView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);



        textView = view.findViewById(R.id.textViewSleep);


        viewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        viewModel.getSleepData().observe(getViewLifecycleOwner(), new Observer<SleepData>() {
            @Override
            public void onChanged(SleepData sleepData) {
                textView.setText(String.valueOf(sleepData.getAverageCo2()));
            }
        });

        button = view.findViewById(R.id.buttonSleep);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.updateSleepData();
            }
        });

        return view;
    }

}
