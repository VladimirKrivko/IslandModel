package ru.javarush.golf.krivko.islandmodel.entities.animals.insects;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Caterpillar extends Animal implements Herbivorous {
    public Caterpillar() {
        this.clazz = Caterpillar.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Caterpillar.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Caterpillar.class)[0]);
    }

}
