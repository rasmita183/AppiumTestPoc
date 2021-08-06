package com.amazon.pages;

import com.amazon.model.Product;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProductPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='bylineInfo']/	android.widget.TextView")
    public WebElement compnayNameField;
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='title_feature_div']/android.view.View")
    public WebElement productTitle;
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='atfRedesign_priceblock_priceToPay']/android.widget.EditText")
    public WebElement price;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Cart']/android.view.View/android.widget.Button")
    public WebElement viewCartButton;
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='sc-mini-buy-box']/android.view.View/android.widget.Button")
    public WebElement proceedToBuyButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='add-to-cart-button']")
    private WebElement addToCart;

    public ProductPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /**
     * @return Product details with title and price
     */
    public Product getSelectedProductDetails() {
        Product product = new Product();
        product.setPrice(Integer.parseInt(getTextValue(price).replaceAll("[^\\d.]", "")));
        product.setTitle(productTitle.getText());
        return product;
    }

    /**
     * add product to cart
     */
    public void addToCart() {
        scrollToText("Add to Cart");
        clickAnElement(addToCart);
    }

    /**
     * view products in cart
     */

    public void viewProductsInCart() {
        waitForElementToBeVisible(viewCartButton);
        clickAnElement(viewCartButton);
    }

    /**
     * check if proceed to buy is displayed
     *
     * @return true if proceed to buy is displayed
     */

    public boolean isProceedToBuyDisplayed() {
        waitForElementToBeVisible(proceedToBuyButton);
        return isElementDisplayed(proceedToBuyButton);
    }


}
