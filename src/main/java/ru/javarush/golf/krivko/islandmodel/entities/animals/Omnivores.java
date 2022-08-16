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
        double satiation = Configuration.CONFIGURATIONS_ANIMALS.get(omnivores.clazz)[3];
        try {
            Map<Class<?>, Integer> victimsMap = Configuration.PROBABILITY_FOR_EATERS.get(omnivores.clazz);
            Iterator<Class<?>> iteratorVictimClasses = victimsMap.keySet().iterator();
            double startingWeightCarnivorous = omnivores.currentWeight;
            while (iteratorVictimClasses.hasNext() || !isAte) {
                Class<?> victimClass = iteratorVictimClasses.next();
                Set<Animal> victims = location.getAnimals().get(victimClass);
                Integer probability = victimsMap.get(victimClass);
                if (Randomizer.getRandom(probability)) {
                    Animal victim = victims.stream().iterator().next();

                    omnivores.currentWeight = Math.min(omnivores.currentWeight + victim.getCurrentWeight(), Configuration.CONFIGURATIONS_ANIMALS.get(omnivores.clazz)[0]);
                    if (omnivores.currentWeight >= startingWeightCarnivorous + satiation || omnivores.currentWeight == Configuration.CONFIGURATIONS_ANIMALS.get(omnivores.clazz)[0]) {
                        isAte = true;
                    }
                    victims.remove(victim);
                }
            }
            double differentWeight = omnivores.currentWeight - startingWeightCarnivorous;
            boolean saturationDidNotCome = differentWeight < satiation;
            if (!isAte || saturationDidNotCome && location.getGrass() > satiation - differentWeight) {
                location.setGrass(location.getGrass() - satiation);
                omnivores.currentWeight = Math.min(omnivores.getCurrentWeight() + satiation, Configuration.CONFIGURATIONS_ANIMALS.get(omnivores.clazz)[0]);
            }
        } finally {
            location.getLock().unlock();
        }
    }
}
