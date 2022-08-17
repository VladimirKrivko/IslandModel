package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public interface Omnivores {
    default void eat(Location location) {
        location.getLock().lock();
        Animal omnivores = (Animal) this;
        boolean isAte = false;
        double startingWeightOmnivores = omnivores.currentWeight;
        double maxWeightOmnivores = Configuration.CONFIGURATIONS_ANIMALS.get(omnivores.clazz)[0];
        double satiation = Configuration.CONFIGURATIONS_ANIMALS.get(omnivores.clazz)[3];
        double differentWeight = maxWeightOmnivores - startingWeightOmnivores;
        try {
            Map<Class<?>, Integer> victimsMap = Configuration.PROBABILITY_FOR_EATERS.get(omnivores.clazz);
            if (differentWeight > 0) {
                Iterator<Map.Entry<Class<?>, Integer>> victimsMapIterator = victimsMap.entrySet().iterator();
                while (!isAte && victimsMapIterator.hasNext()) {
                    Map.Entry<Class<?>, Integer> probabilityPair = victimsMapIterator.next();
                    Class<?> classVictim = probabilityPair.getKey();
                    Integer probability = probabilityPair.getValue();

                    Set<Animal> victims = location.getAnimals().get(classVictim);
                    Iterator<Animal> victimsIterator = victims.iterator();
                    if (Randomizer.getRandom(probability) && !victims.isEmpty() && victimsIterator.hasNext()) {
                        Animal victim = victimsIterator.next();
                        omnivores.currentWeight = Math.min(omnivores.currentWeight + victim.getCurrentWeight(), maxWeightOmnivores);
                        if (omnivores.currentWeight >= startingWeightOmnivores + satiation || omnivores.currentWeight == maxWeightOmnivores) {
                            isAte = true;
                        }
                        victimsIterator.remove();
                    }
                }
            }
            satiation = omnivores.currentWeight - startingWeightOmnivores;
            if (location.getGrass() > satiation) {
                location.setGrass(Math.max(location.getGrass() - satiation, 0));
                omnivores.currentWeight = Math.min(omnivores.getCurrentWeight() + satiation, Configuration.CONFIGURATIONS_ANIMALS.get(omnivores.clazz)[0]);
            } else {
                omnivores.currentWeight = omnivores.currentWeight + location.getGrass();
                location.setGrass(0);
            }
        } finally {
            location.getLock().unlock();
        }
    }
}
