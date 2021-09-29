package com.elitespectra.marsvehicles.marsrover;

import com.elitespectra.models.Plateau;
import com.elitespectra.models.Rover;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NavigationServiceTest {

    NavigationService roverNavigationService = NavigationService.getInstance();

    Plateau plateau = new Plateau(5, 5);

    Rover alpha = new Rover(plateau, "Alpha", 1, 2, "N");
    Rover beta = new Rover(plateau, "Beta", 3, 3, "E");
    Rover vega = new Rover(plateau, "Beta", 2, 4, "N");
    Rover gamma = new Rover(plateau, "Gamma", 5, 5, "N");


    @Before
    public void setUp() {

        // Try adding rovers to roverNavigationService which is an instance of NavigationService
        // If rover already exists in the roverNavigationService, it can't be added to roverNavigationService

        roverNavigationService.addRover(alpha);
        roverNavigationService.addRover(beta);
        roverNavigationService.addRover(vega);
        roverNavigationService.addRover(gamma);

    }


    @Test
    public void checkExistingRoverCantBeAddedAgainToNavigationService() {

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(alpha));

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(gamma));
    }


    @Test
    public void checkInitialRoverCoordinatesNotOutsidePlateau() {

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(new Rover(plateau, "Zetron", 1,
                        plateau.getXCoordinate() + 1, "N")));

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(new Rover(plateau, "Sirius", plateau.getXCoordinate() + 1,
                        plateau.getXCoordinate() + 1, "N")));

    }


    @Test
    public void checkForwardMovingFinalRoverCoordinatesAndFace() {

        roverNavigationService.navigateRover(alpha, "LFLFLFLFF");

        String actualCoordinatesAndFace = String.valueOf(alpha.getxCoordinate()) +
                String.valueOf(alpha.getyCoordinate()) +
                alpha.getFace();

        assertEquals("13N", actualCoordinatesAndFace);


        roverNavigationService.navigateRover(beta, "FFRFFRFRRF");

        actualCoordinatesAndFace = String.valueOf(beta.getxCoordinate()) +
                String.valueOf(beta.getyCoordinate()) +
                beta.getFace();

        assertEquals("51E", actualCoordinatesAndFace);

    }


    @Test
    public void checkForwardAndBackwardMovingFinalRoverCoordinatesAndFace() {

        roverNavigationService.navigateRover(vega, "BBBLFLFRF");

        String actualCoordinatesAndFace = String.valueOf(vega.getxCoordinate()) +
                String.valueOf(vega.getyCoordinate()) +
                vega.getFace();

        assertEquals("00W", actualCoordinatesAndFace);

    }


    @Test
    public void checkRoverCantMoveOutsidePlateau() {

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.navigateRover(gamma, "F"));

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.navigateRover(gamma, "LLB"));
    }


    @Test
    public void checkRoverNotGoingToCollide() {

        // A rover can't be landed or moved to coordinates where there's a rover already positioned
        assertFalse(roverNavigationService.isSpaceAvailableToLandOrMove(
                "TempRover",
                alpha.getxCoordinate(),
                alpha.getyCoordinate()));

        // A rover can't be landed or moved to coordinates where there's a rover already positioned
        assertFalse(roverNavigationService.isSpaceAvailableToLandOrMove(
                "TempRover",
                beta.getxCoordinate(),
                beta.getyCoordinate()));

        // A rover can land or move to coordinates where there isn't any rover
        assertTrue(roverNavigationService.isSpaceAvailableToLandOrMove(
                "TempRover",
                4,
                4));

    }

}
