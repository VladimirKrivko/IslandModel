package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Rabbit extends Animal {

    public static final double SATIATION = Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[3];

    public Rabbit() {
        this.clazz = Rabbit.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0]);
        this.isAte = false;
    }

    @Override
    public void eat(Location location) {
        location.getLock().lock();
        try {
            if (location.getGrass() > SATIATION) {
                location.setGrass(location.getGrass() - SATIATION);
                this.currentWeight = Math.min(this.getCurrentWeight() + SATIATION, Configuration.CONFIGURATIONS_ANIMALS.get(Rabbit.class)[0]);
            }
        } finally {
            location.getLock().unlock();
        }
    }
}
