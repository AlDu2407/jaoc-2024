package org.aldu.jaoc.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GridMap<T> {
  public final int rowCount, colCount;
  private final List<List<T>> grid = new ArrayList<>();

  private GridMap(int rowCount, int colCount) {
    this.rowCount = rowCount;
    this.colCount = colCount;
  }

  public T at(Vec2 pos) {
    return grid.get(pos.y()).get(pos.x());
  }

  public List<Vec2> findNeighbours(Vec2 point) {
    return Direction.compassDirections().stream()
        .map(point::calculate)
        .filter(p -> p.withinBoundaries(rowCount, colCount))
        .toList();
  }

  public static <T> GridMap<T> parseGrid(List<String> lines, Function<Character, T> mapping) {
    var rowCount = lines.size();
    var colCount = lines.getFirst().length();
    var result = new GridMap<T>(lines.size(), lines.getFirst().length());
    for (var y = 0; y < rowCount; y++) {
      var line = new ArrayList<T>();
      for (var x = 0; x < colCount; x++) {
        line.add(x, mapping.apply(lines.get(y).charAt(x)));
      }

      result.grid.add(y, line);
    }
    return result;
  }
}
