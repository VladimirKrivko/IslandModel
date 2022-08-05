package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.lang.reflect.InvocationTargetException;

public abstract class Animal<T> {

    protected Class<T> clazz;

//    private int weight;
    private static int maxCountOfAnimalInTheLocation; //Нужна ли? усть же статическая коллекция MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.
//    private int maxNumberOfStepsAnimal;
//    private int amountOfFoodToSatiate;

//    public Animal(Class<T> clazz) {
//        this.clazz = clazz;
//    }

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

    }

}




//    protected void choiceOfAvailableLocation(){
//        int yMin = Math.max(this.coordinates.getY() - MAX_NUMBER_OF_STEPS_ANIMAL, 0);
//        int yMax = Math.min(this.coordinates.getY() + MAX_NUMBER_OF_STEPS_ANIMAL, Configuration.SIZE_Y_GAME_FIELD);
//        int xMin = Math.max(this.coordinates.getX() - MAX_NUMBER_OF_STEPS_ANIMAL, 0);
//        int xMax = Math.min(this.coordinates.getX() + MAX_NUMBER_OF_STEPS_ANIMAL, Configuration.SIZE_X_GAME_FIELD);
//
//        int coordinateY = random.nextInt(yMin, yMax);
//        int coordinateX = random.nextInt(xMin, xMax);
//
//        this.coordinates.setY(coordinateY);
//        this.coordinates.setX(coordinateX);
//    }