package maze;

/**
 * DisjointSet class is used to determine whether a wall should be removed or not.
 */
public class DisjointSet {
  private int[] parent;
  private int[] rank;
  private int size;

  public DisjointSet(int length) {
    this.parent = new int[length];
    this.rank = new int[length];
    this.size = length;
    for (int i = 0; i < parent.length; i++) {
      parent[i] = i;
    }
    for (int i = 0; i < rank.length; i++) {
      rank[i] = 1;
    }
  }

  public void union(int x, int y) {
    if (x >= size || y>= size) {
      throw new IndexOutOfBoundsException("Invalid input!");
    }
    int rootX = find(x);
    int rootY = find(y);
    int rankX = rank[rootX];
    int rankY = rank[rootY];
    if (rankX < rankY) {
      parent[rootX] = rootY;

    } else {
      if (rankX == rankY) {
        rank[rootX]++;
      }
      parent[rootY] = rootX;
    }
  }

  public int find(int x) {
    if (x >= size) {
      throw new IndexOutOfBoundsException("Invalid input!");
    }
    if (parent[x] == x) {
      return parent[x];
    }
    //compress path
    parent[x] = find(parent[x]);
    return parent[x];
  }

  public boolean isConnected(int x, int y) {
    if (x >= size || y>= size) {
      throw new IndexOutOfBoundsException("Invalid input!");
    }
    if (find(x) != find(y)) {
      return false;
    }
    return true;
  }

  public int[] getParent() {
    return this.parent;
  }
  public int getSize() {
    return this.size;
  }

}
