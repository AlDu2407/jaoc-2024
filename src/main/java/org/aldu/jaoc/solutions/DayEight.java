package org.aldu.jaoc.solutions;

import java.util.*;
import org.aldu.jaoc.utils.FileUtils;
import org.aldu.jaoc.utils.Vec2;

public class DayEight extends AbstractDay {
  @Override
  protected String getDay() {
    return "day08";
  }

  @Override
  protected void taskOne() {
    var grid = FileUtils.readInput(getInputFileName());
    var rowCount = grid.size();
    var colCount = grid.getFirst().length();
    var antennaMap = parseGrid(rowCount, colCount, grid);

    var antinodesSet = new HashSet<Vec2>();
    antennaMap
        .values()
        .forEach(
            antennaPositions -> {
              for (var i = 0; i < antennaPositions.size() - 1; i++) {
                for (var j = i + 1; j < antennaPositions.size(); j++) {
                  var firstPos = antennaPositions.get(i);
                  var secondPos = antennaPositions.get(j);
                  var diff = firstPos.subtract(secondPos);
                  var firstAntinodePos = firstPos.add(diff);
                  if (firstAntinodePos.withinBoundaries(rowCount, colCount)) {
                    antinodesSet.add(firstAntinodePos);
                  }
                  var secondAntinodePos = secondPos.add(diff.flip());
                  if (secondAntinodePos.withinBoundaries(rowCount, colCount)) {
                    antinodesSet.add(secondAntinodePos);
                  }
                }
              }
            });

    printResult(Task.ONE, antinodesSet.size());
  }

  @Override
  protected void taskTwo() {
    var grid = FileUtils.readInput(getInputFileName());
    var rowCount = grid.size();
    var colCount = grid.getFirst().length();
    var antennaMap = parseGrid(rowCount, colCount, grid);

    var antinodesSet = new HashSet<Vec2>();
    antennaMap
        .values()
        .forEach(
            antennaPositions -> {
              for (var i = 0; i < antennaPositions.size() - 1; i++) {
                for (var j = i + 1; j < antennaPositions.size(); j++) {
                  var firstPos = antennaPositions.get(i);
                  var secondPos = antennaPositions.get(j);
                  antinodesSet.add(firstPos);
                  antinodesSet.add(secondPos);
                  var diffVector = firstPos.subtract(secondPos);
                  var firstAntinodePos = firstPos.add(diffVector);
                  while (firstAntinodePos.withinBoundaries(rowCount, colCount)) {
                    antinodesSet.add(firstAntinodePos);
                    firstAntinodePos = firstAntinodePos.add(diffVector);
                  }
                  var flippedDiffVector = diffVector.flip();
                  var secondAntinodePos = secondPos.add(flippedDiffVector);
                  while (secondAntinodePos.withinBoundaries(rowCount, colCount)) {
                    antinodesSet.add(secondAntinodePos);
                    secondAntinodePos = secondAntinodePos.add(flippedDiffVector);
                  }
                }
              }
            });

    printResult(Task.TWO, antinodesSet.size());
  }

  private static HashMap<Character, List<Vec2>> parseGrid(
      int rowCount, int colCount, List<String> grid) {
    var antennaMap = new HashMap<Character, List<Vec2>>();
    for (var row = 0; row < rowCount; row++) {
      for (var col = 0; col < colCount; col++) {
        var val = grid.get(row).charAt(col);
        if (val == '.') {
          continue;
        }

        var positions = antennaMap.getOrDefault(val, new ArrayList<>());
        positions.add(new Vec2(col, row));
        antennaMap.put(val, positions);
      }
    }
    return antennaMap;
  }
}
