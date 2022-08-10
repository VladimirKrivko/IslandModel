package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Horse;

public class HorseFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Horse();
    }
}
