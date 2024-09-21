package fr.zelytra.daedalus.builders.customCraft;

public class CraftPattern {
  /**
   * Minecraft craft pattern finder by Nicolas61x
   *
   * <p>Never use '*' as an ingredient key Always put the shape in the upper left side of the shape
   */
  public char[][] pattern;

  public int Width() {
    return getLength(1);
  }

  public int Height() {
    return getLength(0);
  }

  public CraftPattern() {
    this.pattern = new char[3][3];

    for (int y = 0; y < pattern.length; y++) {
      for (int x = 0; x < pattern.length; x++) {
        this.pattern[x][y] = '*';
      }
    }
  }

  public CraftPattern(String[] pattern) {
    int SizeX = 0;
    int SizeY = pattern.length;
    for (int i = 0; i < pattern.length; i++)
      if (pattern[i].length() > SizeX) SizeX = pattern[i].length();

    this.pattern = new char[SizeX][SizeY];

    for (int y = 0; y < this.pattern.length; y++) {
      for (int x = 0; x < this.pattern[0].length; x++) {
        this.pattern[x][y] = '*';
      }
    }

    for (int i = 0; i < pattern.length; i++) {
      char[] ligne = pattern[i].toCharArray();

      for (int x = 0; x < ligne.length; x++) {
        this.pattern[x][i] = ligne[x];
      }
    }
  }

  public CraftPattern(char[][] pattern, int OX, int OY) {
    this.pattern = new char[pattern.length][pattern[0].length];

    for (int y = 0; y < pattern.length; y++) {
      for (int x = 0; x < pattern[0].length; x++) {
        this.pattern[x][y] = '*';
      }
    }

    for (int y = 0; y < pattern.length - OY; y++) {
      for (int x = 0; x < pattern[0].length - OX; x++) {
        this.pattern[x + OX][y + OY] = pattern[x][y];
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    for (int y = 0; y < pattern.length; y++) {
      for (int x = 0; x < pattern[0].length; x++) {
        res.append(pattern[x][y]);
      }
      res.append('\n');
    }

    return res.toString();
  }

  public int getLength(int dim) // dim = 0 for height 1 for Width
      {
    int min = dim == 1 ? pattern[0].length : pattern.length;
    int max = 0;

    for (int y = 0; y < (dim == 1 ? pattern.length : pattern[0].length); y++)
      for (int x = 0; x < pattern.length; x++) {
        if (pattern[x * dim + y * ((dim + 1) % 2)][x * ((dim + 1) % 2) + y * dim] != '*') {
          if (min > x) {
            min = x;
          }

          if (max < x) {
            max = x;
          }
        }
      }

    if (min > max) // no pattern
    return -1;

    return max - min + 1;
  }

  public CraftPattern[] getAllPossibilities() {
    int MaxCraftSize = 3;
    if (Width() > MaxCraftSize || Height() > MaxCraftSize) return new CraftPattern[0];

    int maxXOffset = MaxCraftSize - Width();
    int maxYOffset = MaxCraftSize - Height();

    CraftPattern[] res = new CraftPattern[(maxXOffset + 1) * (maxYOffset + 1)];

    for (int y = 0; y <= maxYOffset; y++) {
      for (int x = 0; x <= maxXOffset; x++) {
        res[x + y * (maxXOffset + 1)] = new CraftPattern(pattern, x, y);
      }
    }

    return res;
  }

  public String[] getShapeArray() {
    String[] shape = new String[this.pattern.length];
    for (int y = 0; y < this.pattern.length; y++) {
      StringBuilder line = new StringBuilder();
      for (int x = 0; x < this.pattern[0].length; x++) {
        line.append(this.pattern[x][y]);
      }
      shape[y] = line.toString();
    }
    return shape;
  }
}
