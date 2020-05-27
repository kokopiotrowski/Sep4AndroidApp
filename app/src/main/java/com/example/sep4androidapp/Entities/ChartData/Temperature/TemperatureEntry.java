package com.example.sep4androidapp.Entities.ChartData.Temperature;

import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.data.CandleEntry;


public class TemperatureEntry extends CandleEntry {
    public TemperatureEntry(float x, float shadowH, float shadowL, float open, float close) {
        super(x, shadowH, shadowL, open, close);
    }

    public TemperatureEntry(float x, float shadowH, float shadowL, float open, float close, Object data) {
        super(x, shadowH, shadowL, open, close, data);
    }

    public TemperatureEntry(float x, float shadowH, float shadowL, float open, float close, Drawable icon) {
        super(x, shadowH, shadowL, open, close, icon);
    }

    public TemperatureEntry(float x, float shadowH, float shadowL, float open, float close, Drawable icon, Object data) {
        super(x, shadowH, shadowL, open, close, icon, data);
    }
}
