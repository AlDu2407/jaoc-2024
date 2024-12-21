package org.aldu.jaoc.utils;

import java.util.EnumSet;

public enum Direction {
  DOWN(0, 1),
  UP(0, -1),
  LEFT(-1, 0),
  RIGHT(1, 0),
  DOWN_RIGHT(1, 1),
  UP_RIGHT(1, -1),
  DOWN_LEFT(-1, 1),
  UP_LEFT(-1, -1);
  public final int xOffset, yOffset;

  Direction(int xOffset, int yOffset) {
    this.xOffset = xOffset;
    this.yOffset = yOffset;
  }

  public Direction rotate90DegRight() {
    return switch (this) {
      case DOWN -> LEFT;
      case UP -> RIGHT;
      case LEFT -> UP;
      case RIGHT -> DOWN;
      case DOWN_RIGHT -> DOWN_LEFT;
      case UP_RIGHT -> DOWN_RIGHT;
      case DOWN_LEFT -> UP_LEFT;
      case UP_LEFT -> UP_RIGHT;
    };
  }

  public static EnumSet<Direction> compassDirections() {
    return EnumSet.of(UP, RIGHT, DOWN, LEFT);
  }
}
