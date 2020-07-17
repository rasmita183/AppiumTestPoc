package com.ama.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    static ExtentReports extent = ExtentManager.getInstance();

    /**
     * Gets instance of extent test
     * @return extent test
     */
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (Thread.currentThread().getId()));
    }

    /**
     * Collects the report at the end of the test
     */
    public static synchronized void endTest() {
        extent.flush();
    }

    /**
     * Creates an instance of extent test
     * @param testName name to be used to create extent test
     * @return instance of extent test
     */
    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put((int) (Thread.currentThread().getId()), test);
        return test;
    }
}
