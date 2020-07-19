package com.amazon.test;

import com.amazon.pages.*;
import com.amazon.reporting.ExtentTestManager;
import com.amazon.utils.PropertiesConstant;
import com.amazon.model.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.amazon.utils.PropertiesReader.properties;
import java.util.Random;

public class ShopAndBrowseTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    SearchPage searchPage;

    /**
     * Check the login functionality
     */
    @Test(priority = 0, description = "Check Login")
    public void validateLogin() {
        loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty(PropertiesConstant.USER_NAME), properties.getProperty(PropertiesConstant.PASS_WORD));
        ExtentTestManager.reportInfo("Verified Login");
        loginPage.selectLanguage();
        ExtentTestManager.reportInfo("Language has been selected");
        Assert.assertTrue(new HomePage(driver).validateIsHomePage(), "HomePage is not displayed");
        ExtentTestManager.reportInfo("Home Page is displayed");
    }

    /**
     * Search items in the text box
     */
    @Test(dependsOnMethods = {"validateLogin"}, description = "Verify search of an item")
    public void searchItemTest() {
        homePage = new HomePage(driver);
        ExtentTestManager.reportInfo("searching for:" + properties.getProperty(PropertiesConstant.ITEMS_TO_SEARCH));
        homePage.searchItem(properties.getProperty(PropertiesConstant.ITEMS_TO_SEARCH));
        int totalItems = new SearchPage(driver).getTotalNumberSearchResults();
        ExtentTestManager.reportInfo("Total search items found: " + totalItems);
        Assert.assertTrue(new SearchPage(driver).getTotalNumberSearchResults() > 0, "Item count is zero");
    }

    /**
     * Select a random item
     */
    @Test(dependsOnMethods = {"searchItemTest"}, description = "Select a random product")
    public void selectSearchedProduct() {
        searchPage = new SearchPage(driver);
        Random random = new Random();
        int index = random.nextInt(searchPage.getTotalNumberSearchResults());
        Product searchProduct = searchPage.selectAnItem(index);
        ExtentTestManager.reportInfo("Selected product at index: " + index);
        ProductPage productPage = new ProductPage(driver);
        Product selectedProduct = productPage.getSelectedProductDetails();
        ExtentTestManager.reportInfo("Selected the product from searched item, title: " + selectedProduct.getTitle() + " price: " + selectedProduct.getPrice());
        Assert.assertEquals(searchProduct.getTitle(), selectedProduct.getTitle(), "Title mismatch");
        Assert.assertEquals(searchProduct.getPrice(), selectedProduct.getPrice(), "Amount mismatch");
    }

    /**
     * Verify add to cart
     */
    @Test(dependsOnMethods = {"selectSearchedProduct"}, description = "verify add to cart - just checking a fail case")
    public void addProductToCart() {
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.viewProductsInCart();
        Assert.assertFalse(productPage.isProceedToBuyDisplayed(), "Proceed to buy is not displayed");
    }
}
