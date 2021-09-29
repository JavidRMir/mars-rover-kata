package com.elitespectra.models;

public class Plateau {

    private final int X_COORDINATE;
    private final int Y_COORDINATE;

    public Plateau(int x, int y) {

        if (x <= 0 || y <= 0)
            throw new IllegalArgumentException("Plateau dimensions can't be zero or negative");

        this.X_COORDINATE = x;
        this.Y_COORDINATE = y;
    }

    public int getXCoordinate() {
        return X_COORDINATE;
    }

    public int getYCoordinate() {
        return Y_COORDINATE;
    }


}
