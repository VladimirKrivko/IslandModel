package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;

public class GameField {
    //Статический костыль. Подумать как избавиться.
    private static final Location[][] LOCATIONS = new Location[Configuration.SIZE_X_GAME_FIELD][Configuration.SIZE_Y_GAME_FIELD];

    private void createLocations() {
        for (int y = 0; y < LOCATIONS[y].length; y++) {
            for (int x = 0; x < LOCATIONS.length; x++) {
                LOCATIONS[x][y] = new Location(y, x);
            }
        }
    }

    public GameField() {
        createLocations();
    }

    public void startSimulation() { //метод для однопоточки. Переписать под многопоточку.
        for (int y = 0; y < LOCATIONS[y].length; y++) {
            for (Location[] location : LOCATIONS) {
                location[y].doAction();
            }
        }
    }

    //Статический костыль. Подумать как избавиться.
    public static Location getLocation(int yPosition, int xPosition) {
        return LOCATIONS[xPosition][yPosition];
    }

    public void print() {
        for (int y = 0; y < LOCATIONS[y].length; y++) {
            for (Location[] location : LOCATIONS) {
                System.out.print(location[y]);
            }
            System.out.println();
        }
        System.out.println("============================================================================");
    }
}