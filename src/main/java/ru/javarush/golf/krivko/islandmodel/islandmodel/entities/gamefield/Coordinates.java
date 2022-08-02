package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.gamefield;

public class Coordinate {
    private final int y;
    private final int x;
    
    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
