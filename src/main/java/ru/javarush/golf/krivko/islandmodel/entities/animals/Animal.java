package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Movable, Cloneable {
    protected Class<? extends Animal> clazz;
    protected double weight;

    protected boolean sex; // если true, то самец!
    protected boolean isAte; //?



    public double getWeight() {
        return weight;
    }

    public void weightLoss() {
        this.weight -= this.weight/5;
    }

    public void timeToDie(Location location) {
        if (this.weight < Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 2.5) {
            location.removeAnimalFromLocation(this);
        }
    }

    public void reproduction(Location location) {
        //this получает список животных
        Set<Animal> animals = location.getAnimals().get(clazz);
        //TODO: сделать разные полы животных!
        boolean femalePresent = animals.stream().anyMatch(o -> !o.sex);
        if (sex && femalePresent && weight == Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0] && animals.size() > 1) {
//            if (this.weight == Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0] && animals.size() > 1) {
                Animal clone = this.clone();
                location.addAnimalToLocation(clone);
                //потеря веса после спаривания ? пол?
                this.weightLoss();
//            }
        }
    }

    public void eat(Location location) {        //не работает!
        location.getLock().lock();

        try {
            Map<Class<?>, Integer> probabilityForEaters = Configuration.PROBABILITY_FOR_EATERS.get(this.clazz);
            Iterator<Map.Entry<Class<?>, Integer>> probabilityForEatersIterator = probabilityForEaters.entrySet().iterator();
            while (!this.isAte || probabilityForEatersIterator.hasNext()) {
                Map.Entry<Class<?>, Integer> probabilityForEater = probabilityForEatersIterator.next();
                Class<?> victimClass = probabilityForEater.getKey();
                Integer probabilityOfBeingEaten = probabilityForEater.getValue();
                Set<Animal> victims = location.getAnimals().get(victimClass);
                if (ThreadLocalRandom.current().nextInt(0, 100) <= probabilityOfBeingEaten && victims != null && !victims.isEmpty()) {
                    Iterator<Animal> victimsIterator = victims.iterator();
                    if (victimsIterator.hasNext()) {
                        Animal victim = victimsIterator.next();
                        this.weight = Math.min(this.weight + (victim.weight / 1.5), Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]);
                        victimsIterator.remove();
//                    location.removeAnimalFromLocation(victim);
                        // после того как животное поест this.didTheAnimalEat = true;
                        this.isAte = true;
                    }
                }
            }
        } finally {
            this.isAte = false;
            location.getLock().unlock();
        }
    }

    @Override
    public void move(Location location){
        location.getLock().lock();
        try {
            Location newLocation = choiceOfAvailableLocation(location);
            if (newLocation.isThereEnoughSpace(this.clazz)) {
                location.removeAnimalFromLocation(this);
                newLocation.addAnimalToLocation(this);
            }
        } finally {
            location.getLock().unlock();
        }
    }

    private int getMaxNumberOfStepsAnimal() {
        return (int) Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[2];
    }

    private Location choiceOfAvailableLocation(Location location){
        int steps = getMaxNumberOfStepsAnimal();
        for(int i = steps; i >= 0; i--) {
            location = location.getNeighboringLocations().get(ThreadLocalRandom.current().nextInt(0, location.getNeighboringLocations().size()));
        }
        return location;
    }

    @Override
    public Animal clone() {
        try {
            Animal clone = (Animal) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            clone.weight = ThreadLocalRandom.current().nextDouble(Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 2.5, Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]);
            clone.sex = ThreadLocalRandom.current().nextBoolean();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
