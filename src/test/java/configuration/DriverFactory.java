package configuration;

import configuration.models.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DriverFactory {

    private WebDriver driver;
    private Browser browser;
    private final String BROWSER_PROPERTY_KEY = "browser name";
    private final String IMPLICIT_WAIT_VALUE_KEY = "implicit wait";

    private final String URL_KEY = "web url";

    public DriverFactory() {
        EnvironmentProperty.getInstance();
    }

    public WebDriver getDriver() {

        browser = Browser.valueOf(System.getProperty(BROWSER_PROPERTY_KEY));

        switch (browser) {
            case CHROME -> setChrome();
            case FIREFOX -> setFirefox();
            case EDGE -> setEdge();
            default -> {
                InternetExplorerOptions optionsDefault = new InternetExplorerOptions();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(optionsDefault);
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(System.getProperty(IMPLICIT_WAIT_VALUE_KEY))));
        driver.get(System.getProperty(URL_KEY));
        return driver;
    }

    private void setChrome() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        getBrowserOptionsArguments().forEach(options::addArguments);
        driver = new ChromeDriver(options);
    }

    private void setFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        getBrowserOptionsArguments().forEach(options::addArguments);
        driver = new FirefoxDriver(options);
    }

    private void setEdge() {
        EdgeOptions options = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        getBrowserOptionsArguments().forEach(options::addArguments);
        driver = new EdgeDriver(options);
    }

    private List<String> getBrowserOptionsArguments() {
        Map<String, String> properties = EnvironmentProperty.getInstance().getProperties();
        List<String> browserOptionsKeys = properties.keySet().stream().filter(key -> key.startsWith("browser option")).toList();
        List<String> arguments = new ArrayList<>();
        for (String browserOptionKey : browserOptionsKeys) {
            arguments.add(properties.get(browserOptionKey));
        }
        return arguments;
    }
}