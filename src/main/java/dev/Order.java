package dev;

public class Order {
    public Order(int product_id, int quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    Integer product_id;
    Integer quantity;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:[").append(product_id).append("], Quantity:[").append(quantity.toString()).append("]");
        return sb.toString();
    }
}
