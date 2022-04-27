package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddedToCartModalPage extends BasePage {

    @Getter
    @FindBy(css = "#myModalLabel")
    private WebElement productAddedToCartLabel;

    public AddedToCartModalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



}
