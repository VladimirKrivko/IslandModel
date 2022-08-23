package ru.javarush.golf.krivko.islandmodel.newservices;

import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

public class GrassTask implements Runnable {
    private final Location location;
    public GrassTask(Location location) {
        this.location = location;
    }

    public void growUp() {
        location.grassGrowth();
    }

    @Override
    public void run() {
        location.getLock().lock();
        try {
            growUp();
        } finally {
            location.getLock().unlock();
        }
    }
}
