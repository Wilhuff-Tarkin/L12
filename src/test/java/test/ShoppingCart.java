package test;

import base.TestBase;
import com.google.gson.annotations.Until;
import configuration.model.CartModel;
import configuration.model.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;
import pages.*;

import static org.hamcrest.MatcherAssert.assertThat;


public class ShoppingCart extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("categories");


    @Test
    void shouldAddProductToCart() {

        int testIterations = 1;

        CartModel cartModel = new CartModel();


        HeaderPage headerPage = new HeaderPage(driver);

        while (testIterations>0) {
            headerPage.enterRandomCategory();
            ProductsSectionPage productsSectionPage = new ProductsSectionPage(driver);
            ProductMiniaturePage randomProduct = productsSectionPage.getRandomProduct();
            String productName = randomProduct.getName();
            randomProduct.getThumbnail().click();

            ProductFullPage productFullPage = new ProductFullPage(driver, productName);

            String name = productFullPage.getName();
            float price = productFullPage.getProductFinalPrice();
            int quantity = productFullPage.setRandomQuantity();

            ProductModel productModel = new ProductModel(name, price, quantity);
            cartModel.addAnother(productModel);

            productFullPage.getAddToCartBtn().click();
//        driver.switchTo().activeElement();

            AddedToCartModalPage modal = new AddedToCartModalPage(driver);

            checkIfPopupDisplayCorrectValues(modal, productModel, cartModel);
            log.info(productName + " successfully added to the cart");

            modal.getContinueShopping().click();
            testIterations--;
        }

    }

    private void checkIfPopupDisplayCorrectValues(AddedToCartModalPage modal, ProductModel productModel, CartModel cart) {

        driverWait.until(ExpectedConditions.visibilityOf(modal.getProductAddedToCartLabel()));
        assertThat("Label not as expected",modal.getProductAddedToCartLabel().getText().equals("\uE876Product successfully added to your shopping cart"));

        log.info("nazwa na modalu " + modal.getLastAddedProductName() + " " + " nazwa na modelu " + productModel.getName());
        assertThat("Product name incorrect", modal.getLastAddedProductName().equals(productModel.getName()));
        assertThat("Product price incorrect", modal.getLastAddedProductPrice() == productModel.getPrice());
        log.info("quantity na modalu " + modal.getLastAddedProductQuantity() + " " + " qt na modelu " + productModel.getQuantity());

        assertThat("Quantity incorrect", modal.getLastAddedProductQuantity() == productModel.getQuantity());
        assertThat("X items on your cart incorrect", modal.getThereAreXelementsInCart() == cart.getNumberOfItems());

    }
}
