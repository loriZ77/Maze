package maze;

import javafx.util.Pair;

/**
 *
 */
public class RoomMaze extends MazeBuilder{
  private int specifiedNumOfWalls;
  private int numOfRemainingWalls;

  public RoomMaze(int r, int c, int numOfWalls) {
    super(r, c);
    this.specifiedNumOfWalls = numOfWalls;
    numOfRemainingWalls = getRemainingWalls();

  }
  public Type getType() {
    return Type.RoomMaze;
  }

  public void constructRoomMaze() {
    // firstly construct a perfect maze to ensure there must be a solution
    constructPerfectMaze();
    System.out.println("Continue to construct Room Maze...");
    // then keep removing the wall till to the specified number of remaining walls, without using disjoint set.
    numOfRemainingWalls = getRemainingWalls();
    while (numOfRemainingWalls > specifiedNumOfWalls) {
      removeRoomWall();
    }
    System.out.println("Room Construction is done. \nNow the number of remaining walls are: " + numOfRemainingWalls);
  }

  public void removeRoomWall() {
    // randomly pick a wall
    Pair<Integer, Integer> randomWall = pickRandomWall();

    // get two neighbours' labels
    int neighbourX = getNeighbourX(randomWall);
    int neighbourY = getNeighbourY(randomWall);

    // get these two cells through look-up in map
    Cell neiX = this.getLabelCellMap().get(neighbourX);
    Cell neiY = this.getLabelCellMap().get(neighbourY);
    System.out.println("Remove wall between: " + neiX + " " + neiY);
    getWallList().remove(randomWall);
    // connect cells
    generatePathway(neiX,neiY);
    numOfRemainingWalls--;
    }
}
