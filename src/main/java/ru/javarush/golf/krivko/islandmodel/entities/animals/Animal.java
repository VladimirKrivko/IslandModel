package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Movable {
    protected Class<? extends Animal> clazz;

    protected double weight;

//    public double getWeight() {
//        return weight;
//    }
//
//    public void setWeight(double weight) {
//        this.weight = weight;
//    }

    public void weightLoss() {
        this.weight -= this.weight/10;
    }

    public void timeToDie(Location location) {
        if (this.weight < Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 2.5) {
            location.removeAnimalFromLocation(this);
        }
    }

    @Override
    public void move(Location location){
        Location newLocation = choiceOfAvailableLocation(location);
        // добавить проверку есть ли место в новой локации для животного this
        if (newLocation.isThereEnoughSpace(this.clazz)) {
            location.removeAnimalFromLocation(this);
            newLocation.addAnimalToLocation(this);
        }

        this.weight -= 0.1;       // животное теряет массу при ходьбе. -1 вынести в Configuration. для каждого животного своя потеря веса!
    }

    private int getMaxNumberOfStepsAnimal() {    //Убрать метод, оставить просто Configuration?
        return (int) Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[2];
    }

    private Location choiceOfAvailableLocation(Location location){
        int steps = getMaxNumberOfStepsAnimal();
//        Location destinationLocation = location;
        for(int i = steps; i >= 0; i--) {
            location = location.getNeighboringLocations().get(ThreadLocalRandom.current().nextInt(0, location.getNeighboringLocations().size()));
        }
        return location;
    }

}
