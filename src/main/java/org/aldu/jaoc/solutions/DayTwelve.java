package org.aldu.jaoc.solutions;

import java.util.*;
import java.util.function.Function;

import org.aldu.jaoc.utils.Direction;
import org.aldu.jaoc.utils.FileUtils;
import org.aldu.jaoc.utils.GridMap;
import org.aldu.jaoc.utils.Vec2;

public class DayTwelve extends AbstractDay {
  @Override
  protected String getDay() {
    return "day12";
  }

  @Override
  protected void taskOne() {
    var input = FileUtils.readInput(getInputFileName());
    var grid = GridMap.parseGrid(input, Function.identity());
    var regions = new LinkedList<Set<Vec2>>();
    var seen = new HashSet<Vec2>();

    for (var y = 0; y < grid.colCount; y++) {
      for (var x = 0; x < grid.rowCount; x++) {
        var region = findRegion(new Vec2(x, y), seen, grid);
        if (region.isEmpty() || regions.contains(region)) continue;
        regions.add(region);
      }
    }

    var result = 0;
    for (var region : regions) {
      var perimeter = 0;
      for (var pos : region) {
        var posPerimeter = 0;
        for (var dir : Direction.compassDirections()) {
          if (!region.contains(pos.calculate(dir))) posPerimeter++;
        }
        perimeter += posPerimeter;
      }
      result += perimeter * region.size();
    }
    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {}

  private Set<Vec2> findRegion(Vec2 pos, Set<Vec2> seen, GridMap<Character> grid) {
    var region = new HashSet<Vec2>();
    var queue = new LinkedList<Vec2>();
    queue.add(pos);
    var value = grid.at(pos);
    while (!queue.isEmpty()) {
      var current = queue.pop();
      if (seen.contains(current) || region.contains(current)) continue;
      region.add(current);
      for (var neighbour : grid.findNeighbours(current)) {
        if (region.contains(neighbour) || grid.at(neighbour) != value) continue;
        queue.add(neighbour);
      }
    }

    return region;
  }
}
