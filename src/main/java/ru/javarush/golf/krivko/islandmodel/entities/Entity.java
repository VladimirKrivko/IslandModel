package ru.javarush.golf.krivko.islandmodel.entities;

public abstract class Entity {

    private final String type = this.getClass().getSimpleName();    //+

    protected double weight;    //+

    public double getWeight() {
        return weight;
    }   //+

    public void setWeight(double weight) {
        this.weight = weight;
    }   //+

    public String getType() {   //+
        return type;
    }




}
