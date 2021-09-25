package com.elitespectra.marsvehicles.marsrover;

import com.elitespectra.models.Rover;

import java.util.HashMap;
import java.util.Map;


public class NavigationService {

    private static final Map<String, Map> ALL_ROVERS = new HashMap<>();

    public void addRover(Rover rover) {

        // TODO: Validate if Rover can be added with the coordinates

        ALL_ROVERS.put(rover.getRoverName(),
                Map.of("xCoordinate", rover.getxCoordinate(),
                        "yCoordinate", rover.getyCoordinate()));
    }

    public void navigateRover(Rover rover, String navigationPath) {

        // TODO: All navigation happens here
//        rover.setxCoordinate(1);
//        rover.setyCoordinate(3);
//        rover.setFace("N");
//        rover.setxCoordinate(5);
//        rover.setyCoordinate(1);
//        rover.setFace("E");

    }

    private boolean isRoverOKtoLandOrMove(int xCoordinate, int yCoordinate) {

        boolean isOkToLandMove = true;

        for (Map.Entry<String, Map> rover : ALL_ROVERS.entrySet()) {
            if (xCoordinate == (int) rover.getValue().get("xCoordinate")
                    && yCoordinate == (int) rover.getValue().get("yCoordinate")) {
                return false;
            }
        }

        return isOkToLandMove;
    }


}
