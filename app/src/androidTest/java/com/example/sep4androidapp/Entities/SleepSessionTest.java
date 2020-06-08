package com.example.sep4androidapp.Entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class SleepSessionTest {


    @Before
    public void setUp() throws Exception {


    }
    @Test
    public void checkCompareToSame() throws Exception{

        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateLast = dateNow.minusDays(1);

        SleepSession sleep1 = new SleepSession(1, "device1", dateLast, dateNow, 4, 200, 20, 20, 20);
        SleepSession sleep2 = new SleepSession(1, "device1", dateLast, dateNow, 4, 200, 20, 20, 20);

        assertEquals(0, sleep1.compareTo(sleep2));
    }

    @Test
    public void checkCompareToDifferent() throws Exception{

        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateLast = dateNow.minusDays(1);
        LocalDateTime dateLast2 = dateNow.minusDays(2);

        SleepSession sleep1 = new SleepSession(1, "device1", dateLast, dateNow, 4, 200, 20, 20, 20);
        SleepSession sleep2 = new SleepSession(1, "device1", dateLast2, dateLast, 4, 200, 20, 20, 20);

        assertEquals(1, sleep1.compareTo(sleep2));
    }
}