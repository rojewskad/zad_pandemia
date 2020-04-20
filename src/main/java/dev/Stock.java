package dev;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table( name = "stocks" )
public class Stock {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    private Integer store_id;
    private Integer product_id;
    private Integer quantity;

    /**
     * Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    Stock(){
    }
    Stock(Integer store_id, Integer product_id, Integer quantity){
        this.store_id = store_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("STORE_ID:[").append(store_id).append("], PRODUCT_ID").append(product_id).append("], Quantity:[").append(quantity.toString()).append("]");
        return sb.toString();
    }
}
