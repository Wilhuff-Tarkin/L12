package configuration.model;

import configuration.driver.DriverFactory;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ProductFullPage;

import java.util.ArrayList;
import java.util.List;

public class CartModel {

    private static final Logger log = LoggerFactory.getLogger("cart model");

    @Getter
    private List<ProductModel> cartContent = new ArrayList<>();

    private float totalOrderCost;

    @Getter
    private int numberOfItems;



    public void addAnother (ProductModel product) {


        if (checkIfThisIsAlreadyOnTheList(product.getName())){
                increaseQuantity(product);
        } else {
            //dodaj produkt do listy
            cartContent.add(product);
            numberOfItems++;

        }

    }

    public float getTotalOrderCost () {

        totalOrderCost = 0;

        for (ProductModel productModel : cartContent) {
            totalOrderCost += (productModel.getPrice() * productModel.getQuantity());
        }

        return totalOrderCost;
    }




    private void increaseQuantity(ProductModel product) {

        int additionalQuantity = product.getQuantity();

        for (int i = 0; i < cartContent.size(); i++) {
            if (cartContent.get(i).getName().contains(product.getName())){
                ProductModel productAlreadyOnTheList = cartContent.get(i);
                productAlreadyOnTheList.setQuantity(productAlreadyOnTheList.getQuantity()+additionalQuantity);
            }
        }
    }

    private boolean checkIfThisIsAlreadyOnTheList(String name) {

//        boolean checkResult = false;
//
//        if (cartContent.size()<1) {
//            log.info("Cart was empty");
//            return checkResult;
//        }
//
//        for (int i = 0; i < cartContent.size(); i++) {
//            if (cartContent.get(i).getName().contains(name)){
//                checkResult = true;
//            }
//        }
//    return checkResult;


        return cartContent.stream().anyMatch(productModel -> productModel.getName().equals(name));

    }


    @Override
    public String toString() {
        return "CartModel{" +
                "cartContent=" + cartContent.toString() +
                ", totalOrderCost=" + getTotalOrderCost() +
                '}';
    }
}
