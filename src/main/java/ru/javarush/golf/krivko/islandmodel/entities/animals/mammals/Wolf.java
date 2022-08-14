package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Carnivorous;

import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal implements Carnivorous {

    public Wolf() {
        this.clazz = Wolf.class;
        this.sex = ThreadLocalRandom.current().nextBoolean();
        this.weight = Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0];//ThreadLocalRandom.current().nextDouble(Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
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