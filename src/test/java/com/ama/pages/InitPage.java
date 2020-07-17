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


public class InitPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(InitPage.class);
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
    private WebElement skipSignIn;
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
    private WebElement alreadyMember;

    public InitPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /**
     * Skips sign in process
     */
    public void skipSignIn() {
        clickAnElement(skipSignIn);
    }

}
