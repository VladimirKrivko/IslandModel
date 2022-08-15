package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal {

    public Wolf() {
        this.clazz = Wolf.class;
        this.sex = ThreadLocalRandom.current().nextBoolean();
        this.weight = ThreadLocalRandom.current().nextDouble(Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
        this.isAte = false;
    }

    @Override
    public void eat(Location location) {
        location.getLock().lock();
        try {
            Set<Animal> rabbits = location.getAnimals().get(Rabbit.class);
            if (rabbits != null && !rabbits.isEmpty()) {
                Animal rabbit = rabbits.stream().findFirst().get();
                this.weight = Math.min(this.weight + (rabbit.getWeight() / 1.5), Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]);
                rabbits.remove(rabbit);
//            this.isAte = false;
            }
        } finally {
            location.getLock().unlock();
        }
    }
    //    @Override
//    public void eat(Location location) {
//        for (Class<?> victimClass : Configuration.PROBABILITY_FOR_EATERS.keySet()) {
//            Set<Animal<?>> victims = location.getAnimals().entrySet().stream()
//                    .findFirst()
//                    .get()
//                    .getValue();
//            if (victims) {
//                Animal<?> victim = victims.stream()
//                        .findAny().get();
//                if (victim != null) {
//                    location.removeAnimalFromLocation(victim);
//                }
//            }
//        }
//    }
}