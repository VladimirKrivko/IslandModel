package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;

public class Rabbit extends Animal {

    public Rabbit() {
        this.clazz = Rabbit.class;
        this.weight = Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0];
    }
}
