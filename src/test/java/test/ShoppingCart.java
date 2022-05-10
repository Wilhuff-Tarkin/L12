package test;

import base.BasketBase;
import configuration.model.CartModel;
import configuration.model.OrderProductModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AddedToCartModalPage;
import pages.HeaderPage;
import pages.ProductFullPage;


public class ShoppingCart extends BasketBase {

    private static final Logger log = LoggerFactory.getLogger("Shopping cart");


    @Test
    void shouldAddProductToCart() {

        CartModel cartModel = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);

        while (addToBasketIterations > 0) {
            headerPage.enterRandomCategory();
            String productName = enterRandomProduct();

            ProductFullPage productFullPage = new ProductFullPage(driver, productName);
            OrderProductModel orderedProduct = modelOrderedProduct(productFullPage);
            cartModel.addAnother(orderedProduct);

            productFullPage.addToCart(customizableText);
            AddedToCartModalPage modal = new AddedToCartModalPage(driver);

            checkIfPopupDisplayCorrectValues(modal, orderedProduct, cartModel);
            log.info(productName + " successfully added to the cart");

            clickOnElement(modal.getContinueShopping());
            checkIfCartDisplaysCorrectNumberOfItems(headerPage, cartModel);
            addToBasketIterations--;
        }
    }
}
