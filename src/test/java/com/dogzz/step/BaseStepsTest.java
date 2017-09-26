package com.dogzz.step;

import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.video.annotations.Video;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/*
* @Author: dogzz
* @Created: 5/30/2016
*/
public class BaseStepsTest {

    @Rule
    public VideoRule videoRule = new VideoRule();

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
    @Video(name = "first_test")
    public void testOne() {
        String result = BaseSteps.convertEpochAndGetResult("1464615337");
        System.out.println(result);
        assertTrue("Verify that result contains Mon, 30 May 2016 13:35:37 GMT", result.contains("Mon, 30 May 2016 13:35:37 GMT"));

    }

    @Test
    @Video(name = "second_test")
    public void testTwo() {
        assertFalse(BaseSteps.isFElementPresent());
    }

}