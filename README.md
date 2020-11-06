# Maze_Kruskal# Homework 3 -- Mazes
<br>

> This homework is designed and implemented the interfaces/classes to generate perfect mazes, room mazes, and wrapping room mazes in a way that captures their similarities and accurately represents the relevant data. Then uses command line arguments to specify parameters to create a player to solve the specified maze.

Requirement:

One goal location cell

20% of locations (at random) have gold coins in them that the player can pick up

10% of locations (at random) have a thief that takes some of the player's gold coins

The maze can produce the player's location and gold count

The maze can produce the possible moves of the player (North, South, East or West) from their current location

The player can make a move by specifying a direction

The player should be able to collect gold such that

a player "collects" gold by entering a room that has gold which is then removed from the room

a player "loses" 10% of their gold by entering a room with a thief




## Implements

* Maze.java is an interface that declares methods of construction of maze.

* MazeBuilder is an abstract class that defines method implementations declared by interface, and construct the perfect maze.
/**
 * MazeBuilder class implements Maze interface.
 * MazeBuilder mainly covers the following functions:
 * 1. construct all walls at first
 * 2. randomly choose a wall, then use disjoint set to determine whether to remove it or not
 * 3. construct perfect maze as basis for room maze and wrapping maze
 * 4. construct the cell map with directions
 * 5. construct goldMap and thiefMap
 */

* PerfectMaze, RoomMaze and WrappingMaze are three concret classes extend MazeBuilder to construct maze. 

 * Player class mainly used to solve the maze, has several steps:
 * 1. begin from starting point
 * 2. check coins or thief
 * 3. randomly pick direction that current cell support
 * 4. make movement, wrapping maze property is considered.

 * SolveMaze is the driver class to use command line arguments to:
 * specify the size of the maze
 * specify whether the maze is perfect or a room maze, and whether it is wrapping or non-wrapping
 * if it is non-perfect, specify the number of walls remaining
 * specify the starting point of the player
 * specify the goal location
 */

<br>

## Test
Specify the row: 
4
Specify the column: 
6
Specify the type: 
RoomMaze
Specify the specified number of remaining walls: 
8
Specify the start X coordinate: 
0
Specify the start Y coordinate: 
0
Specify the goal X coordinate: 
3
Specify the goal Y coordinate: 
5
Number of cells contain gold is: 4
Number of cells contain thief is: 2
Randomly pick the wall between: 17 23
Successfully remove the wall between: 17 23
Randomly pick the wall between: 19 20
Successfully remove the wall between: 19 20
Randomly pick the wall between: 14 20
Successfully remove the wall between: 14 20
Randomly pick the wall between: 8 9
Successfully remove the wall between: 8 9
Randomly pick the wall between: 0 1
Successfully remove the wall between: 0 1
Randomly pick the wall between: 4 10
Successfully remove the wall between: 4 10
Randomly pick the wall between: 7 8
Successfully remove the wall between: 7 8
Randomly pick the wall between: 11 17
Successfully remove the wall between: 11 17
Randomly pick the wall between: 12 18
Successfully remove the wall between: 12 18
Randomly pick the wall between: 3 9
Successfully remove the wall between: 3 9
Randomly pick the wall between: 22 23
Successfully remove the wall between: 22 23
Randomly pick the wall between: 4 5
Successfully remove the wall between: 4 5
Randomly pick the wall between: 2 3
Successfully remove the wall between: 2 3
Randomly pick the wall between: 18 19
Successfully remove the wall between: 18 19
Randomly pick the wall between: 1 7
Successfully remove the wall between: 1 7
Randomly pick the wall between: 6 7
Successfully remove the wall between: 6 7
Randomly pick the wall between: 13 19
Successfully remove the wall between: 13 19
Randomly pick the wall between: 5 11
Successfully remove the wall between: 5 11
Randomly pick the wall between: 10 16
Successfully remove the wall between: 10 16
Randomly pick the wall between: 8 14
Successfully remove the wall between: 8 14
Randomly pick the wall between: 16 22
Keep wall between: 16 22
Randomly pick the wall between: 14 15
Successfully remove the wall between: 14 15
Randomly pick the wall between: 16 22
Keep wall between: 16 22
Randomly pick the wall between: 0 6
Keep wall between: 0 6
Randomly pick the wall between: 15 21
Successfully remove the wall between: 15 21
Randomly pick the wall between: 2 8
Keep wall between: 2 8
Randomly pick the wall between: 21 22
Successfully remove the wall between: 21 22
Perfect Maze construction is done. 
Now the number of Remaining walls: 15
Continue to construct Room Maze...
Remove wall between: 9 10
Remove wall between: 3 4
Remove wall between: 1 2
Remove wall between: 0 6
Remove wall between: 7 13
Remove wall between: 10 11
Remove wall between: 15 16
Room Construction is done. 
Now the number of remaining walls are: 8
GET COINS! 
Current coins: 5
GET COINS! 
Current coins: 10
GET ROBBED! 
Current coins: 9
GET ROBBED! 
Current coins: 8
GET COINS! 
Current coins: 13
GET COINS! 
Current coins: 18
Congratulation! 
The player solved the maze!
The total number of collected coins is: 18

Process finished with exit code 0


