package com.amazon.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    protected final AndroidDriver<MobileElement> driver;

    public BasePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    /**
     * Wait for the element to be visible
     *
     * @param element element to be visible
     * @return visible element
     */
    public WebElement waitForElementToBeVisible(WebElement element) {
        logger.info("Wait For element to be present - {}", element);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * clicks an element
     *
     * @param element to click
     */
    public void clickAnElement(WebElement element) {
        element.click();
        logger.info("clicked element: " + element);
    }

    /**
     * Gets text from the element
     *
     * @param element Element to find text
     * @return text of the element
     */
    public String getTextValue(WebElement element) {
        return element.getText();
    }

    /**
     * Eneters text inside an element
     *
     * @param element element to enter text
     * @param text    text to be entered
     */
    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
        logger.info("Entered text {} in element: {}", text, element);
    }

    /**
     * @param text scroll to the text
     */
    public void scrollToText(String text) {
        logger.info("Scrolling to text: {}", text);
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
    }

    /**
     * this method is used to press enter
     */
    public void pressEnter() {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    /**
     *
     * @param element element to check visibility
     * @return true is element is displayed
     */
    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Finds an element
     * @param by
     * @return element
     */
    public WebElement getElement(By by) {
        return driver.findElement(by);
    }

}
