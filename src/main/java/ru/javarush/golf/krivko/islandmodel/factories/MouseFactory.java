package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Mouse;

public class MouseFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new Mouse();
    }
}
