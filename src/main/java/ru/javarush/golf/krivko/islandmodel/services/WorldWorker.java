package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.WorldGenerator;
import ru.javarush.golf.krivko.islandmodel.constants.Configuration;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorldWorker extends Thread{
    private final WorldGenerator worldGenerator;

    //
    private final long lifeCycleDuration = 1000;
    private final Boolean stopOnTimeout = true;
    private final int gameDuration = 10000;

    public WorldWorker(WorldGenerator worldGenerator) {
        this.worldGenerator = worldGenerator;
    }

    @Override
    public void run() {
        worldGenerator.getGameField().print();//+
        //
        ScheduledExecutorService gameScheduledThreadPool = Executors.newScheduledThreadPool(4);
        gameScheduledThreadPool.scheduleWithFixedDelay(this::runAndWaitAnimalWorkers, lifeCycleDuration, lifeCycleDuration, TimeUnit.MILLISECONDS);

        if (stopOnTimeout) runTimer();
    }

    private void runAndWaitAnimalWorkers() {
        ArrayList<AnimalWorker> animalWorkers = new ArrayList<>();
        for (Class animalClass : Configuration.CLASS_ANIMALS) {
            animalWorkers.add(new AnimalWorker(animalClass, worldGenerator.getGameField()));
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
                worldGenerator.getGameField().print();
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
