package pages;

import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;


public class BasePage {

    private static Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected Random random = new Random();
    protected WebDriver driver;
    protected WebDriverWait wait;
    private JavascriptExecutor js = (JavascriptExecutor) driver;


    //todo wziac timeout z konfiguracji doalem inego waita w baseTEst - czy tego nalezy wywalic?

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }


    public float parsePrice (WebElement price) {
        wait.until(ExpectedConditions.elementToBeClickable(price));
        return Float.parseFloat(price.getText().substring(1));
    }



    public void waitForPageLoaded() {
//todo
    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


}
