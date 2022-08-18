package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Goat extends Animal implements Herbivorous {
    public Goat() {
        this.clazz = Goat.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Goat.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Goat.class)[0]);
    }
}
