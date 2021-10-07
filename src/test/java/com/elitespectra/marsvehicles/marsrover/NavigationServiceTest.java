package com.elitespectra.marsvehicles.marsrover;

import com.elitespectra.models.Plateau;
import com.elitespectra.models.Rover;
import org.junit.Test;

import static org.junit.Assert.*;

public class NavigationServiceTest {

    NavigationService roverNavigationService = NavigationService.getInstance();

    Plateau plateau = new Plateau(5, 5);

    Rover alpha = new Rover(plateau, "Alpha", 1, 2, "N");
    Rover beta = new Rover(plateau, "Beta", 3, 3, "E");
    Rover vega = new Rover(plateau, "Vega", 2, 4, "N");
    Rover gamma = new Rover(plateau, "Gamma", 5, 5, "N");
    Rover dummy = new Rover(plateau, "Dummy", 0, 0, "N");



    @Test
    public void checkPlateauCoordinatesCantBeZeroOrNegative() {

        assertThrows(IllegalArgumentException.class, () ->
                new Plateau(0, -5));

    }

    @Test
    public void checkRoverCoordinatesCantBeNegative() {

        assertThrows(IllegalArgumentException.class, () ->
        new Rover(plateau, "Dummy", -2, -5, "E"));

    }

    @Test
    public void checkForwardAndBackwardMovingFinalRoverCoordinatesAndFace() {

        roverNavigationService.addRover(alpha);
        roverNavigationService.addRover(beta);
        roverNavigationService.addRover(vega);
        roverNavigationService.addRover(gamma);

        // check final coordinates of a Forward And Backward moving rover
        // from NavigatuonService after it receives and executes navigation commands

        var rover = getUpdatedRover(vega.getRoverName());

        roverNavigationService.navigateRover(rover.getRoverName(), "BBBLFLFRF");
        rover = getUpdatedRover(rover.getRoverName());

        String actualCoordinatesAndFace = String.valueOf(rover.getxCoordinate()) +
                String.valueOf(rover.getyCoordinate()) +
                rover.getFace();

        assertEquals("00W", actualCoordinatesAndFace);

    }


    @Test
    public void checkRoverCantNavigateIfNotPartOfNavigationService() {

        // Rover can't be navigated unless added to the NavigationService

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.navigateRover(dummy.getRoverName(), "F"));

    }


    @Test
    public void checkExistingRoverCantBeAddedAgainToNavigationService() {

        // A rover can't be added (again) if it already exists in the NavigationService

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(alpha));

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(gamma));
    }


    @Test
    public void checkInitialLandingRoverCoordinatesNotOutsidePlateau() {

        // A rover can't be landed outside of the Plateau

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(new Rover(plateau, "Zetron", 1,
                        plateau.getXCoordinate() + 1, "N")));

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(new Rover(plateau, "Sirius", plateau.getXCoordinate() + 1,
                        plateau.getXCoordinate() + 1, "N")));

    }


    @Test
    public void checkForwardMovingFinalRoverCoordinatesAndFace() {

        // check final coordinates of a Forward moving rover
        // after it receives and executes navigation commands

        var rover = getUpdatedRover(alpha.getRoverName());
        roverNavigationService.navigateRover(rover.getRoverName(), "LFLFLFLFF");

        rover = getUpdatedRover(rover.getRoverName());

        String actualCoordinatesAndFace = String.valueOf(rover.getxCoordinate()) +
                String.valueOf(rover.getyCoordinate()) +
                rover.getFace();

        assertEquals("13N", actualCoordinatesAndFace);


        rover = getUpdatedRover(beta.getRoverName());

        roverNavigationService.navigateRover(rover.getRoverName(), "FFRFFRFRRF");

        rover = getUpdatedRover(rover.getRoverName());

        actualCoordinatesAndFace = String.valueOf(rover.getxCoordinate()) +
                String.valueOf(rover.getyCoordinate()) +
                rover.getFace();

        assertEquals("51E", actualCoordinatesAndFace);

    }


    @Test
    public void checkRoverCantNavigateOutsideOfPlateau() {

        // A rover can't be navigated to outside of the Plateau

        var rover = getUpdatedRover(gamma.getRoverName());

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.navigateRover(rover.getRoverName(), "F"));

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.navigateRover(rover.getRoverName(), "LLB"));
    }


    @Test
    public void checkRoverNotGoingToCollide() {

        // A rover CAN'T be landed or moved to coordinates where there's a rover already positioned

        var rover = getUpdatedRover(alpha.getRoverName());

        assertFalse(roverNavigationService.isSpaceAvailableToLandOrMove(
                "TempRover",
                rover.getxCoordinate(),
                rover.getyCoordinate()));


        rover = getUpdatedRover(beta.getRoverName());

        assertFalse(roverNavigationService.isSpaceAvailableToLandOrMove(
                "TempRover",
                rover.getxCoordinate(),
                rover.getyCoordinate()));

        assertTrue(roverNavigationService.isSpaceAvailableToLandOrMove(
                "TempRover",
                4,
                4));

    }


    public Rover getUpdatedRover(String roverName) {
        var rovers = roverNavigationService.getAllRovers();
        return rovers.get(roverNavigationService.getRoverIndex(roverName));
    }

}
