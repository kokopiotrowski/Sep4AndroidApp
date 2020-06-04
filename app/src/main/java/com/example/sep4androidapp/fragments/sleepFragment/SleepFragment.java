package com.example.sep4androidapp.fragments.sleepFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.LocalStorage.ConnectionLiveData;
import com.example.sep4androidapp.R;
import com.example.sep4androidapp.ValueFormatters.SleepFragmentValueFormatter;
import com.example.sep4androidapp.ViewModels.SleepDataViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class SleepFragment extends Fragment {
    private SleepDataViewModel viewModel;
    private TextView sound, temperature, humidity, co2;
    private LineChart mpLineChart;
    private Spinner deviceSpinner;
    private Spinner sleepSpinner;

    private List<String> deviceNameList = new ArrayList<>();
    private List<String> deviceIdList = new ArrayList<>();
    private List<String> sleepDateList = new ArrayList<>();
    private List<Integer> sleepIdList = new ArrayList<>();

    private boolean isActiveFragment;
    private ArrayAdapter<String> deviceAdapter;
    private ArrayAdapter<String> sleepAdapter;

    private ArrayList<Entry> temperatureValues = new ArrayList<>();
    private ArrayList<Entry> soundValues = new ArrayList<>();
    private ArrayList<Entry> co2Values = new ArrayList<>();
    private ArrayList<Entry> humidityValues = new ArrayList<>();
    private ArrayList<ILineDataSet> dataSets = new ArrayList<>();

    long numberOfSeconds = 0;

    private LineDataSet temperatureDataSet, soundDataSet, humidityDataSet, co2DataSet;
    private LineData data;
    private Spinner sleepDataSpinner;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
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

        @SuppressLint("RestrictedApi") ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(getActivity(), connection -> {
            if (isActiveFragment && connection != null) {
                if (connection.getIsConnected()) {

                    deviceSpinner.setEnabled(true);
                    sleepDataSpinner.setEnabled(true);
                    sleepSpinner.setEnabled(true);
                } else {
                    deviceSpinner.setEnabled(false);
                    sleepDataSpinner.setEnabled(false);
                    sleepSpinner.setEnabled(false);
                }
            }
        });

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
            Collections.sort(sleepSessions);
            for (int i = 0; i < sleepSessions.size(); i++) {
                String date = sleepSessions.get(i).getTimeStart().getYear() + "/" + sleepSessions.get(i).getTimeStart().getMonthValue() + "/" + sleepSessions.get(i).getTimeStart().getDayOfMonth()
                        + "-" + sleepSessions.get(i).getTimeFinish().getYear() + "/" + sleepSessions.get(i).getTimeFinish().getMonthValue() + "/" + sleepSessions.get(i).getTimeFinish().getDayOfMonth();
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

        viewModel.getSleepData().observe(getViewLifecycleOwner(), sleepData -> {
            sound.setText(String.format("%.0f", sleepData.getAverageSound()) + " dB");
            temperature.setText(String.format("%.0f", sleepData.getAverageTemperature()) + " °C");
            humidity.setText(String.format("%.0f", sleepData.getAverageHumidity()) + " %");
            co2.setText(String.format("%.0f", sleepData.getAverageCo2()) + " ppm");

            ArrayList<RoomCondition> roomConditions = sleepData.getRoomConditions();
            Collections.sort(roomConditions);
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
        });

        String[] arraySpinner = new String[]{
                "-choose parameter-", "Temperature", "Sound", "Humidity", "Co2"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sleepDataSpinner.setAdapter(adapter);
        //selection doesn't work on position 0, otherwise listener is activated while initializing the fragment and the array lists are still empty at that point (throws null pointer)
        sleepDataSpinner.setSelection(0, false);
        setListeners();
        return view;
    }

    public void setListeners() {
        deviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.updateSleepSessions(deviceIdList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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

        sleepDataSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Object item = parentView.getItemAtPosition(position).toString();
                dataSets.clear();

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
                xAxis.setValueFormatter(new SleepFragmentValueFormatter());
                mpLineChart.setData(data);
                mpLineChart.getMarker();
                mpLineChart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
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




