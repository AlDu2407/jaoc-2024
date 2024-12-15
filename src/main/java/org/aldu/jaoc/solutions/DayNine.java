package org.aldu.jaoc.solutions;

import org.aldu.jaoc.utils.FileUtils;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class DayNine extends AbstractDay {
  @Override
  protected String getDay() {
    return "day09";
  }

  @Override
  protected void taskOne() {
    var input = FileUtils.readInputAsLine(getInputFileName());
    var sum =
        IntStream.range(0, input.length())
            .map(input::charAt)
            .mapToObj(Character::toString)
            .map(Integer::parseInt)
            .reduce(0, Integer::sum);

    var memory = new Integer[sum];
    var fileId = 0;
    var idx = 0;
    for (var i = 0; i < input.length(); i++) {
      var isFileBlock = i % 2 == 0;
      var value = Integer.parseInt(Character.toString(input.charAt(i)));
      for (var j = 0; j < value; j++) {
        memory[idx + j] = isFileBlock ? fileId : -1;
      }

      fileId = isFileBlock ? fileId + 1 : fileId;
      idx += value;
    }

    var i = 0;
    var j = sum - 1;
    while (i < j) {
      while (memory[i] != -1) i++;
      while (memory[j] == -1) j--;
      memory[i] = memory[j];
      memory[j] = -1;
      i++;
      j--;
    }
    var result = 0L;
    var n = 0;
    while (memory[n] != -1) {
      result += n * (long) memory[n];
      n++;
    }
    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    printResult(Task.TWO, 0L);
  }
}
