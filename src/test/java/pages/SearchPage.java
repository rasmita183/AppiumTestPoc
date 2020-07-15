package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage{

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/list_product_linear_layout")
    private List<WebElement> searchItem;

    public SearchPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public int getTotalNumberSearchResults() {
        return this.searchItem.size();
    }

    public void selectAnItem(int index) {
        searchItem.get(index).click();
    }
}
