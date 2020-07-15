package com.ama.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(SearchPage.class);

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/list_product_linear_layout")
    private List<WebElement> searchItem;

    public SearchPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public int getTotalNumberSearchResults() {
        return this.searchItem.size();
    }

    public Product selectAnItem(int index) {
        Product product = new Product();
        WebElement ele = searchItem.get(index);
        product.setTitle(ele.findElement(By.id("com.amazon.mShop.android.shopping:id/item_title")).getText());
        String price = ele.findElement(By.xpath("./android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_styled_price_v2']/android.widget.TextView")).getText();
        logger.info("Title of the product to select: " + product.getTitle());
        logger.info("Price of the product to select: " + product.getAmount());
        product.setAmount(Integer.parseInt(price.replaceAll("[^\\d.]", "")));
        ele.click();
        return product;
    }
}
