package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

@FunctionalInterface
public interface Movable {
    void move(Location location);
}
