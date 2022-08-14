package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Movable, Cloneable {
    protected Class<? extends Animal> clazz;

    public double getWeight() {
        return weight;
    }

    protected double weight;

    public void weightLoss() {
        this.weight -= this.weight/10;
    }

    public void timeToDie(Location location) {
        if (this.weight < Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 2.5) {
            location.removeAnimalFromLocation(this);
        }
    }

    public void reproduction(Location location) {
        //this получает список животных
        Set<Animal> animals = location.getAnimals().get(clazz);
        //если есть любое другое животное своего типа, то
        if (this.weight == Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0] && animals.size() > 1) {
            Animal clone = this.clone();
            location.addAnimalToLocation(clone);
            //потеря веса после спаривания ?
        }
    }

    @Override
    public void move(Location location){
        Location newLocation = choiceOfAvailableLocation(location);
        if (newLocation.isThereEnoughSpace(this.clazz)) {
            location.removeAnimalFromLocation(this);
            newLocation.addAnimalToLocation(this);
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
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
