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
public class RoomConditionTest {


    @Before
    public void setUp() throws Exception {


    }
    @Test
    public void checkCompareToSame() throws Exception{

        LocalDateTime dateNow = LocalDateTime.now();


        RoomCondition roomCondition1 = new RoomCondition(1, dateNow,  10, 4, 10,10);
        RoomCondition roomCondition2 = new RoomCondition(1, dateNow, 10,10,10, 10);

        assertEquals(0, roomCondition1.compareTo(roomCondition2));
    }

    @Test
    public void checkCompareToDifferent() throws Exception{

        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateLast = dateNow.minusDays(1);


        RoomCondition roomCondition1 = new RoomCondition(1, dateNow,  10, 4, 10,10);
        RoomCondition roomCondition2 = new RoomCondition(1, dateLast, 10,10,10, 10);

        assertEquals(1, roomCondition1.compareTo(roomCondition2));
    }

}
