package configuration.driver;

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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    public WebDriver getDriver(Browser browser) {
        WebDriver driver;
        switch (browser) {
            case CHROME:
                driver = getChrome();
                break;
            case FIREFOX:
                driver = getFirefox();
                break;
            case EDGE:
                driver = getEdge();
                break;
            case IE:
                driver = getIe();
                break;
            default:
                driver = getIe();
        }
        log.info(">>>> Driver started. Browser: " + browser.name());
        return driver;
    }

    private WebDriver getIe() {

        InternetExplorerOptions defaultOptions = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }

    private WebDriver getEdge() {
        EdgeOptions optionsEdge = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        optionsEdge.addArguments("start-maximized");
        return new EdgeDriver(optionsEdge);
    }

    private WebDriver getFirefox() {
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(optionsFirefox);
    }

    private WebDriver getChrome() {
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("start-maximized");
        return new ChromeDriver(optionsChrome);
    }
}
