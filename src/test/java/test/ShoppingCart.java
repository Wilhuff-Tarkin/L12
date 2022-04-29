package test;

import base.TestBase;
import configuration.model.CartModel;
import configuration.model.ProductMiniatureModel;
import configuration.model.ProductOrderModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AddedToCartModalPage;
import pages.HeaderPage;
import pages.ProductFullPage;
import pages.ProductsSectionPage;

import static org.hamcrest.MatcherAssert.assertThat;


public class ShoppingCart extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("categories");


    @Test
    void shouldAddProductToCart() {

        int testIterations = Integer.parseInt(testEnvironment.returnValueAsString("iterations"));
        String customizableText = testEnvironment.returnValueAsString("customTxt");
        CartModel cartModel = new CartModel();


        HeaderPage headerPage = new HeaderPage(driver);

        while (testIterations > 0) {
            headerPage.enterRandomCategory();
            ProductsSectionPage productsSectionPage = new ProductsSectionPage(driver);
            ProductMiniatureModel randomProduct = productsSectionPage.getRandomProduct();
            String productName = randomProduct.getName();
            randomProduct.getThumbnail().click();

            ProductFullPage productFullPage = new ProductFullPage(driver, productName);

            String name = productFullPage.getName();
            float price = productFullPage.getProductFinalPrice();
            int quantity = productFullPage.setRandomQuantity();

            ProductOrderModel productOrderModel = new ProductOrderModel(name, price, quantity);
            cartModel.addAnother(productOrderModel);

            productFullPage.addToCart(customizableText);

            AddedToCartModalPage modal = new AddedToCartModalPage(driver);

            checkIfPopupDisplayCorrectValues(modal, productOrderModel, cartModel);
            log.info(productName + " successfully added to the cart");

            modal.getContinueShopping().click();
            checkIfCartDisplaysCorrectNumberOfItems(headerPage, cartModel);
            testIterations--;
        }

    }

    private void checkIfCartDisplaysCorrectNumberOfItems(HeaderPage headerPage, CartModel cartModel) {

        int productCountFromHeader = Integer.parseInt(headerPage.getCartProductsCount().getText());

        assertThat("Product count in cart incorrect", productCountFromHeader == cartModel.getNumberOfItems());

    }

    private void checkIfPopupDisplayCorrectValues(AddedToCartModalPage modal, ProductOrderModel productOrderModel, CartModel cart) {

        driverWait.until(ExpectedConditions.visibilityOf(modal.getProductAddedToCartLabel()));
        assertThat("Label not as expected", modal.getProductAddedToCartLabel().getText().equals("\uE876Product successfully added to your shopping cart"));

//        log.info("nazwa na modalu " + modal.getLastAddedProductName() + " " + " nazwa na modelu " + productOrderModel.getName());
        assertThat("Product name incorrect", modal.getLastAddedProductName().equals(productOrderModel.getName()));
        assertThat("Product price incorrect", modal.getLastAddedProductPrice() == productOrderModel.getPrice());
//        log.info("quantity na modalu " + modal.getLastAddedProductQuantity() + " " + " qt na modelu " + productOrderModel.getQuantity());

        assertThat("Quantity incorrect", modal.getLastAddedProductQuantity() == cart.getQuantity(productOrderModel.getName()));
//        log.info("w koszyku na modalu " + modal.getThereAreXelementsInCart() + " " + " w koszyku modelowym " + cart.getNumberOfItems());

        assertThat("X items on your cart incorrect", modal.getThereAreXelementsInCart() == cart.getNumberOfItems());
        log.info("wartosc produkotw z modala " + modal.getSubTotalValue() + " wartosc z modelu koszyka " + cart.getTotalOrderCost());
        assertThat("Total products value incorrect", modal.getSubTotalValue() == cart.getTotalOrderCost());
    }
}
