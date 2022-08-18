package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.entities.gamefield.GameField;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GrassWorker implements Runnable {
    private final GameField gameField;
    private final Queue<GrassTask> grassTasks = new ConcurrentLinkedQueue<>();
    public GrassWorker(GameField gameField) {
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
        location.getLock().lock();
        try {
            grassTasks.add(new GrassTask(location));
        } finally {
            location.getLock().unlock();
        }

        grassTasks.forEach(GrassTask::growUp);
        grassTasks.clear();
    }

}
