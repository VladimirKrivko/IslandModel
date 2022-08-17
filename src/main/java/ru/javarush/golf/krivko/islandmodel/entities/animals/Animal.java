package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Cloneable {
    protected Class<? extends Animal> clazz;
    protected double currentWeight;

    protected boolean sex; // если true, то самец!



    public double getCurrentWeight() {
        return currentWeight;
    }

    public void weightLoss(Location location) {
        location.getLock().lock();
        try {
            currentWeight = currentWeight - currentWeight / 10;
        } finally {
            location.getLock().unlock();
        }
    }

    public void timeToDie(Location location) {
        location.getLock().lock();
        try {
            if (currentWeight < Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 3) {
                location.removeAnimalFromLocation(this);
            }
        } finally {
            location.getLock().unlock();
        }
    }

    public void reproduction(Location location) {
        location.getLock().lock();
        try {
            Set<Animal> animals = location.getAnimals().get(clazz);
            boolean femalePresent = animals.stream().anyMatch(o -> !o.sex);
            if (sex && femalePresent && currentWeight > Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] && animals.size() > 1) {
                Animal clone = this.clone();
                location.addAnimalToLocation(clone);
                weightLoss(location);
            }
        } finally {
            location.getLock().unlock();
        }
    }

    public void move(Location location){
        location.getLock().lock();
        Location newLocation = choiceOfAvailableLocation(location);
        try {
            if (newLocation.isThereEnoughSpace(this.clazz)) {
                newLocation.addAnimalToLocation(this);
                location.removeAnimalFromLocation(this);
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
            clone.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 1.5, Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]);
            clone.sex = Randomizer.getRandom();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
