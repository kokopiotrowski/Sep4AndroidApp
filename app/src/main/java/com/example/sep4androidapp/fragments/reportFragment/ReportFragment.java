package com.example.sep4androidapp.fragments.reportFragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.ReportViewModel;
import com.github.mikephil.charting.charts.HorizontalBarChart;

public class ReportFragment extends Fragment {

    private ReportViewModel mViewModel;
    View v;
    RadioGroup radioGroup;
    RadioButton yesterday, lastWeek, lastMonth;
    HorizontalBarChart reportChart;

    RatingBar ratingBar;
    Button rateSleepButton;


    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_report, container, false);


        radioGroup = v.findViewById(R.id.lastReportRadioGroup);
        yesterday = v.findViewById(R.id.lastSleepRadioButton);
        lastWeek = v.findViewById(R.id.reportLastWeekRadioButton);
        lastMonth = v.findViewById(R.id.reportLastMonthRadioButton);
        HorizontalBarChart reportChart = v.findViewById(R.id.temperatureChart);

        ratingBar = v.findViewById(R.id.ratingBar);
        rateSleepButton = v.findViewById(R.id.rateYourSleepButton);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(0 != i){
                    ratingBar.setVisibility(View.INVISIBLE);
                    rateSleepButton.setVisibility(View.INVISIBLE);
                }
                else{
                    ratingBar.setVisibility(View.VISIBLE);
                    rateSleepButton.setVisibility(View.VISIBLE);;
                }
            }
        });



        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
        // TODO: Use the ViewModel
    }

}
