package org.aldu.jaoc.utils;

import java.util.Objects;

public record PosDir(Vec2 pos, Direction dir) {
  public PosDir calculate() {
    var newPos = pos.calculate(dir);
    return new PosDir(newPos, dir);
  }

  public PosDir rotate90DegRight() {
    return new PosDir(pos, dir.rotate90DegRight());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    PosDir posDir = (PosDir) o;
    return Objects.equals(pos, posDir.pos) && dir == posDir.dir;
  }

  @Override
  public int hashCode() {
    return Objects.hash(pos, dir);
  }
}
