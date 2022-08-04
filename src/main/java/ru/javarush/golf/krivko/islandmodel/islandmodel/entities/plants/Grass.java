package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.plants;

import ru.javarush.golf.krivko.islandmodel.islandmodel.constants.Configuration;

public class Grass extends Plant{
    public int getPlantWeight() {
        return Configuration.GRASS_WEIGHT;
    }
}
