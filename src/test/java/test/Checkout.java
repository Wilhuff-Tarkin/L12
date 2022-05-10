package test;

import base.BasketBase;
import configuration.handler.UserFactory;
import configuration.model.CartModel;
import configuration.model.OrderProductModel;
import configuration.model.UserModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HeaderPage;
import pages.OrderCheckoutFormPage;
import pages.OrderConfirmationPage;
import pages.ShoppingCartPage;

import java.util.List;
import java.util.stream.Collectors;

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

        clickOnElement(cartPage.getProceedToCheckoutBtn());

        OrderCheckoutFormPage orderCheckout = new OrderCheckoutFormPage(driver);
        orderCheckout.fillPersonalInformation(user);
        orderCheckout.fillAddress(user, testEnvironment);
        orderCheckout.chooseDelivery();
        orderCheckout.choosePayment();
        assertThat("Terms and conditions empty", orderCheckout.checkTermsAndServiceConditions());
        orderCheckout.finalizeOrder();

        OrderConfirmationPage orderConfirmation = new OrderConfirmationPage(driver);
        productsOnConfirmationPageAsExpected(orderConfirmation, cartModelFromOrder);
        checkShippingMethod(orderConfirmation, orderCheckout);
        checkPaymentMethod(orderConfirmation, orderCheckout);
        verifyOrderHistory(orderConfirmation, orderCheckout);
    }

    private void verifyOrderHistory(OrderConfirmationPage orderConfirmation, OrderCheckoutFormPage orderCheckout) {

        String orderRef = orderConfirmation.getOrderReference();

        //todo
        //        o go to order history
        //        o find Order reference on the list
        //        o check if date, total price, payment and status is correct
        //        o click on details
        //        o check if all added products are displayed and have all correct details (names, prices quantities)
        //        o check if Delivery address and Invoice address have values that you used in checkout
    }

    private void checkShippingMethod(OrderConfirmationPage orderConfirmation, OrderCheckoutFormPage orderCheckout) {

        orderConfirmation.setOrderDetails();
        assertThat("Delivery not as requested", orderConfirmation.getShippingMethod().contains(orderCheckout.getChosenDelivery()));
    }

    private void checkPaymentMethod(OrderConfirmationPage orderConfirmation, OrderCheckoutFormPage orderCheckout) {

        orderConfirmation.setOrderDetails();
        assertThat("Payment not as requested", orderConfirmation.getPaymentMethod().contains(orderCheckout.getChosenPayment()));
    }

    private void productsOnConfirmationPageAsExpected(OrderConfirmationPage orderConfirmation, CartModel cartModelFromOrder) {

        List<OrderProductModel> cartExpectedContent = cartModelFromOrder.getCartContent();
        List<OrderProductModel> orderContentActual = orderConfirmation.getItemsOnOrderConfirmation();

        List<OrderProductModel> productWithoutMatch = orderContentActual.stream().filter(e -> (cartExpectedContent.stream().filter(d -> d.equals(e)).count()) < 1)
                .collect(Collectors.toList());

        if (productWithoutMatch.size() == 0) {
            log.info("Shopping cart content as expected");
        }
        assertThat("Shopping cart content not as expected", productWithoutMatch.isEmpty());
    }
}
