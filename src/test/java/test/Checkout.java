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
import pages.OrderConfirmationPage;
import pages.ShoppingCartPage;

import static org.hamcrest.MatcherAssert.assertThat;


public class Checkout extends BasketBase {

    private static final Logger log = LoggerFactory.getLogger("Checkout");

    @Test
    void orderDetailsShouldBeKeptThroughCheckout() throws InterruptedException {
        UserModel user = UserFactory.getRandomUser();
        CartModel cartModelFromOrder = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);
        populateBasket(headerPage, cartModelFromOrder);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        cartPage.getProceedToCheckoutBtn().click();

        OrderCheckoutFormPage orderCheckout = new OrderCheckoutFormPage(driver);
        orderCheckout.fillPersonalInformation(user);
        orderCheckout.fillAddress(user, testEnvironment);
        orderCheckout.chooseDelivery();
        orderCheckout.choosePayment();
        assertThat("Terms and conditions empty", orderCheckout.checkTermsAndServiceConditions());
        orderCheckout.finalizeOrder();
        OrderConfirmationPage orderConfirmation = new OrderConfirmationPage(driver);



//        on order confirmation page check if all added products are displayed and have all correct details (names, prices quantities)
//        o check if Payment method and Shipping method is matching what you selected in last step
//        o save Order reference number
//        o go to order history
//        o find Order reference on the list
//        o check if date, total price, payment and status is correct
//        o click on details
//        o check if all added products are displayed and have all correct details (names, prices quantities)
//        o check if Delivery address and Invoice address have values that you used in checkout
    }
}
