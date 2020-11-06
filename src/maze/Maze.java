package maze;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

/**
 * Maze interface includes some operations to build a maze.
 */
public interface Maze {
  int getRow();

  int getCol();

  int getRemainingWalls();

  DisjointSet getDS();

  int getNumOfCells();

  Type getType();

  int[][] createGrid();

  List<Cell> constructCellList();

  void assignGoldToCell();

  void assignThiefToCell();

  Map<Integer, Boolean> getGoldMap();

  Map<Integer, Boolean> getThiefMap();

  Map<Integer, Cell> getLabelCellMap();

  Map<Cell, Set<Direction>> getCellDirectionMap();

  void buildAllWalls();

  List<Pair<Integer, Integer>> getWallList();

  List<Cell> getCellList();

  int getNumOfWalls();

  Pair<Integer, Integer> pickRandomWall();

  int getNeighbourX(Pair<Integer, Integer> wall);

  int getNeighbourY(Pair<Integer, Integer> wall);

  void removeWall();

  void generatePathway(Cell x, Cell y);

  void constructPerfectMaze();



}
