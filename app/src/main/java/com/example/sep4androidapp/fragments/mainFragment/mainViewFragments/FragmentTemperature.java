package com.example.sep4androidapp.fragments.mainFragment.mainViewFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.FragmentFirstPageViewModel;
import com.example.sep4androidapp.ViewModels.PrefrencesViewModel;
import com.example.sep4androidapp.ViewModels.ReportViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentTemperature extends Fragment {


    private ReportViewModel viewModelReport;
    private PrefrencesViewModel viewModelPreferences;
    private FragmentFirstPageViewModel viewModelFirstPage;
    float temperatureValue;
    private List<BarEntry> dailyTemperature = new ArrayList<>();
    private List<BarEntry> weeklyTemperature = new ArrayList<>();
    private List<BarEntry> monthlyTemperature = new ArrayList<>();
    private List<SleepSession> sleepSessionsList = new ArrayList<>();
    private BarDataSet dailyBarDataSet;
    private BarDataSet weeklyBarDataSet;
    private BarDataSet monthlyBarDataSet;
    private BarData dailyBarData;
    private BarData weeklyBarData;
    private BarData monthlyBarData;
    private BarChart dailyBarChart;
    private BarChart weeklyBarChart;
    private BarChart monthlyBarChart;

    private double temperatureMax = 20 ;
    private double temperatureMin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_temp_sound_co2_hum, container, false);
        dailyBarChart = v.findViewById(R.id.dailyBarChart);
        weeklyBarChart = v.findViewById(R.id.weeklyBarChart);
        monthlyBarChart = v.findViewById(R.id.monthlyBarChart);

        viewModelReport = new ViewModelProvider(this).get(ReportViewModel.class);
        viewModelPreferences = new ViewModelProvider(this).get(PrefrencesViewModel.class);
        viewModelFirstPage = new ViewModelProvider(this).get(FragmentFirstPageViewModel.class);




        viewModelPreferences.getLastPreference().observe(getViewLifecycleOwner(), new Observer<Preferences>() {
            @Override
            public void onChanged(Preferences preferences) {

               temperatureMax =  preferences.getTemperatureMax();
               temperatureMin = preferences.getTemperatureMin();

            }
        });

        viewModelReport.getSleepSessions().observe(getViewLifecycleOwner(), new Observer<List<SleepSession>>() {
            @Override
            public void onChanged(List<SleepSession> sleepSessions) {

                sleepSessionsList = sleepSessions;
                updateDailyChart();
                updateWeeklyChart();


                /*temperature.clear();

                temperatureValue = (float)roomCondition.getTemperature();
                temperature.add(new BarEntry(0,temperatureValue));


                LimitLine limitMax = new LimitLine((float) temperatureMax, "Max temperature");
                LimitLine limitMin = new LimitLine((float) temperatureMin, "Min temperature");


                barChart.getAxisLeft().addLimitLine(limitMax);
                barChart.getAxisLeft().addLimitLine(limitMin);


                YAxis leftYAxis = barChart.getAxisLeft();
                YAxis rightYAxis = barChart.getAxisRight();
                XAxis xAxis = barChart.getXAxis();

                leftYAxis.setAxisMinimum(0);
                rightYAxis.setAxisMinimum(0);
                leftYAxis.setAxisMaximum((float)temperatureMax + 5);
                rightYAxis.setAxisMaximum((float)temperatureMax + 5);
                xAxis.setDrawLabels(false);


            barDataSet = new BarDataSet(temperature, "Temperature");
            barDataSet.setDrawValues(true);
            barData = new BarData(barDataSet);

            barChart.getDescription().setText("Temperature");
            barChart.getLegend().setEnabled(false);
            barChart.setData(barData);
            barChart.setFitBars(true);
            barChart.invalidate();*/



            }
        });
        viewModelReport.updateSleepSessions("fake_device3");

        return v;
    }

    public void updateDailyChart(){
        for (int i = 0; i < (sleepSessionsList.size() <= 1 ? sleepSessionsList.size() : 1); i++) {

            SleepSession cSleep = sleepSessionsList.get(i);
            int dayOfMonth = cSleep.getTimeStart().getDayOfMonth();
            dailyTemperature.add(new BarEntry(dayOfMonth, (float) cSleep.getAverageTemperature()));


        }

        Collections.sort(dailyTemperature, new EntryXComparator());

        dailyBarDataSet = new BarDataSet(dailyTemperature, "Temperature");


        dailyBarData = new BarData(dailyBarDataSet);
        dailyBarData.setBarWidth(0.9f);


        dailyBarChart.setData(dailyBarData);
        dailyBarChart.setFitBars(true);
        dailyBarChart.getDescription().setText("Temperature");
        dailyBarChart.getLegend().setEnabled(false);

        YAxis leftYAxis = dailyBarChart.getAxisLeft();
        YAxis rightYAxis = dailyBarChart.getAxisRight();
        XAxis xAxis = dailyBarChart.getXAxis();

        leftYAxis.setAxisMinimum(0);
        rightYAxis.setAxisMinimum(0);
        leftYAxis.setAxisMaximum((float)temperatureMax + 5);
        rightYAxis.setAxisMaximum((float)temperatureMax + 5);
        xAxis.setDrawLabels(false);

        dailyBarChart.invalidate();


    }

    public void updateWeeklyChart(){

        for (int i = 0; i < (sleepSessionsList.size() <= 7 ? sleepSessionsList.size() : 7); i++) {

            SleepSession cSleep = sleepSessionsList.get(i);
            int dayOfMonth = cSleep.getTimeStart().getDayOfMonth();
            weeklyTemperature.add(new BarEntry(dayOfMonth, (float) cSleep.getAverageTemperature()));


        }

        Collections.sort(weeklyTemperature, new EntryXComparator());

        weeklyBarDataSet = new BarDataSet(weeklyTemperature, "Temperature");


        weeklyBarData = new BarData(weeklyBarDataSet);
        weeklyBarData.setBarWidth(0.9f);


        weeklyBarChart.setData(weeklyBarData);
        weeklyBarChart.setFitBars(true);
        weeklyBarChart.getDescription().setText("Temperature");
        weeklyBarChart.getLegend().setEnabled(false);

        YAxis leftYAxis = weeklyBarChart.getAxisLeft();
        YAxis rightYAxis = weeklyBarChart.getAxisRight();
        XAxis xAxis = weeklyBarChart.getXAxis();

        leftYAxis.setAxisMinimum(0);
        rightYAxis.setAxisMinimum(0);
        leftYAxis.setAxisMaximum((float)temperatureMax + 5);
        rightYAxis.setAxisMaximum((float)temperatureMax + 5);
        xAxis.setDrawLabels(false);

        weeklyBarChart.invalidate();


    }

    public void updateMonthlyChart(){



    }
}
