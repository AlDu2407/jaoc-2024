package org.aldu.jaoc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class FileUtils {
  public static List<String> readInput(String fileName) {
    var classLoader = FileUtils.class.getClassLoader();
    try (var inputStream = classLoader.getResourceAsStream(fileName)) {
      if (inputStream == null) {
        throw new IllegalArgumentException("File not found: %s".formatted(fileName));
      }
      try (var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
        return reader.lines().toList();
      }
    } catch (IOException | IllegalArgumentException ex) {
      System.err.println("Failed to load data!");
    }

    return Collections.emptyList();
  }
}
