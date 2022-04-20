package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;

public class BasePage {

    private static Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected Random random = new Random();
    protected WebDriver driver;
    protected WebDriverWait wait;

    //todo wziac timeout z konfiguracji

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }




}
