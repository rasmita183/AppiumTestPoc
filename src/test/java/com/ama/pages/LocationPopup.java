package com.ama.pages;

import com.ama.reporting.ExtentTestManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LocationPopup extends BasePage {

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_gps_auto_detect")
    private WebElement useCurrentLocation;

    public LocationPopup(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /**
     * Use Current location
     */
    public void usingCurrentLocation() {

        clickAnElement(useCurrentLocation);
        ExtentTestManager.reportInfo("Location Pop is displaying");
    }
}
