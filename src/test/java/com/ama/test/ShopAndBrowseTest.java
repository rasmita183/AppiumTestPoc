package com.ama.test;

import com.ama.pages.*;
import com.ama.reporting.ExtentTestManager;
import com.ama.utils.PropertiesConstant;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import com.ama.model.Product;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ama.utils.PropertiesReader.properties;

import java.util.Random;

public class ShopAndBrowseTest extends BaseTest {
    InitPage initialPage;
    HomePage homePage;
    SearchPage searchPage;

    @Test(priority = 0, description = "Check skip Login")
    public void testLogin() {
        initialPage = new InitPage(driver);
        initialPage.skipSignIn();
        ExtentTestManager.getTest().log(Status.INFO, "Using Skip sign In");
        Assert.assertTrue(new HomePage(driver).isHomePage(), "HomePage is not displayed");
        ExtentTestManager.getTest().log(Status.INFO, "Home screen displayed");
    }

    @Test(priority = 1, description = "Verify search of an item")
    public void searchItemTest() {
        homePage = new HomePage(driver);
        ExtentTestManager.getTest().log(Status.INFO, "searching for: " + properties.getProperty(PropertiesConstant.ITEMS_TO_SEARCH));
        homePage.searchItem(properties.getProperty(PropertiesConstant.ITEMS_TO_SEARCH));
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        int totalItems = new SearchPage(driver).getTotalNumberSearchResults();
        ExtentTestManager.getTest().log(Status.INFO, "Total search items found: " + totalItems);
        Assert.assertTrue(new SearchPage(driver).getTotalNumberSearchResults() > 0, "Item count is zero");
    }

    @Test(priority = 2, description = "Verify items in search page and product page - making a fail test")
    public void checkProductPage() {
        searchPage = new SearchPage(driver);
        Random random = new Random();
        int index = random.nextInt(searchPage.getTotalNumberSearchResults());
        Product searchProduct = searchPage.selectAnItem(index);
        ExtentTestManager.getTest().log(Status.INFO, "Selecting product at index: " + index);
        new LocationPopup(driver).usingCurrentLocation();
        ExtentTestManager.getTest().log(Status.INFO, "Using current location to deliver");
        new ConfirmationPopup(driver).useOneTime();
        ProductPage productPage = new ProductPage(driver);
        Product selectedProduct = productPage.getSelectedProductDetails();
        ExtentTestManager.getTest().log(Status.INFO, "Select the product from the displayed items");
        Assert.assertEquals(searchProduct.getTitle(), selectedProduct.getTitle(), "Title mismatch");
        Assert.assertEquals(searchProduct.getPrice(), selectedProduct.getPrice(), "Amount mismatch");
        ExtentTestManager.getTest().log(Status.PASS, "Check for the amount is getting mismatch");
    }
}
