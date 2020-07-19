package com.ama.pages;

import com.ama.reporting.ExtentTestManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import com.ama.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;

public class ProductPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    @AndroidFindBy(id = "titleExpanderContent")
    private WebElement productTitle;

    @AndroidFindBy(xpath = "//android.webkit.WebView//android.widget.EditText[contains(text(), 'rupees')]")
    private WebElement price;

    @AndroidFindBy(className = "android.webkit.WebView")
    private WebElement productsDetails;

    @AndroidFindBy(id = "add-to-cart-button")
    private WebElement addToCart;

    public ProductPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /**
     * @return Product details with title and price
     */
    public Product getSelectedProductDetails() {
        logger.info(getTextValue(productsDetails));
        Product product = new Product();
        product.setPrice(Integer.parseInt(getTextValue(price).replaceAll("[^\\d.]", "")));
        product.setTitle(productTitle.getText());
        return product;
    }

    /**
     * @return scroll to a text
     */
    public void verifyAddToCart() {
        scrollToText("add-to-cart-button");
        Assert.assertEquals(addToCart, "ADD TO CART");
        ExtentTestManager.reportInfo("Verify Add To Cart");
    }


}
