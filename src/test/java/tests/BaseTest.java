package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() {
        try {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/rasmi/Downloads/Amazon_shopping.apk");
        capabilities.setCapability(MobileCapabilityType.UDID, "8ADY0JDW1");
//            capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
//            capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.amazon.mShop.android.shopping.MainActivity");
//            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.amazon.mShop.android.shopping");
//            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);


            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//            driver.installApp("/Users/rasmi/Downloads/Amazon_shopping.apk");
            driver.launchApp();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unable to create driver: " + e);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
