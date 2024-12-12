package org.aldu.jaoc.utils;

import java.util.Objects;

public record Position(Integer x, Integer y) {
  public Position calculate(Direction dir) {
    return new Position(x + dir.xOffset, y + dir.yOffset);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Position position = (Position) o;
    return Objects.equals(x, position.x) && Objects.equals(y, position.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
