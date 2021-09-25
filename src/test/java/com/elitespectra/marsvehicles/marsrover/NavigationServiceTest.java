package com.elitespectra.marsvehicles.marsrover;

import com.elitespectra.models.Plateau;
import com.elitespectra.models.Rover;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class NavigationServiceTest {

    NavigationService roverNavigationService = new NavigationService();

    Plateau plateau = new Plateau(5, 5);
    Rover alpha = new Rover(plateau, "Alpha", 1, 2, "N");
    Rover beta = new Rover(plateau, "Beta", 3, 3, "E");


    @Before
    public void setUp() {
        roverNavigationService.addRover(alpha);
        roverNavigationService.addRover(beta);
    }


    @Test
    public void checkFinalRoverCoordinatesAndFace() {

        roverNavigationService.navigateRover(alpha, "LMLMLMLMM");

        String actualCoordinatesAndFace = String.valueOf(alpha.getxCoordinate()) +
                String.valueOf(alpha.getyCoordinate()) +
                alpha.getFace();

        assertEquals("13N", actualCoordinatesAndFace);



        roverNavigationService.navigateRover(beta, "MMRMMRMRRM");

        actualCoordinatesAndFace = String.valueOf(beta.getxCoordinate()) +
                String.valueOf(beta.getyCoordinate()) +
                beta.getFace();

        assertEquals("51E", actualCoordinatesAndFace);

    }

    @Test
    public void checkInitialRoverCoordinatesNotOutsidePlateau() {

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(new Rover(
                        plateau, "Zetron",
                        1, plateau.getX() + 1,
                        "N")));

        assertThrows(IllegalArgumentException.class, () ->
                roverNavigationService.addRover(new Rover(
                        plateau, "Sirius",
                        plateau.getX() + 1, plateau.getX() + 1,
                        "N")));

    }


    @Test
    public void checkRoversNotColliding() {

    }

}
