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
        double satiation = Configuration.CONFIGURATIONS_ANIMALS.get(carnivorous.clazz)[3];
        try {
            Map<Class<?>, Integer> victimsMap = Configuration.PROBABILITY_FOR_EATERS.get(carnivorous.clazz);
            Iterator<Class<?>> iteratorVictimClasses = victimsMap.keySet().iterator();
            while (iteratorVictimClasses.hasNext() || !isAte) {
                Class<?> victimClass = iteratorVictimClasses.next();
                Set<Animal> victims = location.getAnimals().get(victimClass);
                Integer probability = victimsMap.get(victimClass);
                if (Randomizer.getRandom(probability)) {
                    Animal victim = victims.stream().iterator().next();
                    double startingWeightCarnivorous = carnivorous.currentWeight;
//                    System.out.println(carnivorous.clazz.getSimpleName() + " ate the " + victim.getClass().getSimpleName());
                    carnivorous.currentWeight = Math.min(carnivorous.currentWeight + victim.getCurrentWeight(), Configuration.CONFIGURATIONS_ANIMALS.get(carnivorous.clazz)[0]);
                    if (carnivorous.currentWeight >= startingWeightCarnivorous + satiation || carnivorous.currentWeight == Configuration.CONFIGURATIONS_ANIMALS.get(carnivorous.clazz)[0]) {
                        isAte = true;
                    }
                    victims.remove(victim);
                }
            }
        } finally {
            location.getLock().unlock();
        }
    }
}
