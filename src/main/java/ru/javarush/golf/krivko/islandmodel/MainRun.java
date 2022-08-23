package ru.javarush.golf.krivko.islandmodel;

import ru.javarush.golf.krivko.islandmodel.configuration.Configuration;
import ru.javarush.golf.krivko.islandmodel.services.LifeWorker;
import ru.javarush.golf.krivko.islandmodel.worldgeneration.WorldGenerator;

public class MainRun {
    public static void main(String[] args) {

        WorldGenerator world = new WorldGenerator(Configuration.SIZE_Y_GAME_FIELD, Configuration.SIZE_X_GAME_FIELD);
        LifeWorker lifeWorker = new LifeWorker(world);
        lifeWorker.start();

    }
}