package org.aldu.jaoc.solutions;

import org.aldu.jaoc.utils.Direction;
import org.aldu.jaoc.utils.FileUtils;
import org.aldu.jaoc.utils.Position;

import java.util.HashSet;

public class DaySix extends AbstractDay {
  @Override
  protected String getDay() {
    return "day06";
  }

  @Override
  protected void taskOne() {
    var lines = FileUtils.readInput(getInputFileName());
    final var rowCount = lines.size();
    final var colCount = lines.getFirst().length();

    var obsticles = new HashSet<Position>();
    var visited = new HashSet<Position>();
    var pos = new Position(0, 0);

    for (var row = 0; row < rowCount; row++) {
      var line = lines.get(row);
      for (var col = 0; col < colCount; col++) {
        switch (line.charAt(col)) {
          case '#' -> obsticles.add(new Position(col, row));
          case '^' -> {
            pos = new Position(col, row);
            visited.add(pos);
          }
          default -> {}
        }
      }
    }

    var direction = Direction.UP;
    while (isInside(pos, rowCount, colCount)) {
      visited.add(pos);
      var updatedPos = pos.calculate(direction);
      if (obsticles.contains(updatedPos)) {
        direction = direction.rotate90DegRight();
        continue;
      }

      pos = updatedPos;
    }

    printResult(Task.ONE, visited.size());
  }

  @Override
  protected void taskTwo() {}

  private boolean isInside(Position pos, int rowCount, int colCount) {
    return pos.x() >= 0 && pos.y() >= 0 && pos.x() < colCount && pos.y() < rowCount;
  }
}
