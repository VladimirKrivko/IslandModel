package ru.javarush.golf.krivko.islandmodel.islandmodel.entities.gamefield;

public class Coordinates {
    private int y;
    private int x;

    public Coordinates(int y, int x) {
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
