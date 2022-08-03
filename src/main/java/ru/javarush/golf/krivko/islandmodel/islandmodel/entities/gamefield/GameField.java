package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.islandmodel.constants.Constants;

public class GameField {

    private final Location[][] island;

    public GameField() {
        this.island = new Location[Constants.MAX_X_GAME_FIELD][Constants.MAX_Y_GAME_FIELD];
        init();
    }

    private void init() {
        for (int y = 0; y < island[y].length; y++) {
            for (int x = 0; x < island.length; x++) {
                island[x][y] = new Location(y, x);
            }
        }
    }

    public void print() {
        for (int y = 0; y < island[y].length; y++) {
            for (int x = 0; x < island.length; x++) {
                System.out.print(island[x][y]);
            }
            System.out.println();
        }
    }
}
