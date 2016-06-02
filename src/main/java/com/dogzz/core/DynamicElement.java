package com.dogzz.core;/*
* @Author: dogzz
* @Created: 6/2/2016
*/

import org.openqa.selenium.WebElement;

public interface DynamicElement {
    default boolean isPresent(String marker) {
        return get(marker) != null;
    }

    WebElement get(String marker);
}
