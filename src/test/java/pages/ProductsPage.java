package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".fancybox-iframe")
    WebElement iframe;

    @FindBy(css = ".product-image-container .product_img_link")
    List<WebElement> products;

    @FindBy(css = ".quick-view")
    WebElement quickView;

    @FindBy(css = "#thumbs_list a")
    List<WebElement> thumbnails;

    @FindBy(css = "#bigpic")
    WebElement mainPicture;

    public ProductsPage hoverOverProduct(int number) {
        mouseHover(products.get(number));
        return this;
    }

    public ProductsPage clickQuickView() {
        mouseClick(quickView);
        return this;
    }

    public int getNumberOfThumbnails() {
        return thumbnails.size();
    }

    public ProductsPage hoverOverThumbnail(int number) {
        mouseHover(thumbnails.get(number));
        return this;
    }

    public String getThumbnailPartLink(int number) {
        return thumbnails.get(number).getAttribute("href").split("-")[0];
    }

    public String getMainPicturePartLink() {
        return mainPicture.getAttribute("src").split("-")[0];
    }

    public ProductsPage switchToProductFrame() {
        driver.switchTo().frame(iframe);
        return this;
    }
}
