package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SignInPage extends BasePage{

    @AndroidFindBy(id = "ap_email_login")
    private WebElement email;

    @AndroidFindBy(id = "ap_password")
    private WebElement password;

    @AndroidFindBy(id = "continue")
    private WebElement continueButton;

    @AndroidFindBy(id = "signInSubmit")
    private WebElement signInButton;

    public SignInPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void signIn(String userName, String password) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        email.click();
        email.clear();
        email.sendKeys(userName);
        continueButton.click();
        this.password.clear();
        this.password.sendKeys(password);
        signInButton.click();
    }
}
