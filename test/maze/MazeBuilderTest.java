package maze;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

public class MazeBuilderTest {
  private MazeBuilder buildTest;
  private int row;
  private int column;

  @Before
  public void setUp() throws Exception {
    buildTest = new MazeBuilder(4, 6);
    row = 4;
    column = 6;


  }

  @Test
  public void getRow() {
  }

  @Test
  public void getCol() {
  }

  @Test
  public void getRemainingWallsTest() {
    buildTest.buildAllWalls();
    assertEquals(38, buildTest.getRemainingWalls());
    buildTest.removeWall();
    buildTest.removeWall();
    buildTest.removeWall();
    assertEquals(35, buildTest.getRemainingWalls());
  }

  @Test
  public void getDSTest() {
    int[] parent = buildTest.getDS().getParent();
    for (int i : parent) {
      System.out.print(i + " ");
    }
  }

  @Test
  public void assignLabelToCell() {
  }

  @Test
  public void getLabel() {
  }

  @Test
  public void createGridTest() {
    int[][] grid = buildTest.createGrid();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
  }

  @Test
  public void getLabelCellMapTest() {
    Map<Integer, Cell> labelCellMap = buildTest.getLabelCellMap();
    System.out.println(labelCellMap.toString());
  }

  @Test
  public void getCellDirectionMapTest() {
    Map<Cell, Set<Direction>> cellDirectionMap = buildTest.getCellDirectionMap();
    System.out.println(cellDirectionMap.toString());
  }

  @Test
  public void buildAllWallsTest() {
    buildTest.buildAllWalls();
    System.out.println(buildTest.getWallList().toString());

  }

  @Test
  public void getNumOfWallsTest() {
    buildTest.buildAllWalls();
    assertEquals(38, buildTest.getNumOfWalls());
  }

  @Test
  public void pickRandomWallTest() {
    buildTest.buildAllWalls();
    Pair<Integer, Integer> wall1 = buildTest.pickRandomWall();
    Pair<Integer, Integer> wall2 = buildTest.pickRandomWall();
    Pair<Integer, Integer> wall3 = buildTest.pickRandomWall();
    System.out.println(wall1.toString());
    System.out.println(wall2.toString());
    System.out.println(wall3.toString());

  }

  @Test
  public void removeWallTest() {
    while (buildTest.getRemainingWalls() > 15) {
      buildTest.removeWall();
    }
//    System.out.println(buildTest.getWallList().toString());
//    System.out.println(buildTest.getLabelCellMap().toString());
//    System.out.println(buildTest.getCellDirectionMap().toString());

  }

  @Test
  public void assignGoldToCellTest() {
    System.out.println(buildTest.getGoldMap());
  }

  @Test
  public void assignThiefToCellTest() {
    System.out.println(buildTest.getThiefMap());
  }

  @Test
  public void perfectMazeTest1() {
    buildTest.buildAllWalls();
    while (buildTest.getRemainingWalls() > 15) {
      buildTest.removeWall();
    }
    assertEquals(15, buildTest.getNumOfWalls());
    System.out.println(buildTest.getWallList().toString());
    System.out.println(buildTest.getLabelCellMap().toString());
    System.out.println(buildTest.getCellDirectionMap().toString());

  }

  @Test
  public void perfectMazeTest2() {
    buildTest.buildAllWalls();
    while (buildTest.getRemainingWalls() > 15) {
      buildTest.removeWall();
    }
    assertEquals(15, buildTest.getNumOfWalls());
    System.out.println(buildTest.getWallList().toString());
    System.out.println(buildTest.getLabelCellMap().toString());
    System.out.println(buildTest.getCellDirectionMap().toString());

  }
}