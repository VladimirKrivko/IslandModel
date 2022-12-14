package ru.javarush.golf.krivko.islandmodel.worldgeneration;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.GameField;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WorldGenerator {
    private final GameField gameField;

    public WorldGenerator(int y, int x) {
        this.gameField = new GameField(y, x);
        initializeLocation();
        generateLocations();
    }

    public GameField getGameField() {
        return gameField;
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

    private void generationAnimals(Location location) {
        for (Class<?> classAnimal : Configuration.CLASS_ANIMALS) {
            if (isCreateEntityType()) {
                int numberOfAnimalType = Randomizer.getRandom(0, (int) Configuration.CONFIGURATIONS_ANIMALS.get(classAnimal)[1]);
                for (int i = 0; i < numberOfAnimalType; i++) {
                    Animal animal = (Animal) tryCreateAnimal(classAnimal);
                    location.getAnimals().get(classAnimal).add(animal);
                }
            }
        }
    }

    private void generationPlants(Location location) {
        if (isCreateEntityType()) {
            location.setGrass(Randomizer.getRandom(0, (int) Configuration.MAX_AMOUNT_OF_GRASS));
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
        for (Class classAnimal : Configuration.CLASS_ANIMALS) {
            Set<Animal> set = ConcurrentHashMap.newKeySet();//new HashSet<>(); ??
            location.getAnimals().put(classAnimal, set);
        }
    }

    private boolean isCreateEntityType() {
        return Randomizer.getRandom();
    }
}
