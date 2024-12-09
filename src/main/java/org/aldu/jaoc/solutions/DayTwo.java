package org.aldu.jaoc.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.aldu.jaoc.utils.FileUtils;

public class DayTwo extends AbstractDay {
  @Override
  protected String getDay() {
    return "day02";
  }

  @Override
  protected void taskOne() {
    var lines = FileUtils.readInput(getInputFileName());
    var result =
        lines.stream()
            .map(line -> Arrays.stream(line.split(" ")).map(Integer::parseInt).toList())
            .map(this::isSafe)
            .filter(Boolean::booleanValue)
            .count();
    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    var lines = FileUtils.readInput(getInputFileName());
    var result =
        lines.stream()
            .map(line -> Arrays.stream(line.split(" ")).map(Integer::parseInt).toList())
            .map(report -> isSafe(report) || isSafeAdjusted(report))
            .filter(Boolean::booleanValue)
            .count();
    printResult(Task.TWO, result);
  }

  private boolean isSafe(List<Integer> report) {
    var differences = new ArrayList<Integer>(report.size() - 1);
    for (var i = 0; i < report.size() - 1; i++) {
      var diff = report.get(i) - report.get(i + 1);
      differences.add(diff);
    }

    return differences.stream().allMatch(val -> val > 0 && val <= 3)
        || differences.stream().allMatch(val -> val < 0 && val >= -3);
  }

  private boolean isSafeAdjusted(List<Integer> report) {
    for (var i = 0; i < report.size(); i++) {
      var updated = new ArrayList<>(report);
      updated.remove(i);
      if (isSafe(updated)) {
        return true;
      }
    }

    return false;
  }
}
