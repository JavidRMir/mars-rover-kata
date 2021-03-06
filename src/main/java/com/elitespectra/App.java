package com.elitespectra;

import com.elitespectra.marsvehicles.marsrover.NavigationService;
import com.elitespectra.models.Plateau;
import com.elitespectra.models.Rover;

import java.util.Arrays;
import java.util.Collections;


public class App {
    public static void main(String[] args) {

        Plateau plateau = new Plateau(5, 5);

        Rover alpha = new Rover(plateau, "Alpha", 1, 2, "N");
        Rover beta = new Rover(plateau, "Beta", 3, 3, "E");
        Rover vega = new Rover(plateau, "Vega", 2, 4, "N");

        NavigationService roverNavigationService = NavigationService.getInstance();

        roverNavigationService.addRover(alpha);
        roverNavigationService.addRover(beta);
        roverNavigationService.addRover(vega);

        var rovers = roverNavigationService.getAllRovers();

//        for (var rover : rovers) {
//            System.out.println(rover.getRoverName() + " " +
//                    rover.getxCoordinate() + " " + rover.getyCoordinate() + " " + rover.getFace());
//        }



        rovers = roverNavigationService.getAllRovers();
        var rover = rovers.get(2);
        System.out.println(rover.getRoverName() + " " +
                rover.getxCoordinate() + " " + rover.getyCoordinate() + " " + rover.getFace());


        var navigationCommand = "BBBLFLFRF";
        roverNavigationService.navigateRover(rover.getRoverName(), navigationCommand);
        rovers = roverNavigationService.getAllRovers();
        rover = rovers.get(2);
        System.out.println(rover.getRoverName() + " " +
                rover.getxCoordinate() + " " + rover.getyCoordinate() + " " + rover.getFace());

        vega.setxCoordinate(5);
        vega.setyCoordinate(5);
        rovers = roverNavigationService.getAllRovers();
        rover = rovers.get(2);
        System.out.println(rover.getRoverName() + " " +
                rover.getxCoordinate() + " " + rover.getyCoordinate() + " " + rover.getFace());


    }

}
