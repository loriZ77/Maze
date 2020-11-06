package maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javafx.util.Pair;

/**
 * MazeBuilder class implements Maze interface.
 * MazeBuilder mainly covers the following functions:
 * 1. construct all walls at first
 * 2. randomly choose a wall, then use disjoint set to determine whether to remove it or not
 * 3. construct perfect maze as basis for room maze and wrapping maze
 * 4. construct the cell map with directions
 * 5. construct goldMap and thiefMap
 */
public class MazeBuilder implements Maze {
  protected DisjointSet disjointSet;
  private int row;
  private int column;
  private int remainingWalls;
  private int perfectRemainingWall;
  private int[][] grid;
  private Type type;
  private final double GOLDFACTOR = 0.2;
  private final double THIEFFACTOR = 0.1;
  private List<Pair<Integer, Integer>> wallList;
  private List<Cell> cellList;
  private Map<Integer, Cell> labelCellMap;
  private Map<Cell, Set<Direction>> cellDirectionMap;
  private Map<Integer, Boolean> goldMap;
  private Map<Integer, Boolean> thiefMap;


  public MazeBuilder(int r, int c) {
    this.row = r;
    this.column = c;
    wallList = new ArrayList<>();
    cellList = new ArrayList<>();
    perfectRemainingWall = r * (c - 1) + c * (r - 1) - r * c + 1;


    // construct a disjoint set to represent each cell in a separate set at first, bounded by wall
    disjointSet = new DisjointSet(row * column);
    labelCellMap = new HashMap<>();
    cellDirectionMap = new HashMap<>();
    goldMap = new HashMap<>();
    thiefMap = new HashMap<>();

    // construct the grid by assigning label to each cell
    this.grid = new int[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        grid[i][j] = i * column + j;
        goldMap.put(grid[i][j], false);
        thiefMap.put(grid[i][j], false);
      }
    }
    // build all walls
    // pair of Integers is used here to represent two neighbours' label aside the wall
    // number of walls = row * (col - 1) + col * (row - 1)
    int[][] grid = this.createGrid();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column - 1; j++) {
        wallList.add(new Pair<>(grid[i][j], grid[i][j + 1]));
      }
    }
    for (int j = 0; j < column; j++) {
      for (int i = 0; i < row - 1; i++) {
        wallList.add(new Pair<>(grid[i][j], grid[i + 1][j]));
      }
    }
    remainingWalls = wallList.size();
    assignGoldToCell();
    assignThiefToCell();

  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCol() {
    return this.column;
  }

  @Override
  public int getRemainingWalls() {
    return this.remainingWalls;
  }


  @Override
  public DisjointSet getDS() {
    return this.disjointSet;
  }

  @Override
  public int getNumOfCells() {
    return row * column;
  }

  @Override
  public Type getType() {
    return this.type;
  }




  @Override
  public int[][] createGrid() {
    // fulfill the grid by assigning label to each cell
    this.grid = new int[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        grid[i][j] = i * column + j;
      }
    }
    return grid;
  }

  @Override
  public List<Cell> constructCellList() {
    //construct cellsList
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        Cell newCell = new Cell(i, j);
        newCell.setLabel(i * column + j);
        labelCellMap.put(newCell.getLabel(), newCell);
        cellDirectionMap.put(newCell, new HashSet<>());
        cellList.add(newCell);
      }
    }
    return cellList;
  }

  @Override
  public void assignGoldToCell() {
    int numOfGoldCell = (int) (GOLDFACTOR * getNumOfCells());
    System.out.println("Number of cells contain gold is: " + numOfGoldCell);
    while (numOfGoldCell > 0) {
      Random pick = new Random();
      int ranLabel = pick.nextInt(getNumOfCells());
      goldMap.put(ranLabel, true);
      numOfGoldCell--;
    }
  }

  @Override
  public void assignThiefToCell() {
    int numOfThiefCell = (int) (THIEFFACTOR * getNumOfCells());
    System.out.println("Number of cells contain thief is: " + numOfThiefCell);
    while (numOfThiefCell > 0) {
      Random pick = new Random();
      int ranLabel = pick.nextInt(getNumOfCells());
      thiefMap.put(ranLabel, true);
      numOfThiefCell--;
    }
  }

  @Override
  public Map<Integer, Boolean> getGoldMap() {
    return goldMap;
  }

  @Override
  public Map<Integer, Boolean> getThiefMap() {
    return thiefMap;
  }

  @Override
  public Map<Integer, Cell> getLabelCellMap() {
    return labelCellMap;
  }

  @Override
  public Map<Cell, Set<Direction>> getCellDirectionMap() {
    return this.cellDirectionMap;
  }

  @Override
  public void buildAllWalls() {
    // pair of Integers is used here to represent two neighbours' label aside the wall
    // number of walls = row * (col - 1) + col * (row - 1)
    int[][] grid = this.createGrid();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column - 1; j++) {
        wallList.add(new Pair<>(grid[i][j], grid[i][j + 1]));
      }
    }
    for (int j = 0; j < column; j++) {
      for (int i = 0; i < row - 1; i++) {
        wallList.add(new Pair<>(grid[i][j], grid[i + 1][j]));
      }
    }
    this.remainingWalls = wallList.size();
  }

  @Override
  public List<Pair<Integer, Integer>> getWallList() {
    return this.wallList;
  }

  @Override
  public List<Cell> getCellList() {
    return cellList;
  }

  @Override
  public int getNumOfWalls() {
    return wallList.size();
  }

  @Override
  public Pair<Integer, Integer> pickRandomWall() {
    Random pick = new Random();
    int ranWallIndex = pick.nextInt(wallList.size());
    return wallList.get(ranWallIndex);
  }

  @Override
  public int getNeighbourX(Pair<Integer, Integer> wall) {
    return wall.getKey();
  }

  @Override
  public int getNeighbourY(Pair<Integer, Integer> wall) {
    return wall.getValue();
  }

  @Override
  public void removeWall() {
    int[] parent = getDS().getParent();
    // randomly pick a wall
    Pair<Integer, Integer> randomWall = pickRandomWall();
    // get two neighbours' labels
    int neighbourX = getNeighbourX(randomWall);
    int neighbourY = getNeighbourY(randomWall);

    // get these two cells through look-up in map
    Cell neiX = this.getLabelCellMap().get(neighbourX);
    Cell neiY = this.getLabelCellMap().get(neighbourY);
    System.out.println("Randomly pick the wall between: " + neiX + " " + neiY);

    // use disjoint set to determine whether to remove this random wall
    if (!disjointSet.isConnected(parent[neighbourX], parent[neighbourY])) {
      // operations to disjoint set and remove the pair from wallList
      disjointSet.union(parent[neighbourX], parent[neighbourY]);
      getWallList().remove(randomWall);
      System.out.println("Successfully remove the wall between: " + neiX + " " + neiY);
      // connect cells
      generatePathway(neiX, neiY);
      remainingWalls--;

    } else {
      System.out.println("Keep wall between: " + neiX + " " + neiY);
    }

  }

  @Override
  public void generatePathway(Cell cellX, Cell cellY) {
    // two cells on same row: connect West and East
    if (cellX.getRowOfCell() == cellY.getRowOfCell()) {
      getCellDirectionMap().get(cellX).add(Direction.East);
      getCellDirectionMap().get(cellY).add(Direction.West);
    }
    // two cells on same column: connect North and South
    if (cellX.getColOfCell() == cellY.getColOfCell()) {
      getCellDirectionMap().get(cellX).add(Direction.South);
      getCellDirectionMap().get(cellY).add(Direction.North);
    }
  }

  @Override
  public void constructPerfectMaze() {
    constructCellList();
    while (getRemainingWalls() > perfectRemainingWall) {
      removeWall();
    }
    System.out.println("Perfect Maze construction is done. \nNow the number of Remaining walls: " +
        getRemainingWalls());
  }
}
