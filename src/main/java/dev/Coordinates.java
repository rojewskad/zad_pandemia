package dev;

import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {
    double x;
    double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Coordinates(){

    }


    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("x:[").append(x).append("], y:[").append(y).append("]");
        return sb.toString();
    }
}
