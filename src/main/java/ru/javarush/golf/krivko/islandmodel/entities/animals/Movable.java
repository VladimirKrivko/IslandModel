package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

@FunctionalInterface // А на кой мне конкретно этот интерфейс??! А мб перекинуть сюда из Animal дефолтную реализацию метода move()??
public interface Movable {
    void move(Location location);
}
