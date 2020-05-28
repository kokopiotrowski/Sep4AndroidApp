package com.example.sep4androidapp.fragments.reportFragment;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportFragment extends Fragment {

    private ReportViewModel mViewModel;
    View v;
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

        radioGroup = v.findViewById(R.id.lastReportRadioGroup);
        yesterday = v.findViewById(R.id.lastSleepRadioButton);
        lastWeek = v.findViewById(R.id.reportLastWeekRadioButton);
        lastMonth = v.findViewById(R.id.reportLastMonthRadioButton);

        temperatureChart = v.findViewById(R.id.temperatureChart);
        co2Chart = v.findViewById(R.id.co2Chart);

        ratingBar = v.findViewById(R.id.ratingBar);
        rateSleepButton = v.findViewById(R.id.rateYourSleepButton);


        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            int buttonId = radioGroup.getCheckedRadioButtonId();

            Log.i("KONRAD", "checkedButton" + buttonId);
            if(yesterday.getId() != buttonId){
                rateSleepButton.setVisibility(View.GONE);
                ratingBar.setVisibility(View.GONE);
            }
            else
            {
                rateSleepButton.setVisibility(View.VISIBLE);
                ratingBar.setVisibility(View.VISIBLE);
            }
        });


        updateCharts();
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
        // TODO: Use the ViewModel
    }


    private void updateCharts()
    {
        temperatureEntries = new ArrayList<>();
        co2Entries = new ArrayList<>();

        mViewModel.updateSleepSessions(1);

        Log.i("Konrad", "Data");
        if(mViewModel.getSleepSessions() != null) {

            Log.i("Konrad", "Report not empty");
            List<SleepSession> data = mViewModel.getSleepSessions().getValue();

            for (int i = 0; i < data.size(); i++) {

                temperatureEntries.add(new Entry(data.get(i).getTimeStart().getDayOfYear(), (float) data.get(i).getAverageTemperature()));
                co2Entries.add(new BarEntry((float) i, (float) data.get(i).getAverageCo2()));
            }

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
        }



    }
}
