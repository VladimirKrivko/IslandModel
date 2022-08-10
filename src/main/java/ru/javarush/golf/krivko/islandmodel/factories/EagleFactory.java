package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.birds.Eagle;

public class EagleFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Eagle();
    }
}
