package com.ama.pages;

import com.ama.test.ShoppingTest;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    protected final AndroidDriver driver;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,20);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
