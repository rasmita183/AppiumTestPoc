package com.ama.pages;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final AndroidDriver<MobileElement> driver;

    public BasePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    /**
     * Wait for the element to be visible
     * @param element element to be visible
     * @return visible element
     */
    public WebElement waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     *  clicks an element
     * @param element to click
     */
    public void clickAnElement(WebElement element) {
        element.click();
    }

    /**
     * Gets text from the element
     * @param element Element to find text
     * @return text of the element
     */
    public String getTextValue(WebElement element) {
        return element.getText();
    }

    /**
     * Eneters text inside an element
     * @param element element to enter text
     * @param text text to be entered
     */
    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }


}
