package pages;

import configuration.model.OrderProductModel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("Shopping cart");

    @Getter
    @FindBy(css = ".text-sm-center .btn.btn-primary")
    private WebElement proceedToCheckoutBtn;

    @Getter
    @FindBy(css = ".product-line-grid")
    private List <WebElement> orderRows;

    @Getter
    @FindBy(css = "#cart-subtotal-products .value")
    private WebElement subTotalValue;




    private List<OrderProductModel> orderedItemsList = new ArrayList<>();



    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<OrderProductModel> getOrderedItemsList() {
        setAllProducts();
        return orderedItemsList;
    }


    public ShoppingCartPage setAllProducts() {

        for (int i = 0; i < orderRows.size(); i++) {

            String name = orderRows.get(i).findElement(By.cssSelector(" :first-child .label")).getText();
            float price = parsePrice(orderRows.get(i).findElement(By.cssSelector(".current-price .price")));
            WebElement quantityField = orderRows.get(i).findElement(By.cssSelector(".js-cart-line-product-quantity.form-control"));
            int quantity = Integer.parseInt(quantityField.getAttribute("value"));
            if (checkIfproductHaveRegularPrice(orderRows.get(i))){
                float regularPrice = parsePrice(orderRows.get(i).findElement(By.cssSelector(".regular-price")));
                orderedItemsList.add(new OrderProductModel(name, price, regularPrice, quantity));
            } else {
                orderedItemsList.add(new OrderProductModel(name, price, quantity));
            }
        }
        log.info("Added " + orderedItemsList.size() + " product(s) to the list");
        return this;
    }

    private boolean checkIfproductHaveRegularPrice(WebElement webElement) {
        return !webElement.findElements(By.cssSelector(".regular-price")).isEmpty();
    }

    public int setQuantityTo(int desiredQuantity, WebElement firstRowOfOrder) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(firstRowOfOrder));
        int quantityChange = 0;
        int currentQuantity = getCurrentQuantity(firstRowOfOrder);
        float valueBeforeChange = getCurrentRowValue(firstRowOfOrder);

        while (!(currentQuantity == desiredQuantity)){
            if (desiredQuantity > currentQuantity){
                WebElement upBtn = firstRowOfOrder.findElement(By.cssSelector(".material-icons.touchspin-up"));
                wait.until(ExpectedConditions.elementToBeClickable(upBtn));
                quantityChange++;
                upBtn.click();
            } else {
                WebElement downBtn = firstRowOfOrder.findElement(By.cssSelector(".material-icons.touchspin-down"));
                wait.until(ExpectedConditions.elementToBeClickable(downBtn));
                quantityChange--;
                downBtn.click();
        }
            currentQuantity = getCurrentQuantity(firstRowOfOrder);}

        return quantityChange;
    }

    private float getCurrentRowValue(WebElement rowOfOrder) {
        return parsePrice(rowOfOrder
                .findElement(By.cssSelector(".col-md-6.col-xs-2.price strong")));
    }

    public int getCurrentQuantity(WebElement rowOfOrder) {
        return Integer.parseInt(rowOfOrder
                .findElement(By.cssSelector(".js-cart-line-product-quantity.form-control")).getAttribute("value"));
    }

}
