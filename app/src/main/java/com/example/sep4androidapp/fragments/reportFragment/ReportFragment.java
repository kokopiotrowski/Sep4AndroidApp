package com.example.sep4androidapp.fragments.reportFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.R;
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

public class ReportFragment extends Fragment {

    private ReportViewModel mViewModel;
    View v;
    TextView reportTextView;
    RadioGroup radioGroup;
    RadioButton yesterday, lastWeek, lastMonth;
    LineChart temperatureChart;
    HorizontalBarChart co2Chart;
    RatingBar ratingBar;
    Button rateSleepButton;

    List<Entry> temperatureEntries = new ArrayList<>();
    List<BarEntry> co2Entries = new ArrayList<>();

    List<SleepSession> sleepSessionsData;



    LineData temperatureData;
    BarData co2Data;


    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_report, container, false);
        mViewModel = new ViewModelProvider(this).get(ReportViewModel.class);

        reportTextView = v.findViewById(R.id.reportTextView);

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




        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            int clickedButtonId = radioGroup.getCheckedRadioButtonId();
            if(yesterday.getId() == clickedButtonId){
                rateSleepButton.setVisibility(View.VISIBLE);
                ratingBar.setVisibility(View.VISIBLE);
                updateCharts(1);
               //updateChartsFakeData(1);
            }
            else if(lastWeek.getId() == clickedButtonId)
            {
                rateSleepButton.setVisibility(View.GONE);
                ratingBar.setVisibility(View.GONE);
                updateCharts(7);
                //updateChartsFakeData(7);
            }
            else if(lastMonth.getId() == clickedButtonId)
            {
                rateSleepButton.setVisibility(View.GONE);
                ratingBar.setVisibility(View.GONE);
                updateCharts(30);
                //updateChartsFakeData(30);
            }
        });

        mViewModel.updateSleepSessions("fake_device3");
        mViewModel.getSleepSessions().observe(getViewLifecycleOwner(), new Observer<List<SleepSession>>() {
            @Override
            public void onChanged(List<SleepSession> sleepSessions) {
                sleepSessionsData = sleepSessions;
                updateCharts(1);
            }

    });

        //updateChartsFakeData(1);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
        // TODO: Use the ViewModel
    }


    private void updateCharts(int lastDays)
    {
        temperatureChart.clear();
        co2Chart.clear();

        temperatureEntries.clear();
        co2Entries.clear();

            for (int i = 0; i < (sleepSessionsData.size()<=lastDays?sleepSessionsData.size():lastDays); i++) {

                SleepSession cSleep = sleepSessionsData.get(i);
                int dayOfMonth = cSleep.getTimeStart().getDayOfMonth();

                temperatureEntries.add(new Entry(dayOfMonth, (float) cSleep.getAverageTemperature()));
                co2Entries.add(new BarEntry(dayOfMonth, (float) cSleep.getAverageCo2()));
            }
            Collections.sort(temperatureEntries, new EntryXComparator());
            Collections.sort(co2Entries, new EntryXComparator());

            LineDataSet temperatureDataSet = new LineDataSet(temperatureEntries, "Temperature");
            BarDataSet co2DataSet = new BarDataSet(co2Entries, "Co2");

            temperatureData = new LineData(temperatureDataSet);

            temperatureChart.setData(temperatureData);


            co2Data = new BarData(co2DataSet);
            co2Data.setBarWidth(0.9f);
            co2Chart.setData(co2Data);
            co2Chart.setFitBars(true);

            temperatureChart.invalidate();
            co2Chart.invalidate();
            styleCharts();


    }

    private void styleCharts()
    {
        temperatureChart.setAutoScaleMinMaxEnabled(true);
        co2Chart.setAutoScaleMinMaxEnabled(true);

    }

    private void updateChartsFakeData(int lastDays)
    {
        temperatureEntries = new ArrayList<>();
        co2Entries = new ArrayList<>();

        for (int i = 1; i <= lastDays; i++) {

            temperatureEntries.add(new Entry(i, (float) Math.floor(Math.random() * 13 + 12)));
            co2Entries.add(new BarEntry(i, (float) Math.floor(Math.random() * 200 +400)));
        }

        LineDataSet temperatureDataSet = new LineDataSet(temperatureEntries, "Temperature");
        BarDataSet co2DataSet = new BarDataSet(co2Entries, "Co2");

        temperatureData = new LineData(temperatureDataSet);
        temperatureChart.setData(temperatureData);


        co2Data = new BarData(co2DataSet);
        co2Data.setBarWidth((float)(1.5 /lastDays));
        co2Chart.setData(co2Data);
        co2Chart.setFitBars(true);

        temperatureChart.invalidate();
        co2Chart.invalidate();

    }
}
