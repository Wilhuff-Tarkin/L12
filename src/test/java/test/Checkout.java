package test;

import base.BasketBase;
import configuration.handler.UserFactory;
import configuration.model.CartModel;
import configuration.model.UserModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HeaderPage;
import pages.OrderCheckoutFormPage;
import pages.ShoppingCartPage;

public class Checkout extends BasketBase {

    private static final Logger log = LoggerFactory.getLogger("Checkout");

    @Test
    void name() throws InterruptedException {
        UserModel user = UserFactory.getRandomUser();
        CartModel cartModelFromOrder = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);
        populateBasket(headerPage, cartModelFromOrder);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        cartPage.getProceedToCheckoutBtn().click();
        OrderCheckoutFormPage orderCheckout = new OrderCheckoutFormPage(driver);
        orderCheckout.fillPersonalInformation(user);

    }
}
