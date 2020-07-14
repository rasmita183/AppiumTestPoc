package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class InitPage {

    private final AndroidDriver driver;

//    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Skip sign in\")")
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
    private WebElement skipSignIn;

    public InitPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void skipSignIn() {
        skipSignIn.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
