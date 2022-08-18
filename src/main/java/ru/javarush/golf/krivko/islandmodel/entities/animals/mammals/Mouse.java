package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Omnivores;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Mouse extends Animal implements Omnivores {

    public Mouse() {
        this.clazz = Mouse.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Mouse.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Mouse.class)[0]);
    }

}
