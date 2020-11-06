package maze;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * In WrappingMaze class, constructCellList() method is overridden,
 * because in wrapping maze, the cell at the border has direction to the other border.
 */
public class WrappingMaze extends RoomMaze {
  public WrappingMaze(int r, int c, int numOfWalls) {
    super(r, c, numOfWalls);
  }

  public Type getType() {
    return Type.WrappingMaze;
  }

  public List<Cell> constructCellList() {
    Map<Integer, Cell> lCellMap = getLabelCellMap();
    Map<Cell, Set<Direction>> cDirectionMap = getCellDirectionMap();
    // construct cellsList by wrapping property
    for (int i = 0; i < getRow(); i++) {
      for (int j = 0; j < getCol(); j++) {
        Cell newCell = new Cell(i, j);
        newCell.setLabel(i * getCol() + j);
        lCellMap.put(newCell.getLabel(), newCell);
        cDirectionMap.put(newCell, new HashSet<>());
        if (i == 0) {
          cDirectionMap.get(newCell).add(Direction.North);
        }
        if (i == getRow() - 1) {
          cDirectionMap.get(newCell).add(Direction.South);
        }
        if (j == 0) {
          cDirectionMap.get(newCell).add(Direction.West);
        }
        if (j == getCol() - 1) {
          cDirectionMap.get(newCell).add(Direction.East);
        }
        getCellList().add(newCell);
      }
    }
    return getCellList();

  }

  public void constructWrappingMaze() {
    constructRoomMaze();
    System.out.println("Specifically, this is a wrapping maze.");
  }
}
