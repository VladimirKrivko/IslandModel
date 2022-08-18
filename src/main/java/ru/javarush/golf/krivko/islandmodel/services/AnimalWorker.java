package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.GameField;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AnimalWorker implements Runnable{
    private final Class<? extends Animal> animalClass;
    private final GameField gameField;
    private final Queue<AnimalTask> animalTasks = new ConcurrentLinkedQueue<>();

    public AnimalWorker(Class<? extends Animal> animalClass, GameField gameField) {
        this.animalClass = animalClass;
        this.gameField = gameField;
    }

    @Override
    public void run() {
        Location[][] locations = gameField.getLocations();
        for (int y = 0; y < locations[y].length; y++) {
            for (int x = 0; x < locations.length; x++) {
                createTasksForArea(locations[x][y]);
            }
        }
    }

    private void createTasksForArea(Location location) {
        Set<Animal> animals = location.getAnimals().get(animalClass);
        if (animals != null) {
            location.getLock().lock();
            try {
                for (Animal animal : animals) {
                    animalTasks.add(new AnimalTask(animal, location));
                }
            } finally {
                location.getLock().unlock();
            }
        }
        animalTasks.forEach(AnimalTask::doAnAction);
        animalTasks.clear();
    }
}
