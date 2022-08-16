package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Carnivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Wolf extends Animal implements Carnivorous {
    public Wolf() {
        this.clazz = Wolf.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
    }

}