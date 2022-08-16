package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Sheep extends Animal implements Herbivorous {
    public Sheep() {
        this.clazz = Sheep.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Sheep.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Sheep.class)[0]);
    }

}
