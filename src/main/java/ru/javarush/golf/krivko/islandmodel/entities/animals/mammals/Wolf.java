package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Wolf extends Animal {

    public static final double SATIATION = Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[3];

    public Wolf() {
        this.clazz = Wolf.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
        this.isAte = false;
    }

    @Override
    public void eat(Location location) {
        location.getLock().lock();
//        boolean isAte = false;
        try {
            Map<Class<?>, Integer> victimsMap = Configuration.PROBABILITY_FOR_EATERS.get(this.clazz);
            Iterator<Class<?>> iteratorVictimClasses = victimsMap.keySet().iterator();
            while (iteratorVictimClasses.hasNext() || !isAte) {
                Class<?> victimClass = iteratorVictimClasses.next();
                Set<Animal> victims = location.getAnimals().get(victimClass);
                if (!(victims == null && victims.isEmpty())) {
                    Iterator<Animal> victimsIterator = victims.iterator();
                    Integer probability = victimsMap.get(victimClass);
                    while (victimsIterator.hasNext() || !isAte) {
                        Animal victim = victimsIterator.next();
                        if (Randomizer.getRandom(probability)) {
                            double startingWeightWolf = currentWeight;
                            System.out.println("the victim was eaten");
                            victimsIterator.remove();
                            currentWeight = Math.min(currentWeight + victim.getCurrentWeight(), Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]);
                            if (currentWeight >= startingWeightWolf + SATIATION || currentWeight == Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]) {
                                isAte = true;
                            }
                        }
                    }
                }
            }
            isAte = false;
        } finally {
            location.getLock().unlock();
        }
//        location.getLock().lock();
//        try {
//            Set<Animal> rabbits = location.getAnimals().get(Rabbit.class);
//            Iterator<Animal> rabbitsIterator = rabbits.iterator();
//            while (rabbitsIterator.hasNext() || !isAte) {
//                double startingWeightWolf = currentWeight;
//                Animal rabbit = rabbitsIterator.next();
//
//                currentWeight = Math.min(currentWeight + rabbit.getCurrentWeight(), Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
//                if (currentWeight >= startingWeightWolf + SATIATION || currentWeight == Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]) {
//                    isAte = true;
//                }
//                rabbitsIterator.remove();
//            }
//            this.isAte = false;
//
//        } finally {
//            location.getLock().unlock();
//        }
    }
}