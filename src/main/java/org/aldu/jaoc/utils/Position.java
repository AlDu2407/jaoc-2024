package org.aldu.jaoc.utils;

public record Position(Integer x, Integer y) {
  public Position calculate(Direction dir) {
    return new Position(x + dir.xOffset, y + dir.yOffset);
  }
}
