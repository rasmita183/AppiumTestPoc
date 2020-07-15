package com.ama.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LocationPopup extends BasePage{

    private static final Logger logger = LogManager.getLogger(LocationPopup.class);
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_gps_auto_detect")
    private WebElement useCurrentLocation;

    public LocationPopup(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void usingCurrentLocation() {
        waitForElementToBeVisible(useCurrentLocation).click();
    }
}
