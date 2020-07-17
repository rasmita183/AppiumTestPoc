package com.ama.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
    private WebElement searchTextField;

    public HomePage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /**
     * Validate home page is displaying
     * @return true if home is displayed
     */
    public boolean isHomePage() {
        return searchTextField.isDisplayed();
    }

    /**
     * Searching an Item in the search box
     * @param searchText Item to be searched
     */
    public void searchItem(String searchText) {
        waitForElementToBeVisible(searchTextField);
        clickAnElement(searchTextField);
        enterText(searchTextField, searchText);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
