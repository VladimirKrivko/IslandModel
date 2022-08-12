package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Rabbit;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.Wolf;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Location {
    private final int xPosition;
    private final int yPosition;
    private double grass;

    private final List<Location> neighboringLocations = new ArrayList<>();


    private final Map<Class, Set<Animal>> animals = new ConcurrentHashMap<>();

    public Location(int y, int x) {
        this.yPosition = y;
        this.xPosition = x;
    }

    public void doAction() {
        for (Map.Entry<Class, Set<Animal>> pair : animals.entrySet()) {
            Set<Animal> value = pair.getValue();
            for (Animal animal : value) {
//                if (animal.getWeight() > (Configuration.CONFIGURATIONS_ANIMALS.get(animal.getClass())[0] / 1.2)) {
                    animal.move(this);
//                } else {
//                    if (animal instanceof Rabbit) {
//                        ((Rabbit) animal).eat(this);
//                    }
//                    if (animal instanceof Wolf) {
//                        ((Wolf) animal).eat(this);
//                    }
//                }
//                if (animal.getWeight() < Configuration.CONFIGURATIONS_ANIMALS.get(animal.getClass())[0] / 2.5) {
//                    this.removeAnimalFromLocation(animal);
//                }
            }
        }
        grassGrowth();
    }

    public void removeAnimalFromLocation(Animal animal) {
        //реализация удаления животного
        animals.get(animal.getClass()).remove(animal);
    }

    public void addAnimalToLocation(Animal animal) { // добавил проверку на нуль
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
        return "[" + "\uD83D\uDC3A" + animals.get(Wolf.class).size()
                + ":\uD83D\uDC07" + animals.get(Rabbit.class).size()
                + ":\uD83C\uDF3F" + String.format("%.2f", grass) + "]";
    }

    public Map<Class, Set<Animal>> getAnimals() {
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

    private void grassGrowth() {                                     //растет трава
        if (this.grass < Configuration.GRASS_WEIGHT) {
            this.grass += 0.1;
        }
    }
}
