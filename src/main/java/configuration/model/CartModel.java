package configuration.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartModel {

    private static final Logger log = LoggerFactory.getLogger("cart model");
    private static final DecimalFormat df = new DecimalFormat("0.00");


    @Getter
    private List<OrderProductModel> cartContent = new ArrayList<>();

    private float totalOrderCost;

    private int numberOfItems;


    public void addAnother(OrderProductModel product) {
        if (checkIfThisIsAlreadyOnTheList(product.getName())) {
            increaseQuantity(product);
        } else {
            cartContent.add(product);
//            product.setQuantity(quantity);
            numberOfItems++;
        }
    }

    public void removeProduct(String productName) {

        if (checkIfThisIsAlreadyOnTheList(productName)) {
            for (int i = 0; i < cartContent.size(); i++) {
                if (cartContent.get(i).getName().equals(productName)) {
                    cartContent.remove(i);
                    log.info(productName + " removed from model cart.");
                }
            }
        } else {
            log.info("No such product in model basket: " + productName);
        }
    }

    public float getTotalOrderCost() {
        totalOrderCost = 0;
        for (OrderProductModel productOrderModel : cartContent) {
            totalOrderCost += (productOrderModel.getPrice() * productOrderModel.getQuantity());
        }
        return Float.parseFloat(df.format(totalOrderCost).replaceAll(",", "."));
    }

    public int getNumberOfItems() {

        numberOfItems = 0;

        for (OrderProductModel productOrderModel : cartContent) {
            numberOfItems += productOrderModel.getQuantity();
        }

        return numberOfItems;
    }


    private void increaseQuantity(OrderProductModel product) {

        int additionalQuantity = product.getQuantity();

        for (int i = 0; i < cartContent.size(); i++) {
            if (cartContent.get(i).getName().contains(product.getName())) {
                OrderProductModel productAlreadyOnTheList = cartContent.get(i);
                productAlreadyOnTheList.setQuantity(productAlreadyOnTheList.getQuantity() + additionalQuantity);
            }
        }
    }

    private boolean checkIfThisIsAlreadyOnTheList(String name) {
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
        return cartContent.stream().filter(OrderProductModel -> OrderProductModel.getName().equals(name))
                .findAny().get().getQuantity();
    }
}
