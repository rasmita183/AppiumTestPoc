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

public class ConfirmationPopup extends BasePage{

    private static final Logger logger = LogManager.getLogger(ConfirmationPopup.class);

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
    private WebElement oneTime;

    public ConfirmationPopup(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void useOneTime() {
        waitForElementToBeVisible(oneTime).click();
    }
}
