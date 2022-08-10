package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Sheep;

public class SheepFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Sheep();
    }
}
