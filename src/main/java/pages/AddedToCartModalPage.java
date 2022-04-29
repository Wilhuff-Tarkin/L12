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

    @FindBy(css = ".h6.product-name")
    private WebElement lastAddedProductName;

    @FindBy(css = ".modal-content .product-price")
    private WebElement lastAddedProductPrice;

    @FindBy(css = ".cart-content .cart-products-count")
    private WebElement thereAreXelementsInCart;

    public float getSubTotalValue() {

        return parsePrice(subTotalValue);
    }

    @FindBy(css = ".subtotal.value")
    private WebElement subTotalValue;

    @Getter
    @FindBy(css = ".btn.btn-secondary")
    private WebElement continueShopping;
    @Getter
    @FindBy(css = ".btn.btn-secondary")
    private WebElement proceedToCheckout;
    @FindBy(css = ".modal-content .product-quantity strong")
    private WebElement lastAddedProductQuantity;

    public AddedToCartModalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLastAddedProductName() {
        return lastAddedProductName.getText();
    }

    public int getThereAreXelementsInCart() {
        String result = thereAreXelementsInCart.getText();
        result = result.replaceAll("[^\\d]", "");
        System.out.println(result);
        return Integer.parseInt(result);

    }

    public int getLastAddedProductQuantity() {
        return Integer.parseInt(lastAddedProductQuantity.getText());
    }

    public float getLastAddedProductPrice() {
        return parsePrice(lastAddedProductPrice);
    }


}
