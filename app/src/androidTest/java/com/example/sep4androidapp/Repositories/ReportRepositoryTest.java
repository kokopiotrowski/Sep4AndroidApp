package com.example.sep4androidapp.Repositories;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.fragments.sleepFragment.ValueFormatters.SleepFragmentValueFormatter;

import static org.junit.Assert.*;

@LargeTest
@RunWith(AndroidJUnit4.class)


public class ReportRepositoryTest {
    ReportRepository reportRepository;

    @Before
    public void setUp() throws Exception {
        reportRepository = ReportRepository.getInstance();

    }

    @Test
    public void testSizeAfterSettingAverage() throws Exception{
        LocalDateTime localDate = LocalDateTime.now();
        LocalDateTime localDate2 = localDate.plusDays(1);
        SleepSession sleepSession = new SleepSession(1, "device1", localDate2, localDate, 1, 1, 1, 1, 1);
        SleepSession sleepSessionLater = new SleepSession(1, "device1", localDate2, localDate, 1, 1, 1, 1, 1);

        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(sleepSession);
        sleepSessions.add(sleepSessionLater);
        List<SleepSession> newList = reportRepository.setAverage(sleepSessions);

        assertEquals(1, newList.size());

    }
    @Test
    public void testValueAfterSettingAverage() throws Exception{
        LocalDateTime localDate = LocalDateTime.now();
        LocalDateTime localDate2 = localDate.plusDays(1);

        SleepSession sleepSession = new SleepSession(1, "device1", localDate2, localDate, 1, 50, 1, 1, 1);
        SleepSession sleepSessionNew = new SleepSession(1, "device1", localDate2, localDate, 1, 30, 1, 1, 1);

        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(sleepSession);
        sleepSessions.add(sleepSessionNew);
        List<SleepSession> newList = reportRepository.setAverage(sleepSessions);

        assertEquals(40,(int)newList.get(0).getAverageCo2());

    }




}
