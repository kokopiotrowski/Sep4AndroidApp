package com.example.sep4androidapp.fragments.sleepFragment;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


import java.time.LocalDateTime;

public class MyValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        /*return new LocalDateTime(new Float(value).longValue()).toString();*/
        return null;
    }
}