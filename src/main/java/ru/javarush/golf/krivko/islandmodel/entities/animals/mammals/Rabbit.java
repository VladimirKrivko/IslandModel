package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Rabbit extends Animal {
    public Rabbit() {
        this.clazz = Rabbit.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0]);
    }

}
