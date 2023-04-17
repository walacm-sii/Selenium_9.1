package tests;

import configuration.DriverFactory;
import configuration.EnvironmentProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;


    @BeforeAll
    static void setup() {
        EnvironmentProperty.getInstance();
    }

    @BeforeEach
    void setupDriver() {
        driver = new DriverFactory().getDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
