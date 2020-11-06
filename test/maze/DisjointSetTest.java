package maze;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DisjointSetTest {
  private DisjointSet testDS;

  @Before
  public void setUp() throws Exception {
    testDS = new DisjointSet(6);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void unionInvalidTest() {
    testDS.union(5, 8);
  }

  @Test
  public void find() {
    testDS.union(2, 3);
    testDS.union(1, 2);
    assertEquals(4, testDS.find(4));
    assertEquals(2,testDS.find(3));
    assertEquals(2,testDS.find(1));
  }

  @Test
  public void isConnected() {
    assertEquals(false, testDS.isConnected(2, 4));
    testDS.union(2, 4);
    assertEquals(true, testDS.isConnected(2, 4));
  }

  @Test
  public void getSize() {
    assertEquals(6, testDS.getSize());
  }
}