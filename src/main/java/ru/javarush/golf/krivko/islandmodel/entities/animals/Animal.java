package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.EntityType;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.GameField;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity implements Movable {
//    protected Class<T> clazz;

//    protected double weight;

//    public double getWeight() {
//        return weight;
//    }
//
//    public void setWeight(double weight) {
//        this.weight = weight;
//    }


    public void move(Location location){
        Location newLocation = choiceOfAvailableLocation(location);
        location.removeAnimalFromLocation(this);
        newLocation.addAnimalToLocation(this);
//        this.weight -= 0.1;       // животное теряет массу при ходьбе. -1 вынести в Configuration. для каждого животного своя потеря веса!
//        if (this.weight < Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 2.5) {
//            newLocation.removeAnimalFromLocation(this);
//        }
    }

    private int getMaxNumberOfStepsAnimal() {    //Убрать метод, оставить просто Configuration?
        return (int) Configuration.CONFIGURATIONS_ANIMALS.get(EntityType.valueOf(this.getType().toUpperCase()))[2];   //+
    }

    private Location choiceOfAvailableLocation(Location location){
        int yMin = Math.max(location.getPositionY() - getMaxNumberOfStepsAnimal(), 0);
        int yMax = Math.min(location.getPositionY() + getMaxNumberOfStepsAnimal(), Configuration.SIZE_Y_GAME_FIELD);

        int xMin = Math.max(location.getPositionX() - getMaxNumberOfStepsAnimal(), 0);
        int xMax = Math.min(location.getPositionX() + getMaxNumberOfStepsAnimal(), Configuration.SIZE_X_GAME_FIELD);

        int coordinateY = ThreadLocalRandom.current().nextInt(yMin, yMax);
        int coordinateX = ThreadLocalRandom.current().nextInt(xMin, xMax);

        return GameField.getLocation(coordinateY, coordinateX);
    }

}
