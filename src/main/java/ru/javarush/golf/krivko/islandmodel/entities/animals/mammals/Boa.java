package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Carnivorous;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

/* Удав конечно же не является млекопитающим животным. */
public class Boa extends Animal implements Carnivorous {

    public Boa() {
        this.clazz = Boa.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Boa.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Boa.class)[0]);
    }

}
