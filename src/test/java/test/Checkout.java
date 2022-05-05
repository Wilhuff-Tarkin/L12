package test;

import base.TestBase;
import configuration.handler.UserFactory;
import configuration.model.CartModel;
import configuration.model.UserModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HeaderPage;
import pages.ShoppingCartPage;

public class Checkout extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("Checkout");

    @Test
    void name() {
        UserModel user = UserFactory.getRandomUser();
        CartModel cartModelFromOrder = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);
        Basket basket = new Basket(driver);
        basket.populateBasket(headerPage, cartModelFromOrder);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        cartPage.getProceedToCheckoutBtn().click();


    }
}
