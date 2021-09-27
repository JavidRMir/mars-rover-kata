package com.elitespectra.models;

public class Plateau {

    private final int X;
    private final int Y;

    public Plateau(int x, int y) {

        if (x <= 0 || y <= 0)
            throw new IllegalArgumentException("Plateau dimensions can't be zero or negative");

        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
