package ru.javarush.golf.krivko.islandmodel.entities.plants;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;

public class Grass extends Plant{
    public int getPlantWeight() {
        return Configuration.GRASS_WEIGHT;
    } // нужно ли мне это здесь?
}
