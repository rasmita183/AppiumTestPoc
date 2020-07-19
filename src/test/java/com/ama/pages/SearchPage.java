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
import java.time.Duration;
import java.util.List;
import com.ama.pages.BasePage;

public class SearchPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SearchPage.class);

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
        logger.info("Title of the product to select: " + product.getTitle());
        logger.info("Price of the product before parsing: " + price);
        logger.info("Price of the product to select: " + product.getPrice());
        clickAnElement(element);
        ExtentTestManager.reportInfo("Select an item");
        return product;
    }
}
