package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Horse extends Animal implements Herbivorous {

    public Horse() {
        this.clazz = Horse.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Horse.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Horse.class)[0]);
    }
}
