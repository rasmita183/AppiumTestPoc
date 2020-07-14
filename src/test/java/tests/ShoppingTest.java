package tests;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.InitPage;

public class ShoppingTest extends BaseTest {

    InitPage initialPage;
    HomePage homePage;

    @Test(priority = 0, description = "Check Login")
    public void testLogin() {
        initialPage = new InitPage(driver);
        initialPage.skipSignIn();
        System.out.println(driver.getBatteryInfo().getState().name());
    }

    @Test(priority = 1, description = "search an item")
    public void searchItemTest() {
        homePage = new HomePage(driver);
        homePage.searchItem("65-inch TV");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
