package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

public interface Herbivorous{
    default void eat(Location location) {
        location.getLock().lock();
        Animal herbivorous = (Animal) this;
        double satiation = Configuration.CONFIGURATIONS_ANIMALS.get(herbivorous.clazz)[3];
        try {
            if (location.getGrass() > satiation) {
                location.setGrass(location.getGrass() - satiation);
                herbivorous.currentWeight = Math.min(herbivorous.getCurrentWeight() + satiation, Configuration.CONFIGURATIONS_ANIMALS.get(herbivorous.clazz)[0]);
//                System.out.println(herbivorous.clazz.getSimpleName() + " ate the grass");
            }
        } finally {
            location.getLock().unlock();
        }
    }

}
