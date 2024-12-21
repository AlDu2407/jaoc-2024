package org.aldu.jaoc.solutions;

import org.aldu.jaoc.utils.Direction;
import org.aldu.jaoc.utils.FileUtils;
import org.aldu.jaoc.utils.Vec2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class DayTen extends AbstractDay {
  private static class HeightMap {
    private int rowCount, colCount;
    private int[][] heights;

    HeightMap(List<String> lines) {
      rowCount = lines.size();
      colCount = lines.getFirst().length();
      heights = new int[rowCount][colCount];
      for (int y = 0; y < rowCount; y++) {
        for (int x = 0; x < colCount; x++) {
          heights[y][x] = Integer.parseInt(String.valueOf(lines.get(y).charAt(x)));
        }
      }
    }

    int at(Vec2 point) {
      return heights[point.y()][point.x()];
    }

    List<Vec2> findNeighbours(Vec2 point) {
      return Direction.compassDirections().stream()
          .map(point::calculate)
          .filter(p -> p.withinBoundaries(rowCount, colCount))
          .toList();
    }

    List<Vec2> followTrail(Vec2 point) {
      var height = at(point);
      return findNeighbours(point).stream()
          .filter(neighbour -> at(neighbour) == height + 1)
          .toList();
    }

    List<List<Vec2>> findTrails(Vec2 point) {
      var result = new ArrayList<List<Vec2>>();
      result.add(List.of(point));
      for (int i = 0; i < 9; i++) {
        var temp = new ArrayList<List<Vec2>>();
        for (var trail : result) {
          var followUp = followTrail(trail.getLast());
          if (!followUp.isEmpty()) {
            followUp.stream()
                .map(
                    nextStep -> {
                      var newTrail = new ArrayList<Vec2>(trail);
                      newTrail.add(nextStep);
                      return newTrail;
                    })
                .forEach(temp::add);
          }
        }
        result = temp;
      }

      return result;
    }

    int trailheadScore(Vec2 point) {
      var paths = findTrails(point);
      return paths.stream().map(List::getLast).collect(Collectors.toSet()).size();
    }

    int totalTrailheadScore() {
      return calculateTotal(this::trailheadScore);
    }

    int distinctTrailheadScore(Vec2 point) {
      return findTrails(point).size();
    }

    int totalDistinctTrailheadScore() {
      return calculateTotal(this::distinctTrailheadScore);
    }

    int calculateTotal(Function<Vec2, Integer> scoreFunc) {
      var result = 0;
      for (int y = 0; y < rowCount; y++) {
        for (int x = 0; x < colCount; x++) {
          var pos = new Vec2(y, x);
          if (at(pos) == 0) {
            result += scoreFunc.apply(pos);
          }
        }
      }
      return result;
    }
  }

  @Override
  protected String getDay() {
    return "day10";
  }

  @Override
  protected void taskOne() {
    var grid = FileUtils.readInput(getInputFileName());
    var heightMap = new HeightMap(grid);

    var result = heightMap.totalTrailheadScore();
    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    var grid = FileUtils.readInput(getInputFileName());
    var heightMap = new HeightMap(grid);

    var result = heightMap.totalDistinctTrailheadScore();
    printResult(Task.TWO, result);
  }
}
