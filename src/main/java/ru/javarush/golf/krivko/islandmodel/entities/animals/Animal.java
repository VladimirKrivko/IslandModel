package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.GameField;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal<T> implements Movable {

    protected Class<T> clazz;

//    private int weight;
//    private int amountOfFoodToSatiate;


    public T tryCreateAnimal() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }





    public void move(Location location){
        Location newLocation = choiceOfAvailableLocation(location);
//        location.найти животное и удалить из списка локации;
//        newLocation.поместить животное в список совего типа новой локации;
    }

    public int getMaxNumberOfStepsAnimal() {    //Убрать метод, оставить просто Configuration?
        return Configuration.MAX_NUMBER_OF_ANIMAL_STEPS.get(clazz);
    }

    protected Location choiceOfAvailableLocation(Location location){
        int yMin = Math.max(location.getPositionY() - getMaxNumberOfStepsAnimal(), 0);
        int yMax = Math.min(location.getPositionY() + getMaxNumberOfStepsAnimal(), Configuration.SIZE_Y_GAME_FIELD);
        int xMin = Math.max(location.getPositionX() - getMaxNumberOfStepsAnimal(), 0);
        int xMax = Math.min(location.getPositionX() + getMaxNumberOfStepsAnimal(), Configuration.SIZE_X_GAME_FIELD);

        int coordinateY = ThreadLocalRandom.current().nextInt(yMin, yMax);
        int coordinateX = ThreadLocalRandom.current().nextInt(xMin, xMax);

        return GameField.getLocation(coordinateY, coordinateX);
    }

}
