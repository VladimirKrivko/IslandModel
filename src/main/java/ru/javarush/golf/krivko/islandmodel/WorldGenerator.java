package ru.javarush.golf.krivko.islandmodel;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.GameField;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class WorldGenerator {
    private final GameField gameField;

    public WorldGenerator(int y, int x) {
        this.gameField = new GameField(y, x);
        initializeLocation();
        generateLocations();
    }

    private void initializeLocation() {
        Location[][] locations = this.gameField.getLocations();
        for (int y = 0; y < locations[y].length; y++) {
            for (int x = 0; x < locations.length; x++) {
                locations[x][y] = new Location(y, x);
            }
        }
    }

    private void generateLocations() {
        Location[][] locations = this.gameField.getLocations();
        for (int y = 0; y < locations[y].length; y++) {
            for (int x = 0; x < locations.length; x++) {
                setNeighboringLocations(locations[x][y]);
                initializeAnimalSet(locations[x][y]);
                generationAnimals(locations[x][y]);
                generationPlants(locations[x][y]);
            }
        }
    }

    private void setNeighboringLocations(Location location) {
        int yMin = Math.max(location.getPositionY() - 1, 0);
        int yMax = Math.min(location.getPositionY() + 1, Configuration.SIZE_Y_GAME_FIELD - 1);

        int xMin = Math.max(location.getPositionX() - 1, 0);
        int xMax = Math.min(location.getPositionX() + 1, Configuration.SIZE_X_GAME_FIELD - 1);

        for(int y = yMin; y <= yMax; y++) {
            for(int x = xMin; x <= xMax; x++) {
                if (!(location.getPositionY() == y && location.getPositionX() == x)) {
                    location.getNeighboringLocations().add(gameField.getLocation(y, x));
                }
            }
        }
    }

    //Инициализировать списки в мапе animals




    private void generationAnimals(Location location) {
        for (Class<?> classAnimal : Configuration.CLASS_ANIMALS) {
            if (isCreateAnimalType()) {
                int numberOfAnimalType = ThreadLocalRandom.current().nextInt(0, (int) Configuration.CONFIGURATIONS_ANIMALS.get(classAnimal)[1]);
                for (int i = 0; i < numberOfAnimalType; i++) {
                    Animal animal = (Animal) tryCreateAnimal(classAnimal);
                    location.getAnimals().get(classAnimal).add(animal);
                }
            }
        }
    }

    private void generationPlants(Location location) {
        if (isCreateAnimalType()) {
            location.setGrass(ThreadLocalRandom.current().nextInt(0, (int) Configuration.GRASS_WEIGHT));
        }
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

    private void initializeAnimalSet(Location location) {
        for (Class<?> classAnimal : Configuration.CLASS_ANIMALS) {
            Set set = Collections.newSetFromMap(new ConcurrentHashMap<>());
            location.getAnimals().put(classAnimal, set);
        }
    }

    private boolean isCreateAnimalType() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public GameField getGameField() {
        return gameField;
    }
}
