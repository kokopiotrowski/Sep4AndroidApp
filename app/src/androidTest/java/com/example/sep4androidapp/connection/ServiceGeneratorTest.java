package com.example.sep4androidapp.connection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ServiceGeneratorTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getAccountDeviceApi() throws Exception
    {
        assertNotEquals(null, ServiceGenerator.getAccountDevicesApi());
    }
    @Test
    public void getFactApi() throws Exception
    {
        assertNotEquals(null, ServiceGenerator.getFactApi());
    }
    @Test
    public void getPreferencesApi() throws Exception
    {
        assertNotEquals(null, ServiceGenerator.getPreferenceApi());
    }
    @Test
    public void getReportApi() throws Exception
    {
        assertNotEquals(null, ServiceGenerator.getReportApi());
    }
    @Test
    public void getRoomConditionApi() throws Exception
    {
        assertNotEquals(null, ServiceGenerator.getRoomConditionApi());
    }
    @Test
    public void getSleepTrackingApi() throws Exception
    {
        assertNotEquals(null, ServiceGenerator.getSleepTrackingApi());
    }

}