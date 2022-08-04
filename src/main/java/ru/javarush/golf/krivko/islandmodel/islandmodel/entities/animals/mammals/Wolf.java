package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.gamefield.Coordinates;

public class Wolf extends Animal {
    public Wolf(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    protected void move() {

    }
}
