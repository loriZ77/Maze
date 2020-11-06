package maze;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PerfectMazeTest {
  private PerfectMaze perfectTest;

  @Before
  public void setUp() throws Exception {
    perfectTest = new PerfectMaze(4, 6);
  }

  @Test
  public void constructPerfectMazeTest() {
    perfectTest.perfectMaze();
    System.out.println("Remaining walls are: " + perfectTest.getWallList());
    System.out.println("Cells with directions map: " + perfectTest.getCellDirectionMap().toString());
  }


}