package maze;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RoomMazeTest {
  private RoomMaze roomMazeTest;

  @Before
  public void setUp() throws Exception {
    roomMazeTest = new RoomMaze(4,6,10);
  }

  @Test
  public void constructRoomMazeTest() {
    roomMazeTest.constructRoomMaze();
    System.out.println("Remaining walls are: " + roomMazeTest.getWallList());
    System.out.println("Cells with directions map: " + roomMazeTest.getCellDirectionMap().toString());
  }

  @Test
  public void removeWallTest() {
    roomMazeTest.removeWall();
    System.out.println(roomMazeTest.getWallList().toString());
    System.out.println(roomMazeTest.getLabelCellMap().toString());
    System.out.println(roomMazeTest.getCellDirectionMap().toString());
  }
}