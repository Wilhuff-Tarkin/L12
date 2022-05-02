package configuration.model;

import lombok.Getter;
import lombok.Setter;

public class OrderProductModel {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private float price;

    @Getter
    @Setter
    private int quantity;


    public OrderProductModel(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
