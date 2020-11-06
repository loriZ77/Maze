package maze;


public class Cell {
  private int r;
  private int c;
  private int label;


  public Cell(int x, int y) {
    this.r = x;
    this.c = y;

  }


  public int getRowOfCell() {
    return r;
  }

  public int getColOfCell() {
    return c;
  }

  public int getLabel() {
    return label;
  }

  public void setLabel(int label) {
    this.label = label;
  }

  public String toString() {
    return String.valueOf(this.label);
  }
}
