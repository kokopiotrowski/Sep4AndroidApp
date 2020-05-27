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

import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ViewModels.ReportViewModel;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReportFragment extends Fragment {

    private ReportViewModel mViewModel;
    View v;
    RadioGroup radioGroup;
    RadioButton yesterday, lastWeek, lastMonth;
    CombinedChart temperatureChart;
    HorizontalBarChart co2Chart;

    RatingBar ratingBar;
    Button rateSleepButton;

    CombinedData temperatureData;
    BarData co2Data;


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

        temperatureChart = v.findViewById(R.id.temperatureChart);
        co2Chart = v.findViewById(R.id.co2Chart);

        ratingBar = v.findViewById(R.id.ratingBar);
        rateSleepButton = v.findViewById(R.id.rateYourSleepButton);


        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if(0 != radioGroup.getCheckedRadioButtonId()){
                ratingBar.setVisibility(View.INVISIBLE);
                rateSleepButton.setVisibility(View.INVISIBLE);
            }
            else
            {
                ratingBar.setVisibility(View.VISIBLE);
                rateSleepButton.setVisibility(View.VISIBLE);;
            }
        });


        //updateCharts(LocalDate.now().minusDays(1),LocalDate.now());
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
        // TODO: Use the ViewModel
    }


    private void updateCharts(LocalDate start, LocalDate end)
    {
        temperatureData = new CombinedData();
        co2Data = new BarData();

        mViewModel.updateSleepSessions(1, start, end);
        List<SleepSession> data = mViewModel.getSleepSessions().getValue();

        for(int i=0; i< data.size(); i++) {
            temperatureData.addEntry(new Entry(data.get(i).getTimeStart().getDayOfYear(), (float) data.get(i).getAverageTemperature()), i);
        }


        temperatureChart.setData(temperatureData);
        co2Chart.setData(co2Data);
    }
}
