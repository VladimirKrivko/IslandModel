package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Buffalo extends Animal implements Herbivorous {

    public Buffalo() {
        this.clazz = Buffalo.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Buffalo.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Buffalo.class)[0]);
    }

}
