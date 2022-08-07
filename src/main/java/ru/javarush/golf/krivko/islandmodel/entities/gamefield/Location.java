package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Rabbit;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Wolf;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Location {
    private final int xPosition;
    private final int yPosition;
    private int grass;

    private final Map<Class, Set<Animal<?>>> animals = new ConcurrentHashMap<>();

    public Location(int y, int x) {
        this.yPosition = y;
        this.xPosition = x;
        initializeAnimalSet();
        generationLife();
    }

    public int getPositionX() {
        return xPosition;
    }

    public int getPositionY() {
        return yPosition;
    }

    public void doAction() {
        for (Map.Entry<Class, Set<Animal<?>>> pair : animals.entrySet()) {
            Set<Animal<?>> value = pair.getValue();
            for (Animal<?> animal : value) {
//                if (animal.weight < (Configuration.CONFIGURATIONS_ANIMALS.get(animal.getClass()) / 10)) {
                    animal.move(this);
//                }
            }
        }

    }

    public void removeAnimalFromLocation(Animal<?> animal) {
        //реализация удаления животного
        animals.get(animal.getClass()).remove(animal);
    }

    public void addAnimalToLocation(Animal<?> animal) { // добавил проверку на нуль
        //реализация добавления животного
        if (animals.get(animal.getClass()) != null) {
            animals.get(animal.getClass()).add(animal);
        } else {
            animals.put(animal.getClass(), new HashSet<>());
            animals.get(animal.getClass()).add(animal);
        }
    }

    @Override
    public String toString() {
        return "[" + "\uD83D\uDC3A:" + animals.get(Wolf.class).size()
                + "\uD83D\uDC07:" + animals.get(Rabbit.class).size()
                + "\uD83C\uDF3F:" + grass + "]";
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

    private void generationAnimals() {  //Здесь не происходит инициализация всех set'ов каждого вида животных!!!
        for (Class<?> classAnimal : Configuration.CLASS_ANIMALS) {
            if (isCreateAnimalType()) {
                int numberOfAnimalType = ThreadLocalRandom.current().nextInt(0, (int) Configuration.CONFIGURATIONS_ANIMALS.get(classAnimal)[1]);
                for (int i = 0; i < numberOfAnimalType; i++) {

                    Object o = tryCreateAnimal(classAnimal);
                    if (o instanceof Wolf) {                    //сравнивать с каждым видом, это временное решение
                        animals.get(classAnimal).add((Wolf) o);
                    }
                    if (o instanceof Rabbit) {
                        animals.get(classAnimal).add((Rabbit) o);
                    }
                }
            }
        }
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

    private void initializeAnimalSet() {
        for (Class<?> classAnimal : Configuration.CLASS_ANIMALS) {
            Set set = Collections.newSetFromMap(new ConcurrentHashMap<>());
            animals.put(classAnimal, set);
        }
    }
}
