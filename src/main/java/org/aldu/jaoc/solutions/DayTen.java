package org.aldu.jaoc.solutions;

import org.aldu.jaoc.utils.FileUtils;

public class DayTen extends AbstractDay {
  @Override
  protected String getDay() {
    return "day10";
  }

  @Override
  protected void taskOne() {
    var grid = FileUtils.readInput(getExampleFileName());
  }

  @Override
  protected void taskTwo() {}
}
