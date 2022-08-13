package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

public class GameField {

    private final Location[][] locations;
    public GameField(int y, int x) {
        this.locations = new Location[x][y];
    }

    public Location[][] getLocations() {
        return this.locations;
    }

    public Location getLocation(int yPosition, int xPosition) {
        return locations[xPosition][yPosition];
    }

    public void print() {
        for (int y = 0; y < locations[y].length; y++) {
            for (int x = 0; x < locations.length; x++) {
                System.out.print(locations[x][y]);
            }
            System.out.println();
        }
        System.out.println("============================================================================");
    }
}