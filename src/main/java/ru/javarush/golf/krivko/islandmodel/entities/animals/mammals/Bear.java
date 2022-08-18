package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Carnivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Bear extends Animal implements Carnivorous {

    public Bear() {
        this.clazz = Bear.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Bear.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Bear.class)[0]);
    }

}
