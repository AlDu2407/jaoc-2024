package org.aldu.jaoc.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.aldu.jaoc.utils.FileUtils;

public class DayOne extends AbstractDay {
  @Override
  protected String getDay() {
    return "day01";
  }

  @Override
  protected void taskOne() {
    var lines = FileUtils.readInput(getInputFileName());
    var first = new ArrayList<Integer>();
    var second = new ArrayList<Integer>();
    lines.stream()
        .map(line -> Arrays.stream(line.split(" {3}")).map(Integer::parseInt).toList())
        .filter(elements -> elements.size() == 2)
        .forEach(
            elements -> {
              first.add(elements.getFirst());
              second.add(elements.getLast());
            });
    first.sort(Integer::compareTo);
    second.sort(Integer::compareTo);

    var result = 0;
    for (var i = 0; i < first.size(); i++) {
      result += Math.abs((first.get(i) - second.get(i)));
    }

    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    var lines = FileUtils.readInput(getInputFileName());
    var first = new ArrayList<Integer>();
    var frequencies = new HashMap<Integer, Integer>();
    lines.stream()
        .map(line -> Arrays.stream(line.split("   ")).map(Integer::parseInt).toList())
        .filter(elements -> elements.size() == 2)
        .forEach(
            elements -> {
              first.add(elements.getFirst());
              var frequency = frequencies.getOrDefault(elements.getLast(), 0);
              frequencies.put(elements.getLast(), frequency + 1);
            });

    var result =
        first.stream()
            .map(number -> number * frequencies.getOrDefault(number, 0))
            .reduce(0, Integer::sum);
    printResult(Task.TWO, result);
  }
}
