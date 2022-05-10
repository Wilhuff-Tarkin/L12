package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FilterPage extends BasePage {


    @Getter
    @FindBy(css = ".material-icons.close")
    private WebElement closeFilterBtn;

    @Getter
    @FindBy(css = ".ps-shown-by-js")
    private WebElement firstFilterOnPage;

    @Getter
    @FindBy(css = ".text-uppercase.h6.hidden-sm-down")
    private WebElement filterByLabel;

    @Getter
    @FindBy(css = "div[id^='slider-range'] > a:nth-of-type(1)")
    private WebElement sliderHandleLeft;

    @Getter
    @FindBy(css = "div[id^='slider-range'] > a:nth-of-type(2)")
    private WebElement sliderHandleRight;

    @Getter
    @FindBy(css = "li>p")
    private WebElement priceScope;

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    public void setPriceFilter(int from, int to) {
        int minPriceValue = getPriceScope(0);
        int maxPriceValue = getPriceScope(1);


        if (from > minPriceValue && from < maxPriceValue) {
            sliderHandleLeft = driver.findElement(By.cssSelector("div[id^='slider-range'] > a:nth-of-type(1)"));
            operatePriceSlider(sliderHandleLeft, from, SliderDirection.RIGHT);
        }

        if (to < maxPriceValue && to > from) {
            sliderHandleRight = driver.findElement(By.cssSelector("div[id^='slider-range'] > a:nth-of-type(2)"));
            operatePriceSlider(sliderHandleRight, to, SliderDirection.LEFT);
        }
    }

    private void operatePriceSlider(WebElement sliderHandle, int price, SliderDirection direction) {
        int offset;
        int sliderPosition = 0;
        Actions builder = new Actions(driver);

        switch (direction){
            case RIGHT:
                while (sliderPosition != price) {
                    offset = 10;
                    moveSliderByOffset(offset, sliderHandle, builder);
                    sliderPosition = getPriceScope(0);
                }
            case LEFT:
                while (sliderPosition != price) {
                    offset = -10;
                    moveSliderByOffset(offset, sliderHandle, builder);
                    sliderPosition = getPriceScope(1);
                }
        }
        builder.release().perform();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".faceted-overlay")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".filter-block")));
    }

    private void moveSliderByOffset(int offset, WebElement sliderHandle, Actions builder) {
        wait.until(ExpectedConditions.elementToBeClickable(sliderHandle));
        builder.clickAndHold(sliderHandle).moveByOffset(offset, 0).perform();
    }

    public void closeFilter() {
        closeFilterBtn.click();
    }

    public int getPriceScope(int position) {
        String[] scopeLabels = priceScope.getText().split(" - ");

        for (int i = 0; i < scopeLabels.length; i++) {
            scopeLabels[i] = scopeLabels[i].replaceAll("[^0-9]", "");
            scopeLabels[i] = scopeLabels[i].substring(0, scopeLabels[i].length() - 2);
        }
        return Integer.parseInt(scopeLabels[position]);
    }

    public enum SliderDirection {
        LEFT,
        RIGHT,
    }
}
