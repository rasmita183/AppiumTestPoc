package com.ama.test;

import com.ama.pages.ConfirmationPopup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    ExtentSparkReporter htmlreport;
    ExtentReports extentReports;

    AndroidDriver<MobileElement> driver;

    @BeforeSuite
    public void reportSetup(){
        htmlreport = new ExtentSparkReporter("extent.html");
        extentReports= new ExtentReports();
        extentReports.attachReporter(htmlreport);
    }

    @BeforeClass
    public void setUp() {
        try {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/rasmi/Downloads/Amazon_shopping.apk");
        capabilities.setCapability(MobileCapabilityType.UDID, "8ADY0JDW1");
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            logger.info("Driver created");
//            driver.installApp("/Users/rasmi/Downloads/Amazon_shopping.apk");
            driver.launchApp();
            logger.info("App launched");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unable to create driver with invalid url: " + e);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void reportTearDown(){
        extentReports.flush();
    }
}
