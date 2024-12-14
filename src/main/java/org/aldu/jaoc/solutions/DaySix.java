package org.aldu.jaoc.solutions;

import org.aldu.jaoc.utils.Direction;
import org.aldu.jaoc.utils.FileUtils;
import org.aldu.jaoc.utils.PosDir;
import org.aldu.jaoc.utils.Vec2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    var obstacles = new HashSet<Vec2>();
    var pos = parseGrid(lines, rowCount, colCount, obstacles);

    var result = executeCourse(pos, rowCount, colCount, obstacles);
    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    var lines = FileUtils.readInput(getInputFileName());
    final var rowCount = lines.size();
    final var colCount = lines.getFirst().length();
    var obstacles = new HashSet<Vec2>();
    var pos = parseGrid(lines, rowCount, colCount, obstacles);

    var result = calculateLoops(pos, rowCount, colCount, obstacles);

    printResult(Task.TWO, result);
  }

  private Vec2 parseGrid(
      List<String> lines, int rowCount, int colCount, Set<Vec2> obstacles) {
    var pos = new Vec2(0, 0);
    for (var row = 0; row < rowCount; row++) {
      var line = lines.get(row);
      for (var col = 0; col < colCount; col++) {
        switch (line.charAt(col)) {
          case '#' -> obstacles.add(new Vec2(col, row));
          case '^' -> {
            pos = new Vec2(col, row);
          }
          default -> {}
        }
      }
    }
    return pos;
  }

  private int executeCourse(Vec2 pos, int rowCount, int colCount, Set<Vec2> obstacles) {
    var visited = new HashSet<Vec2>();
    var direction = Direction.UP;
    while (isInside(pos, rowCount, colCount)) {
      visited.add(pos);
      var updatedPos = pos.calculate(direction);
      if (obstacles.contains(updatedPos)) {
        direction = direction.rotate90DegRight();
        continue;
      }

      pos = updatedPos;
    }
    return visited.size();
  }

  private boolean isInfiniteLoop(
          Vec2 pos, int rowCount, int colCount, Set<Vec2> obstacles) {
    var visitedPosDirections = new HashSet<PosDir>();
    var currentPosDir = new PosDir(pos, Direction.UP);
    while (isInside(currentPosDir.pos(), rowCount, colCount)) {
      visitedPosDirections.add(currentPosDir);
      var updatedPosDir = currentPosDir.calculate();
      if (obstacles.contains(updatedPosDir.pos())) {
        currentPosDir = currentPosDir.rotate90DegRight();
        continue;
      }

      if (visitedPosDirections.contains(updatedPosDir)) {
        return true;
      }
      currentPosDir = updatedPosDir;
    }
    return false;
  }

  private boolean isInside(Vec2 pos, int rowCount, int colCount) {
    return pos.x() >= 0 && pos.y() >= 0 && pos.x() < colCount && pos.y() < rowCount;
  }

  private int calculateLoops(Vec2 pos, int rowCount, int colCount, Set<Vec2> obstacles) {
    var result = 0;
    for (var row = 0; row < rowCount; row++) {
      for (var col = 0; col < colCount; col++) {
        var obstacle = new Vec2(col, row);
        if (obstacles.contains(obstacle)) {
          continue;
        }
        var updatedObstacles = new HashSet<>(obstacles);
        updatedObstacles.add(obstacle);
        if (isInfiniteLoop(pos, rowCount, colCount, updatedObstacles)) {
          result++;
        }
      }
    }
    return result;
  }
}
