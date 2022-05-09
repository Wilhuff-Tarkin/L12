package pages;

import configuration.model.OrderProductModel;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationPage extends BasePage {

    @Getter
    String orderReference;
    @Getter
    String paymentMethod;
    @Getter
    String shippingMethod;

    @FindBy(css = ".material-icons.rtl-no-flip.done")
    private WebElement orderConfirmation;
    @FindBy(css = ".order-line.row")
    private List<WebElement> orderedItemsList;
    @FindBy(css = "#order-details li")
    private List<WebElement> orderDetails;
    private List<OrderProductModel> itemsOnOrderConfirmation;

    private float totalOrderCost;

    private static final DecimalFormat df = new DecimalFormat("0.00");




    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public OrderConfirmationPage populateItemsList() {
        wait.until(ExpectedConditions.visibilityOf(orderConfirmation));
        itemsOnOrderConfirmation = new ArrayList<>();
//        waitForListSizeIsHigherThanZero(orderedItemsList);

        for (WebElement orderRow : orderedItemsList) {
            //todo handle sizes and colors
            String name = StringUtils.substringBefore(orderRow.findElement(By.cssSelector(".col-sm-4.col-xs-9.details")).getText(), " - ");
            float price = parsePrice(orderRow.findElement(By.cssSelector((".col-xs-4.text-sm-center.text-xs-left"))));
            int quantity = Integer.parseInt(orderRow.findElement(By.cssSelector(".col-sm-6.col-xs-12.qty .row :nth-child(2)")).getText());
            itemsOnOrderConfirmation.add(new OrderProductModel(name, price, quantity));
        }
        return this;
    }

    public void setOrderDetails() {
        for (int i = 0; i < orderDetails.size(); i++) {
            String detailLine = orderDetails.get(i).getText();

            if (detailLine.startsWith("Order reference:")) {
                orderReference = parseOrderDetail(detailLine, "Order reference: ");
            }

            if (detailLine.startsWith("Payment method: ")) {
                paymentMethod = parseOrderDetail(detailLine, "Payment method: ");
            }

            if (detailLine.startsWith("Shipping method: ")) {
                shippingMethod = parseOrderDetail(detailLine, "Shipping method: ");
            }


        }
    }

    private String parseOrderDetail(String detailLine, String leading) {
        return detailLine.replaceAll(leading, "");
    }

    public List<OrderProductModel> getItemsOnOrderConfirmation() {
        populateItemsList();
        return itemsOnOrderConfirmation;
    }


    public float getTotalOrderCost() {
        totalOrderCost = 0;
        for (OrderProductModel productOrderModel : itemsOnOrderConfirmation) {
            totalOrderCost += (productOrderModel.getPrice() * productOrderModel.getQuantity());
        }
        return Float.parseFloat(df.format(totalOrderCost).replaceAll(",", "."));
    }

}
