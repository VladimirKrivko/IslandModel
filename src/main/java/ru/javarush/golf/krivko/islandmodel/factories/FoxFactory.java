package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Fox;

public class FoxFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Fox();
    }
}
