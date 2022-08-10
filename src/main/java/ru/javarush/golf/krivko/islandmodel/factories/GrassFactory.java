package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.plants.Grass;

public class GrassFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Grass();
    }
}
