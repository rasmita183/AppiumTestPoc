package com.ama.test;

import com.ama.pages.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ShoppingTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ShoppingTest.class.getName());
    ExtentTest test;
    InitPage initialPage;
    HomePage homePage;
    SearchPage searchPage;
    SignInPage signInPage;

    @Test(priority = 0, description = "Check Login")
    public void testLogin() {
        test = extentReports.createTest("Check Login/skip login");
        test.log(Status.INFO,"Test started - Check Login/skip login");
        initialPage = new InitPage(driver);
        initialPage.skipSignIn();
        Assert.assertTrue(new HomePage(driver).isHomePage(), "HomePage is not displayed");
        test.log(Status.PASS,"Check Login. skip login");
//        initialPage.selectAlreadyMember();
//        signInPage = new SignInPage(driver);
//        signInPage.signIn("", "");
    }

    @Test(priority = 1, description = "search an item")
    public void searchItemTest() {
        test = extentReports.createTest("search an item");
        test.log(Status.INFO,"Test started - search an item");
        homePage = new HomePage(driver);
        homePage.searchItem("65-inch TV");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Assert.assertTrue(new SearchPage(driver).getTotalNumberSearchResults()>0, "Item count is zero");
        test.log(Status.PASS, "search an item");
    }

    @Test(priority = 2, description = "product page")
    public void checkProductPage() {
        test = extentReports.createTest("product page");
        test.log(Status.INFO,"Test started - product page");
        searchPage = new SearchPage(driver);
        Random random = new Random();
        Product searchProduct = searchPage.selectAnItem(random.nextInt(searchPage.getTotalNumberSearchResults()));
        new LocationPopup(driver).usingCurrentLocation();
        new ConfirmationPopup(driver).useOneTime();
        ProductPage productPage  = new ProductPage(driver);
        Product selectedProduct = productPage.getSelectedProductDetails();
        Assert.assertEquals(searchProduct.getTitle(), selectedProduct.getTitle(), "Title mismatch");
        Assert.assertEquals(searchProduct.getAmount(), selectedProduct.getAmount(), "Amount mismatch");
    }
}
