package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;

public class Wolf extends Animal {

    public Wolf() {
        this.clazz = Wolf.class;
        this.weight = Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0];
    }
}