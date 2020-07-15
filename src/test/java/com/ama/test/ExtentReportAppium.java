package com.ama.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class ExtentReportAppium {
    ExtentSparkReporter htmlreport;
    ExtentReports extentReports;

    @BeforeSuite
    public void reportSetup(){
        htmlreport = new ExtentSparkReporter("extent.html");
        ExtentReports extentReports= new ExtentReports();
        extentReports.attachReporter(htmlreport);
    }
    @AfterSuite
    public void reportTearDown(){
        extentReports.flush();
    }
}
