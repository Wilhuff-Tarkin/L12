package base;

import configuration.driver.Browser;
import configuration.driver.DriverFactory;
import configuration.handler.YamlReader;
import configuration.model.EnvironmentModel;
import configuration.model.YamlModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import java.util.Locale;

public class TestBase {

    private static final Logger log = LoggerFactory.getLogger("TestBase.class");
    protected static EnvironmentModel testEnvironment;
    private static String loadedEnvironmentName;
    private static Browser loadedBrowser;
    private static YamlModel model;
    public WebDriver driver;
    public WebDriverWait driverWait;


    @BeforeAll
    static void beforeAll() {
        initializeTestEnvironment();
        logBasicLoadInformation();
        logDetailedLoadInformation();
    }

    private static void initializeTestEnvironment() {
        model = new YamlReader().loadData();
        loadedEnvironmentName = model.getTestedDataSet();
        loadedBrowser = model.getTestedBrowser();
        testEnvironment = new EnvironmentModel(model.getSpecificTestData(loadedEnvironmentName));


    }

    private static void logDetailedLoadInformation() {
        log.info("Tests will proceed using following data:");
        testEnvironment.getTestPropertiesMap().forEach((k, v) -> log.info("--> " + k + ": " + v));
    }

    private static void logBasicLoadInformation() {
        log.info(">>>> Parsed " + model.getAllTestData().size() + " sets of environment settings");
        log.info(">>>> Configuration loaded successfully. " + "Performing test on browser: " + loadedBrowser.toString().toUpperCase(Locale.ROOT) + " using environment: " + loadedEnvironmentName.toUpperCase(Locale.ROOT));
        log.info(">>>> Environment " + loadedEnvironmentName.toUpperCase(Locale.ROOT) + " contains " + testEnvironment.getTestPropertiesMap().size() + " properties");
    }

    @BeforeEach
    void setUp() {
        DriverFactory browser = new DriverFactory();
        driver = browser.getDriver(model.getTestedBrowser());
        driver.get(testEnvironment.returnValueAsString("appUrl"));
        setWindowOptions(driver);
        int timeout = Integer.parseInt(testEnvironment.returnValueAsString("timeout"));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    private void setWindowOptions(WebDriver driver) {
        driver.manage().window().maximize();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("document.body.style.zoom='100%'");
    }

    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
        log.info(">>>>>  Driver closed successfully.");
    }

    public boolean checkIfDesiredPageLoaded(String expectedTitle) {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".header-top")));
        String actualTitle = driver.findElement(By.cssSelector("#js-product-list-header")).getText();
        log.info("Expected title is " + expectedTitle + " and actual title is " + actualTitle);
        return actualTitle.equalsIgnoreCase(expectedTitle);
    }


}
