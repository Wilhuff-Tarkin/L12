package pages;

import ch.qos.logback.core.joran.conditional.ThenAction;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.invoke.SwitchPoint;

public class FilterPage extends BasePage {




    @Getter
    @FindBy(css = ".material-icons.close")
    private WebElement closeFilterBtn;

    @Getter
    @FindBy(css = ".ps-shown-by-js")
    private WebElement firstFilterOnPage;

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
            operatePriceSlider(sliderHandleLeft, from, SliderDirection.RIGHT);
        }

        if (to < maxPriceValue && to > from) {
            operatePriceSlider(sliderHandleRight, to, SliderDirection.LEFT);
        }


    }

    private void operatePriceSlider(WebElement sliderHandle, int price, SliderDirection direction) {

        int offset = 0;
        int postion = 0;

        switch (direction){

            case RIGHT:
                while (postion != price) {
                    offset = 10;
//                    moveSliderByOffset(offset, sliderHandle);
                    moveSliderBySendKeys(direction, sliderHandleLeft);
                    postion = getPriceScope(0);

                }
            case LEFT:
                while (postion != price) {
                    offset = -10;
//                    moveSliderByOffset(offset, sliderHandle);
                    moveSliderBySendKeys(direction, sliderHandleRight);

                    postion = getPriceScope(1);
                }


        }

//
//        closeFilterBtn = driver.findElement(By.cssSelector(".material-icons.close"));
//        closeFilterBtn.click();
    }
//
//    private void moveSliderByOffset(int offset, WebElement sliderHandle) {
//        Actions builder = new Actions(driver);
//        builder.dragAndDropBy(sliderHandle, offset, 0).perform();
//        wait.until(ExpectedConditions.elementToBeClickable(sliderHandle));
//    }

    private void moveSliderBySendKeys (SliderDirection direction, WebElement sliderHandle){
                Actions builder = new Actions(driver);
                wait.until(ExpectedConditions.elementToBeClickable(sliderHandle));
              if (direction == SliderDirection.RIGHT) {
//                  sliderHandle = driver.findElement(By.cssSelector("div[id^='slider-range'] > a:nth-of-type(1)"));
                  builder.clickAndHold(sliderHandle).sendKeys(Keys.ARROW_RIGHT).release().perform();
              } else {
//                  sliderHandle = driver.findElement(By.cssSelector("div[id^='slider-range'] > a:nth-of-type(2)"));
                  builder.clickAndHold(sliderHandle).sendKeys(Keys.ARROW_LEFT).release().perform();

              }



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
