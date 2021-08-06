package com.amazon.pages;

import com.amazon.model.Product;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SearchPage.class);
    String indexedProduct = "//android.widget.ListView[@resource-id='com.amazon.mShop.android.shopping:id/rs_vertical_stack_view']/android.widget.LinearLayout[%d]";
    String productTitle = indexedProduct + "//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title']";
    String productPrice = indexedProduct + "//android.widget.LinearLayout[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_price']//android.widget.TextView";
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/list_product_linear_layout")
    private List<WebElement> searchItem;
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
    private WebElement selectItem;
    @AndroidFindBy(id = "//android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_styled_price_v2']/android.widget.TextView")
    private String price;

    public SearchPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /**
     * @return Total number of search results
     */
    public int getTotalNumberSearchResults() {
        return this.searchItem.size();
    }

    /**
     * @return Select an Item from the list
     */
    public Product selectAnItem(int index) {
        Product product = new Product();
        WebElement element = searchItem.get(index);
        String titleXpath = String.format(productTitle, index + 1);
        String priceXpath = String.format(productPrice, index + 1);
        product.setTitle(getTextValue(getElement(By.xpath(titleXpath))));
        product.setPrice(Integer.parseInt(getTextValue(getElement(By.xpath(priceXpath))).split(" ")[0].replaceAll("[^\\d.]", "")));
        clickAnElement(element);
        return product;
    }
}
