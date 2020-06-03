package com.example.sep4androidapp.fragments.reportFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.LocalStorage.ConnectionLiveData;
import com.example.sep4androidapp.LocalStorage.ConnectionModel;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.ChartsReportViewModel;
import com.example.sep4androidapp.ViewModels.ReportViewModel;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class ReportFragment extends Fragment {
    private ChartsReportViewModel mViewModel;
    private Spinner deviceReportSpinner;
    private RadioGroup radioGroup;
    private RadioButton yesterday, lastWeek, lastMonth;
    private LineChart temperatureChart;
    private HorizontalBarChart co2Chart;
    private RatingBar ratingBar;
    private Button rateSleepButton;

    private List<Entry> temperatureEntries = new ArrayList<>();
    private List<BarEntry> co2Entries = new ArrayList<>();

    private List<SleepSession> sleepSessionsData;

    private List<String> nameList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private boolean isActiveFragment;

    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_report, container, false);
        mViewModel = new ViewModelProvider(this).get(ChartsReportViewModel.class);

        deviceReportSpinner = v.findViewById(R.id.twojaStara);

        radioGroup = v.findViewById(R.id.lastReportRadioGroup);
        yesterday = v.findViewById(R.id.lastSleepRadioButton);
        lastWeek = v.findViewById(R.id.reportLastWeekRadioButton);
        lastMonth = v.findViewById(R.id.reportLastMonthRadioButton);

        temperatureChart = v.findViewById(R.id.temperatureChart);
        co2Chart = v.findViewById(R.id.co2Chart);

        ratingBar = v.findViewById(R.id.ratingBar);
        rateSleepButton = v.findViewById(R.id.rateYourSleepButton);

        temperatureEntries = new ArrayList<>();
        co2Entries = new ArrayList<>();

        ratingBar.setStepSize(1);
        settingListenersAndObservers();
        mViewModel.updateSleepSessions();

        @SuppressLint("RestrictedApi") ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(getActivity(), connection -> {
            if (isActiveFragment && connection != null) {
                if (connection.getIsConnected()) {
                    yesterday.setEnabled(true);
                    lastWeek.setEnabled(true);
                    lastMonth.setEnabled(true);
                    deviceReportSpinner.setEnabled(true);
                    ratingBar.setEnabled(true);
                    rateSleepButton.setEnabled(true);
                } else {
                    yesterday.setEnabled(false);
                    lastWeek.setEnabled(false);
                    lastMonth.setEnabled(false);
                    deviceReportSpinner.setEnabled(false);
                    ratingBar.setEnabled(false);
                    rateSleepButton.setEnabled(false);
                }
            }
        });
        return v;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(ChartsReportViewModel.class);
