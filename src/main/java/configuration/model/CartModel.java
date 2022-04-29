package configuration.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CartModel {

    private static final Logger log = LoggerFactory.getLogger("cart model");

    @Getter
    private List<ProductOrderModel> cartContent = new ArrayList<>();

    private float totalOrderCost;

    @Getter
    private int numberOfItems;



    public void addAnother (ProductOrderModel product) {


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

        for (ProductOrderModel productOrderModel : cartContent) {
            totalOrderCost += (productOrderModel.getPrice() * productOrderModel.getQuantity());
        }

        return totalOrderCost;
    }

    public int getNumberOfItems () {

        numberOfItems = 0;

        for (ProductOrderModel productOrderModel : cartContent) {
            numberOfItems += productOrderModel.getQuantity();
        }

        return numberOfItems;
    }



    private void increaseQuantity(ProductOrderModel product) {

        int additionalQuantity = product.getQuantity();

        for (int i = 0; i < cartContent.size(); i++) {
            if (cartContent.get(i).getName().contains(product.getName())){
                ProductOrderModel productAlreadyOnTheList = cartContent.get(i);
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


        return cartContent.stream().anyMatch(productOrderModel -> productOrderModel.getName().equals(name));

    }


    @Override
    public String toString() {
        return "CartModel{" +
                "cartContent=" + cartContent.toString() +
                ", totalOrderCost=" + getTotalOrderCost() +
                '}';
    }

    public int getQuantity(String name) {
        return cartContent.stream().filter(productOrderModel -> productOrderModel.getName().equals(name))
                .findAny().get().getQuantity();
    }
}
