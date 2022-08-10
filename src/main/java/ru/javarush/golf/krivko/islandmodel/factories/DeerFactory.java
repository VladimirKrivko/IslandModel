package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Deer;

public class DeerFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Deer();
    }
}
