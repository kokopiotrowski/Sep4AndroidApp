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

    @Override
    public String getFormattedValue(float value, AxisBase axis) {


        /*long time = (long)value;
        Instant instant = Instant.ofEpochSecond(time);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        String actualTime = date.getHour() + ":" + date.getMinute() + ":" + date.getSecond();*/
        int timeInSeconds = (int) value;

        if(value > 86400){

            timeInSeconds = timeInSeconds - 86400;

        }

        hours = timeInSeconds / 3600;
        minutes = (timeInSeconds % 3600) / 60;
        seconds = timeInSeconds % 60;

        String timeString = String.format(Locale.ENGLISH,"%02d:%02d:%02d", hours, minutes, seconds);


        return timeString;
    }
}