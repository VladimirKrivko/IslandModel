package ru.javarush.golf.krivko.islandmodel;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.services.WorldWorker;
import ru.javarush.golf.krivko.islandmodel.worldgeneration.WorldGenerator;

public class MainRun {
    public static void main(String[] args) {

        WorldGenerator worldGenerator = new WorldGenerator(Configuration.SIZE_Y_GAME_FIELD, Configuration.SIZE_X_GAME_FIELD);
        WorldWorker worldWorker = new WorldWorker(worldGenerator);
        worldWorker.start();
    }
}
