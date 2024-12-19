package org.aldu.jaoc.solutions;

import org.aldu.jaoc.utils.FileUtils;

import java.util.*;
import java.util.stream.IntStream;

import static org.aldu.jaoc.solutions.AbstractDay.Task.ONE;
import static org.aldu.jaoc.solutions.AbstractDay.Task.TWO;

public class DayEleven extends AbstractDay {
  @Override
  protected String getDay() {
    return "day11";
  }

  @Override
  protected void taskOne() {
    var line = FileUtils.readInputAsLine(getInputFileName());
    var cache = new HashMap<Integer, Map<Long, Long>>();
    IntStream.range(0, 25).forEach(i -> cache.put(i + 1, new HashMap<>()));
    var result =
        Arrays.stream(line.split(" "))
            .map(Long::parseLong)
            .map(stone -> countStones(stone, cache, 25))
            .reduce(0L, Long::sum);
    printResult(ONE, result);
  }

  @Override
  protected void taskTwo() {
    var line = FileUtils.readInputAsLine(getInputFileName());
    var cache = new HashMap<Integer, Map<Long, Long>>();
    IntStream.range(0, 75).forEach(i -> cache.put(i + 1, new HashMap<>()));
    var result =
        Arrays.stream(line.split(" "))
            .map(Long::parseLong)
            .map(stone -> countStones(stone, cache, 75))
            .reduce(0L, Long::sum);

    printResult(TWO, result);
  }

  private long countStones(long stone, Map<Integer, Map<Long, Long>> cache, int depth) {
    if (depth == 0) return 1;
    var iterationCache = cache.get(depth);
    var count = iterationCache.get(stone);
    if (count != null) {
      return count;
    } else {
      var stringRepresentation = Long.toString(stone);
      if (stone == 0L) {
        iterationCache.put(stone, countStones(1L, cache, depth - 1));
        return iterationCache.get(stone);
      } else if (stringRepresentation.length() % 2 == 0) {
        var middle = stringRepresentation.length() / 2;
        var first = stringRepresentation.substring(0, middle);
        var second = stringRepresentation.substring(middle);
        iterationCache.put(
            stone,
            countStones(Long.parseLong(first), cache, depth - 1)
                + countStones(Long.parseLong(second), cache, depth - 1));
      } else {
        iterationCache.put(stone, countStones(stone * 2024, cache, depth - 1));
      }
    }
    return iterationCache.get(stone);
  }
}
