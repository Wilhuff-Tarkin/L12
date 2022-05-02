package test;

import configuration.model.CartModel;
import configuration.model.OrderProductModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AddedToCartModalPage;
import pages.HeaderPage;
import pages.ProductFullPage;
import pages.ShoppingCartPage;

public class Basket extends ShoppingCart {

    private static final Logger log = LoggerFactory.getLogger("Basket");
    int testIterations = Integer.parseInt(testEnvironment.returnValueAsString("basketIterations"));
    String customizableText = testEnvironment.returnValueAsString("customTxt");

    @Test
    void basketShouldContainOrderedProducts() {
        CartModel cartModel = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);

        populateBasket(headerPage, cartModel);
        headerPage.getOpenCartBtn().click();

        ShoppingCartPage cartPage = new ShoppingCartPage(driver);

        checkBasketContent(cartPage, cartModel);
        checkBasketValue(cartPage, cartModel);
        setQuantityOfProduct(5);
        

    }

    private void setQuantityOfProduct(int i) {
    }

    private void checkBasketValue(ShoppingCartPage cartPage, CartModel cartModel) {
    }

    private void checkBasketContent(ShoppingCartPage cartPage, CartModel cartModel) {
        
        
        
        
        
        
    }


    void populateBasket(HeaderPage headerPage, CartModel cartModel) {


        while (testIterations > 0) {
            headerPage.enterRandomCategory();
            String productName = enterRandomProduct();
            ProductFullPage productFullPage = new ProductFullPage(driver, productName);
            OrderProductModel orderedProduct = modelOrderedProduct(productFullPage);
            cartModel.addAnother(orderedProduct);
            productFullPage.addToCart(customizableText);
            AddedToCartModalPage modal = new AddedToCartModalPage(driver);
            checkIfPopupDisplayCorrectValues(modal, orderedProduct, cartModel);
            log.info(productName + " successfully added to the cart");
            modal.getContinueShopping().click();
            checkIfCartDisplaysCorrectNumberOfItems(headerPage, cartModel);
            testIterations--;
        }

    }

}
