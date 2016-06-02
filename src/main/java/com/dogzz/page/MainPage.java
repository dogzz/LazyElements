/*
* @Author: dogzz
* @Created: 5/30/2016
*/

package com.dogzz.page;

import static org.openqa.selenium.By.*;

import com.dogzz.core.DynamicElement;
import com.dogzz.core.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //Simple lazy

    private Element epochInput = () -> find(id("ecinput"));
    private Element convertEpochAction = () -> find(xpath("//*[contains(@title, 'Epoch to Human')]"), true);
    private Element result = () -> find(id("result1"), true, 10);
    private Element fictionalElement = () -> find(id("result123456"), false, 5);

    //Complicated lazy
    private DynamicElement epochInputEl = s -> find(id(s));
    private DynamicElement fictionalElementEl = s -> find(id(s), false, 5);

    public void enterEpoch(String epoch) {
//        WebElement elem = epochInput.get();
        WebElement elem = epochInputEl.get("ecinput");
        elem.clear();
        elem.sendKeys(epoch);
    }

    public void convertEpoch() {
        convertEpochAction.get().click();
    }

    public String getConvertedResult() {
        return result.get().getText();
    }

    public boolean isFictionalElementPresent() {
        return fictionalElementEl.isPresent("result12345");
    }

    public void open() {
        driver.get("http://www.epochconverter.com/");
    }
}
