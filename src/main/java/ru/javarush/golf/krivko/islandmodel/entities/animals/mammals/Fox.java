package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Carnivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Fox extends Animal implements Carnivorous {

    public Fox() {
        this.clazz = Fox.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Fox.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Fox.class)[0]);
    }

}
