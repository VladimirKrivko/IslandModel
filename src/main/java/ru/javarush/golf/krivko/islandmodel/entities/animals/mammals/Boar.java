package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Omnivores;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Boar extends Animal implements Omnivores {
    public Boar() {
        this.clazz = Wolf.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
    }

}
