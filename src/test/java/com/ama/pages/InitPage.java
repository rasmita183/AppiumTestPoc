package com.ama.pages;

import com.ama.reporting.ExtentTestManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.testng.Assert.assertEquals;


public class InitPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(InitPage.class);
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

    public InitPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }
    /**
     * Validate login button and login with credentials
     */
    public void validateLoginButton(String userName , String passWord){
        validateElementIsDisplayed(alreadyMember);
        ExtentTestManager.reportPass("Validate login page");
        clickAnElement(alreadyMember);
        ExtentTestManager.reportInfo("Click Already a login member");
        validateElementIsDisplayed(userNameTextBox);
        ExtentTestManager.reportPass("Validate Username");
        clickAnElement(userNameTextBox);
        ExtentTestManager.reportInfo("click username field");
        enterText(userNameTextBox,userName);
        ExtentTestManager.reportInfo("gmail id is entered");
        clickAnElement(continueButton);
        ExtentTestManager.reportInfo("click on continue button");
        validateElementIsDisplayed(passwordBox);
        ExtentTestManager.reportPass("validate password field");
        clickAnElement(passwordBox);
        ExtentTestManager.reportInfo("Password text box has been clicked");
        enterText(passwordBox,passWord);
        ExtentTestManager.reportInfo("Password is entered");
        validateElementIsDisplayed(signInSubmitButton);
        ExtentTestManager.reportPass("Sign in button validate");
        clickAnElement(signInSubmitButton);
        ExtentTestManager.reportInfo("click sign in button");



    }

    /**
     * This method clicks in RadioButton for language set
     */
    public void selectLanguage() {
        clickAnElement(languageRadioButton);
        clickAnElement(saveChangesButton);

    }
}
