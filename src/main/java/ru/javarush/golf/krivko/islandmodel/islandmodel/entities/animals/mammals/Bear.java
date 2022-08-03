package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.gamefield.Coordinates;

public class Bear extends Animal {
    public Bear(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
