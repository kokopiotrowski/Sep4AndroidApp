package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.fragments.sleepFragment.ValueFormatters.FragmentsValueFormatter;
import com.example.sep4androidapp.ViewModels.Co2FragmentViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentCo2 extends Fragment {
    private Co2FragmentViewModel viewModel;

    private String deviceId;
    private TextView deviceName;

    private List<BarEntry> dailyCo2 = new ArrayList<>();
    private List<BarEntry> weeklyCo2 = new ArrayList<>();
    private List<BarEntry> monthlyCo2 = new ArrayList<>();

    private List<SleepSession> sleepSessionsDaily = new ArrayList<>();
    private List<SleepSession> sleepSessionsWeekly = new ArrayList<>();
    private List<SleepSession> sleepSessionsMonthly = new ArrayList<>();

    private BarDataSet dailyBarDataSet;
    private BarDataSet weeklyBarDataSet;
    private BarDataSet monthlyBarDataSet;

    private BarData dailyBarData;
    private BarData weeklyBarData;
    private BarData monthlyBarData;

    private BarChart dailyBarChart;
    private BarChart weeklyBarChart;
    private BarChart monthlyBarChart;

    private double co2Max;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_temp_sound_co2_hum, container, false);
        dailyBarChart = v.findViewById(R.id.dailyBarChart);
        weeklyBarChart = v.findViewById(R.id.weeklyBarChart);
        monthlyBarChart = v.findViewById(R.id.monthlyBarChart);

        deviceName = v.findViewById(R.id.deviceText);
        viewModel = new ViewModelProvider(this).get(Co2FragmentViewModel.class);

        viewModel.getPreferences().observe(getViewLifecycleOwner(), preferences -> co2Max = preferences.getCo2Max());

        viewModel.getChosenDeviceId().observe(getViewLifecycleOwner(), s -> {
            deviceId = s;
        });

        viewModel.getDevicesForFragments().observe(getViewLifecycleOwner(), devices -> {
            for (int i = 0; i < devices.size(); i++) {
                if (devices.get(i).getDeviceId().equals(deviceId)) {

                    deviceName.setText(devices.get(i).getName());
                }
            }
        });

        viewModel.getSleepSessionsDaily().observe(getViewLifecycleOwner(), sleepSessions -> {
            dailyBarChart.clear();
            if(dailyBarDataSet != null){

                dailyBarDataSet.clear();
            }
            sleepSessionsDaily.clear();
            dailyBarChart.getAxisLeft().removeAllLimitLines();

            sleepSessionsDaily = sleepSessions;

            for (int i = 0; i < sleepSessionsDaily.size(); i++) {
                SleepSession cSleep = sleepSessionsDaily.get(i);
                int days = cSleep.getTimeStart().getDayOfYear();
                dailyCo2.add(new BarEntry(days, (float) cSleep.getAverageCo2()));
            }
            Collections.sort(dailyCo2, new EntryXComparator());
            dailyBarDataSet = new BarDataSet(dailyCo2, "Co2");

            dailyBarData = new BarData(dailyBarDataSet);
            dailyBarData.setBarWidth(0.9f);

            LimitLine limitMax = new LimitLine((float) co2Max, "Max co2");

            dailyBarChart.setData(dailyBarData);
            dailyBarChart.setFitBars(true);
            dailyBarChart.getDescription().setText("Co2");
            dailyBarChart.getLegend().setEnabled(false);

            dailyBarChart.getAxisLeft().addLimitLine(limitMax);

            YAxis leftYAxis = dailyBarChart.getAxisLeft();
            YAxis rightYAxis = dailyBarChart.getAxisRight();
            XAxis xAxis = dailyBarChart.getXAxis();

            leftYAxis.setAxisMinimum(0);
            rightYAxis.setAxisMinimum(0);
            leftYAxis.setAxisMaximum((float) co2Max + 200);
            rightYAxis.setAxisMaximum((float) co2Max + 200);
            xAxis.setDrawLabels(true);
            xAxis.setValueFormatter(new FragmentsValueFormatter());

            dailyBarChart.invalidate();
        });
        viewModel.getSleepSessionsWeekly().observe(getViewLifecycleOwner(), sleepSessions -> {
            weeklyBarChart.clear();
            if(weeklyBarDataSet != null){

                weeklyBarDataSet.clear();
            }
            sleepSessionsWeekly.clear();
            weeklyBarChart.getAxisLeft().removeAllLimitLines();
            sleepSessionsWeekly = sleepSessions;

            for (int i = 0; i < sleepSessionsWeekly.size(); i++) {

                SleepSession cSleep = sleepSessionsWeekly.get(i);
                int days = cSleep.getTimeStart().getDayOfYear();
                weeklyCo2.add(new BarEntry(days, (float) cSleep.getAverageCo2()));
            }
            Collections.sort(weeklyCo2, new EntryXComparator());
            weeklyBarDataSet = new BarDataSet(weeklyCo2, "Co2");

            weeklyBarData = new BarData(weeklyBarDataSet);
            weeklyBarData.setBarWidth(0.9f);

            LimitLine limitMax = new LimitLine((float) co2Max, "Max co2");

            weeklyBarChart.setData(weeklyBarData);
            weeklyBarChart.setFitBars(true);
            weeklyBarChart.getDescription().setText("Co2");
            weeklyBarChart.getLegend().setEnabled(false);

            weeklyBarChart.getAxisLeft().addLimitLine(limitMax);

            YAxis leftYAxis = weeklyBarChart.getAxisLeft();
            YAxis rightYAxis = weeklyBarChart.getAxisRight();
            XAxis xAxis = weeklyBarChart.getXAxis();

            leftYAxis.setAxisMinimum(0);
            rightYAxis.setAxisMinimum(0);
            leftYAxis.setAxisMaximum((float) co2Max + 200);
            rightYAxis.setAxisMaximum((float) co2Max + 200);
            xAxis.setDrawLabels(true);
            xAxis.setValueFormatter(new FragmentsValueFormatter());

            weeklyBarChart.invalidate();
        });

        viewModel.getSleepSessionsMonthly().observe(getViewLifecycleOwner(), sleepSessions -> {
            monthlyBarChart.clear();
            if(monthlyBarDataSet != null){

                monthlyBarDataSet.clear();
            }
            sleepSessionsMonthly.clear();
            monthlyBarChart.getAxisLeft().removeAllLimitLines();
            sleepSessionsMonthly = sleepSessions;

            for (int i = 0; i < sleepSessionsMonthly.size(); i++) {
                SleepSession cSleep = sleepSessionsMonthly.get(i);
                int days = cSleep.getTimeStart().getDayOfYear();
                monthlyCo2.add(new BarEntry(days, (float) cSleep.getAverageCo2()));
            }
            Collections.sort(monthlyCo2, new EntryXComparator());
            monthlyBarDataSet = new BarDataSet(monthlyCo2, "Co2");

            monthlyBarData = new BarData(monthlyBarDataSet);
            monthlyBarData.setBarWidth(0.9f);

            LimitLine limitMax = new LimitLine((float) co2Max, "Max co2");

            monthlyBarChart.setData(monthlyBarData);
            monthlyBarChart.setFitBars(true);
            monthlyBarChart.getDescription().setText("Co2");
            monthlyBarChart.getLegend().setEnabled(false);

            monthlyBarChart.getAxisLeft().addLimitLine(limitMax);

            YAxis leftYAxis = monthlyBarChart.getAxisLeft();
            YAxis rightYAxis = monthlyBarChart.getAxisRight();
            XAxis xAxis = monthlyBarChart.getXAxis();

            leftYAxis.setAxisMinimum(0);
            rightYAxis.setAxisMinimum(0);

            leftYAxis.setAxisMaximum((float) co2Max + 200);
            rightYAxis.setAxisMaximum((float) co2Max + 200);
            xAxis.setDrawLabels(true);
            xAxis.setValueFormatter(new FragmentsValueFormatter());

            monthlyBarChart.invalidate();
        });
        return v;
    }
}
