package maze;

import java.util.Scanner;

/**
 * This is driver class to use command line arguments to:
 * specify the size of the maze
 * specify whether the maze is perfect or a room maze, and whether it is wrapping or non-wrapping
 * if it is non-perfect, specify the number of walls remaining
 * specify the starting point of the player
 * specify the goal location
 */
public class SolveMaze {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Specify the row: ");
    int row = in.nextInt();
    System.out.println("Specify the column: ");
    int col = in.nextInt();
    System.out.println("Specify the type: ");
    String typeString = in.next();
    Type type = Type.valueOf(typeString);
    int specifiedRW = 0;
    if (!type.equals(Type.PerfectMaze)) {
      System.out.println("Specify the specified number of remaining walls: ");
      specifiedRW = in.nextInt();
    }
    System.out.println("Specify the start X coordinate: ");
    int startX = in.nextInt();
    System.out.println("Specify the start Y coordinate: ");
    int startY = in.nextInt();
    System.out.println("Specify the goal X coordinate: ");
    int goalX = in.nextInt();
    System.out.println("Specify the goal Y coordinate: ");
    int goalY = in.nextInt();
//    int row = 4;
//    int col = 6;
//    int startX = 0;
//    int startY = 0;
//    int goalX = 3;
//    int goalY = 5;
//    int specifiedRW = 5;
//    Type type = Type.PerfectMaze;
    //Type type = Type.RoomMaze;
    //Type type = Type.WrappingMaze;

    switch (type) {
      case PerfectMaze:
        PerfectMaze pm = new PerfectMaze(row, col);
        pm.constructPerfectMaze();
        Player playerPM = new Player(startX, startY, goalX, goalY, startX, startY, pm);
        System.out.println("Start to solve the maze! \n...........");
        playerPM.solveMaze(pm);
        //playerPM.extraPoint(pm);
        break;

      case RoomMaze:
        RoomMaze rm = new RoomMaze(row, col, specifiedRW);
        rm.constructRoomMaze();
        Player playerRM = new Player(startX, startY, goalX, goalY, startX, startY, rm);
        playerRM.solveMaze(rm);
        break;

      case WrappingMaze:
        WrappingMaze wm = new WrappingMaze(row, col, specifiedRW);
        wm.constructWrappingMaze();
        Player playerWM = new Player(startX, startY, goalX, goalY, startX, startY, wm);
        playerWM.solveMaze(wm);
        break;
    }


  }
}
