package com.example.sep4androidapp.fragments.sleepFragment.ValueFormatters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FragmentsValueFormatterTest {

    FragmentsValueFormatter formatter;

    @Before
    public void setUp() throws Exception{
        formatter = new FragmentsValueFormatter();
    }


    @Test
    public void testFormatZero() throws Exception{

        assertEquals("01/01", formatter.getFormattedValue(0, null));

    }

    @Test
    public void testFormatAny() throws Exception{

        assertEquals("01/04", formatter.getFormattedValue(3, null));

    }

    @Test
    public void testFormatYear() throws Exception{

        int year = LocalDate.now().getYear();
        int days = 365;
        if((year%4 == 0 && year%100 != 0) || year%400==0)
            days = 366;
        assertEquals("01/01", formatter.getFormattedValue(days, null));

    }

    @Test
    public void testFormatMinus() throws Exception{


        assertEquals("12/31", formatter.getFormattedValue(-1, null));

    }
}