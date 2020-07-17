package com.ama.reporting;

import com.ama.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    public void onStart(ITestContext context) {
        logger.info("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        logger.info(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        logger.info(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getDescription());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = ExtentTestManager.getTest();
        logger.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        extentTest.info((result.getMethod().getDescription() + " failed!"));

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        ;

        String targetLocation = null;
        String testClassName = result.getInstanceName();
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String testMethodName = result.getName().trim();
        String screenShotName = testMethodName + timeStamp + ".png";
        String reportsPath = System.getProperty("user.dir") + File.separator + "TestReport" + File.separator + "screenshots";
        String encodedBase64 = null;
        logger.info("Screen shots reports path - " + reportsPath);
        try {
            File file = new File(reportsPath + File.separator + testClassName);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    logger.info("Directory: " + file.getAbsolutePath() + " is created!");
                } else {
                    logger.info("Failed to create directory: " + file.getAbsolutePath());
                }

            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            targetLocation = reportsPath + File.separator + testClassName + File.separator + screenShotName;
            File targetFile = new File(targetLocation);
            logger.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
            logger.info("Target File location - " + targetFile.getAbsolutePath());
            FileHandler.copy(screenshotFile, targetFile);

            FileInputStream fileInputStream = new FileInputStream(targetFile);
            byte[] bytes = new byte[(int) targetFile.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
            ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(encodedBase64).build());
        } catch (FileNotFoundException e) {
            logger.info("File not found exception occurred while taking screenshot " + e.getMessage());
        } catch (IOException e) {
            logger.info("An exception occured while capturing screenshot from base 64" + e.getMessage());
        }
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

}
