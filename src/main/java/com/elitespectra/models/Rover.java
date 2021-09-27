package com.elitespectra.models;

public class Rover {

    private final Plateau plateau;
    private final String roverName;
    private int xCoordinate;
    private int yCoordinate;
    private String face;

    public Rover(Plateau plateau, String roverName, int xCoordinate, int yCoordinate, String face) {

        if (xCoordinate < 0 || xCoordinate > plateau.getX() ||
                yCoordinate < 0 || yCoordinate > plateau.getY())
            throw new IllegalArgumentException(roverName + " rover coordinates negative or outside Plateau range");

        this.plateau = plateau;
        this.roverName = roverName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.face = face;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public String getRoverName() {
        return roverName;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }
}
