package com.elitespectra.models;

public class Rover {

    private final Plateau PLATEAU;
    private final String ROVER_NAME;
    private int xCoordinate;
    private int yCoordinate;
    private String face;

    public Rover(Plateau plateau, String roverName, int xCoordinate, int yCoordinate, String face) {

        if (xCoordinate < 0 || xCoordinate > plateau.getXCoordinate() ||
                yCoordinate < 0 || yCoordinate > plateau.getYCoordinate())
            throw new IllegalArgumentException(roverName + " rover coordinates negative or outside Plateau range");

        this.PLATEAU = plateau;
        this.ROVER_NAME = roverName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.face = face;
    }

    public Plateau getPlateau() {
        return PLATEAU;
    }

    public String getRoverName() {
        return ROVER_NAME;
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
