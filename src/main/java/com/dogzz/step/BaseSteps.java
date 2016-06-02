/*
* @Author: dogzz
* @Created: 5/30/2016
*/

package com.dogzz.step;

import com.dogzz.page.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseSteps {

    protected static MainPage mainPage;

    public static void init() {
        WebDriver driver = new FirefoxDriver();
        mainPage = new MainPage(driver);
    }

    public static void startApp() {
        mainPage.open();
    }

    public static void closeApp() {
        mainPage.closeBrowser();
    }

    public static String convertEpochAndGetResult(String epoch) {
        mainPage.enterEpoch(epoch);
        mainPage.convertEpoch();
        return mainPage.getConvertedResult();
    }

    public static boolean isFElementPresent() {
        return mainPage.isFictionalElementPresent();
    }
}
