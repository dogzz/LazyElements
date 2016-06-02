package com.dogzz.step;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
* @Author: dogzz
* @Created: 5/30/2016
*/
public class BaseStepsTest {

    @Before
    public void setUp() {
        BaseSteps.init();
        BaseSteps.startApp();
    }

    @After
    public void tearDown() {
        BaseSteps.closeApp();
    }

    @Test
    public void testOne() {
        String result = BaseSteps.convertEpochAndGetResult("1464615337");
        System.out.println(result);
        assertTrue("Verify that result contains Mon, 30 May 2016 13:35:37 GMT", result.contains("Mon, 30 May 2016 13:35:37 GMT"));

    }

    @Test
    public void testTwo() {
        assertFalse(BaseSteps.isFElementPresent());
    }

}