package maze;

import org.junit.Before;
import org.junit.Test;

public class WrappingMazeTest {
  private WrappingMaze wrappingMazeTest;

  @Before
  public void setUp() throws Exception {
    wrappingMazeTest = new WrappingMaze(4,6,10);
  }

  @Test
  public void constructWrappingMazeTest() {
    wrappingMazeTest.constructWrappingMaze();
    System.out.println("Remaining walls are: " + wrappingMazeTest.getWallList());
    System.out.println("Cells with directions map: " + wrappingMazeTest.getCellDirectionMap().toString());
    System.out.println("GoldMap" + wrappingMazeTest.getGoldMap().get(1).toString());
  }
}