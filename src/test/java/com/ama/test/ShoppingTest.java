package com.ama.test;

import com.ama.pages.*;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import model.Product;
import org.testng.annotations.Test;

import java.util.Random;

public class ShoppingTest extends BaseTest {

    InitPage initialPage;
    HomePage homePage;
    SearchPage searchPage;
    SignInPage signInPage;

    @Test(priority = 0, description = "Check Login")
    public void testLogin() {
        initialPage = new InitPage(driver);
        initialPage.skipSignIn();
//        initialPage.selectAlreadyMember();
//        signInPage = new SignInPage(driver);
//        signInPage.signIn("", "");
    }

    @Test(priority = 1, description = "search an item")
    public void searchItemTest() {
        homePage = new HomePage(driver);
        homePage.searchItem("65-inch TV");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        searchPage = new SearchPage(driver);
        Random random = new Random();
        searchPage.selectAnItem(random.nextInt(searchPage.getTotalNumberSearchResults()));
        new LocationPopup(driver).usingCurrentLocation();
        new ConfirmationPopup(driver).useOneTime();
        ProductPage productPage  = new ProductPage(driver);
        Product product = productPage.getSelectedProductDetails();
        productPage.addToCart();
    }
}
