package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.PrefrencesViewModel;
import com.example.sep4androidapp.ViewModels.ReportViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class FragmentCo2 extends Fragment {
    private ReportViewModel viewModelReport;
    private PrefrencesViewModel viewModelPreferences;
    float co2Value;
    private List<BarEntry> co2 = new ArrayList<>();
    private BarDataSet barDataSet;
    private BarData barData;
    private BarChart barChart;
    private double co2Max = 20 ;
    private double co2Min;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_temp_sound_co2_hum, container, false);
        barChart = v.findViewById(R.id.barChart);

        viewModelReport = new ViewModelProvider(this).get(ReportViewModel.class);
        viewModelPreferences = new ViewModelProvider(this).get(PrefrencesViewModel.class);

        viewModelPreferences.getLastPreference().observe(getViewLifecycleOwner(), new Observer<Preferences>() {
            @Override
            public void onChanged(Preferences preferences) {

                co2Max =  preferences.getCo2Max();
                co2Min = preferences.getCo2Min();

            }
        });

        viewModelReport.getRoomCondition().observe(getViewLifecycleOwner(), new Observer<RoomCondition>() {
            @Override
            public void onChanged(RoomCondition roomCondition) {

                co2.clear();

                co2Value = (float)roomCondition.getCo2();
                co2.add(new BarEntry(0,co2Value));


                LimitLine limitMax = new LimitLine((float) co2Max, "Max co2");
                LimitLine limitMin = new LimitLine((float) co2Min, "Min co2");


                barChart.getAxisLeft().addLimitLine(limitMax);
                barChart.getAxisLeft().addLimitLine(limitMin);


                YAxis leftYAxis = barChart.getAxisLeft();
                YAxis rightYAxis = barChart.getAxisRight();
                XAxis xAxis = barChart.getXAxis();

                leftYAxis.setAxisMinimum(0);
                rightYAxis.setAxisMinimum(0);
                leftYAxis.setAxisMaximum((float)co2Max + 5);
                rightYAxis.setAxisMaximum((float)co2Max + 5);
                xAxis.setDrawLabels(false);


                barDataSet = new BarDataSet(co2, "Co2");
                barDataSet.setDrawValues(true);
                barData = new BarData(barDataSet);

                barChart.getDescription().setText("Co2");
                barChart.getLegend().setEnabled(false);
                barChart.setData(barData);
                barChart.setFitBars(true);
                barChart.invalidate();



            }
        });
        viewModelReport.updateRoomCondition("device1");

        return v;
    }
}