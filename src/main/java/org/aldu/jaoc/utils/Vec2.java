package org.aldu.jaoc.utils;

import java.util.Objects;

public record Vec2(Integer x, Integer y) {
  public Vec2 calculate(Direction dir) {
    return new Vec2(x + dir.xOffset, y + dir.yOffset);
  }

  public Vec2 subtract(Vec2 other) {
    if (other == null) return this;
    return new Vec2(x - other.x, y - other.y);
  }

  public Vec2 add(Vec2 other) {
    if (other == null) return this;
    return new Vec2(x + other.x, y + other.y);
  }

  public Vec2 flip() {
    return new Vec2(x * (-1), y * (-1));
  }

  public boolean withinBoundaries(int rowCount, int colCount) {
    return x >= 0 && y >= 0 && x < colCount && y < rowCount;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Vec2 vec2 = (Vec2) o;
    return Objects.equals(x, vec2.x) && Objects.equals(y, vec2.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
