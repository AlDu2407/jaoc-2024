package org.aldu.jaoc.solutions;

import java.util.*;
import org.aldu.jaoc.utils.FileUtils;

public class DayFive extends AbstractDay {
  @Override
  protected String getDay() {
    return "day05";
  }

  @Override
  protected void taskOne() {
    var lines = FileUtils.readInput(getInputFileName());
    var orderingRules = new HashMap<Integer, Set<Integer>>();
    var printUpdates = new ArrayList<List<Integer>>();

    var parseOrdering = true;
    for (var line : lines) {
      if (line.isBlank()) {
        parseOrdering = false;
        continue;
      }
      if (parseOrdering) {
        var orderingValues = Arrays.stream(line.split("\\|")).map(Integer::parseInt).toList();
        var preceding = orderingValues.getFirst();
        var succeeding = orderingValues.getLast();
        var existing = orderingRules.getOrDefault(preceding, new HashSet<>());
        existing.add(succeeding);
        orderingRules.put(preceding, existing);
      } else {
        var updateValues = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
        printUpdates.add(updateValues);
      }
    }

    var result = 0;
    for (var update : printUpdates) {
      if (isUpdateCorrect(update, orderingRules)) {
        result += update.get(update.size() / 2);
      }
    }
    printResult(Task.ONE, result);
  }

  @Override
  protected void taskTwo() {
    var lines = FileUtils.readInput(getInputFileName());
    var orderingRules = new HashMap<Integer, Set<Integer>>();
    var printUpdates = new ArrayList<List<Integer>>();

    var parseOrdering = true;
    for (var line : lines) {
      if (line.isBlank()) {
        parseOrdering = false;
        continue;
      }
      if (parseOrdering) {
        var orderingValues = Arrays.stream(line.split("\\|")).map(Integer::parseInt).toList();
        var preceding = orderingValues.getFirst();
        var succeeding = orderingValues.getLast();
        var existing = orderingRules.getOrDefault(preceding, new HashSet<>());
        existing.add(succeeding);
        orderingRules.put(preceding, existing);
      } else {
        var updateValues = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
        printUpdates.add(updateValues);
      }
    }

    var result = 0;
    for (var update : printUpdates) {
      if (isUpdateCorrect(update, orderingRules)) {
        continue;
      }
      var correctUpdate = fixUpdateOrder(update, orderingRules);
      result += correctUpdate.get(correctUpdate.size() / 2);
    }
    printResult(Task.TWO, result);
  }

  private boolean isUpdateCorrect(List<Integer> update, Map<Integer, Set<Integer>> orderingRules) {
    var alreadySeen = new HashSet<Integer>(update.size());
    alreadySeen.add(update.getFirst());
    for (var i = 1; i < update.size(); i++) {
      var curr = update.get(i);
      var successors = new HashSet<>(orderingRules.getOrDefault(curr, new HashSet<>()));
      successors.retainAll(alreadySeen);
      if (!successors.isEmpty()) {
        return false;
      }
      alreadySeen.add(curr);
    }
    return true;
  }

  private List<Integer> fixUpdateOrder(
      List<Integer> update, Map<Integer, Set<Integer>> orderingRules) {
    var fixedUpdate = new ArrayList<Integer>();
    fixedUpdate.add(update.getFirst());
    for (var i = 1; i < update.size(); i++) {
      var curr = update.get(i);
      var successors = new HashSet<>(orderingRules.getOrDefault(curr, new HashSet<>()));
      successors.retainAll(fixedUpdate);
      if (!successors.isEmpty()) {
        fixedUpdate.add(i - successors.size(), curr);
      } else {
        fixedUpdate.add(curr);
      }
    }
    return fixedUpdate;
  }
}
