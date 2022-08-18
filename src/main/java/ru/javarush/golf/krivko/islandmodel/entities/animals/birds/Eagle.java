package ru.javarush.golf.krivko.islandmodel.entities.animals.birds;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Carnivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Eagle extends Animal implements Carnivorous {

    public Eagle() {
        this.clazz = Eagle.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Eagle.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Eagle.class)[0]);
    }

}
