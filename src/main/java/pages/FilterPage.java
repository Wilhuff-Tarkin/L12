package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterPage extends BasePage {


    @Getter
    @FindBy(css = ".material-icons.close")
    private WebElement closeFilterBtn;

    @Getter
    @FindBy(css = ".ps-shown-by-js")
    private WebElement firstFilterOnPage;

    @Getter
    @FindBy(css = "#slider-range_98591 :nth-child(2)")
    private WebElement sliderHandleLeft;

    @Getter
    @FindBy(css = "#slider-range_98591 :nth-child(3)")
    private WebElement sliderHandleRight;


    public FilterPage(WebDriver driver) {
        super(driver);
    }

    public void setPriceFilter (int from, int to){

    }

    public void closeFilter (){
                closeFilterBtn.click();
    }

}
