package org.aldu.jaoc.solutions;

import java.util.List;
import org.aldu.jaoc.utils.FileUtils;

public class DayFour extends AbstractDay {
  private static final String XMAS = "XMAS";

  @Override
  protected String getDay() {
    return "day04";
  }

  @Override
  protected void taskOne() {
    var grid = FileUtils.readInput(getInputFileName());
    var result = 0;
    var rowCount = grid.size();
    var colCount = grid.getFirst().length();
    for (int row = 0; row < rowCount; row++) {
      var line = grid.get(row);
      for (int col = 0; col < colCount; col++) {
        if (line.charAt(col) != 'X') {
          continue;
        }

        for (var dir : Direction.values()) {
          if (findWord(row, col, dir, grid)) {
            result++;
          }
        }
      }
    }

    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {}

  private enum Direction {
    DOWN(0, 1),
    UP(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    DOWN_RIGHT(1, 1),
    UP_RIGHT(1, -1),
    DOWN_LEFT(-1, 1),
    UP_LEFT(-1, -1);
    private final int xOffset, yOffset;

    Direction(int xOffset, int yOffset) {
      this.xOffset = xOffset;
      this.yOffset = yOffset;
    }
  }

  private boolean findWord(int row, int col, Direction dir, List<String> grid) {
    for (int i = 0; i < XMAS.length(); i++) {
      var currChar = XMAS.charAt(i);
      var x = col + i * dir.yOffset;
      var y = row + i * dir.xOffset;
      if (y < 0 || y >= grid.size() || x < 0 || x >= grid.getFirst().length()) {
        return false;
      }

      if (currChar != grid.get(y).charAt(x)) {
        return false;
      }
    }

    return true;
  }
}
