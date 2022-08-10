package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.birds.Duck;

public class DuckFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Duck();
    }
}
