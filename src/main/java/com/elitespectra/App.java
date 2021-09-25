package com.elitespectra;

import com.elitespectra.marsvehicles.marsrover.NavigationService;
import com.elitespectra.models.Plateau;


public class App {
    public static void main(String[] args) {

        NavigationService roverNavigationService = new NavigationService();
        roverNavigationService.addRover(new com.elitespectra.models.Rover(new Plateau(5, 5), "Alpha", 1, 2, "N"));


//        NavigationService beta = new NavigationService(new Plateau(5, 5),
//                "Beta", 3, 3, "N");

//        Map<String, Map> rovers = NavigationService.getAllRovers();
//        System.out.println(rovers);
//        for (Map.Entry<String, Map> rover : rovers.entrySet()) {
//            System.out.println(rover.getValue().get("xCoordinate") + ", " +
//                    rover.getValue().get("yCoordinate"));
//        }


    }
}
