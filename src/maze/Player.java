package maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import javafx.util.Pair;

/**
 * Player class mainly used to solve the maze, has several steps:
 * 1. begin from starting point
 * 2. check coins or thief
 * 3. randomly pick direction that current cell support
 * 4. make movement, wrapping maze property is considered
 */
public class Player {
  private int startX;
  private int startY;
  private int goalX;
  private int goalY;
  private int currX;
  private int currY;
  private int startLabel;
  private int goalLabel;
  private int currLabel;
  private int coinCounter;
  private final int COINS = 3;
  private final double ROB_FACTOR = 0.9;
  List<Cell> path;
  Set<List<Cell>> uniquePath;
  private Maze maze;
  PriorityQueue<Pair<Integer, Set<List<Cell>>>> minHeap;


  public Player(int sX, int sY, int gX, int gY, int cX, int cY, Maze maze) {
    if (sX >= maze.getRow() || gX >= maze.getRow() || sY >= maze.getCol() || gY >= maze.getCol()) {
      throw new IllegalArgumentException("Out of maze boundary!");
    }
    startX = sX;
    startY = sY;
    goalX = gX;
    goalY = gY;
    currX = cX;
    currY = cY;
    path = new ArrayList<>();
    uniquePath = new HashSet<>();
    this.maze = maze;

    minHeap = new PriorityQueue<>((x, y) -> x.getKey() - y.getKey());
  }

  public int convertStartLabel() {
    startLabel = startX * maze.getCol() + startY;
    return startLabel;
  }

  public int convertGoalLabel() {
    goalLabel = goalX * maze.getCol() + goalY;
    return goalLabel;
  }

  public int getCurrLabel() {
    return currLabel;
  }

  public int getCoinCounter() {
    return coinCounter;
  }

  public void solveMaze(Maze maze) {
    // start from startLabel
    currLabel = convertStartLabel();
    path.add(maze.getLabelCellMap().get(currLabel));
    // currLabel = startLabel;
    Direction randomDirection;
    Cell currCell;
    while (getCurrLabel() != convertGoalLabel()) {
      currCell = maze.getLabelCellMap().get(currLabel);
      Set<Direction> currCellDirection = maze.getCellDirectionMap().get(currCell);
      // coins check
      coinsCheck(maze);
      // randomly pick a direction, check current cells contains this or not
      do {
        randomDirection = randomEnum(Direction.class);
      }
      while (!currCellDirection.contains(randomDirection));
      switch (randomDirection) {
        case North:
          moveNorth(maze);
          break;
        case South:
          moveSouth(maze);
          break;
        case West:
          moveWest(maze);
          break;
        case East:
          moveEast(maze);
          break;
      }

      // check current cell's direction, randomly pick one


    }
    System.out.println("Congratulation! " +
        "\nThe player solved the maze!" +
        "\nTotal Coins: " + getCoinCounter());
  }

  public void coinsCheck(Maze maze) {
    if (maze.getGoldMap().get(currLabel)) {
      coinCounter += COINS;
      maze.getGoldMap().put(currLabel, false);
      System.out.println("GET COINS! \nCurrent coins: " + coinCounter);
    }
    if (maze.getThiefMap().get(currLabel)) {
      coinCounter *= ROB_FACTOR;
      maze.getThiefMap().put(currLabel, false);
      System.out.println("GET ROBBED! \nCurrent coins: " + coinCounter);
    }
  }

  public void moveNorth(Maze maze) {
    if (maze.getType() == Type.WrappingMaze) {
      if (currLabel >= maze.getCol()) {
        currLabel -= maze.getCol();
      } else {
        currLabel += (maze.getRow() - 1) * maze.getCol();
      }
    } else {
      currLabel -= maze.getCol();
    }

    path.add(maze.getLabelCellMap().get(currLabel));

  }

  public void moveSouth(Maze maze) {
    if (maze.getType() == Type.WrappingMaze) {
      if (currLabel + maze.getCol() <= maze.getRow() * maze.getCol() - 1) {
        currLabel += maze.getCol();
      } else {
        currLabel -= (maze.getRow() - 1) * maze.getCol();
      }
    } else {
      currLabel += maze.getCol();
    }

    path.add(maze.getLabelCellMap().get(currLabel));
  }

  public void moveWest(Maze maze) {
    if (maze.getType() == Type.WrappingMaze) {
      if (currLabel % maze.getCol() == 0) {
        currLabel += maze.getCol() - 1;
      } else {
        currLabel -= 1;
      }
    } else {
      currLabel -= 1;
    }
    path.add(maze.getLabelCellMap().get(currLabel));
  }

  public void moveEast(Maze maze) {
    if (maze.getType() == Type.WrappingMaze) {
      if ((currLabel + 1) % maze.getCol() == 0) {
        currLabel -= maze.getCol() - 1;
      } else {
        currLabel += 1;
      }
    } else {
      currLabel += 1;
    }
    path.add(maze.getLabelCellMap().get(currLabel));
  }
  public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
    Random pick = new Random();
    int x = pick.nextInt(clazz.getEnumConstants().length);
    return clazz.getEnumConstants()[x];
  }

  /**
   * Some thoughts about extra point question:
   * 1. more clarifications are needed:
   *  (1) Whether the player clever enough to know all the positions of gold and thief in advance?
   *  (2) all the routines have the same positions of gold and thief?
   * 2. use minHeap to store a pair, ordered by key: key is # of coins, value is path
   * 3. if the player is clever, avoid all the thief or meet thief at beginning (coin = 0)
   *
   * @param maze Maze
   */
  public void extraPoint(Maze maze) {
    // # of maze solution depend on size and type of maze
    // not figured out yet
    int numOfSolutions = 10000;
    while (numOfSolutions > 0) {
      coinCounter = 0;
      path = new ArrayList<>();
      uniquePath = new HashSet<>();
      solveMaze(maze);
      uniquePath.add(path);
      Pair<Integer, Set<List<Cell>>> pair = new Pair<>(coinCounter, uniquePath);
      minHeap.add(pair);
      if (minHeap.size() > 1) {
        minHeap.poll();
      }
      numOfSolutions--;
      System.out.println("Path is: " + getPath().toString());
      System.out.println(" ");
    }

    System.out.println(minHeap.toString());

  }

  public PriorityQueue<Pair<Integer, Set<List<Cell>>>> getMinHeap() {
    return minHeap;
  }
  public List<Cell> getPath() {
    return path;
  }
}
