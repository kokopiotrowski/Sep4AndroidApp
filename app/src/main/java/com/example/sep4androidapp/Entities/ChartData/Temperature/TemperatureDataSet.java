package com.example.sep4androidapp.Entities.ChartData.Temperature;

import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.List;

public class TemperatureDataSet extends CandleDataSet {



    public TemperatureDataSet(List<CandleEntry> yVals, String label) {
        super(yVals, label);
    }
}
