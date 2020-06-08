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



public class SleepFragmentValueFormatterTest {

    SleepFragmentValueFormatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new SleepFragmentValueFormatter();
    }
    @Test
    public void testFormatZero() throws Exception{

        assertEquals("00:00:00", formatter.getFormattedValue(0, null));

    }

    @Test
    public void testFormatAny() throws Exception{

        assertEquals("01:00:00", formatter.getFormattedValue(3600, null));

    }

    @Test
    public void testOver24H() throws Exception{

        assertEquals("00:00:01", formatter.getFormattedValue(864001, null));

    }




}

