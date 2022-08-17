package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

public interface Herbivorous {
    default void eat(Location location) {
        location.getLock().lock();
        Animal herbivorous = (Animal) this;
        double satiation = Configuration.CONFIGURATIONS_ANIMALS.get(herbivorous.clazz)[3];

        try {
            if (location.getGrass() > satiation) {
                location.setGrass(Math.max(location.getGrass() - satiation, 0));
                herbivorous.currentWeight = Math.min(herbivorous.getCurrentWeight() + satiation, Configuration.CONFIGURATIONS_ANIMALS.get(herbivorous.clazz)[0]);
            } else {
                herbivorous.currentWeight = herbivorous.currentWeight + location.getGrass();
                location.setGrass(0);
            }
        } finally {
            location.getLock().unlock();
        }
    }

}
