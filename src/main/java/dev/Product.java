package dev;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table( name = "products" )
public class Product {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    private String name;

    /**
     * Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    Product(){
    }
    Product(Integer id, String name){
        this.id = id;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:[").append(id).append("], Name:[").append(name.toString()).append("]");
        return sb.toString();
    }
}
