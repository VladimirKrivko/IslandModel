package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Buffalo;

public class BuffaloFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Buffalo();
    }
}
