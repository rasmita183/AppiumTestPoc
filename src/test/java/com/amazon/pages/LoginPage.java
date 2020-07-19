package com.amazon.pages;

import com.amazon.reporting.ExtentTestManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
    private WebElement skipSignIn;
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
    private WebElement alreadyMember;
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_itself")
    private WebElement hamburger;
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/gno_greeting_text_view")
    private WebElement signInButton;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_email_login']")
    public WebElement userNameTextBox;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='continue']")
    public WebElement continueButton;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_password']")
    public WebElement passwordBox;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='signInSubmit']")
    public WebElement signInSubmitButton;
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='English - EN']")
    public WebElement languageRadioButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Save Changes']")
    public WebElement saveChangesButton;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }
    /**
     * Validate login with credentials
     */
    public void login(String userName , String passWord){
        clickAnElement(alreadyMember);
        ExtentTestManager.reportInfo("Clicked 'Already a login member'");
        clickAnElement(userNameTextBox);
        enterText(userNameTextBox,userName);
        ExtentTestManager.reportInfo("email id is entered");
        clickAnElement(continueButton);
        ExtentTestManager.reportInfo("clicked on continue button");
        clickAnElement(passwordBox);
        enterText(passwordBox,passWord);
        ExtentTestManager.reportInfo("Password is entered");
        clickAnElement(signInSubmitButton);
        ExtentTestManager.reportInfo("clicked sign in button");
    }

    /**
     * selects a language
     */
    public void selectLanguage() {
        clickAnElement(languageRadioButton);
        clickAnElement(saveChangesButton);
    }
}
