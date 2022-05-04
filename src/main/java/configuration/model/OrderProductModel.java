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
    private float regularPrice;

    @Getter
    @Setter
    private int quantity;


    public OrderProductModel(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderProductModel(String name, float price, float regularPrice, int quantity) {
        this.name = name;
        this.price = price;
        this.regularPrice = regularPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProductModel that = (OrderProductModel) o;

        if (Float.compare(that.price, price) != 0) return false;
        if (quantity != that.quantity) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (regularPrice != +0.0f ? Float.floatToIntBits(regularPrice) : 0);
        result = 31 * result + quantity;
        return result;
    }
}
