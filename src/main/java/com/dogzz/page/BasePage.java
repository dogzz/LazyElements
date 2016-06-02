/*
* @Author: dogzz
* @Created: 5/30/2016
*/

package com.dogzz.page;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class BasePage {

    private static final int DEFAULT_DRIVER_WAIT_TIMEOUT = 20;
    private static final int DEFAULT_DRIVER_POLLING_INTERVAL = 500;
    private Logger log = LoggerFactory.getLogger(BasePage.class);

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Find UI Element using instance of Driver with default timeout
     * NoSuchElementException will be raised if no element found during timeout
     * @param locator of the element to be found
     * @return WebElement if found
     */
    protected WebElement find(By locator) {
        return find(locator, true, DEFAULT_DRIVER_WAIT_TIMEOUT, DEFAULT_DRIVER_POLLING_INTERVAL);
    }

    /**
     * Find UI Element using instance of Driver
     * @param locator of the element to be found
     * @param isRequired if true exception will be raised if element not found
     * @return WebElement if found or null otherwise
     */
    protected WebElement find(By locator, boolean isRequired) {
        return find(locator, isRequired, DEFAULT_DRIVER_WAIT_TIMEOUT, DEFAULT_DRIVER_POLLING_INTERVAL);
    }

    /**
     * Find UI Element using instance of Driver
     * NoSuchElementException will be raised if no element found during timeout
     * @param locator of the element to be found
     * @param timeout to wait element in seconds
     * @return WebElement if found
     */
    protected WebElement find(By locator, int timeout) {
        return find(locator, true, timeout, DEFAULT_DRIVER_POLLING_INTERVAL);
    }

    /**
     * Find UI Element using instance of Driver
     * @param locator of the element to be found
     * @param isRequired if true exception will be raised if element not found
     * @param timeout to wait element in seconds
     * @return WebElement if found or null otherwise
     */
    protected WebElement find(By locator, boolean isRequired, int timeout) {
        return find(locator, isRequired, timeout, DEFAULT_DRIVER_POLLING_INTERVAL);
    }

    /**
     * Find UI Element using instance of Driver
     * @param locator of the element to be found
     * @param isRequired if true exception will be raised if element not found
     * @param timeout to wait element in seconds
     * @param pollingInterval interval of checking element existence in milliseconds
     * @return WebElement if found or null otherwise
     */
    protected WebElement find(By locator, boolean isRequired, int timeout, int pollingInterval) {
        String strLocator = locator.toString();
        log.info("Find Element Using Driver with locator {}; Required={}; Timeout={}; Polling={}",
                strLocator, isRequired, timeout, pollingInterval);

        WebElement result;
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(pollingInterval, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        try {
            result = wait.until(
                    new Function<WebDriver, WebElement>() {
                        @Override
                        public WebElement apply(WebDriver driver) {
                            log.info("Try to find element");
                            return driver.findElement(locator);
                        }
                    }
            );
        } catch (TimeoutException e) {
            if (isRequired) {
                throw new NoSuchElementException(String.format("Cant find Element using Driver with locator %s", strLocator));
            } else {
                log.info("Element not found {}", strLocator);
                return null;
            }
        }
        log.info("Element found {}", strLocator);
        return result;
    }

}
