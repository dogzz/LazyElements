package com.dogzz.core;/*
* @Author: dogzz
* @Created: 5/30/2016
*/

import org.openqa.selenium.WebElement;

public interface Element {

    default boolean isPresent() {
        return get() != null;
    }

    WebElement get();
}
