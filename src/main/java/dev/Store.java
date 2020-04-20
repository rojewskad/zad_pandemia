package dev;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table( name = "stores" )
public class Store {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")

    private Integer id;
    @Embedded
    private Coordinates coordinates;

    @Transient
    private double distanceFromCustomer;

    /**
     * Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    Store(){
    }
    Store(Integer id, Coordinates coordinates){
        this.id = id;
        this.coordinates = coordinates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getDistanceFromCustomer() {
        return distanceFromCustomer;
    }

    public void setDistanceFromCustomer(double distanceFromCustomer) {
        this.distanceFromCustomer = distanceFromCustomer;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:[").append(id).append("], Coordinates:[").append(coordinates.toString()).append("]");
        return sb.toString();
    }
}
