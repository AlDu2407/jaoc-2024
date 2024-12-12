package org.aldu.jaoc.solutions;

import java.util.Arrays;
import java.util.List;
import org.aldu.jaoc.utils.FileUtils;

public class DaySeven extends AbstractDay {
  private enum Operation {
    PLUS,
    MUL
  }

  private static class Calculation {
    private final long result;
    private final List<Long> arguments;

    private Calculation(long result, List<Long> arguments) {
      this.result = result;
      this.arguments = arguments;
    }

    private long result() {
      return result;
    }

    private boolean isSolvable() {
      // Use permutation/binary number to represent all combinations and the first fitting is
      // correct.
      // e.g. 0 => '+', 1 => '*' therefore 010101 => `+ * + * + *`
      // arguments 7 => arg1 + arg2 * arg3 + arg4 * arg5 + arg6 * arg7
      var possibilities = Math.pow(2, arguments.size() - 1);
      for (var i = 0; i < possibilities; i++) {
        var intermediateResult = arguments.getFirst();
        for (var j = 0; j < arguments.size() - 1; j++) {
          var op = ((i >> j) & 1) == 0 ? Operation.PLUS : Operation.MUL;
          switch (op) {
            case PLUS -> {
              intermediateResult += arguments.get(j + 1);
            }
            case MUL -> {
              intermediateResult *= arguments.get(j + 1);
            }
          }
        }
        if (intermediateResult == result) {
          return true;
        }
      }

      return false;
    }

    public static Calculation parseInput(String line) {
      var parts = line.split(":");
      var result = Long.parseLong(parts[0]);
      var arguments =
          Arrays.stream(parts[1].split(" "))
              .filter(val -> !val.isBlank())
              .map(Long::parseLong)
              .toList();
      return new Calculation(result, arguments);
    }
  }

  @Override
  protected String getDay() {
    return "day07";
  }

  @Override
  protected void taskOne() {
    var lines = FileUtils.readInput(getInputFileName());
    var result =
        lines.stream()
            .map(Calculation::parseInput)
            .filter(Calculation::isSolvable)
            .map(Calculation::result)
            .reduce(0L, Long::sum);
    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {}
}
