package ru.javarush.golf.krivko.islandmodel.entities.animals.birds;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Omnivores;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Duck extends Animal implements Omnivores {

    public Duck() {
        this.clazz = Duck.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Duck.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Duck.class)[0]);
    }

}
