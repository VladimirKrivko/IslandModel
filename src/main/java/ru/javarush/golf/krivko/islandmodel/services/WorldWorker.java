package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.worldgeneration.WorldGenerator;
import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorldWorker extends Thread{
    private final WorldGenerator world;
    private final boolean stopOnTimeout = true;
    private final int gameDuration = 10000;

    public WorldWorker(WorldGenerator world) {
        this.world = world;
    }

    @Override
    public void run() {
        world.getGameField().print();
        //
        ScheduledExecutorService gameScheduledThreadPool = Executors.newScheduledThreadPool(4);
        long lifeCycleDuration = 500;
        gameScheduledThreadPool.scheduleWithFixedDelay(this::runAndWaitAnimalWorkers, lifeCycleDuration, lifeCycleDuration, TimeUnit.MILLISECONDS);

        if (stopOnTimeout) runTimer();
    }

    private void runAndWaitAnimalWorkers() {
        ArrayList<AnimalWorker> animalWorkers = new ArrayList<>();
        for (Class animalClass : Configuration.CLASS_ANIMALS) {
            animalWorkers.add(new AnimalWorker(animalClass, world.getGameField()));
        }
        GrassWorker grassWorker = new GrassWorker(world.getGameField());

        int CORE_POOL_SIZE = 8;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        for (AnimalWorker animalWorker : animalWorkers) {
            fixedThreadPool.submit(animalWorker);
        }
        fixedThreadPool.submit(grassWorker);

        fixedThreadPool.shutdown();

        try {
            if (fixedThreadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS)) {
                world.getGameField().print();
            }
        } catch (InterruptedException e) {

            System.out.println("Simulation is finished");
        }
    }

    private void runTimer() {
        try {
            Thread.sleep(gameDuration);
        } catch (InterruptedException e) {
            System.out.println("Simulation is over");
        }
        //
        System.out.println("Simulation time out");
        System.exit(1);
    }
}
