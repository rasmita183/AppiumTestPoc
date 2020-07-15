package com.ama.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Set;


public class InitPage extends BasePage{

//    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Skip sign in\")")
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
    private WebElement skipSignIn;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
    private WebElement alreadyMember;

    public InitPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void skipSignIn() {
        skipSignIn.click();
    }
    public void selectAlreadyMember() {
        alreadyMember.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> contexts = driver.getContextHandles();

        for (String context : contexts) {
            System.out.println(contexts);
            if (!context.equals("NATIVE_APP")) {
                driver.context(context);
                break;
            }
        }
    }

}
