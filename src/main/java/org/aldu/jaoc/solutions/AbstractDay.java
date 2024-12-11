package org.aldu.jaoc.solutions;

/** Base class providing minor utilities to simplify the implementation of the actual solutions. */
public abstract class AbstractDay {
  protected enum Task {
    ONE("task one"),
    TWO("task two");

    private final String value;

    Task(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  public void executeTasks() {
    System.out.println(
        "---------------------------------------------------------------------------------------------");
    System.out.printf("Executing task for %s:\n", getDay());
    taskOne();
    taskTwo();
  }

  protected abstract String getDay();

  protected abstract void taskOne();

  protected abstract void taskTwo();

  protected String getInputFileName() {
    return "inputs/%s/%s.in".formatted(getDay(), getDay());
  }

  protected String getExampleFileName() {
    return "inputs/%s/example.in".formatted(getDay());
  }

  protected <T> void printResult(Task task, T result) {
    System.out.printf("The result for %s is %s\n", task, result);
  }
}
