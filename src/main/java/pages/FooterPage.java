package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage extends BasePage{

    @Getter
    @FindBy(css = "#link-product-page-prices-drop-1")
    private WebElement pricedDropBtn;



    public FooterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


}
