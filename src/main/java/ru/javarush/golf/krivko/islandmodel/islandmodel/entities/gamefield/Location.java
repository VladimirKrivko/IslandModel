package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.animals.mammals.Bear;

import java.util.HashSet;
import java.util.Set;

public class Location {

    private final Coordinates coordinates;

    private Set<Animal> animals = new HashSet<>();

    public Location(int y, int x) {
        this.coordinates = new Coordinates(y, x);
        init();
    }

    private void init() {
        animals.add(new Bear(coordinates));
    }

    @Override
    public String toString() {
        return "[" + "]";
    }
}
