package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.WorldGenerator;
import ru.javarush.golf.krivko.islandmodel.constants.Configuration;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorldWorker extends Thread{
    private final WorldGenerator world;

    //
    private final long lifeCycleDuration = 100;
    private final Boolean stopOnTimeout = true;
    private final int gameDuration = 30000;

    public WorldWorker(WorldGenerator world) {
        this.world = world;
    }

    @Override
    public void run() {
        world.getGameField().print();//+
        //
        ScheduledExecutorService gameScheduledThreadPool = Executors.newScheduledThreadPool(4);
        gameScheduledThreadPool.scheduleWithFixedDelay(this::runAndWaitAnimalWorkers, lifeCycleDuration, lifeCycleDuration, TimeUnit.MILLISECONDS);

        if (stopOnTimeout) runTimer();
    }

    private void runAndWaitAnimalWorkers() {
        ArrayList<AnimalWorker> animalWorkers = new ArrayList<>();
        for (Class animalClass : Configuration.CLASS_ANIMALS) {
            animalWorkers.add(new AnimalWorker(animalClass, world.getGameField()));
        }
        //
        int CORE_POOL_SIZE = 4;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        for (AnimalWorker animalWorker : animalWorkers) {
            fixedThreadPool.submit(animalWorker);
        }
        fixedThreadPool.shutdown();

        try {
            if (fixedThreadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS)) {
                world.getGameField().print();
            }
        } catch (InterruptedException e) {
            //
            System.out.println("The game is finished");
        }
    }

    private void runTimer() {
        try {
            Thread.sleep(gameDuration);
        } catch (InterruptedException e) {
            System.out.println("The game is already finished");
        }
        //
        System.out.println("The game is over by timeout");
        System.exit(1);
    }
}
