package com.elitespectra.marsvehicles.marsrover;

import com.elitespectra.models.Rover;

import java.util.*;


public final class NavigationService {

    private static NavigationService INSTANCE;
    private final List<Rover> ALL_ROVERS = new ArrayList<>();

    private NavigationService() {}

    public synchronized static NavigationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NavigationService();
        }

        return INSTANCE;
    }

    public void addRover(Rover rover) throws IllegalArgumentException {

        // Check If roverName already exists, not allowed
        var roverIndex = getRoverIndex(rover.getRoverName());
        if (roverIndex != -1) {
            throw new IllegalArgumentException(rover.getRoverName() +
                    " rover already exists in the Navigation Service");
        }

        // Check if any existing rover is not positioned at the newly adding rover coordinates
        if (!isSpaceAvailableToLandOrMove(rover.getRoverName(), rover.getxCoordinate(), rover.getyCoordinate()))
            throw new IllegalArgumentException("There's already a rover at these coordinates");

        // Add a copy of the rover, not reference but value using Rover copy constructor
        ALL_ROVERS.add(new Rover(rover));
    }


    public void navigateRover(String roverName, String navigationPath) {

        // Check if rover exists in NavigationService in ALL_ROVERS
        var roverIndex = getRoverIndex(roverName);
        if (roverIndex == -1) throw new IllegalArgumentException(roverName +
                " rover needs to be added to the NavigationService before navigating in the Plateau");

        var rover = ALL_ROVERS.get(roverIndex);


        var navigationCommandList = navigationPath.split("");

        Map<String, Integer> tempCoordinatesMap = Map.of(
                "xCoordinate", rover.getxCoordinate(),
                "yCoordinate", rover.getyCoordinate()
        );

        var tempFace = rover.getFace();
        boolean roverNavigationSuccess = false;

        for (var navigationCommand : navigationCommandList) {
            switch (navigationCommand) {
                case "R", "L" -> tempFace = spinFace(tempFace, navigationCommand);
                case "F", "B" -> {

                    tempCoordinatesMap = moveRover(tempCoordinatesMap.get("xCoordinate"),
                            tempCoordinatesMap.get("yCoordinate"),
                            tempFace, navigationCommand);

                    if (tempCoordinatesMap.get("xCoordinate") > rover.getPlateau().getXCoordinate()
                            || tempCoordinatesMap.get("yCoordinate") > rover.getPlateau().getYCoordinate()) {

                        roverNavigationSuccess = false;

                    } else {
                        roverNavigationSuccess = isSpaceAvailableToLandOrMove(
                                rover.getRoverName(),
                                tempCoordinatesMap.get("xCoordinate"),
                                tempCoordinatesMap.get("yCoordinate"));
                    }

                    if (!roverNavigationSuccess)
                        throw new IllegalArgumentException(rover.getRoverName() +
                                " Rover can't be navigated to the commanded coordinates");
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + navigationCommand);
            }
        }

        if (roverNavigationSuccess) {
            rover.setFace(tempFace);
            rover.setxCoordinate(tempCoordinatesMap.get("xCoordinate"));
            rover.setyCoordinate(tempCoordinatesMap.get("yCoordinate"));
        }

    }


    public boolean isSpaceAvailableToLandOrMove(String roverName, int xCoordinate, int yCoordinate) {

        for (var rover : ALL_ROVERS) {
            if (!roverName.equals(rover.getRoverName())) {
                if (xCoordinate == rover.getxCoordinate()
                        && yCoordinate == rover.getyCoordinate()) {
                    return false;
                }
            }

        }
        return true;
    }

    private String spinFace(String tempFace, String navigationCommand) {
        return switch (tempFace + navigationCommand) {
            case "ER", "WL" -> "S";
            case "EL", "WR" -> "N";
            case "NR", "SL" -> "E";
            case "NL", "SR" -> "W";
            default -> "";
        };
    }

    private Map<String, Integer> moveRover(int xCoordinate, int yCoordinate,
                                           String tempFace, String navigationCommand) {

        Map<String, Integer> tempCoordinatesMap = new HashMap<>();
        tempCoordinatesMap.put("xCoordinate", xCoordinate);
        tempCoordinatesMap.put("yCoordinate", yCoordinate);

        switch (tempFace + navigationCommand) {
            case "EF", "WB" -> tempCoordinatesMap.replace("xCoordinate", xCoordinate + 1);
            case "WF", "EB" -> tempCoordinatesMap.replace("xCoordinate", xCoordinate - 1);
            case "NF", "SB" -> tempCoordinatesMap.replace("yCoordinate", yCoordinate + 1);
            case "SF", "NB" -> tempCoordinatesMap.replace("yCoordinate", yCoordinate - 1);

        }

        return tempCoordinatesMap;

    }

    public List<Rover> getAllRovers() {
        List<Rover> tempAllRovers = new ArrayList<Rover>();
        for (var rover : ALL_ROVERS) {
            tempAllRovers.add(new Rover(rover));
        }
        return Collections.unmodifiableList(tempAllRovers);
    }

    public int getRoverIndex(String roverName) {
        for (var rover : ALL_ROVERS) {
            if (roverName.equalsIgnoreCase(rover.getRoverName())) {
                return ALL_ROVERS.indexOf(rover);
            }
        }
        return -1;
    }

}
