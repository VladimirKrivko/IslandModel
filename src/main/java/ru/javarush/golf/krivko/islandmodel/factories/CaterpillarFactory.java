package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.insects.Caterpillar;

public class CaterpillarFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Caterpillar();
    }
}
