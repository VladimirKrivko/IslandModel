package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

public interface Herbivorous{
//    default void eat(Location location) {
//        Animal herbivorous = (Animal) this;
//        if (location.getGrass() > 0.45) {      // Вынести количество съедаемой пищи в константу конфигуратора
//            location.setGrass(location.getGrass() - 0.45);
//            herbivorous.weight = Math.min(herbivorous.getWeight() + 0.45, Configuration.CONFIGURATIONS_ANIMALS.get(herbivorous.clazz)[0]);
//        }
//    }

    void eat(Location location);
}
