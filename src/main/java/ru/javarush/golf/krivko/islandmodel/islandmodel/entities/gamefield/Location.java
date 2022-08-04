package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.animals.mammals.Wolf;
import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.plants.Grass;
import ru.javarush.golf.krivko.islandmodel.islandmodel.entities.plants.Plant;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Location {

    private final Coordinates coordinates;

    private Set<Animal> animals = new HashSet<>();
    private Set<Plant> plants = new HashSet<>();

    public Location(int y, int x) {
        this.coordinates = new Coordinates(y, x);
        generationLife();
    }

    private void generationLife() {
        generationPlants();
        generationAnimals();
    }

    private void generationPlants() {
        int numberOfPlants = ThreadLocalRandom.current().nextInt(Configuration.MAX_NUMBER_OF_PLANTS_PER_LOCATION);
        for (int i = 0; i < numberOfPlants; i++) {
            plants.add(new Grass());
        }
    }

    private void generationAnimals() {
        int numberOfWolfs = ThreadLocalRandom.current().nextInt(Configuration.MAX_NUMBER_OF_WOLFS_PER_LOCATION);
        for (int i = 0; i < numberOfWolfs; i++) {
            animals.add(new Wolf(coordinates));
        }
    }


    @Override
    public String toString() {
        return "[" + "\uD83D\uDC3A:" + animals.size() + " T:" + plants.size() + "]";
    }
}
