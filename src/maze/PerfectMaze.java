package maze;

public class PerfectMaze extends MazeBuilder {


  public PerfectMaze(int r, int c) {
    super(r, c);

  }
  public Type getType() {
    return Type.PerfectMaze;
  }

  public void perfectMaze() {
    constructPerfectMaze();
  }

}
