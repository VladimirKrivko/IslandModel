package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

public class GrassTask {

    private final Location location;

    public GrassTask(Location location) {
        this.location = location;
    }

    public void growUp() {
        location.grassGrowth();
    }
}
