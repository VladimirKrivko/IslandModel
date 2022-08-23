package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.birds.Duck;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Bear;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Horse;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Mouse;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Wolf;
import ru.javarush.golf.krivko.islandmodel.services.AnimalTask;
import ru.javarush.golf.krivko.islandmodel.services.GrassTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Location {

    private final ExecutorService poolThread = Executors.newFixedThreadPool(8);
    private final int xPosition;
    private final int yPosition;
    private volatile double grass;
    private final List<Location> neighboringLocations = new ArrayList<>();
    private final Map<Class<? extends Animal>, Set<Animal>> animals = new ConcurrentHashMap<>();//new HashMap<>();
    private final Lock lock = new ReentrantLock(true);
    public Location(int y, int x) {
        this.yPosition = y;
        this.xPosition = x;
    }


    public void start() {
        lock.lock();
        try {
            for (Class<? extends Animal> animalClass : animals.keySet()) {
                for (Animal animal : animals.get(animalClass)) {
                    poolThread.submit(new AnimalTask(animal, this));
                }
            }
            poolThread.submit(new GrassTask(this));
        }finally {
            lock.unlock();
        }
    }

    public void await(int milliseconds) throws InterruptedException {
        poolThread.awaitTermination(milliseconds, TimeUnit.MILLISECONDS);
    }

    public void shutdown(){
        poolThread.shutdown();
    }

    public void removeAnimalFromLocation(Animal animal) {
        animals.get(animal.getClass()).remove(animal);
    }

    public void addAnimalToLocation(Animal animal) {
        if (isThereEnoughSpace(animal.getClass())) {
            animals.get(animal.getClass()).add(animal);
        }
    }

    public boolean isThereEnoughSpace(Class<? extends Animal> animalClass) {
        return animals.get(animalClass).size() < Configuration.CONFIGURATIONS_ANIMALS.get(animalClass)[1];
    }

    public void grassGrowth() {
        getLock().lock();
        try {
            double grassGrowth = Configuration.GRASS_GROWTH;
            if (this.grass + grassGrowth < Configuration.MAX_AMOUNT_OF_GRASS) {
                this.grass += grassGrowth;
            } else {
                this.grass = Configuration.MAX_AMOUNT_OF_GRASS;
            }
        }finally {
            getLock().unlock();
        }
    }

    @Override
    public String toString() {
        return "[" + "\uD83D\uDC3B" + animals.get(Bear.class).size()
                /*+ "\uD83D\uDC0D" + animals.get(Boa.class).size()
                + ":\uD83D\uDC17" + animals.get(Boar.class).size()
                + "\uD83D\uDC03" + animals.get(Buffalo.class).size()
                + "\uD83E\uDD8C" + animals.get(Deer.class).size()
                + "\uD83E\uDD8A" + animals.get(Fox.class).size()
                + "\uD83D\uDC10" + animals.get(Goat.class).size()*/
                + "\uD83D\uDC0E" + animals.get(Horse.class).size()
                + "\uD83D\uDC01" + animals.get(Mouse.class).size()
                /*+ ":\uD83D\uDC07" + animals.get(Rabbit.class).size()
                + ":\uD83D\uDC11" + animals.get(Sheep.class).size()*/
                + "\uD83D\uDC3A" + animals.get(Wolf.class).size()
                + "\uD83E\uDD86" + animals.get(Duck.class).size()
                /*+ "\uD83E\uDD85" + animals.get(Eagle.class).size()
                + ":\uD83D\uDC1B" + animals.get(Caterpillar.class).size()*/
                + ":\uD83C\uDF3F" + String.format("%.2f", grass) + "]";
    }

    public Lock getLock() {
        return lock;
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
}