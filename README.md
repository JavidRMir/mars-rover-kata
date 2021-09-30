# Mars Rover Kata

## Introduction

A team of professionals is exploring Mars by sending remotely controlled vehicles/rovers to the surface of the planet.

## Problem
The rovers need software to land and navigate safely. The software would take commands from the Mars exploration team on earth and send them to rovers. 
The commands are instructions that are understood by the rover for it to land and navigate on Mars. 

## Solution
Develop a foolproof navigation system that will be in charge of landing, navigating and tracking rovers on the 
surface of Mars.

## Key Components
🧋 Plateau - a representation of Mars surface. The Plateau is divided into a grid. 
            For the purpose of this task, we assume the Plateau is rectangular. 

🧋 Rovers - special purpose vehicles to navigate the Plateau.

🧋 NavigationService - a full scale rover navigation/transport service for landing, navigating and tracking rovers on the Plateau.
 

## Requirements
🧋 Plateau maximum coordinates of x, and y. Example - A Plateau with 5, 5  as (x, y) has maximum coordinates of (5, 5).

🧋 Rover landing/starting point (x, y) of a rover and the direction (N,S,E,W) it is facing.

🧋 Navigation commands - A string of letters representing the instructions to spin/move a rover around the Plateau.


## Navigation Commands
🧋 R - Spins the Rover 90 degrees Right/clockwise without moving from the current coordinate position.

🧋 L - Spins the Rover 90 degrees Left/anticlockwise without moving from the current coordinate position.

🧋 F - Moves the Rover forward by one grid point, maintaining the same heading orientation.

🧋 B - Moves the Rover backward/reverse by one grid point, maintaining the same heading orientation.


## Validations
🧋 Plateau (x, y) coordinates can't be zero or negative.

🧋 Rover (x, y) coordinates can't be negative.

🧋 A rover can't be landed/navigated outside the Plateau.

🧋 A rover can't be added (again) if it already exists in the NavigationService. 

🧋 No two rovers can be landed at the same coordinates.

🧋 Collision check - rovers move sequentially, a rover can't be landed or moved to coordinates where a rover is already positioned.

🧋 A rover can't be navigated/landed unless added to the NavigationService.


## Execution
🧋 Define Plateau size/coordinates by creating an instance of it.

🧋 Create instances of Rover that need to be landed on Mars.

🧋 Create an instance (Singleton) of NavigationService - solely responsible for tracking all the rovers added to it on the Plateau.

🧋 Land these rovers on the Plateau by adding the instances to the NavigationService.

🧋 Receive navigation commands as a string from a user.

🧋 Use navigateRover method of NavigationService to try to move the rover based on the instructions received. However,
  if it can't navigate the rover due to a potential collision, or some other reason the rover will stay at its original position. 






