package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public interface Carnivorous {
    default void eat(Location location) {
        Animal carnivorous = (Animal) this;
        Map<Class<?>, Integer> probabilityVictimsMap = Configuration.PROBABILITY_FOR_EATERS.get(carnivorous.clazz);
        Iterator<Map.Entry<Class<?>, Integer>> probabilityVictimsMapIterator = probabilityVictimsMap.entrySet().iterator();
        while(carnivorous.isAte || probabilityVictimsMapIterator.hasNext()) {
            Map.Entry<Class<?>, Integer> probabilityForEater = probabilityVictimsMapIterator.next();
            Class<?> victimClass = probabilityForEater.getKey();
            Integer probabilityToEat = probabilityForEater.getValue();
            Set<Animal> victims = location.getAnimals().get(victimClass);
            if(ThreadLocalRandom.current().nextDouble(0, 100) <= probabilityToEat && victims!=null && !victims.isEmpty()) {
                Iterator<Animal> victimsIterator = victims.iterator();
                if (victimsIterator.hasNext()) {
                    Animal victim = victims.stream().findFirst().get();
                    carnivorous.weight = Math.min(carnivorous.weight + (victim.weight / 1.5), Configuration.CONFIGURATIONS_ANIMALS.get(carnivorous.clazz)[0]);
                    victimsIterator.remove();
                    // после того как животное поест this.didTheAnimalEat = true;
                    carnivorous.isAte = true;
                }
            }
        }
        carnivorous.isAte = false;
    }
}
