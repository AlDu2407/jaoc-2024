package org.aldu.jaoc.solutions;

import org.aldu.jaoc.utils.FileUtils;

public class DayThree extends AbstractDay {

  @Override
  protected String getDay() {
    return "day03";
  }

  @Override
  protected void taskOne() {
    var line = FileUtils.readInputAsLine(getInputFileName());

    var result = 0;
    for (var i = 0; i < line.length(); i++) {
      var substring = line.substring(i);
      if (substring.startsWith("mul(")) {
        var closeParen = substring.indexOf(")");
        var inputs = substring.substring(4, closeParen).split(",");
        if (inputs.length == 2) {
          try {
            var lhs = Integer.parseInt(inputs[0]);
            var rhs = Integer.parseInt(inputs[1]);
            result += lhs * rhs;
          } catch (NumberFormatException _) {
          }
        }
      }
    }

    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    var line = FileUtils.readInputAsLine(getInputFileName());

    var result = 0;
    var enabled = true;
    for (var i = 0; i < line.length(); i++) {
      var substring = line.substring(i);
      if (substring.startsWith("don't()")) {
        enabled = false;
      } else if (substring.startsWith("do()")) {
        enabled = true;
      } else if (enabled && substring.startsWith("mul(")) {
        var closeParen = substring.indexOf(")");
        var inputs = substring.substring(4, closeParen).split(",");
        if (inputs.length == 2) {
          try {
            var lhs = Integer.parseInt(inputs[0]);
            var rhs = Integer.parseInt(inputs[1]);
            result += lhs * rhs;
          } catch (NumberFormatException _) {
          }
        }
      }
    }

    printResult(Task.TWO, result);
  }
}
