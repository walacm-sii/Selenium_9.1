package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#block_top_menu a[title='Women']")
    WebElement womenCategory;

    @FindBy(css = "#block_top_menu a[title='Blouses']")
    WebElement blouses;

    public MainPage hoverOverWomenCategory() {
        mouseHover(womenCategory);
        return this;
    }

    public MainPage selectBlouses() {
        mouseClick(blouses);
        return this;
    }
}
