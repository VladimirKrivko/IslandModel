package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public interface Carnivorous {
    default void eat(Location location) {
        location.getLock().lock();
        Animal carnivorous = (Animal) this;
        boolean isAte = false;
        double startingWeightCarnivorous = carnivorous.currentWeight;
        double maxWeightCarnivorous = Configuration.CONFIGURATIONS_ANIMALS.get(carnivorous.clazz)[0];
        double satiation = Configuration.CONFIGURATIONS_ANIMALS.get(carnivorous.clazz)[3];
        double differentWeight = maxWeightCarnivorous - startingWeightCarnivorous;
        try {
            Map<Class<?>, Integer> victimsMap = Configuration.PROBABILITY_FOR_EATERS.get(carnivorous.clazz);
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
                        carnivorous.currentWeight = Math.min(carnivorous.currentWeight + victim.getCurrentWeight(), maxWeightCarnivorous);
                        if (carnivorous.currentWeight >= startingWeightCarnivorous + satiation || carnivorous.currentWeight == maxWeightCarnivorous) {
                            isAte = true;
                        }
                        victimsIterator.remove();
                    }
                }
            }
        } finally {
            location.getLock().unlock();
        }
    }
}
