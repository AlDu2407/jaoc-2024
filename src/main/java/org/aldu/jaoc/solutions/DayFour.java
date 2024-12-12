package org.aldu.jaoc.solutions;

import java.util.List;

import org.aldu.jaoc.utils.Direction;
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
          if (checkXmasWord(row, col, dir, grid)) {
            result++;
          }
        }
      }
    }

    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    var grid = FileUtils.readInput(getInputFileName());
    var result = 0;
    var rowCount = grid.size();
    var colCount = grid.getFirst().length();
    for (int row = 1; row < rowCount - 1; row++) {
      var line = grid.get(row);
      for (int col = 1; col < colCount - 1; col++) {
        if (line.charAt(col) != 'A') {
          continue;
        }

        if (checkDiagonal(row, col, Direction.UP_LEFT, Direction.DOWN_RIGHT, grid)
            && checkDiagonal(row, col, Direction.UP_RIGHT, Direction.DOWN_LEFT, grid)) {
          result++;
        }
      }
    }

    printResult(Task.ONE, result);
  }

  private boolean checkXmasWord(int row, int col, Direction dir, List<String> grid) {
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

  private boolean checkDiagonal(
      int row, int col, Direction first, Direction second, List<String> grid) {
    int x1, x2, y1, y2;

    // First Diagonal
    x1 = col + first.xOffset;
    y1 = row + first.yOffset;
    x2 = col + second.xOffset;
    y2 = row + second.yOffset;
    var firstChar = grid.get(y1).charAt(x1);
    var secondChar = grid.get(y2).charAt(x2);
    return (firstChar == 'M' && secondChar == 'S') || (firstChar == 'S' && secondChar == 'M');
  }
}
