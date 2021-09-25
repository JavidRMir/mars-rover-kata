package com.elitespectra.models;

public class Rover {

    private Plateau plateau;
    private String roverName;
    private int xCoordinate;
    private int yCoordinate;
    private String face;

    public Rover(Plateau plateau, String roverName, int xCoordinate, int yCoordinate, String face) {

        if (xCoordinate > plateau.getX() || yCoordinate > plateau.getY())
            throw new IllegalArgumentException("Rover Coordinates outside Plateau range");

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

    public int getyCoordinate() {
        return yCoordinate;
    }

    public String getFace() {
        return face;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setFace(String face) {
        this.face = face;
    }
}