//    }

    private void updateCharts(int lastDays) {
        temperatureChart.clear();
        co2Chart.clear();

        temperatureEntries.clear();
        co2Entries.clear();

        if (sleepSessionsData.size() != 0) {
            for (int i = 0; i < (sleepSessionsData.size() <= lastDays ? sleepSessionsData.size() : lastDays); i++) {

                SleepSession cSleep = sleepSessionsData.get(i);
                int dayOfMonth = cSleep.getTimeStart().getDayOfMonth();
                temperatureEntries.add(new Entry(dayOfMonth, (float) cSleep.getAverageTemperature()));
                co2Entries.add(new BarEntry(dayOfMonth, (float) cSleep.getAverageCo2()));
            }
            Collections.sort(temperatureEntries, new EntryXComparator());
            Collections.sort(co2Entries, new EntryXComparator());

            LineDataSet temperatureDataSet = new LineDataSet(temperatureEntries, "Temperature");
            BarDataSet co2DataSet = new BarDataSet(co2Entries, "Co2");

            LineData temperatureData = new LineData(temperatureDataSet);
            temperatureChart.setData(temperatureData);

            BarData co2Data = new BarData(co2DataSet);
            co2Data.setBarWidth(0.9f);
            co2Chart.setData(co2Data);
            co2Chart.setFitBars(true);

            temperatureChart.invalidate();
            co2Chart.invalidate();
            styleCharts();
        }
    }

    private void styleCharts() {
        temperatureChart.setAutoScaleMinMaxEnabled(true);
        co2Chart.setAutoScaleMinMaxEnabled(true);
        temperatureChart.setScaleX(1);
        co2Chart.setScaleX(1);
    }

    @SuppressLint("SetTextI18n")
    private void settingListenersAndObservers() {
        rateSleepButton.setOnClickListener(v -> {
            mViewModel.rateSleep(sleepSessionsData.get(0).getSleepId(), (int) ratingBar.getRating());
            mViewModel.updateSleepSessions();
        });

        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            int clickedButtonId = radioGroup.getCheckedRadioButtonId();
            if (yesterday.getId() == clickedButtonId) {
                rateSleepButton.setVisibility(View.VISIBLE);
                ratingBar.setVisibility(View.VISIBLE);
                updateCharts(1);
            } else if (lastWeek.getId() == clickedButtonId) {
                rateSleepButton.setVisibility(View.GONE);
                ratingBar.setVisibility(View.GONE);
                updateCharts(7);
            } else if (lastMonth.getId() == clickedButtonId) {
                rateSleepButton.setVisibility(View.GONE);
                ratingBar.setVisibility(View.GONE);
                updateCharts(30);
            }
        });

        mViewModel.getSleepSessions().observe(getViewLifecycleOwner(), sleepSessions -> {
            sleepSessionsData = sleepSessions;
            updateCharts(1);
            if (sleepSessionsData.size() != 0) {
                int lastSleepRating = sleepSessionsData.get(0).getRating();
                if (lastSleepRating != 0) {
                    ratingBar.setRating(lastSleepRating);
                    ratingBar.setIsIndicator(true);
                    rateSleepButton.setClickable(false);
                    rateSleepButton.setText("Already Rated!");
                } else {
                    rateSleepButton.setText("Rate your last sleep");
                    rateSleepButton.setClickable(true);
                    ratingBar.setRating(0);
                    ratingBar.setIsIndicator(false);
                }
            } else {
                ratingBar.setRating(0);
                ratingBar.setIsIndicator(true);
                rateSleepButton.setClickable(false);
                rateSleepButton.setText("Nothing to rate");
            }
        });

        mViewModel.getDevices().observe(getViewLifecycleOwner(), devices -> {
            nameList.clear();
            idList.clear();
            for (int i = 0; i < devices.size(); i++) {
                nameList.add(devices.get(i).getName());
                idList.add(devices.get(i).getDeviceId());
            }
            adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, nameList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            deviceReportSpinner.setAdapter(adapter);
        });

        deviceReportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.setDeviceId(idList.get(position));
                mViewModel.updateSleepSessions();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
//    private void updateChartsFakeData(int lastDays) {
//        temperatureEntries = new ArrayList<>();
//        co2Entries = new ArrayList<>();
//
//        for (int i = 1; i <= lastDays; i++) {
//
//            temperatureEntries.add(new Entry(i, (float) Math.floor(Math.random() * 13 + 12)));
//            co2Entries.add(new BarEntry(i, (float) Math.floor(Math.random() * 200 + 400)));
//        }
//
//        LineDataSet temperatureDataSet = new LineDataSet(temperatureEntries, "Temperature");
//        BarDataSet co2DataSet = new BarDataSet(co2Entries, "Co2");
//
//        temperatureData = new LineData(temperatureDataSet);
//        temperatureChart.setData(temperatureData);
//
//        co2Data = new BarData(co2DataSet);
//        co2Data.setBarWidth((float) (1.5 / lastDays));
//        co2Chart.setData(co2Data);
//        co2Chart.setFitBars(true);
//
//        temperatureChart.invalidate();
//        co2Chart.invalidate();
//    }
    @Override
    public void onResume() {
        super.onResume();
        isActiveFragment = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isActiveFragment = false;
    }
}
