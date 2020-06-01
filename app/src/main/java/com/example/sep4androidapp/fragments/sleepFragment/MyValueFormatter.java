package com.example.sep4androidapp.fragments.sleepFragment;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;

public class MyValueFormatter implements IAxisValueFormatter {

    int hours;
    int minutes;
    int seconds;
    int count;

    @Override
    public String getFormattedValue(float value, AxisBase axis) {


        int timeInSeconds = (int) value;

        count = timeInSeconds/86400;

        if(count > 0){

            timeInSeconds = timeInSeconds - 86400*count;

        }

        hours = timeInSeconds / 3600;
        minutes = (timeInSeconds % 3600) / 60;
        seconds = timeInSeconds % 60;

        String timeString = String.format(Locale.ENGLISH,"%02d:%02d:%02d", hours, minutes, seconds);


        return timeString;
    }
}