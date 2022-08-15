package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Animal implements Herbivorous {

    public Rabbit() {
        this.clazz = Rabbit.class;
        this.sex = ThreadLocalRandom.current().nextBoolean();
        this.weight = ThreadLocalRandom.current().nextDouble(Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0]);
        this.isAte = false;
    }

    @Override
    public void eat(Location location) {
        location.getLock().lock();
        try {
            if (location.getGrass() > 0.45) {      // Вынести количество съедаемой пищи в константу конфигуратора
                location.setGrass(location.getGrass() - 0.45);
                this.weight = Math.min(this.getWeight() + 0.45, Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0]);
            }
        } finally {
            location.getLock().unlock();
        }
    }
}
