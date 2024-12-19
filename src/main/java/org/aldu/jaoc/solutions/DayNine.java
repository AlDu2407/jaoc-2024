package org.aldu.jaoc.solutions;

import java.util.*;
import java.util.stream.IntStream;
import org.aldu.jaoc.utils.FileUtils;

public class DayNine extends AbstractDay {
  private record Space(int startIndex, int size) {}

  private record FileData(int fileId, Space space) {}

  @Override
  protected String getDay() {
    return "day09";
  }

  @Override
  protected void taskOne() {
    var input = FileUtils.readInputAsLine(getInputFileName());
    var sum = calculateSum(input);
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

    var result = calculateResult(memory);
    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    var input = FileUtils.readInputAsLine(getInputFileName());
    var sum = calculateSum(input);
    var memory = new Integer[sum];
    var freeSpaces = new ArrayList<Space>();
    var files = new ArrayList<FileData>();
    var fileId = 0;
    var idx = 0;
    for (var i = 0; i < input.length(); i++) {
      var isFileBlock = i % 2 == 0;
      var size = Integer.parseInt(Character.toString(input.charAt(i)));
      if (isFileBlock) {
        files.add(new FileData(fileId, new Space(idx, size)));
      } else {
        freeSpaces.add(new Space(idx, size));
      }
      fileId = isFileBlock ? fileId + 1 : fileId;
      idx += size;
    }

    for (var i = files.size() - 1; i >= 0; i--) {
      var file = files.get(i);
      for (var j = 0; j < freeSpaces.size(); j++) {
        var freeSpace = freeSpaces.get(j);
        if (freeSpace.startIndex() >= file.space().startIndex()) {
          break;
        }
        if (freeSpace.size() >= file.space().size()) {
          var _ = files.remove(i);
          files.add(
              i,
              new FileData(file.fileId(), new Space(freeSpace.startIndex(), file.space().size())));
          int spaceDifference = freeSpace.size() - file.space().size();
          if (spaceDifference > 0) {
            var _ = freeSpaces.remove(j);
            freeSpaces.add(
                j, new Space(freeSpace.startIndex() + file.space().size(), spaceDifference));
          } else {
            var _ = freeSpaces.remove(j);
          }
          break;
        }
      }
    }

    for (var file : files) {
      for (var i = 0; i < file.space().size; i++) {
        memory[file.space().startIndex() + i] = file.fileId();
      }
    }

    var result = calculateResult(memory);
    printResult(Task.TWO, result);
  }

  private int calculateSum(String input) {
    return IntStream.range(0, input.length())
        .map(input::charAt)
        .mapToObj(Character::toString)
        .map(Integer::parseInt)
        .reduce(0, Integer::sum);
  }

  private long calculateResult(Integer[] memory) {
    var result = 0L;
    for (var n = 0; n < memory.length; n++) {
      if (memory[n] != null && memory[n] != -1) {
        result += n * (long) memory[n];
      }
    }
    return result;
  }
}
