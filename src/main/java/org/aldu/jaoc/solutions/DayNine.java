package org.aldu.jaoc.solutions;

import org.aldu.jaoc.utils.FileUtils;

public class DayNine extends AbstractDay {
  @Override
  protected String getDay() {
    return "day09";
  }

  @Override
  protected void taskOne() {
    var input = FileUtils.readInputAsLine(getExampleFileName());
  }

  @Override
  protected void taskTwo() {}
}
