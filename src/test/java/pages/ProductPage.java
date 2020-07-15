package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import model.Product;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage{

    @AndroidFindBy(id = "titleExpanderContent")
    private WebElement productTitle;

    @AndroidFindBy(id = "atfRedesign_priceblock_priceToPay")
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
     *
     * @return Product details with title and price
     */
    public Product getSelectedProductDetails() {
        System.out.println(productsDetails.getText());
        Product product = new Product();
        product.setAmount(Integer.parseInt(price.getText().replaceAll("[^\\d.]", "")));
        product.setProductTitle(productTitle.getText());
        return product;
    }

    /**
     * Adds the product to cart
     */
    public void addToCart() {
        addToCart.click();
    }


}
