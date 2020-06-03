package com.example.sep4androidapp.ValueFormatters;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.time.LocalDate;
import java.util.Locale;

public class FragmentsValueFormatter implements IAxisValueFormatter {
    int year;
    int days;
    int months;
    long newValue;
    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        newValue = (long) value;
        year = LocalDate.now().getYear();
        LocalDate firstDate = LocalDate.of(year,1,1);
        LocalDate newDate  = firstDate.plusDays(newValue);
        months = newDate.getMonthValue();
        days = newDate.getDayOfMonth();

        String dateString = String.format(Locale.ENGLISH,"%02d/%02d", months, days);

        return dateString;
    }
}
