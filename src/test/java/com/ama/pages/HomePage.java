package com.ama.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HomePage extends BasePage{

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
    private WebElement searchTextField;

    public HomePage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public boolean isHomePage() {
        return searchTextField.isDisplayed();
    }

    public void searchItem(String searchText) {
        searchTextField.click();
        searchTextField.sendKeys(searchText);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
