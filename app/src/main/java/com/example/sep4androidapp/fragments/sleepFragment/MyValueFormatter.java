package com.example.sep4androidapp.fragments.sleepFragment;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class MyValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {


        long time = (long)value;
        Instant instant = Instant.ofEpochSecond(time);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        String actualTime = date.getHour() + ":" + date.getMinute() + ":" + date.getSecond();


        return actualTime;
    }
}