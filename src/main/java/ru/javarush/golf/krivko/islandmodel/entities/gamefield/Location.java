package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Wolf;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Location {
    private final int xPosition;
    private final int yPosition;

    private int grass;

    private final Map<Class<?>, Set<Animal<?>>> animals = new HashMap<>();

    public Location(int y, int x) {
        this.yPosition = y;
        this.xPosition = x;
        initializeAnimalSet();
        generationLife();
    }

    private void initializeAnimalSet() {
        for (Class<?> classAnimal : Configuration.CLASS_ANIMALS) {
            animals.put(classAnimal, new HashSet<>());
        }
    }

    public int getPositionX() {
        return xPosition;
    }

    public int getPositionY() {
        return yPosition;
    }

    private void generationLife() {
        generationPlants();
        generationAnimals();
    }

    private void generationPlants() {
        if (isCreateAnimalType()) {
            this.grass = ThreadLocalRandom.current().nextInt(0, Configuration.GRASS_WEIGHT);
        }
    }

    private void generationAnimals() {  //Сделать уникальность создаваемых животных?
        for (Class<?> classAnimal : Configuration.CLASS_ANIMALS) {
            if (isCreateAnimalType()) {
                int numberOfAnimalType = ThreadLocalRandom.current().nextInt(0, Configuration.MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.get(classAnimal));
                for (int i = 0; i < numberOfAnimalType; i++) {

                    Object o = tryCreateAnimal(classAnimal);
                    if (o instanceof Wolf) {
                        animals.get(classAnimal).add((Wolf) o);
                    }
                }
            }
        }
    }

    public void removeAnimalFromLocation(Animal<?> animal) {
        //реализация удаления животного
    }

    public void addAnimalToLocation(Animal<?> animal) {
        //реализация добавления животного
    }

    private boolean isCreateAnimalType() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    private <T> T tryCreateAnimal(Class<T> clazz) {
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

    @Override
    public String toString() {
        return "[" + "\uD83D\uDC3A:" + animals.get(Wolf.class).size() + "\uD83C\uDF3F:" + grass + "]";
    }
}
