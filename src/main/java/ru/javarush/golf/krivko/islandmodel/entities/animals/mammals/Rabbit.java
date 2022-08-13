package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

public class Rabbit extends Animal implements Herbivorous {

    public Rabbit() {
        this.clazz = Rabbit.class;
        this.weight = Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0];
    }

    @Override
    public void eat(Location location) {
        if (location.getGrass() > 1) {              // Вынести количество съедаемой пищи в константу конфигуратора
            location.setGrass(location.getGrass() - 1);
//            this.setWeight(this.getWeight() + 1);
        } else {
//            this.setWeight(this.getWeight() - 0.1);
        }
    }
}
