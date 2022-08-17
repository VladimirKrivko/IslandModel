package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.insects.Caterpillar;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Boar;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Rabbit;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Sheep;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Wolf;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Location {
    private final int xPosition;
    private final int yPosition;
    private volatile double grass;
    private final List<Location> neighboringLocations = new ArrayList<>();

    private final Map<Class<? extends Animal>, Set<Animal>> animals = new ConcurrentHashMap<>();//new HashMap<>();
    private final Lock lock = new ReentrantLock(true);
    public Lock getLock() {
        return lock;
    }

    public Location(int y, int x) {
        this.yPosition = y;
        this.xPosition = x;
    }

    public void removeAnimalFromLocation(Animal animal) {
        animals.get(animal.getClass()).remove(animal);
    }

    public void addAnimalToLocation(Animal animal) {
        if (isThereEnoughSpace(animal.getClass())) {
            animals.get(animal.getClass()).add(animal);
        }
    }

    @Override
    public String toString() {
        return "[" + "\uD83D\uDC3A" + animals.get(Wolf.class).size()
                + ":\uD83D\uDC11" + animals.get(Sheep.class).size()
                + ":\uD83D\uDC07" + animals.get(Rabbit.class).size()
                + ":\uD83D\uDC17" + animals.get(Boar.class).size()
                + ":\uD83D\uDC1B" + animals.get(Caterpillar.class).size()
                + ":\uD83C\uDF3F" + String.format("%.2f", grass) + "]";
    }

    public Map<Class<? extends Animal>, Set<Animal>> getAnimals() {
        return animals;
    }

    public List<Location> getNeighboringLocations() {
        return neighboringLocations;
    }

    public double getGrass() {
        return grass;
    }

    public void setGrass(double grass) {
        this.grass = grass;
    }

    public int getPositionX() {
        return xPosition;
    }

    public int getPositionY() {
        return yPosition;
    }

    public boolean isThereEnoughSpace(Class<? extends Animal> animalClass) {
        return animals.get(animalClass).size() < Configuration.CONFIGURATIONS_ANIMALS.get(animalClass)[1];
    }

    public synchronized void grassGrowth() {
        getLock().lock();
        try {
            if (this.grass + 5 < Configuration.AMOUNT_OF_GRASS) {
                this.grass += 5;
            } else {
                this.grass = Configuration.AMOUNT_OF_GRASS;
            }
        }finally {
            getLock().unlock();
        }
    }
}
