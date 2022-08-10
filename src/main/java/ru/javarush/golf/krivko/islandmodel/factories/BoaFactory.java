package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Boa;

public class BoaFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Boa();
    }
}
