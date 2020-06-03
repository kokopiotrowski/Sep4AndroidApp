package com.example.sep4androidapp.fragments.sleepFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import com.example.sep4androidapp.ViewModels.SleepDataViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SleepFragment extends Fragment {
    SleepDataViewModel viewModel;
    TextView sound, temperature, humidity, co2;
    LineChart mpLineChart;
    Spinner deviceSpinner;
    Spinner sleepSpinner;

    private List<String> deviceNameList = new ArrayList<>();
    private List<String> deviceIdList = new ArrayList<>();
    private List<String> sleepDateList = new ArrayList<>();
    private List<Integer> sleepIdList = new ArrayList<>();


    private List<SleepSession> sleepSessions = new ArrayList<>();
    ArrayAdapter<String> deviceAdapter;
    ArrayAdapter<String> sleepAdapter;

    ArrayList<Entry> temperatureValues = new ArrayList<>();
    ArrayList<Entry> soundValues = new ArrayList<>();
    ArrayList<Entry> co2Values = new ArrayList<>();
    ArrayList<Entry> humidityValues = new ArrayList<>();
    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    int lastSavedDay = 0;
    int firstDay = 0;
    float timeInSeconds = 0;
    int days = 0;
    long numberOfSeconds = 0;
    float totalSeconds = 0;


    LineDataSet temperatureDataSet, soundDataSet, humidityDataSet, co2DataSet;
    LineData data;
    Spinner sleepDataSpinner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);
        deviceSpinner = view.findViewById(R.id.deviceSpinner);
        sleepSpinner = view.findViewById(R.id.sleepSpinner);
        sleepDataSpinner = view.findViewById(R.id.spinnerSleepData);
        mpLineChart = view.findViewById(R.id.lineChart);
        sound = view.findViewById(R.id.averageSoundNum);
        temperature = view.findViewById(R.id.averageTemperatureNum);
        humidity = view.findViewById(R.id.averageHumidityNum);
        co2 = view.findViewById(R.id.averageCo2Num);

        viewModel = new ViewModelProvider(this).get(SleepDataViewModel.class);


        viewModel.getDevices().observe(getViewLifecycleOwner(), devices -> {
            deviceNameList.clear();
            deviceIdList.clear();
            for (int i = 0; i < devices.size(); i++) {
                deviceNameList.add(devices.get(i).getName());
                deviceIdList.add(devices.get(i).getDeviceId());
            }
            deviceAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, deviceNameList);
            deviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            deviceSpinner.setAdapter(deviceAdapter);
            if (getArguments() != null) {
                deviceSpinner.setSelection(deviceAdapter.getPosition(getArguments().getString("deviceName")));
            }
        });

        viewModel.getSleepSessions().observe(getViewLifecycleOwner(), sleepSessions -> {
            sleepDateList.clear();
            sleepIdList.clear();
            for (int i = 0; i < sleepSessions.size(); i++) {
                String date = sleepSessions.get(i).getTimeStart().getYear()+ "/" + sleepSessions.get(i).getTimeStart().getMonthValue()+ "/" + sleepSessions.get(i).getTimeStart().getDayOfMonth()
                        + "-" + sleepSessions.get(i).getTimeFinish().getYear()+ "/" + sleepSessions.get(i).getTimeFinish().getMonthValue()+ "/" + sleepSessions.get(i).getTimeFinish().getDayOfMonth();
                sleepDateList.add(date);
                sleepIdList.add(sleepSessions.get(i).getSleepId());
            }
            sleepAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, sleepDateList);
            sleepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sleepSpinner.setAdapter(sleepAdapter);
            if (getArguments() != null) {
                deviceSpinner.setSelection(deviceAdapter.getPosition(getArguments().getString("deviceName")));
            }
        });

        viewModel.getSleepData().observe(getViewLifecycleOwner(), new Observer<SleepData>() {
            @Override
            public void onChanged(SleepData sleepData) {
                sound.setText(String.format("%.0f", sleepData.getAverageSound()) + " dB");
                temperature.setText(String.format("%.0f", sleepData.getAverageTemperature()) + " °C");
                humidity.setText(String.format("%.0f", sleepData.getAverageHumidity()) + " %");
                co2.setText(String.format("%.0f", sleepData.getAverageCo2()) + " ppm");

                ArrayList<RoomCondition> roomConditions = sleepData.getRoomConditions();

                Collections.sort(roomConditions);
                firstDay = roomConditions.get(0).getTimestamp().getDayOfYear();

                int size = roomConditions.size();


                for (int i = 0; i < size; i++) {
                    RoomCondition temp = roomConditions.get(i);


                    LocalDateTime ldt1 = roomConditions.get(0).getTimestamp();
                    LocalDateTime ldt2 = roomConditions.get(i).getTimestamp();

                    numberOfSeconds = Duration.between(ldt1, ldt2).toMillis() / 1000;


                    int seconds = ldt1.getHour() * 3600 + ldt1.getMinute() * 60 + ldt1.getSecond();

                    float totalSeconds = (float) seconds + (float) numberOfSeconds;


                    float temperature = (float) temp.getTemperature();
                    float sound = (float) temp.getSound();
                    float humidity = (float) temp.getHumidity();
                    float co2 = (float) temp.getCo2();

                    setTemperatureValues(temperature, totalSeconds);
                    setSoundValues(sound, totalSeconds);
                    setHumidityValues(humidity, totalSeconds);
                    setCo2Values(co2, totalSeconds);

                }

            }
        });

        /* oldViewModel.updateSleepData();*/

        String[] arraySpinner = new String[]{
                "-choose parameter-", "Temperature", "Sound", "Humidity", "Co2"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sleepDataSpinner.setAdapter(adapter);

        //selection doesn't work on position 0, otherwise listener is activated while initializing the fragment and the array lists are still empty at that point (throws null pointer)
        sleepDataSpinner.setSelection(0, false);
        sleepDataSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Object item = parentView.getItemAtPosition(position).toString();

                if(item.equals("-choose parameter-")) {
                    dataSets.clear();
                }

                if (item.equals("Temperature")) {
                    temperatureDataSet = new LineDataSet(getTemperatureValues(), "Temperature");
                    dataSets.add(temperatureDataSet);
                }
                if (item.equals("Sound")) {

                    soundDataSet = new LineDataSet(getSoundValues(), "Sound");
                    dataSets.add(soundDataSet);
                }
                if (item.equals("Humidity")) {

                    humidityDataSet = new LineDataSet(getHumidityValues(), "Humidity");
                    dataSets.add(humidityDataSet);
                }
                if (item.equals("Co2")) {

                    co2DataSet = new LineDataSet(getCo2Values(), "Co2");
                    dataSets.add(co2DataSet);
                }


                data = new LineData(dataSets);
                XAxis xAxis = mpLineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setValueFormatter(new MyValueFormatter());
                mpLineChart.setData(data);
                mpLineChart.getMarker();
                mpLineChart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        setDeviceListeners();
        setSleepListeners();
        return view;

    }

    private void setTemperatureValues(float temperature, float index) {

        temperatureValues.add(new Entry(index, temperature));
    }

    private void setSoundValues(float sound, float index) {

        soundValues.add(new Entry(index, sound));
    }

    private void setHumidityValues(float humidity, float index) {

        humidityValues.add(new Entry(index, humidity));
    }

    private void setCo2Values(float co2, float index) {

        co2Values.add(new Entry(index, co2));

    }

    private ArrayList<Entry> getTemperatureValues() {

        return temperatureValues;
    }

    private ArrayList<Entry> getSoundValues() {

        return soundValues;
    }

    private ArrayList<Entry> getCo2Values() {

        return co2Values;
    }

    private ArrayList<Entry> getHumidityValues() {

        return humidityValues;
    }


    public void setDeviceListeners() {
        deviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.updateSleepSessions(deviceIdList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void setSleepListeners() {
        sleepSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.updateSleepData(sleepIdList.get(position));
                sleepDataSpinner.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}




