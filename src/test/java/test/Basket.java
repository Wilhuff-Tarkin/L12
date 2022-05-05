package test;

import base.BasketBase;
import configuration.model.CartModel;
import configuration.model.OrderProductModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AddedToCartModalPage;
import pages.HeaderPage;
import pages.ProductFullPage;
import pages.ShoppingCartPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class Basket extends BasketBase {

    private static final Logger log = LoggerFactory.getLogger("Basket");

    int testItemQuantity = Integer.parseInt(testEnvironment.returnValueAsString("testItemQuantity"));



    @Test
    void basketShouldContainOrderedProducts() throws InterruptedException {
        CartModel cartModelFromOrder = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);
        populateBasket(headerPage, cartModelFromOrder);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        checkBasketContent(cartPage, cartModelFromOrder);
        checkBasketValue(cartPage, cartModelFromOrder);

    }

    @Test
    void basketShouldRespondToQuantityChanges() throws InterruptedException {
        CartModel cartModelFromOrder = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);
        populateBasket(headerPage, cartModelFromOrder);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        checkBasketContent(cartPage, cartModelFromOrder);

        changeQuantityOfFirstItem(cartPage, cartModelFromOrder, testItemQuantity);
        checkBasketValue(cartPage, cartModelFromOrder);
        changeQuantityOfFirstItem(cartPage, cartModelFromOrder, testItemQuantity--);
        checkBasketValue(cartPage, cartModelFromOrder);
        changeQuantityOfFirstItem(cartPage, cartModelFromOrder, testItemQuantity++);
        checkBasketValue(cartPage, cartModelFromOrder);
    }

    @Test
    void basketShouldRespondToDeletions() throws InterruptedException {
        CartModel cartModelFromOrder = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);
        populateBasket(headerPage, cartModelFromOrder);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);

        List <WebElement> productRows = cartPage.getOrderRows();

        for (int i = 0; i < productRows.size(); i++) {
            String productName = productRows.get(i).findElement(By.cssSelector(".product-line-info>.label")).getText();
            productRows.get(i).findElement(By.cssSelector(".material-icons.float-xs-left")).click();

            cartModelFromOrder.removeProduct(productName);
            checkBasketValue(cartPage, cartModelFromOrder);
        }


    }




        private void changeQuantityOfFirstItem(ShoppingCartPage cartPage, CartModel cartModelFromOrder, int desiredQuantity) throws InterruptedException {
        WebElement firstRowOfOrder = cartPage.getOrderRows().get(0);
        String nameOfFirstProduct = firstRowOfOrder.findElement(By.cssSelector(" :first-child .label")).getText();
        int quantityChange = cartPage.setQuantityTo(desiredQuantity, firstRowOfOrder);
        List<OrderProductModel> list = cartModelFromOrder.getCartContent();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(nameOfFirstProduct)) {

                int quantity = list.get(i).getQuantity();
                int newQuantity = quantity + quantityChange;
                log.info("Quantity change: " + quantityChange + "old quant " + quantity + " new quantiy " + newQuantity);
                list.get(i).setQuantity(newQuantity);
            }
        }
        assertThat("Quantity not as expected", cartPage.getCurrentQuantity(firstRowOfOrder) == desiredQuantity);
    }

    private void checkBasketValue(ShoppingCartPage cartPage, CartModel cartModel) throws InterruptedException {
        //todo need better wait
        Thread.sleep(1500);
        float cartValue = cartPage.parsePrice(cartPage.getSubTotalValue());
        float modelCartValue = cartModel.getTotalOrderCost();

        log.info("Value of products in cart: " + cartValue + ". Expected value is " + modelCartValue);
        assertThat("Cart value not as expected", cartValue == modelCartValue);

    }

    private void checkBasketContent(ShoppingCartPage cartPage, CartModel cartModelFromOrder) {

        List<OrderProductModel> cartExpectedContent = cartModelFromOrder.getCartContent();
        List<OrderProductModel> cartActualContent = cartPage.getOrderedItemsList();

        List<OrderProductModel> productWithoutMatch = cartActualContent.stream().filter(e -> (cartExpectedContent.stream().filter(d -> d.equals(e)).count()) < 1)
                .collect(Collectors.toList());

        if (productWithoutMatch.size() == 0) {
            log.info("Shopping cart content as expected");
        }
        assertThat("Shopping cart content not as expected", productWithoutMatch.isEmpty());
    }



}
