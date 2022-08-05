package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;

public class GameField {

    private static final Location[][] locations = new Location[Configuration.SIZE_X_GAME_FIELD][Configuration.SIZE_Y_GAME_FIELD];

    static {
        for (int y = 0; y < locations[y].length; y++) {
            for (int x = 0; x < locations.length; x++) {
                locations[x][y] = new Location(y, x);
            }
        }
    }

    private GameField() { }

    public static Location getLocation(int yPosition, int xPosition) {
        return locations[xPosition][yPosition];
    }

    public static void print() {
        for (int y = 0; y < locations[y].length; y++) {
            for (Location[] location : locations) {
                System.out.print(location[y]);
            }
            System.out.println();
        }
    }
}


/*private static void creatingLocations() {
        for (int y = 0; y < locations[y].length; y++) {
            for (int x = 0; x < locations.length; x++) {
                locations[x][y] = new Location(y, x);
            }
        }
    }*/