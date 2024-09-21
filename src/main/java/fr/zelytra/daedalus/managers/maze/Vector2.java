package fr.zelytra.daedalus.managers.maze;

public class Vector2 {
  public int x;
  public int z;

  public Vector2(int x, int z) {
    this.x = x;
    this.z = z;
  }

  public Vector2() {}

  public void add(Vector2 v) {
    this.x += v.x;
    this.z += v.z;
  }
}
