package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.islandmodel.constants.Configuration;

public class GameField {

    private final Location[][] locations;

    public GameField() {
        this.locations = new Location[Configuration.SIZE_X_GAME_FIELD][Configuration.SIZE_Y_GAME_FIELD];
        creatingLocations();
    }

    private void creatingLocations() {
        for (int y = 0; y < locations[y].length; y++) {
            for (int x = 0; x < locations.length; x++) {
                locations[x][y] = new Location(y, x);
            }
        }
    }

    public void print() {
        for (int y = 0; y < locations[y].length; y++) {
            for (int x = 0; x < locations.length; x++) {
                System.out.print(locations[x][y]);
            }
            System.out.println();
        }
    }
}
