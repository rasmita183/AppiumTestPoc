package com.ama.test;

import com.ama.pages.*;
import com.ama.reporting.ExtentTestManager;
import com.ama.utils.PropertiesConstant;
import com.ama.model.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.ama.utils.PropertiesReader.properties;
import java.util.Random;

public class ShopAndBrowseTest extends BaseTest {
    InitPage initialPage;
    HomePage homePage;
    SearchPage searchPage;

    /**
     * Check the login functionality
     */

    @Test(priority = 0, description = "Check Login")
    public void testLogin() {
        initialPage = new InitPage(driver);
        initialPage.validateLoginButton(properties.getProperty(PropertiesConstant.USER_NAME), properties.getProperty(PropertiesConstant.PASS_WORD));
        ExtentTestManager.reportInfo("Verify Login");
        initialPage.selectLanguage();
        ExtentTestManager.reportInfo("Language has been selected");
        Assert.assertTrue(new HomePage(driver).validateIsHomePage(), "HomePage is not displayed");
        ExtentTestManager.reportInfo("Home Page is displayed");
    }

    /**
     * Search items in the text box
     */
    @Test(dependsOnMethods = {"testLogin"}, description = "Verify search of an item")
    public void searchItemTest() {
        homePage = new HomePage(driver);
        ExtentTestManager.reportInfo("searching for:" + properties.getProperty(PropertiesConstant.ITEMS_TO_SEARCH));
        homePage.searchItem(properties.getProperty(PropertiesConstant.ITEMS_TO_SEARCH));
        int totalItems = new SearchPage(driver).getTotalNumberSearchResults();
        ExtentTestManager.reportInfo("Total search items found: " + totalItems);
        Assert.assertTrue(new SearchPage(driver).getTotalNumberSearchResults() > 0, "Item count is zero");
    }

    /**
     * Search an item move to the product details page and scroll to add to cart
     */
    @Test(dependsOnMethods = {"searchItemTest"}, description = "Verify items in search page and product page - making a fail test")
    public void checkProductPage() {
        searchPage = new SearchPage(driver);
        Random random = new Random();
        int index = random.nextInt(searchPage.getTotalNumberSearchResults());
        Product searchProduct = searchPage.selectAnItem(index);
        ExtentTestManager.reportInfo("Selecting product at index: " + index);
        new LocationPopup(driver).usingCurrentLocation();
        ExtentTestManager.reportInfo("Current location detected");
        new ConfirmationPopup(driver).useOneTime();
        ProductPage productPage = new ProductPage(driver);
        Product selectedProduct = productPage.getSelectedProductDetails();
        ExtentTestManager.reportInfo("Select the product from the displayed items");
        productPage.verifyAddToCart();
        ExtentTestManager.reportPass("Check for scrolling to an element");
        Assert.assertEquals(searchProduct.getTitle(), selectedProduct.getTitle(), "Title mismatch");
        Assert.assertEquals(searchProduct.getPrice(), selectedProduct.getPrice(), "Amount mismatch");
        ExtentTestManager.reportPass("Check for the amount is getting mismatch");
    }
}
